package gr.sppzglou.scenelab.data.remote

import gr.sppzglou.composebooster.NetworkUtils
import gr.sppzglou.composebooster.NoInternetException
import gr.sppzglou.scenelab.data.remote.dto.CreditsResponse
import gr.sppzglou.scenelab.data.remote.dto.MovieDto
import gr.sppzglou.scenelab.data.remote.dto.MoviesResponse
import gr.sppzglou.scenelab.data.remote.dto.ReviewsResponse
import gr.sppzglou.scenelab.modules.Client
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

class Api {
    private val client = Client().create(true)

    suspend fun fetchMovies(page: Int = 1) = checkConnection {
        client.get("movie/popular") {
            parameter("page", page)
        }.handleResponse<MoviesResponse>()
    }

    suspend fun fetchMovie(id: Int) = checkConnection {
        client.get("movie/$id")
            .handleResponse<MovieDto>()
    }

    suspend fun fetchSimilarMovies(id: Int) = checkConnection {
        client.get("movie/$id/similar")
            .handleResponse<MoviesResponse>()
    }

    suspend fun fetchCredits(id: Int) = checkConnection {
        client.get("movie/$id") {
            parameter("append_to_response", "credits")
        }.handleResponse<CreditsResponse>()
    }

    suspend fun fetchReviews(id: Int) = checkConnection {
        client.get("movie/$id/reviews")
            .handleResponse<ReviewsResponse>()
    }


    private suspend inline fun <reified T> HttpResponse.handleResponse(): T {
        if (status.value != 200)
            throw Exception("${status.value} - ${status.description}")
        return body<T>()
    }

    private suspend fun <T> checkConnection(block: suspend () -> T): T {
        if (!NetworkUtils.isConnected()) throw NoInternetException()

        return block()
    }
}