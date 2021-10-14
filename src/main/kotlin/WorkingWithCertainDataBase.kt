import java.io.File


/**
@brief
Функция перезаписывает содержимое файла.
@detailed
Функция очищает файл и вместо строчек с чётными индексами вставляет ключи, а вместо строчек с нечётными индексами значения,
которые получены из map.
@param
Функция принимает на вход файл, который надо перезаписать и map из которого надо взять данные.
 */
fun refillFile(dataBase: File, data: LinkedHashMap<String, String>) {
    dataBase.writeText("")
    data.forEach {
        dataBase.appendText("${it.key}\n")
        val value = data[it.key]
        dataBase.appendText("${value}\n")
    }
}

fun readFromUserFile(file: File) {
    TODO()
}

/**
@brief
Функция проверяет, что по введённому ключу существует значение.
@detailed
Функция будет просить ввести ключ, пока пользователь не введёт ключ, по которому хранится какое-то значение из нашей базы данных,
проверяя введённые данные на корректность.
@param
Функция принимает на вход map, в котором хранится база данных.
@return
Функция возвращает ключ (строку).
 */
fun checkValueForKeyIsNotEmpty(dataBase: LinkedHashMap<String, String>): String {
    println("Введите ключ")
    var userKey = readLine()
    while (userKey == null || dataBase[userKey].isNullOrEmpty()) {
        println("Invalid key, пожалуйста, укажите другой ключ")
        userKey = readLine()
    }
    return userKey
}

/**
@brief
Функция проверяет, что по введённому ключу не существует значения.
@detailed
Функция будет просить ввести ключ, пока пользователь не введёт ключ, по которому ничего не хранится в нашей базе данных, проверяя
введённые данные на корректность.
@param
Функция принимает на вход map, в котором хранится база данных.
@return
Функция возвращает ключ (строку).
 */
fun checkValueForKeyIsEmpty(dataBase: LinkedHashMap<String, String>): String {
    println("Введите ключ")
    var userKey = readLine()
    while (userKey == null || dataBase[userKey]?.isNotEmpty() == true) {
        println("Invalid key or value already exist, пожалуйста, укажите другой ключ")
        userKey = readLine()
    }
    return userKey
}

/**
@brief
Функция проверяет, что введённое значение не равно null.
@detailed
Функция будет просить ввести значение, пока пользователь не введёт корректное значение.
@return
Функция возвращает введённое значение (строку).
 */
fun checkInputValue(): String {
    println("Введите значение")
    var userValue = readLine()
    while (userValue == null) {
        println("Invalid value, пожалуйста, укажите другое значение")
        userValue = readLine()
    }
    return userValue
}

/**
@brief
Функция считывает из файла с базой данных значения и записывает их в map.
@detailed
Функция двигается по файлу и считывает строки с чётными индексами, как ключи, а строки с нечётными индексами как значения.
@param
Функция принимает на вход файл, в котором хранится база данных.
@return
Функция возвращает map с базой данных.
 */
fun getDataFromFile(dataBaseFile: File): LinkedHashMap<String, String> {
    var key = ""
    val res = linkedMapOf<String, String>()
    dataBaseFile.readLines().forEachIndexed { index, string ->
        when (index % 2) {
            0 -> {
                res[string] = ""
                key = string
            }
            1 -> res[key]=string
        }
    }
    return res
}

fun commandList() {
    println("Команды:")
    println("Напишите 'add', чтобы добавить новый элемент в БД")
    println("Напишите 'update', чтобы изменить значение в БД по ключу")
    println("Напишите 'del', чтобы удалить значение из БД по ключу")
    println("Напишите 'find', чтобы вывести элемент по ключу")
    println("Напишите 'check', чтобы проверить, лежит ли какое-то значение по заданному ключу")
    println("Напишите 'save', чтобы сохранить изменения в бд")
    println("Напишите 'load save', чтобы сохранить изменения в бд")
    println("Напишите 'print all', чтобы вывести ключи и значения вашей БД")
    println("Напишите 'clear', чтобы очистить вашу БД")
    println("Напишите 'q', чтобы вернуться в менеджер свои БД")

    println("Write anything to continue")
    readLine()
}

/**
@brief
Функция позволяет пользователю изменять и выводить данные из своей базы данных.
@detailed
Сначала пользователю выводится список доступных команд, после чего программа считывает команды вводимые пользователем,
проверяя их на корректность. Если вводится команда не из списка, программа выводит, что это неизвестная команда.
@param
Функция принимает на вход файл, в котором хранится база данных.
 */
fun openDataBase(dataBaseFile: File) {
    var dataBase = getDataFromFile(dataBaseFile)


    while (true) {
        println("Введите команду или напишите 'list', чтобы посмотреть доступные команды")

        while (true) {
            val userInput = readLine()
            when (userInput?.trim()?:"Mistake") {
                "list", "List", "LIST" -> {
                    commandList()
                    break
                }
                "update", "Update", "UPDATE" -> {
                    val userKey = checkValueForKeyIsNotEmpty(dataBase)
                    val userValue = checkInputValue()
                    dataBase[userKey]= userValue
                    break
                }
                "add", "Add", "ADD" -> {
                    val userKey = checkValueForKeyIsEmpty(dataBase)
                    val userValue = checkInputValue()
                    dataBase[userKey] = userValue
                    break
                }
                "del", "Del", "DEL" -> {
                    val userKey = checkValueForKeyIsNotEmpty(dataBase)
                    dataBase.remove(userKey)
                    break
                }
                "find", "Find", "FIND" -> {
                    val userKey = checkValueForKeyIsNotEmpty(dataBase)
                    println("key: $userKey value: ${dataBase[userKey]}")

                    println("Write anything to continue")
                    readLine()
                    break
                }
                "save", "Save", "SAVE" -> {
                    refillFile(dataBaseFile, dataBase)
                    println("Changes saved")
                    break
                }
                "load save", "Load save", "LOAD SAVE" -> {
                    dataBase = getDataFromFile(dataBaseFile)
                    println("Save loaded")
                    break
                }
                "print all", "Print All", "PRINT ALL" -> {
                    refillFile(dataBaseFile, dataBase)
                    dataBase.forEach {
                        println("key: ${it.key} value: ${it.value}")
                    }
                    println("Write anything to continue")
                    readLine()
                    break
                }
                "clear", "Clear", "CLEAR" -> {
                    dataBase.clear()
                    println("База данных очищена")
                    break
                }
                "q", "Q" -> {
                    refillFile(dataBaseFile, dataBase)
                    return
                }
                else -> println("Unknown command")
            }
        }
    }
}
