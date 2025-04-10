package org.example.progreactive

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

//KOTLIN
@RestController
@RequestMapping("/api/tvshows")
class TVShowController(var tvShowService: TVShowService) {

    //http://localhost:8080/api/tvshows
    @GetMapping
    fun getAllTVShows() : Flux<TVShow> = tvShowService.getAllTVShows()
}