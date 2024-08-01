package py.edu.ucom.is2.proyectocamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
public class transferenciasConsumer extends RouteBuilder {
    private static final String[] BANCOS = {"ITAU", "ATLAS", "FAMILIAR"};
    private static final double MONTO_MAXIMO = 20000000;

    @Override
    public void configure() throws Exception {
        from("timer:transferenciaTimer?period=1000")
            .process(exchange -> {
                String cuenta = String.valueOf((int)(Math.random() * 4000) + 1000);
                double monto = (int)(Math.random() * 40000000) + 1000; // Ajustado para que genere montos mayores también
                String bancoOrigen = BANCOS[(int)(Math.random() * BANCOS.length)];
                String bancoDestino = BANCOS[(int)(Math.random() * BANCOS.length)];
                String fecha = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                String idTransaccion = UUID.randomUUID().toString();

                String json = String.format("{\"cuenta\": \"%s\", \"monto\": %s, \"banco_origen\": \"%s\", \"banco_destino\": \"%s\", \"fecha\": \"%s\", \"id_transaccion\": \"%s\"}",
                    cuenta, monto, bancoOrigen, bancoDestino, fecha, idTransaccion);
                exchange.getIn().setBody(json);
            })
            .choice()
                .when(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    int montoIndexStart = body.indexOf("\"monto\":") + 8;
                    int montoIndexEnd = body.indexOf(",", montoIndexStart);
                    String montoStr = body.substring(montoIndexStart, montoIndexEnd).trim();
                    double monto = Double.parseDouble(montoStr);
                    return monto >= MONTO_MAXIMO;
                })
                    .process(exchange -> {
                        String body = exchange.getIn().getBody(String.class);
                        String idTransaccion = body.substring(body.indexOf("\"id_transaccion\":") + 17, body.indexOf("\"", body.indexOf("\"id_transaccion\":") + 17));
                        String rechazo = String.format("{\"id_transaccion\": \"%s\", \"mensaje\": \"El monto supera el máximo permitido\"}", idTransaccion);
                        exchange.getIn().setBody(rechazo);
                    })
                    .log("Transacción rechazada: ${body}")
                .otherwise()
                    .log("Transacción encolada: ${body}")
                    .to("activemq:queue:centurion-ITAU-IN")
            .end();
    }
}