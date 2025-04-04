package org.example.tchat.model

data class StudentBean(var name: String = "", var note: Int = 0)

data class MessageBean(var pseudo : String = "", var message: String = "")
data class UserBean(var id:Long? = null, var login : String = "", var password: String = "")