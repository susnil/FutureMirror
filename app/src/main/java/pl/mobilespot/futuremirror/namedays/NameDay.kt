package pl.mobilespot.futuremirror.namedays

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class NameDay(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val day: Int,
    val month: Int,
) : Parcelable {
    companion object {
        val raw = NameDay(name = "Anonim", day = 3, month = 3)
    }
}