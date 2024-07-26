package py.edu.ucom.is2.proyectocamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class Transferencias extends RouteBuilder {
    private static final String[] BANCOS = {"ITAU", "ATLAS", "FAMILIAR"};

    @Override
    public void configure() throws Exception {
        from("timer:transferenciaTimer?period=1000")
            .process(exchange -> {
                String cuenta = String.valueOf((int)(Math.random() * 4000) + 1000);
                double monto = (int)(Math.random() * 4000) + 1000;
                String bancoOrigen = BANCOS[(int)(Math.random() * BANCOS.length)];
                String bancoDestino = BANCOS[(int)(Math.random() * BANCOS.length)];

                String json = String.format("{\"cuenta\": \"%s\", \"monto\": %s, \"banco_origen\": \"%s\", \"banco_destino\": \"%s\"}",
                    cuenta, monto, bancoOrigen, bancoDestino);
                exchange.getIn().setBody(json);
            })
            .log("JSON generado: ${body}")
            .to("activemq:queue:centurion-ITAU-IN");
    }
}