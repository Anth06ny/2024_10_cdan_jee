package org.example.tchat.restcontroller

import org.example.tchat.model.UserBean
import org.example.tchat.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserRestController {

    @PostMapping
    fun post (@RequestBody userBean: UserBean): ResponseEntity<UserBean> {
        println("/users  : $userBean")

        userBean.id = null
        UserService.save(userBean)

        return ResponseEntity(userBean, HttpStatus.CREATED)
    }

    @GetMapping
    fun get(): ArrayList<UserBean> {
       return UserService.load()
    }

    @GetMapping ("/{id}")
    fun get(@PathVariable id:Long): ResponseEntity<UserBean> {
        val user = UserService.findById(id)
        if(user != null){
            return ResponseEntity(user, HttpStatus.OK)
        }
        else {
            return ResponseEntity.notFound().build()
        }
    }

    @PutMapping    ("/{id}")
    fun put (@PathVariable id:Long, @RequestBody userBean: UserBean): ResponseEntity<UserBean> {

        val user = UserService.findById(id)
        return if(user != null){
            user.id = id
            ResponseEntity(UserService.save(userBean), HttpStatus.OK)
        }
        else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping    ("/{id}")
    fun delete (@PathVariable id:Long): ResponseEntity<UserBean> {

        val user = UserService.findById(id)
        return if(user != null){
            UserService.deleteById(id)
            ResponseEntity(HttpStatus.NO_CONTENT)
        }
        else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}