package gr.sppzglou.scenelab.data.remote


class RemoteDataSource(
    private val api: Api
) {
    suspend fun fetchMovies(page: Int) = api.fetchMovies(page)

    suspend fun fetchMovie(id: Int) = api.fetchMovie(id)

    suspend fun fetchSimilarMovies(id: Int) = api.fetchSimilarMovies(id)

    suspend fun fetchCredits(id: Int) = api.fetchCredits(id)

    suspend fun fetchReviews(id: Int) = api.fetchReviews(id)
}