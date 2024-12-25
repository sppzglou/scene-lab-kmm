package gr.sppzglou.scenelab.data.local

import androidx.room.TypeConverter
import gr.sppzglou.composebooster.isNotNullOrEmptyOrBlank


class StringListConverter {
    @TypeConverter
    fun from(list: List<String>): String {
        return list.joinToString("|")
    }

    @TypeConverter
    fun to(data: String): List<String> {
        return if (!data.isNotNullOrEmptyOrBlank) emptyList()
        else data.split("|")
    }
}

