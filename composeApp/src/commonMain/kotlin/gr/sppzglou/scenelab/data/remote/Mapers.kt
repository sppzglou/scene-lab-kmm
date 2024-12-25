package gr.sppzglou.scenelab.data.remote

import gr.sppzglou.composebooster.fromJson
import gr.sppzglou.composebooster.toJson
import gr.sppzglou.scenelab.ImageUrl
import gr.sppzglou.scenelab.data.local.entities.MovieEntity
import gr.sppzglou.scenelab.data.remote.dto.CastDto
import gr.sppzglou.scenelab.data.remote.dto.CreditsDto
import gr.sppzglou.scenelab.data.remote.dto.CrewDto
import gr.sppzglou.scenelab.data.remote.dto.MovieDto
import gr.sppzglou.scenelab.data.remote.dto.ReviewDto
import gr.sppzglou.scenelab.domain.models.CastDomainModel
import gr.sppzglou.scenelab.domain.models.CreditsDomainModel
import gr.sppzglou.scenelab.domain.models.CrewDomainModel
import gr.sppzglou.scenelab.domain.models.MovieDomainModel
import gr.sppzglou.scenelab.domain.models.ReviewDomainModel

fun MovieDto.toDomainModel(
    credits: CreditsDomainModel? = null,
    page: Int = 1
): MovieDomainModel {

    return MovieDomainModel(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath.getImage(),
        genres = getGenreList(),
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath.getImage(),
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
        crew = credits?.crew ?: listOf(),
        cast = credits?.cast ?: listOf(),
        page = page
    )
}

fun String?.getImage() = this?.let { "$ImageUrl$it" }

fun CastDto.toDomainModel() = CastDomainModel(
    name = this.name,
    photo = this.photo.getImage(),
    character = this.character
)

fun CrewDto.toDomainModel() = CrewDomainModel(
    name = this.name,
    photo = this.photo.getImage(),
    job = this.job
)

fun ReviewDto.toDomainModel() = ReviewDomainModel(
    author = this.author,
    content = this.content,
    id = this.id,
    date = this.date,
    url = this.url
)

fun CreditsDto.toDomainModel() = CreditsDomainModel(
    cast = this.cast.map { it.toDomainModel() },
    crew = this.crew.map { it.toDomainModel() }
)

fun MovieEntity.toDomainModel(): MovieDomainModel {
    return MovieDomainModel(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genres = this.genres,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        voteAverage = this.voteAverage,
        crew = fromJson(this.crew),
        cast = fromJson(this.cast),
        page = 1
    )
}

fun MovieDomainModel.toMovieEntity() = MovieEntity(
    id = this.id,
    adult = this.adult,
    backdropPath = this.backdropPath,
    genres = this.genres,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    voteAverage = this.voteAverage,
    crew = toJson(this.crew),
    cast = toJson(this.cast)
)