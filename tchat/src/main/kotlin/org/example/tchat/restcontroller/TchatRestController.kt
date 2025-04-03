package org.example.tchat.restcontroller

import org.example.tchat.model.MessageBean
import org.example.tchat.model.StudentBean
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tchat")
class TchatRestController {

    val list = ArrayList<MessageBean>()

    //http://localhost:8080/tchat/saveMessage
//Json Attendu : {"pseudo": "toto","message": "coucou"}
    @PostMapping("/saveMessage")
    fun saveMessage (@RequestBody messageBean: MessageBean) {
        println("/saveMessage  : $messageBean")
        list.add(messageBean)
    }

    //http://localhost:8080/tchat/allMessages
    @GetMapping("/allMessages")
    fun allMessages(): ArrayList<MessageBean> {
        println("/allMessages")

        return list
    }
}