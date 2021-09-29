import java.io.File
import java.time.LocalDateTime


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
fun  checkCorrectInput(minVal: Int, maxVal: Int): Int {
    var someNumber = readLine()
    while (someNumber == null || someNumber.toIntOrNull() == null || someNumber.toInt() < minVal || someNumber.toInt() > maxVal) {
        println("Write correct number")
        someNumber = readLine()
    }
    return someNumber.toInt()
}

/**
@brief
Функция перезаписывает содержимое файла.
@detailed
Функция очищает файл и вместо строчек с чётными индексами вставляет ключи, а вместо строчек с нечётными индексами значения,
которые получены из map.
@param
Функция принимает на вход файл, который надо перезаписать и map из которого надо взять данные.
 */
fun refillFile(dataBase: File, data: LinkedHashMap<String,MutableList<String>>)
{
    dataBase.writeText("")
    data.forEach{
        dataBase.appendText("${it.key }\n")
        val value=data[it.key]?.first()
        dataBase.appendText("${value}\n")
    }
}

fun readFromUserFile(file: File){
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
fun checkValueForKeyIsNotEmpty(dataBase: LinkedHashMap<String,MutableList<String>>):String{
    println("Введите ключ")
    var userKey = readLine()
    while (userKey==null || dataBase[userKey].isNullOrEmpty() )
    {
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
fun checkValueForKeyIsEmpty(dataBase: LinkedHashMap<String,MutableList<String>>):String{
    println("Введите ключ")
    var userKey = readLine()
    while (userKey==null || dataBase[userKey]?.isNotEmpty() == true)
    {
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

/**
@brief
Функция считывает из файла с базой данных значения и записывает их в map.
@detailed
Функция двигается по файлу и считывает строки с чётными индексами, как ключи, а строки с нечётнвми индексами, как значения.
@param
Функция принимает на вход файл, в котором хранится база данных.
@return
Функция возвращает map с базой данных.
 */
fun getDataFromFile(dataBaseFile: File): LinkedHashMap<String, MutableList<String>>{
    var key=""
    val res = linkedMapOf<String,MutableList<String>>()
    dataBaseFile.readLines().forEachIndexed { index, string ->
        when(index%2/*3*/) {
            0 -> {res[string] = mutableListOf();key=string}
            1 -> res[key]?.add(string)
            //2 -> dataBase[key]?.addAll(string.split(' '))
        }
    }
    return res
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
    val dataBase = getDataFromFile(dataBaseFile)


    while (true) {
        println("Команды:")
        println("Напишите 'add', чтобы добавить новый элемент в БД")
        println("Напишите 'change', чтобы изменить значение в БД по ключу")
        println("Напишите 'del', чтобы удалить значение из БД по ключу")
        println("Напишите 'find', чтобы вывести элемент по ключу")
        println("Напишите 'print all', чтобы вывести ключи и значения вашей БД")
        println("Напишите 'q', чтобы выйти из БД")

        var userInput: String
        while (true) {
            userInput = readLine().toString()
            when (userInput) {
                "change", "Change", "CHANGE" -> {
                    val userKey = checkValueForKeyIsNotEmpty(dataBase)
                    val userValue = checkInputValue()
                    dataBase[userKey]?.set(0, userValue)
                    /*TODO("update date of change")*/
                    refillFile(dataBaseFile,dataBase)
                    break
                }
                "add", "Add", "ADD" -> {
                    val userKey = checkValueForKeyIsEmpty(dataBase)
                    val userValue = checkInputValue()
                    dataBase[userKey] = mutableListOf(userValue/*,TODO("add date of creation and date of change")*/)
                    refillFile(dataBaseFile,dataBase)
                    break
                }
                "del", "Del", "DEL" -> {
                    val userKey = checkValueForKeyIsNotEmpty(dataBase)
                    dataBase.remove(userKey)
                    refillFile(dataBaseFile,dataBase)
                    break
                }
                "find", "Find", "FIND" -> {
                    val userKey = checkValueForKeyIsNotEmpty(dataBase)
                    println("key: $userKey value: ${dataBase[userKey]}")
                    break
                }
                "print all", "Print All", "PRINT ALL" -> {
                    refillFile(dataBaseFile,dataBase)
                    dataBase.forEach{
                        println("key: ${it.key} value: ${it.value}" )
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
