package pl.mobilespot.futuremirror.namedays

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity
data class NameDay(
    val name: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
) : Parcelable {
    companion object {
        val raw = NameDay("Anonim")
    }
}