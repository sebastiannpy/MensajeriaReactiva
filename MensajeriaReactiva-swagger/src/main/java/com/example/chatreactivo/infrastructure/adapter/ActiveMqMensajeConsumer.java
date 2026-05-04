package com.example.chatreactivo.infrastructure.adapter;

import com.example.chatreactivo.domain.model.Mensaje;
import com.example.chatreactivo.infrastructure.realtime.MensajeRealtimeStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqMensajeConsumer {

    private final ObjectMapper objectMapper;
    private final MensajeRealtimeStream mensajeRealtimeStream;

    public ActiveMqMensajeConsumer(
            ObjectMapper objectMapper,
            MensajeRealtimeStream mensajeRealtimeStream
    ) {
        this.objectMapper = objectMapper;
        this.mensajeRealtimeStream = mensajeRealtimeStream;
    }

    @JmsListener(destination = "${app.messaging.queue-name}")
    public void consumir(String payload) {
        try {
            Mensaje mensaje = objectMapper.readValue(payload, Mensaje.class);

            System.out.println("====================================");
            System.out.println("CONSUMIDO DESDE ACTIVEMQ");
            System.out.println("Mensaje ID: " + mensaje.getId());
            System.out.println("Contenido: " + mensaje.getContenido());
            System.out.println("Remitente: " + mensaje.getRemitenteId());
            System.out.println("Receptor: " + mensaje.getReceptorId());
            System.out.println("====================================");

            mensajeRealtimeStream.emitir(mensaje);
        } catch (Exception e) {
            throw new RuntimeException("Error consumiendo mensaje desde ActiveMQ", e);
        }
    }
}