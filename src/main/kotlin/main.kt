import java.io.File

fun dataBaseCreator(){
    println("Введите имя файла без расширения")
    var dataBaseName= readLine()
    while (dataBaseName==null || dataBaseName==""  || dataBaseName.findAnyOf(listOf(".","\\","/",":",","))!=null) {
        println("Write correct and not empty file name")
        dataBaseName = readLine()
    }
    val isNewFileCreated :Boolean = File("src/main/resources/$dataBaseName.txt").createNewFile()
    if(isNewFileCreated){
        println("$dataBaseName is created successfully.")
    } else{
        println("$dataBaseName already exists.")
    }
}


fun dataBasesManager(){
    while (true) {
        println("Команды:")
        println("Напишите 'new', чтобы создать новую БД")
        println("Напишите 'open', чтобы продолжить работу с одной из имеющихся БД")
        println("Напишите 'del', чтобы удалить одну из своих БД")
        println("Напишите 'q', чтобы выйти из утилиты")
        var userInput: String
        while (true) {
            userInput = readLine().toString()
            when (userInput) {
                "new", "New", "NEW" -> {
                    dataBaseCreator();break
                }
                "open", "Open", "OPEN" -> {
                    startWorkingWithDataBase();break
                }
                "del", "Del", "DEL" -> {
                    if (File("src/main/resources/file.txt").delete())
                        println("File file.txt deleted successfully")
                    break
                }
                "q","Q" -> return
                else -> println("Unknown command")
            }
        }
    }

}

fun startWorkingWithDataBase() {
    File("src/main/resources/").walk().forEach {
    if (!it.isFile )
        return@forEach
    println(it)
    }
}

fun main(args: Array<String>) {
   println("Welcome to data base manager")
    dataBasesManager()
}
