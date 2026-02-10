import java.util.Scanner

class Menu(private val scanner: Scanner) {
    fun showMenu(
        title: String,
        items: List<String>,
        onItemSelected: (Int) -> Unit
    ): Boolean {
        while (true) {
            println(title)
            items.forEachIndexed { index, item ->
                println("${index + 1}. $item")
            }

            print("Выберите пункт (1-${items.size}): ")
            val input = scanner.nextLine()
            val choice = input.toIntOrNull()

            when {
                choice == null -> {
                    println("Ошибка: нужно ввести число")
                }
                choice < 1 || choice > items.size -> {
                    println("Ошибка: нет такого пункта. Введите число от 1 до ${items.size}")
                }
                choice == items.size -> {
                    return false
                }
                else -> {
                    onItemSelected(choice - 1)
                    return true
                }
            }
        }
    }

    fun showWelcome() {
        println("Добро пожаловать в приложение \"Заметки\"")
        println("---------------------------")
    }

    fun showGoodbye() {
        println("---------------------------")
        println("Спасибо, что попробовали наше приложение.")
        println("До скорых встреч!")
    }

    fun readString(prompt: String): String {
        while (true) {
            print("$prompt: ")
            val input = scanner.nextLine().trim()
            if (input.isNotBlank()) {
                return input
            }
            println("Поле не может быть пустым. Попробуйте снова.")
        }
    }
}