package org.example.tchat

import org.example.tchat.model.MessageBean
import org.example.tchat.model.MessageRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TchatApplication (val messageRepository: MessageRepository) : CommandLineRunner {


    override fun run(vararg args: String?) {

        if(messageRepository.count()== 0L) {

            repeat(15){
                messageRepository.save(MessageBean(message =  "Hello$it", pseudo = "Toto$it"))
            }

        }

    }

}

fun main(args: Array<String>) {
    runApplication<TchatApplication>(*args)
    //cdan2410
}
