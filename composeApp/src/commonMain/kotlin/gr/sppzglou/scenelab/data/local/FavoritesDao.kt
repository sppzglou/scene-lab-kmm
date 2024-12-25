package gr.sppzglou.scenelab.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gr.sppzglou.scenelab.data.local.entities.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: FavoriteEntity)

    @Delete
    suspend fun delete(item: FavoriteEntity)

    @Query("SELECT * FROM favorites WHERE id = :id")
    fun getFlow(id: Int): Flow<FavoriteEntity?>

    @Query("SELECT * FROM favorites")
    fun getListFlow(): Flow<List<FavoriteEntity>>
}