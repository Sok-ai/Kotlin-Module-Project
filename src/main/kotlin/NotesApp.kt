import java.util.Scanner

class NotesApp {
    private val scanner = Scanner(System.`in`)
    private val menu = Menu(scanner)
    private val archiveManager = ArchiveManager()

    fun start() {
        menu.showWelcome()

        while (true) {
            val menuItems = mutableListOf<String>()
            menuItems.add("Создать архив")
            archiveManager.archives.forEach { archive ->
                menuItems.add(archive.name)
            }
            menuItems.add("Выход")

            val continueMainMenu = menu.showMenu(
                title = "Список архивов:",
                items = menuItems
            ) { choice ->
                when (choice) {
                    0 -> createArchive()
                    else -> {
                        val archiveIndex = choice - 1
                        archiveManager.getArchive(archiveIndex)?.let { archive ->
                            openArchive(archive)
                        }
                    }
                }
            }

            if (!continueMainMenu) break
        }

        menu.showGoodbye()
    }

    private fun createArchive() {
        val name = menu.readString("Введите название архива")
        val archive = archiveManager.createArchive(name)
        println("Архив '${archive.name}' создан!")
    }

    private fun openArchive(archive: Archive) {
        while (true) {
            val noteMenuItems = mutableListOf<String>()
            noteMenuItems.add("Создать заметку")
            archive.notes.forEach { note ->
                noteMenuItems.add(note.title)
            }
            noteMenuItems.add("Назад")

            val continueArchiveMenu = menu.showMenu(
                title = "Архив: ${archive.name}",
                items = noteMenuItems
            ) { choice ->
                when (choice) {
                    0 -> createNote(archive)
                    else -> {
                        val noteIndex = choice - 1
                        archive.getNote(noteIndex)?.let { note ->
                            showNoteContent(note)
                        }
                    }
                }
            }

            if (!continueArchiveMenu) break
        }
    }

    private fun createNote(archive: Archive) {
        val title = menu.readString("Введите заголовок заметки")

        println("Введите содержание заметки:")
        print("> ")
        val content = scanner.nextLine().trim()

        if (content.isBlank()) {
            println("Ошибка: содержание заметки не может быть пустым!")
            return
        }

        archive.addNote(title, content)
        println("Заметка '$title' создана в архиве '${archive.name}'!")
    }

    private fun showNoteContent(note: Note) {
        println("\n" + "=".repeat(40))
        println("ЗАМЕТКА: ${note.title}")
        println("=".repeat(40))
        println()
        println(note.content)
        println()
        println("=".repeat(40))
        println("Нажмите Enter для возврата в меню...")
        scanner.nextLine()
    }
}