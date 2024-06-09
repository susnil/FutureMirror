package pl.mobilespot.futuremirror.namedays.local.data

data class SearchResult(
    val names: List<NameDay> = emptyList()
) {
    fun isEmpty() = names.isEmpty()
}
