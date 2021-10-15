import java.io.File

fun main() {
    File("dataBases/").mkdir()
    println("Добро пожаловать в менеджер баз данных")
    dataBasesManager()
}