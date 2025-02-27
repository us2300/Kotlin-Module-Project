data class Note(val name: String) {
    private val content = mutableMapOf<String, String>()

    override fun toString(): String {
        return this.name
    }
}