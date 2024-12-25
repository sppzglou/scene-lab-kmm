package gr.sppzglou.scenelab.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//Data Transfer Object
@Serializable
data class MovieDto(
    @SerialName("adult")
    val adult: Boolean = false, // Default value
    @SerialName("backdrop_path")
    val backdropPath: String? = null, // Optional
    @SerialName("belongs_to_collection")
    val belongsToCollection: CollectionDto? = null, // Optional
    @SerialName("budget")
    val budget: Long = 0, // Default value
    @SerialName("genres")
    val genres: List<GenreDto> = emptyList(), // Empty list
    @SerialName("homepage")
    val homepage: String? = null, // Optional
    @SerialName("id")
    val id: Int, // Υποχρεωτικό, δεν γίνεται nullable
    @SerialName("imdb_id")
    val imdbId: String? = null, // Optional
    @SerialName("origin_country")
    val originCountry: List<String>? = null, // Optional
    @SerialName("original_language")
    val originalLanguage: String = "Unknown", // Default value
    @SerialName("original_title")
    val originalTitle: String = "Untitled", // Default value
    @SerialName("overview")
    val overview: String = "No overview available.", // Default value
    @SerialName("popularity")
    val popularity: Double = 0.0, // Default value
    @SerialName("poster_path")
    val posterPath: String? = null, // Optional
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDto> = emptyList(), // Empty list
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDto> = emptyList(), // Empty list
    @SerialName("release_date")
    val releaseDate: String? = null, // Optional
    @SerialName("revenue")
    val revenue: Long = 0, // Default value
    @SerialName("runtime")
    val runtime: Int? = null, // Optional
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto> = emptyList(), // Empty list
    @SerialName("status")
    val status: String = "Unknown", // Default value
    @SerialName("tagline")
    val tagline: String? = null, // Optional
    @SerialName("title")
    val title: String = "Untitled", // Default value
    @SerialName("video")
    val video: Boolean = false, // Default value
    @SerialName("vote_average")
    val voteAverage: Double = 0.0, // Default value
    @SerialName("vote_count")
    val voteCount: Int = 0 // Default value
) {
    fun getCollection(): Pair<Int, String>? {
        return this.belongsToCollection?.let {
            it.id to it.name
        }
    }

    fun getGenreList(): List<String> {
        return this.genres.map { it.name }
    }

    fun getProductionCompanyList(): List<String> {
        return this.productionCompanies.map { it.name }
    }

    fun geProductionCountryList(): List<String> {
        return this.productionCountries.map { it.name }
    }

    fun MovieDto.getSpokenLanguageList(): List<String> {
        return this.spokenLanguages.map { it.englishName }
    }
}