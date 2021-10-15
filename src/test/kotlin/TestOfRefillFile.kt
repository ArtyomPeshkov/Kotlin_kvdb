import java.io.File
import kotlin.test.*

internal class TestOfRefillFile {
    private val testMap: LinkedHashMap<String, String> = linkedMapOf(
        Pair("key1", "val1"),
        Pair("key2", "val2"),
        Pair("key4", "val5"),
        Pair("hmmm...", ""),
        Pair("lol", "nothing")
    )

    private val emptyTestMap: LinkedHashMap<String, String> = linkedMapOf()


    @Test
    fun refillFileTestNormal() {
        File("src/test/resources/rewriteFunctionsTest/fileToRefill.txt").delete()
        refillFile(File("src/test/resources/rewriteFunctionsTest/fileToRefill.txt"), testMap)
        assertEquals(testMap, getDataFromFile(File("src/test/resources/rewriteFunctionsTest/fileToRefill.txt")))
    }

    @Test
    fun refillFileTestEmpty() {
        File("src/test/resources/rewriteFunctionsTest/fileToRefill.txt").delete()
        refillFile(File("src/test/resources/rewriteFunctionsTest/fileToRefill.txt"), emptyTestMap)
        assertEquals(emptyTestMap, getDataFromFile(File("src/test/resources/rewriteFunctionsTest/fileToRefill.txt")))
    }

}
