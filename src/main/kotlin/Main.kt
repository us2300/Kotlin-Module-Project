fun main(args: Array<String>) {
    val archives = mutableListOf<Archive>()
    val archiveMenu = NavigationMenu<Archive>()

    archiveMenu.showMenu(archives)
    archiveMenu.navigateMenu()
}