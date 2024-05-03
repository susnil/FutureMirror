package pl.mobilespot.futuremirror.datastore

data class UserPreferences(
    val showCompleted: Boolean
) {
    companion object {
        val raw = UserPreferences(showCompleted = true)

    }
}
