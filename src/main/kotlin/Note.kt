class Note(override val name: String, var content: String) : MenuItem {

    override fun toString(): String {
        return name
    }
}