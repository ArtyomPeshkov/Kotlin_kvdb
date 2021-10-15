import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.*

class TestKeyOrValueExists {

    private val testMap: LinkedHashMap<String, String> = linkedMapOf(
        Pair("key1", "val1"),
        Pair("key2", "val2"),
        Pair("key4", "val5"),
        Pair("hmmm...", ""),
        Pair("lol", "nothing")
    )

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
    fun keyExistsTrue() {
        rewriteFile(File("src/test/resources/checkKey/checkKey.txt"), listOf("lol"))
        streamIn= ByteArrayInputStream(File("src/test/resources/checkKey/checkKey.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(
            File("src/test/resources/checkKey/checkKeyOutput.txt"), listOf("Введите ключ"))
        checkValueForKeyIsNotEmpty(testMap)
        var str = ""
        File("src/test/resources/checkKey/checkKeyOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/newDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }

    @Test
    fun keyExistsFalse() {
        rewriteFile(File("src/test/resources/checkKey/checkKey.txt"), listOf("any","lol"))
        streamIn= ByteArrayInputStream(File("src/test/resources/checkKey/checkKey.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(
            File("src/test/resources/checkKey/checkKeyOutput.txt"), listOf("Введите ключ","Invalid key, пожалуйста, укажите другой ключ"))
        checkValueForKeyIsNotEmpty(testMap)
        var str = ""
        File("src/test/resources/checkKey/checkKeyOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/newDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }


    @Test
    fun keyNotExistsTrue() {
        rewriteFile(File("src/test/resources/checkKey/checkKey.txt"), listOf("any"))
        streamIn= ByteArrayInputStream(File("src/test/resources/checkKey/checkKey.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(
            File("src/test/resources/checkKey/checkKeyOutput.txt"), listOf("Введите ключ"))
        checkValueForKeyIsEmpty(testMap)
        var str = ""
        File("src/test/resources/checkKey/checkKeyOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/newDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }

    @Test
    fun keyNotExistsFalse() {
        rewriteFile(File("src/test/resources/checkKey/checkKey.txt"), listOf("lol","any"))
        streamIn= ByteArrayInputStream(File("src/test/resources/checkKey/checkKey.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(
            File("src/test/resources/checkKey/checkKeyOutput.txt"), listOf("Введите ключ","Invalid key or value already exist, пожалуйста, укажите другой ключ"))
        checkValueForKeyIsEmpty(testMap)
        var str = ""
        File("src/test/resources/checkKey/checkKeyOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/newDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }

    @Test
    fun valueCorrectTrue() {
        rewriteFile(File("src/test/resources/checkKey/checkKey.txt"), listOf("ste"))
        streamIn= ByteArrayInputStream(File("src/test/resources/checkKey/checkKey.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(
            File("src/test/resources/checkKey/checkKeyOutput.txt"), listOf("Введите ключ"))
        checkValueForKeyIsEmpty(testMap)
        var str = ""
        File("src/test/resources/checkKey/checkKeyOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        File("dataTest/newDB.txt").delete()
        assertEquals(str.trim(), streamOut.toString().trim())
    }

    @Test
    fun valueCorrectFalse() {
        rewriteFile(File("src/test/resources/checkKey/checkKey.txt"), listOf(null,"any"))//null превращается в "null", ошибки не будет
        streamIn= ByteArrayInputStream(File("src/test/resources/checkKey/checkKey.txt").readBytes())
        System.setIn(streamIn)
        rewriteFile(
            File("src/test/resources/checkKey/checkKeyOutput.txt"), listOf("Введите ключ"))
        checkValueForKeyIsEmpty(testMap)
        var str = ""
        File("src/test/resources/checkKey/checkKeyOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        assertEquals(str.trim(), streamOut.toString().trim())
    }
}