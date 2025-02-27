class Archive(val name: String) {
    private val notes = mutableListOf<Note>()

    override fun toString(): String {
        return this.name
    }

}