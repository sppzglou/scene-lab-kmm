package gr.sppzglou.scenelab.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gr.sppzglou.scenelab.data.local.entities.MovieEntity

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: MovieEntity)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun get(id: Int): MovieEntity?

}