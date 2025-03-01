import java.util.Scanner
import kotlin.system.exitProcess

class App {
    private val archives = mutableListOf<Archive>()
    private lateinit var archiveToReturnTo: Archive // Затычка для запоминания, из какого архива открываем заметку :))

    private val mainMenu = NavigationMenu(
        name = "Список архивов: ",
        createMessage = "Создание архива",
        menuItems = archives,
        onCreate = { toCreateArchiveMenu() },
        onItemSelect = { archive -> toArchiveMenu(archive) },
        onExit = { println("Работа программы завершена") }

    )

    fun start() {
        mainMenu.showMenu()
    }

    private fun toArchiveMenu(archive: Archive) {
        NavigationMenu(
            name = "Список заметок в архиве ${archive.name}: ",
            createMessage = "Создание заметки",
            menuItems = archive.notes,
            onCreate = { toCreateNoteMenu(archive) },
            onItemSelect = { note ->
                archiveToReturnTo = archive
                toNoteMenu(note)
            },
            onExit = {
                mainMenu.showMenu()
                exitProcess(0) // Иначе не получалось завершить программу, если создавал хотя бы один архив (showMenu висели в стеке)
            }
        ).showMenu()
    }

    private fun toNoteMenu(note: Note) {
        NavigationMenu(
            name = "Заметка $note",
            createMessage = "Редактирование текста заметки \nТекст заметки: ${note.content}",
            menuItems = mutableListOf(),
            onCreate = {
                note.content = requestNonEmptyString("Текст заметки")
                toNoteMenu(note)
            },
            onItemSelect = {},
            onExit = { toArchiveMenu(archiveToReturnTo) }

        ).showMenu()
    }

    private fun toCreateNoteMenu(archive: Archive) {
        println("Меню создания заметки")
        val name = requestNonEmptyString("Название заметки")
        val content = requestNonEmptyString("Текст заметки")
        val note = Note(name, content)


        archive.notes.add(note)
        archiveToReturnTo = archive
        println("Заметка с именем $note успешно создана\n")
        toNoteMenu(note)
    }

    private fun toCreateArchiveMenu() {
        println("Меню создания архива")
        val name = requestNonEmptyString("Название архива")
        val archive = Archive(name)

        archives.add(archive)
        println("Архив с именем $archive успешно создан\n")
        toArchiveMenu(archive)
    }

    private fun requestNonEmptyString(message: String): String {
        while (true) {
            print("Введите ${message.lowercase()}: ")
            val str = Scanner(System.`in`).nextLine()

            if (str.isBlank()) {
                println("$message не может быть пустым")
                continue
            } else return str
        }
    }
}
