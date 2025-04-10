package org.example.moviesservice

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MoviesServiceApplication(val movieRepository: MovieRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (movieRepository.count() == 0L) {
            val movies = listOf(
                Movie(title = "Inception", length = 148),
                Movie(title = "Interstellar", length = 169),
                Movie(title = "The Matrix", length = 136),
                Movie(title = "Pulp Fiction", length = 154),
                Movie(title = "The Godfather", length = 175)
            )
            movieRepository.saveAll(movies)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<MoviesServiceApplication>(*args)
}
