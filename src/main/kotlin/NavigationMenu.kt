import java.util.InputMismatchException
import java.util.Scanner

class NavigationMenu<T : MenuItem>(
    private val name: String, // Шапка меню
    private val createMessage: String, // Сообщение для пункта меню по созданию экземпляра класса
    private val menuItems: MutableList<T>,

    val onCreate: () -> Unit,
    val onItemSelect: (T) -> Unit,
    val onExit: () -> Unit
) {

    fun showMenu() {
        println("$name:")
        println("0. $createMessage")

        for (i in menuItems.indices) {
            println("${i + 1}. ${menuItems[i]}")
        }

        println("${menuItems.size + 1}. Выход")

        navigateMenu()
    }

    private fun navigateMenu() {
        while (true) {
            try {
                println("Для навигации введите цифру-индекс пункта меню")
                println()

                when (val input: Int = Scanner(System.`in`).nextInt()) {
                    0 -> onCreate()
                    in 1..menuItems.size -> onItemSelect(menuItems[input - 1])
                    menuItems.size + 1 -> {
                        onExit()
                        return
                    }
                    else -> {
                        println("Введенная цифра вне диапазона меню")
                        continue
                    }
                }
            } catch (e: InputMismatchException) {
                println("Ввод должен состоять из цифры")
                continue
            }
        }
    }
}