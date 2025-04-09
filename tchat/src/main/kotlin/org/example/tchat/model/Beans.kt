package org.example.tchat.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class StudentBean(var name: String = "", var note: Int = 0)


data class UserBean(var id:Long? = null, var login : String = "", var password: String = "")