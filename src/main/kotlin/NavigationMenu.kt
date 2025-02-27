import java.util.Scanner

class NavigationMenu<T> {
    private val menuItems = mutableListOf<String>()

    fun showMenu(elements: List<T>) {
        println("Список архивов:")

        menuItems.add("0. Создать архив")
        println(menuItems[0])

        for (i in elements.indices) {
            menuItems.add("${i + 1}. ${elements[i]}")
            println(menuItems[i])
        }

        menuItems.add("${menuItems.size + 1}. Выход")
    }

    fun navigateMenu() {

        while (true) {
            var input: Int = -1

            if (Scanner(System.`in`).hasNextInt()) {
                input = Scanner(System.`in`).nextInt()
            } else {
                println("Необходимо ввести цифру")
                continue
            }

            if (input > menuItems.lastIndex || input < 0) {
                println("Введенная цифра вне диапазона меню")
                continue
            }

            when (input) {
                0 -> StoredData.
                menuItems.lastIndex -> TODO("Выход")
                else -> TODO("Перейти к архиву menu[input - 1]")
            }
        }
    }
}