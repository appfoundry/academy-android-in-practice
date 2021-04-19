package be.appfoundry.aipdemo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Card(

    @PrimaryKey
    @ColumnInfo(name = "multiverse_id")
    @field:Json(name = "multiverseid")
    val multiverseId: Int,

    val name: String,

    val type: String,

    @ColumnInfo(name="image_url")
    val imageUrl: String?
) {

    fun getSecureImageUrl(): String? =
        imageUrl?.replace("http://", "https://")
}