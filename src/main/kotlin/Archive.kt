class Archive(val name: String) {
    private val _notes = mutableListOf<Note>()
    val notes: List<Note> get() = _notes.toList()
    private var nextId = 1

    fun addNote(name: String, content: String): Note {
        val note = Note(nextId++, name, content)
        _notes.add(note)
        return note
    }

    fun getNoteById(id: Int): Note? = _notes.find { it.id == id }

    fun isEmpty() = _notes.isEmpty()
}