import java.io.File

fun rewriteFile(file: File, newData: List<String?>) {
    file.writeText("")
    newData.forEach {
            file.appendText("$it\n")
    }
}