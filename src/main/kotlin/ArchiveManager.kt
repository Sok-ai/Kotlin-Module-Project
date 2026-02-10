import java.util.InputMismatchException

class ArchiveManager {
    private val _archives = mutableListOf<Archive>()
    val archives: List<Archive> get() = _archives.toList()

    fun createArchive(name: String): Archive {
        val archive = Archive(name)
        _archives.add(archive)
        return archive
    }
}