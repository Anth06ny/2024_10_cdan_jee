package org.example.tchat.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.assertEquals


@ExtendWith(MockitoExtension::class)
class MessageServiceTest {

    val messageRepository: MessageRepository = Mockito.mock(MessageRepository::class.java)

    @InjectMocks
    lateinit var messageService: MessageService

    @Test
    fun testAddMessage() {
        val message = MessageBean(null, "toto14789632", "coucou")
        messageService.addMessage(message)

        Mockito.verify(messageRepository, times(1)).save(message);
    }


    @Test
    fun testGet10Last() {

        val list = ArrayList<MessageBean>()

        repeat(15) {
            val message = MessageBean(null, "bob$it", "coucou$it")
            messageService.addMessage(message)
            list.add(message)
        }
        list.reverse()


        Mockito.`when`(messageRepository.findTop10ByOrderByIdDesc())
            .thenReturn(list.take(10))


        val listBDD = messageService.last10messages()
        assertEquals(10, listBDD.size, "List size should be 10")

        assertEquals(list[0].message, listBDD[0].message, "Message doivent être identique")
    }

}