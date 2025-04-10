package org.example.tchat.controller

import org.example.tchat.config.CHANNEL_NAME
import org.example.tchat.model.MessageBean
import org.example.tchat.model.MessageService
import org.springframework.context.event.EventListener
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.socket.messaging.SessionSubscribeEvent

@Controller
@RequestMapping("/ws") // Chemin de base pour toutes les méthodes de ce contrôleur
class WebSocketController(private val messagingTemplate: SimpMessagingTemplate, val messageService: MessageService) {


    @MessageMapping("/chat")
    fun receiveMessage(message: MessageBean) {
        println("/ws/chat $message")

        messageService.addMessage(message)

        // Envoyer la liste des messages sur le channel
        //Si la variable est dans le même package il faut enlever WebSocketConfig.
        messagingTemplate.convertAndSend(CHANNEL_NAME, messageService.last10messages().reversed())
    }

    //A mettre dans le controller
    @EventListener
    fun handleWebSocketSubscribeListener(event: SessionSubscribeEvent) {
        val headerAccessor = StompHeaderAccessor.wrap(event.message)
        if (CHANNEL_NAME == headerAccessor.destination) {
            messagingTemplate.convertAndSend(CHANNEL_NAME, messageService.last10messages().reversed())
        }
    }
}