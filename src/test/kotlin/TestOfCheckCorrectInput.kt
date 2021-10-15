import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.*

internal class TestOfCheckCorrectInput {
    private val standardIn = System.`in`
    private val standardOut = System.out
    private var streamIn = ByteArrayInputStream(File("src/test/resources/dbCheckCorrect/dbCheckCorrect.txt").readBytes())
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
    fun checkCorrectInputTestStringInInput() {
        rewriteFile(File("src/test/resources/dbCheckCorrect/dbCheckCorrect.txt"), listOf("afaf","","1","3"))
        streamIn=ByteArrayInputStream(File("src/test/resources/dbCheckCorrect/dbCheckCorrect.txt").readBytes())
        System.setIn(streamIn)
        assertEquals(1, checkCorrectInput(0,10))
    }

    @Test
    fun checkCorrectInputTestOutOfBounds() {
        rewriteFile(File("src/test/resources/dbCheckCorrect/dbCheckCorrect.txt"), listOf("2","-1","3","0", "1"))
        streamIn=ByteArrayInputStream(File("src/test/resources/dbCheckCorrect/dbCheckCorrect.txt").readBytes())
        System.setIn(streamIn)
        assertEquals(0, checkCorrectInput(0,1))
    }

    @Test
    fun checkCorrectInputTestStringsWithNumbers() {
        rewriteFile(File("src/test/resources/dbCheckCorrect/dbCheckCorrect.txt"), listOf("afaf","","1ewfe234","12fqwf","wqdf123","123","asff","as2sf"))
        streamIn=ByteArrayInputStream(File("src/test/resources/dbCheckCorrect/dbCheckCorrect.txt").readBytes())
        System.setIn(streamIn)
        assertEquals(123, checkCorrectInput(0,1000))
    }

}
