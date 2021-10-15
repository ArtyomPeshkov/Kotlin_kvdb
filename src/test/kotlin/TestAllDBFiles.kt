import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.*

internal class TestAllDBFiles {
    private val standardIn = System.`in`
    private val standardOut = System.out
    private var streamIn = ByteArrayInputStream(File("src/test/resources/allDBFiles/allDBFile.txt").readBytes())
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
    fun printFilesTestNormal() {
        rewriteFile(
            File("src/test/resources/allDBFiles/allDBFileOutput.txt"), listOf(
                "1. alreadyDB.txt",
                "2. correctDB.txt",
                "3. newDB.txt"
            )
        )
        File("dataTest/newDB.txt").createNewFile()
        File("dataTest/alreadyDB.txt").createNewFile()
        File("dataTest/correctDB.txt").createNewFile()
        allDataBaseFiles("dataTest")
        var str = ""
        File("src/test/resources/allDBFiles/allDBFileOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/newDB.txt").delete()
        File("dataTest/alreadyDB.txt").delete()
        File("dataTest/correctDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }

    @Test
    fun printFilesTestEmpty() {
        rewriteFile(
            File("src/test/resources/allDBFiles/allDBFileOutput.txt"), listOf(

            )
        )
        allDataBaseFiles("dataTest")
        var str = ""
        File("src/test/resources/allDBFiles/allDBFileOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        assertEquals(str.trim(), streamOut.toString().trim())
    }
}