import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.*

internal class TestDataBaseCreator {
    private val standardIn = System.`in`
    private val standardOut = System.out
    private var streamIn = ByteArrayInputStream(File("src/test/resources/dbCreator/dbCreator.txt").readBytes())
    private val streamOut = ByteArrayOutputStream()

    @BeforeTest
    fun setUp() {
        System.setIn(streamIn)
        System.setOut(PrintStream(streamOut))
    }


    @AfterTest
    fun tearDown() {
        System.setIn(standardIn)
        System.setOut(standardOut)
    }


    @Test
    fun checkCorrectInputTestCreatingNew() {
        rewriteFile(File("src/test/resources/dbCreator/dbCreator.txt"), listOf("newDB"))
        streamIn=ByteArrayInputStream(File("src/test/resources/dbCreator/dbCreator.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(File("src/test/resources/dbCreator/dBCreatorOutput.txt"), listOf("Введите имя файла без расширения и посторонних символов (точки, слэша и бэкслэша (/ \\), двоеточия, запятой , кавычек и пробела)",
            "newDB is created successfully."))
        dataBaseCreator("dataTest")
        var str = ""
        File("src/test/resources/dbCreator/dBCreatorOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/newDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }

    @Test
    fun checkCorrectInputTestCreatingAlready() {
        rewriteFile(File("src/test/resources/dbCreator/dbCreator.txt"), listOf("alreadyDB"))
        streamIn=ByteArrayInputStream(File("src/test/resources/dbCreator/dbCreator.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(File("src/test/resources/dbCreator/dBCreatorOutput.txt"), listOf("Введите имя файла без расширения и посторонних символов (точки, слэша и бэкслэша (/ \\), двоеточия, запятой , кавычек и пробела)",
            "alreadyDB already exists."))
        File("dataTest/alreadyDB.txt").createNewFile()
        dataBaseCreator("dataTest")
        var str = ""
        File("src/test/resources/dbCreator/dBCreatorOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/alreadyDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }

    @Test
    fun checkCorrectInputTestCreatingMistake() {
        rewriteFile(File("src/test/resources/dbCreator/dbCreator.txt"), listOf("alr.eadyDB","alr:eadyDB","alr eadyDB","alr,eadyDB","alr/eadyDB","correctDB"))
        streamIn=ByteArrayInputStream(File("src/test/resources/dbCreator/dbCreator.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(File("src/test/resources/dbCreator/dBCreatorOutput.txt"), listOf("Введите имя файла без расширения и посторонних символов (точки, слэша и бэкслэша (/ \\), двоеточия, запятой , кавычек и пробела)",
            "Write correct and not empty file name",
            "Write correct and not empty file name",
            "Write correct and not empty file name",
            "Write correct and not empty file name",
            "Write correct and not empty file name",
            "correctDB is created successfully."))
        dataBaseCreator("dataTest")
        var str = ""
        File("src/test/resources/dbCreator/dBCreatorOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/correctDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }

}
