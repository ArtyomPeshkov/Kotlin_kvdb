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


    @Test
    fun refillFileTest1() {
        File("src/test/resources/fileToRefill.txt").delete()
        refillFile(File("src/test/resources/fileToRefill.txt"), testMap)
        assertEquals(testMap, getDataFromFile(File("src/test/resources/fileToRefill.txt")))
    }

}
