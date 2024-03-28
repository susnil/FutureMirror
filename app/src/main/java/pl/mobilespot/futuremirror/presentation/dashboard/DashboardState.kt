package pl.mobilespot.futuremirror.presentation.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DashboardState(
    val namesDay: List<String>,
    val selectedDay: Int? = null
) : Parcelable {
    companion object {
        val raw = DashboardState(emptyList())
    }
}