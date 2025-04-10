package org.example.progreactive

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux


@Table("tv_show")
data class TVShow(
    @Id // Indique la clé primaire
    var id: Long? = null,
    var name: String? = null,
    var genre: String? = null,
    var seasons: Int? = null
)

interface TVShowRepository : ReactiveCrudRepository<TVShow, Long> {
    // Méthode personnalisée pour rechercher par genre
    fun findByGenre(genre: String?): Flux<TVShow>
}

//KOTLIN
@Service
class TVShowService(val tvShowRepository: TVShowRepository) {
    fun getAllTVShows(): Flux<TVShow> = tvShowRepository.findAll()
}