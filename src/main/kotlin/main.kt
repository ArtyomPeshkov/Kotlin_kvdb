import java.io.File

fun  checkCorrectInput(minVal: Int, maxVal: Int): Int {
    var someNumber = readLine()
    while (someNumber == null || someNumber.toIntOrNull() == null || someNumber.toInt() < minVal || someNumber.toInt() > maxVal) {
        println("Write correct number")
        someNumber = readLine()
    }
    return someNumber.toInt()
}

fun dataBaseCreator() {
    println("Введите имя файла без расширения")
    var dataBaseName = readLine()
    while (dataBaseName == null || dataBaseName == "" || dataBaseName.findAnyOf(
            listOf(
                ".",
                "\\",
                "/",
                ":",
                ","
            )
        ) != null
    ) {
        println("Write correct and not empty file name")
        dataBaseName = readLine()
    }
    val isNewFileCreated: Boolean = File("src/main/resources/$dataBaseName.txt").createNewFile()
    if (isNewFileCreated) {
        println("$dataBaseName is created successfully.")
    } else {
        println("$dataBaseName already exists.")
    }
}

fun dataBasesManager() {
    while (true) {
        println("Команды:")
        println("Напишите 'new', чтобы создать новую БД")
        println("Напишите 'open', чтобы продолжить работу с одной из имеющихся БД")
        println("Напишите 'del', чтобы удалить одну из своих БД")
        println("Напишите 'print', чтобы увидеть список своих БД")
        println("Напишите 'q', чтобы выйти из утилиты")
        var userInput: String
        while (true) {
            userInput = readLine().toString()
            when (userInput) {
                "new", "New", "NEW" -> {
                    dataBaseCreator();break
                }
                "open", "Open", "OPEN" -> {
                    val allFiles = allDataBaseFiles()
                    if (allFiles.isEmpty()) {
                        println("You do not have any data-bases")
                        break
                    }
                    println("Choose file (write a number)")
                    val position = checkCorrectInput(1, allFiles.size)
                    openDataBase(allFiles[position - 1])
                    break
                }
                "del", "Del", "DEL" -> {
                    val allFiles = allDataBaseFiles()

                    if (allFiles.isEmpty()) {
                        println("You do not have any data-bases")
                        break
                    }

                    println("Choose file (write a number)")
                    val position = checkCorrectInput(1, allFiles.size)
                    println(allFiles[position - 1].toString())
                    allFiles[position - 1].delete()
                    break
                }
                "print", "Print", "PRINT" -> {
                    if (allDataBaseFiles().isEmpty()) {
                        println("You do not have any data-bases")
                        break
                    }

                    println("Write anything to continue")
                    readLine()
                    break
                    //Выставить задержку, пока не произойдёт что-то
                    TODO()
                }
                "q", "Q" -> return
                else -> println("Unknown command")
            }
        }
    }

}

fun allDataBaseFiles(): List<File> {
    val fileNames = mutableListOf<File>()
    var numberInOutput = 1
    File("src/main/resources/").walk().forEach { file ->
        if (file.isFile) {
            val fileName = file.toString().replace("src\\main\\resources\\", "")
            println("${numberInOutput++}. $fileName")
            fileNames.add(file)
        }
    }
    return fileNames
}

fun readFromFile(file: File){
    TODO()
}

fun checkValueForKeyIsNotEmpty(dataBase: MutableMap<String,MutableList<String>>):String{
    println("Введите ключ")
    var userKey = readLine()
    while (userKey==null || dataBase[userKey].isNullOrEmpty() )
    {
        println("Invalid key or несуществующее value, пожалуйста, укажите другой ключ")
        userKey = readLine()
    }
    return userKey
}

fun checkValueForKeyIsEmpty(dataBase: MutableMap<String,MutableList<String>>):String{
    println("Введите ключ")
    var userKey = readLine()
    while (userKey==null || dataBase[userKey]?.isNotEmpty() == true)
    {
        println("Invalid key or value already exist, пожалуйста, укажите другой ключ")
        userKey = readLine()
    }
    return userKey
}

fun checkInputValue():String{
    println("Введите значение")
    var userValue = readLine()
    while (userValue==null )
    {
        println("Invalid value, пожалуйста, укажите другое значение")
        userValue = readLine()
    }
    return userValue
}


fun openDataBase(dataBaseFile: File) {
    val dataBase = mutableMapOf<String,MutableList<String>>()
    var key=""
    dataBaseFile.readLines().forEachIndexed { index, string ->
        when(index%2/*3*/) {
            0 -> {dataBase[string] = mutableListOf();key=string}
            1 -> dataBase[key]?.add(string)
            //2 -> dataBase[key]?.addAll(string.split(' '))
        }
    }

    while (true) {
        println("Команды:")
        println("Напишите 'add', чтобы добавить новый элемент в БД")
        println("Напишите 'change', чтобы изменить значение в БД по ключу")
        println("Напишите 'del', чтобы удалить значение из БД по ключу")
        println("Напишите 'print', чтобы вывести ключи и значения вашей БД")
        var userInput: String
        while (true) {
            userInput = readLine().toString()
            when (userInput) {
                "change", "Change", "CHANGE" -> {
                    val userKey = checkValueForKeyIsNotEmpty(dataBase)
                    val userValue = checkInputValue()
                    dataBase[userKey]?.set(0, userValue)
                    /*TODO("update date of change")*/
                    break
                }
                "add", "Add", "ADD" -> {
                    val userKey = checkValueForKeyIsEmpty(dataBase)
                    val userValue = checkInputValue()
                    dataBase[userKey] = mutableListOf(userValue/*,TODO("add date of creation and date of change")*/)
                    break
                }
                "del", "Del", "DEL" -> {
                    val userKey = checkValueForKeyIsNotEmpty(dataBase)
                    dataBase.remove(userKey)
                    break
                }
                "print", "Print", "PRINT" -> {
                    dataBase.forEach{
                        println("key: ${it.key} value: ${it.value}" )
                    }
                    println("Write anything to continue")
                    readLine()
                }
                "q", "Q" -> return
                else -> println("Unknown command")
            }
        }
    }
}

fun main() {
    println("Welcome to data base manager")
    dataBasesManager()
}
