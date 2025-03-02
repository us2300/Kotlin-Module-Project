class Archive(override val name: String) : MenuItem {
    val notes = mutableListOf<Note>()

    override fun toString(): String {
        return name
    }
}
