package gr.sppzglou.scenelab.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import gr.sppzglou.composebooster.room.getDatabase
import gr.sppzglou.scenelab.data.local.entities.FavoriteEntity
import gr.sppzglou.scenelab.data.local.entities.MovieEntity

expect object Constructor : RoomDatabaseConstructor<AppDatabase>

fun appDatabase() = getDatabase<AppDatabase>()


@Database(
    entities = [MovieEntity::class, FavoriteEntity::class],
    version = 1
)
@ConstructedBy(Constructor::class)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun favsDao(): FavoritesDao
}

