package org.example.tchat.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Entity
@Table(name = "message")
data class MessageBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var pseudo : String = "",
    var message: String = "")

@Repository                       //<Bean, Typage Id>
interface MessageRepository : JpaRepository<MessageBean, Long> {
    fun findTop10ByOrderByIdDesc() : List<MessageBean>
}

@Service
class MessageService(val messageRepository:MessageRepository) {

    fun addMessage(messageBean: MessageBean) {
        if(messageBean.pseudo.isEmpty()) {
            throw Exception("pseudo is empty")
        }

        messageRepository.save(messageBean)
    }

    fun findById(id:Long) = messageRepository.findByIdOrNull(id)

    fun allMessages() = messageRepository.findAll()

    fun last10messages() = messageRepository.findTop10ByOrderByIdDesc()
}
