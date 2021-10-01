import java.io.File

/**
@brief
Функция проверяет правильное ли число ввёл пользователь.
@detailed
Функция будет просить ввести число, находящееся в заданном диапазоне, пока пользователь не введёт корректное число.
@param
Функция принимает на вход границы диапазона в котором должно находиться введённое число.
@return
Когда пользователь ввёл корректное число, функция возвращает его.
 */


fun checkCorrectInput(minVal: Int, maxVal: Int): Int {
    var someNumberOrFileName = readLine()
//проверка корректности числа
    while (someNumberOrFileName == null || someNumberOrFileName.toIntOrNull() == null || someNumberOrFileName.toInt() < minVal || someNumberOrFileName.toInt() > maxVal) {
        println("Write correct number or file name")
        someNumberOrFileName = readLine()
    }
    return someNumberOrFileName.toInt()

}

/**
@brief
Функция создаёт базу данных с именем, которое вводит пользователь.
@detailed
Функция просит ввести имя файла до того момента, пока пользователь не введёт корректное имя, после чего создаёт файл
с заданным именем или говорит, что такой файл уже существует.
 */
fun dataBaseCreator(folderName: String) {
    println("Введите имя файла без расширения и посторонних символов (точки, слэша и бэкслэша (/ \\), двоеточия, запятой , кавычек и пробела)")
    var dataBaseName = readLine()
    //проверка корректности введённого файла
    while (dataBaseName == null || dataBaseName.isBlank() || dataBaseName.findAnyOf(
            listOf(
                ".",
                "\\",
                "/",
                ":",
                ",",
                " ",
                "\"",
                "'"
            )
        ) != null
    ) {
        println("Write correct and not empty file name")
        dataBaseName = readLine()
    }
    // Возвращает true, если файл был создан
    val isNewFileCreated: Boolean = File("$folderName/$dataBaseName.txt").createNewFile()
    if (isNewFileCreated) {
        println("$dataBaseName is created successfully.")
    } else {
        println("$dataBaseName already exists.")
    }
}

/**
@brief
Функция двигается по папке src\main\resources и находит в ней все файлы.
@detailed
Функция записывает все файлы лежащие в src\main\resources в List и выводит пронумерованный список файлов на экран.
@return
Функция возвращает список файлов (которые по сути являются базами данных), лежащих в src\main\resources.
 */
fun allDataBaseFiles(folderName: String): List<File> {
    val fileNames = mutableListOf<File>()
    var numberInOutput = 1
    //Создаёт файл в нужной директории
    File(folderName).walk().forEach { file ->
        if (file.isFile) {
            val fileName =
                file.toString().replace("$folderName\\", "")// удаляет часть пути содержащую папки, оставляя только файл
            println("${numberInOutput++}. $fileName")
            fileNames.add(file)
        }
    }
    return fileNames
}

/**
@brief
Функция позволяет пользователю работать со своими базами данных.
@detailed
Сначала пользователю выводится список доступных команд, после чего программа считывает команды вводимые пользователем,
проверяя их на корректность. Если вводится команда не из списка, программа выводит, что это неизвестная команда.
 */
fun dataBasesManager(folderName: String = "dataBases") {
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
                    dataBaseCreator(folderName);break
                }
                "open", "Open", "OPEN" -> {
                    val allFiles = allDataBaseFiles(folderName)

                    if (allFiles.isEmpty()) {
                        println("You do not have any data-bases")
                        break
                    }

                    println("Choose file (write a number from 1 to ${allFiles.size})")
                    val file = checkCorrectInput(1, allFiles.size)
                    openDataBase(allFiles[file - 1])
                    break
                }
                "del", "Del", "DEL" -> {
                    val allFiles = allDataBaseFiles(folderName)

                    if (allFiles.isEmpty()) {
                        println("You do not have any data-bases")
                        break
                    }

                    println("Choose file (write a number from 1 to ${allFiles.size})")
                    val file = checkCorrectInput(1, allFiles.size)
                    println(allFiles[file - 1].toString())
                    allFiles[file - 1].delete()
                    break
                }
                "print", "Print", "PRINT" -> {
                    if (allDataBaseFiles(folderName).isEmpty()) {
                        println("You do not have any data-bases")
                        break
                    }

                    println("Write anything to continue")
                    readLine()
                    break
                }
                "q", "Q" -> return
                else -> println("Unknown command")
            }
        }
    }

}