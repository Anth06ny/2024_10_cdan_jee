package org.example.tchat.restcontroller

import org.example.tchat.model.MessageBean
import org.example.tchat.model.MessageService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tchat")
class TchatRestController(val messageService: MessageService) {

    //val list = ArrayList<MessageBean>()

    //http://localhost:8080/tchat/saveMessage
//Json Attendu : {"pseudo": "toto","message": "coucou"}
    @PostMapping("/saveMessage")
    fun saveMessage (@RequestBody messageBean: MessageBean) {
        println("/saveMessage  : $messageBean")


        messageService.addMessage(messageBean)
    }

    //http://localhost:8080/tchat/allMessages
    @GetMapping("/allMessages")
    fun allMessages(): List<MessageBean> {
        println("/allMessages")

        return messageService.last10messages()
    }
}