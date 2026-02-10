import java.util.InputMismatchException
import java.util.Scanner

class Menu(private val scanner: Scanner) {
    fun showMenu(
        title: String,
        items: List<String>,
        onItemSelected: (Int) -> Unit
    ): Boolean {
        try {
            println(title)
            items.forEachIndexed { index, text ->
                println("$index. $text")
            }
            val selectedVar = scanner.nextInt()
            if (selectedVar in 0 until items.size) {
                onItemSelected(selectedVar)
            } else {
                println("Некорректный выбор. Введите число от 0 до ${items.size - 1}")
            }
        } catch (_: InputMismatchException) {
            println("Нужно число от 0 до ${items.size - 1}")
            scanner.nextLine()
        }
        return true
    }

    fun showWelcome() {
        println("Доброе пожаловать в приложение \"Заметки\"")
        println("---------------------------")
    }

    fun showGoodbye() {
        println("---------------------------")
        println("Спасибо, что попробовали наше приложение. \nДо скорых встреч!")
    }

    fun readString(prompt: String): String {
        print("$prompt: ")
        return scanner.nextLine()
    }

    fun readLine(prompt: String): String {
        println(prompt)
        return scanner.nextLine()
    }
}