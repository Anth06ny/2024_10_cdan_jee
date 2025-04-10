package org.example.moviesservice

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Entity
data class Movie(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title : String = "",
    var length: Int = 0)

@Repository                       //<Bean, Typage Id>
interface MovieRepository : JpaRepository<Movie, Long> {
}

