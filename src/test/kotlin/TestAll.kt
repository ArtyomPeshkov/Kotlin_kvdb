import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.*


/*
Есть два файла:
testAll.txt - файл с командами
testAllOutput.txt - ожидаемый вывод
Мы перенаправляем поток ввода на файл с командами и проверяем, что вывод совпал с ожидаемым и что программа в принципе завершилась
 */
internal class TestAll {
    private val standardIn = System.`in`
    private val standardOut = System.out
    private val streamIn = ByteArrayInputStream(File("src/test/resources/testAll.txt").readBytes())
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
    fun testAll(){
        println("Welcome to data base manager")
        dataBasesManager("dataTest")
        var str = ""
        File("src/test/resources/testAllOutput.txt").forEachLine {
            str += "$it\r\n"
        }
        assertEquals(str.trim(),streamOut.toString().trim())
    }
}