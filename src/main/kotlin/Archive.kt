class Archive(val name: String) {
    private val _notes = mutableListOf<Note>()
    val notes: List<Note> get() = _notes.toList()
    private var nextId = 1

    fun addNote(name: String, content: String): Note {
        val note = Note(nextId++, name, content)
        _notes.add(note)
        return note
    }

    fun getNote(index: Int): Note? = _notes.getOrNull(index)

}