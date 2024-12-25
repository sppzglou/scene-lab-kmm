package gr.sppzglou.scenelab.data.local

import gr.sppzglou.scenelab.data.local.entities.FavoriteEntity
import gr.sppzglou.scenelab.data.local.entities.MovieEntity


class LocalDataSource(
    private val db: AppDatabase
) {
    suspend fun getMovie(id: Int) = db.moviesDao().get(id)

    suspend fun insertMovie(item: MovieEntity) = db.moviesDao().insert(item)

    suspend fun addFavorite(id: Int) = db.favsDao().insert(FavoriteEntity(id))

    suspend fun removeFavorite(id: Int) = db.favsDao().delete(FavoriteEntity(id))

    fun getFavoriteFlow(id: Int) = db.favsDao().getFlow(id)

    fun getFavoritesFlow() = db.favsDao().getListFlow()

}