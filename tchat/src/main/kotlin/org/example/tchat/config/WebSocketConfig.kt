package org.example.tchat.config

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

const val CHANNEL_NAME: String = "/ws/myChannel"

@Configuration //Création d'un fichier de configuration pour les WebSocket
@EnableWebSocketMessageBroker
open class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    // Le point de terminaison pour les connexions WebSocket (handshake)
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws/chat-app").withSockJS()
    }

    // Configurer les channels (broker) de messages que les clients viendront écouter
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        //Il peut y avoir plusieurs channels
        registry.enableSimpleBroker(CHANNEL_NAME)
        //Définir les préfixes des destinations
        registry.setApplicationDestinationPrefixes("/ws")
    }
}