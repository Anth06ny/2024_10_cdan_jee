package org.example.tchat.restcontroller

import org.example.tchat.model.StudentBean
import org.example.tchat.model.TeacherBean
import org.example.tchat.model.TeacherService
import org.springframework.web.bind.annotation.*

@RestController
class MyRestController(val teacherService: TeacherService) {


    //http://localhost:8080/testPublic
    @GetMapping("/testPublic")
    fun testPublic(): String {
        println("/testPublic")
        return "Hello public"
    }

    //http://localhost:8080/testPrivate
    @GetMapping("/testPrivate")
    fun testPrivate(): String {
        println("/testPrivate")
        return "Hello private"
    }

    //http://localhost:8080/testPrivateAdmin
    @GetMapping("/testPrivateAdmin")
    fun testPrivateAdmin(): String {
        println("/testPrivateAdmin")
        return "Hello private admin"
    }

    //http://localhost:8080/addTeacher?name=tata&code=8
    @GetMapping("/addTeacher")
    fun addTeacher(name:String , code:Int) : List<TeacherBean> {
        println("/addTeacher name=$name code=$code")

        teacherService.createTeacher(name, code)

        return  teacherService.getAll()
    }

    //http://localhost:8080/increment
//Json Attendu : {"name": "toto","note": 12}
    @PostMapping("/increment")
    fun increment (@RequestBody student: StudentBean): StudentBean {
        println("/increment  : $student")

        student.note++

        return student
    }


    //http://localhost:8080/receiveStudent
//Json Attendu : {"name": "toto","note": 12}
    @PostMapping("/receiveStudent")
    fun receiveStudent(@RequestBody student: StudentBean) {
        println("/receiveStudent : $student")

        //traitement, mettre en base…
        //Retourner d'autres données
    }

    //http://localhost:8080/max?p1=5&p2=12
    @GetMapping("/max")
    fun max(p1:Int?, p2:Int?): Int? {
        //name contiendra bob
        //note contiendra 12
        println("/max : p1=$p1 p2=$p2")

        if(p1 == null){
            return p2
        }
        else if(p2 == null){
            return p1
        }

        return Math.max(p1,p2)
    }

    //http://localhost:8080/max2?p1=5&p2=toto
    @GetMapping("/max2")
    fun max2(p1:String?, p2:String?): Int? {
        //name contiendra bob
        //note contiendra 12
        println("/max : p1=$p1 p2=$p2")

        var p1Int = p1?.toIntOrNull()
        var p2Int = p2?.toIntOrNull()

        if(p1Int == null){
            return p2Int
        }
        else if(p2Int == null){
            return p1Int
        }

        return Math.max(p1Int,p2Int)
    }

    //http://localhost:8080/getStudent
    @GetMapping("/getStudent")
    fun getStudent(): StudentBean {
        println("/getStudent")
        var student =  StudentBean("toto", 12)

        return student
    }

    //http://localhost:8080/createStudent?name=bob&notation=12
    @GetMapping("/createStudent")
    fun createStudent(name: String = "",
                      @RequestParam(value = "notation", defaultValue = "0") note: Int): StudentBean {
        //name contiendra bob
        //note contiendra 12
        println("/createStudent : name=$name note=$note")

        return StudentBean(name, note)
    }

    //http://localhost:8080/test
    @GetMapping("/test")
    fun hello(): String {
       println("hello")

       return "HelloWorld"
    }
}