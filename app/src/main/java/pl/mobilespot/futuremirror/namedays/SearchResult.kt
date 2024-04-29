package pl.mobilespot.futuremirror.namedays

data class SearchResult(
    val names: List<NameDay> = emptyList()
) {
    fun isEmpty() = names.isEmpty()
}