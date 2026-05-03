package com.example.chatreactivo.infrastructure.adapter;

import com.example.chatreactivo.domain.model.Mensaje;
import com.example.chatreactivo.domain.ports.out.MensajeQueuePort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ActiveMqMensajePublisher implements MensajeQueuePort {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;
    private final String queueName;

    public ActiveMqMensajePublisher(
            JmsTemplate jmsTemplate,
            ObjectMapper objectMapper,
            @Value("${app.messaging.queue-name}") String queueName
    ) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
        this.queueName = queueName;
    }

    @Override
    public Mono<Void> publicar(Mensaje mensaje) {
        return Mono.fromRunnable(() -> {
            try {
                String payload = objectMapper.writeValueAsString(mensaje);

                System.out.println("====================================");
                System.out.println("PUBLICANDO EN ACTIVEMQ");
                System.out.println("Cola: " + queueName);
                System.out.println("Mensaje ID: " + mensaje.getId());
                System.out.println("Contenido: " + mensaje.getContenido());
                System.out.println("Remitente: " + mensaje.getRemitenteId());
                System.out.println("Receptor: " + mensaje.getReceptorId());
                System.out.println("====================================");

                jmsTemplate.convertAndSend(queueName, payload);
            } catch (Exception e) {
                throw new RuntimeException("Error publicando mensaje en ActiveMQ", e);
            }
        });
    }
}