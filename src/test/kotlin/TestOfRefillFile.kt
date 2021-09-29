import java.io.File
import kotlin.test.*

internal class TestOfRefillFile {
    private val testMap: LinkedHashMap<String, MutableList<String>> = linkedMapOf(
        Pair("key1", mutableListOf("val1")),
        Pair("key2", mutableListOf("val2")),
        Pair("key4", mutableListOf("val5")),
        Pair("hmmm...", mutableListOf("")),
        Pair("lol", mutableListOf("nothing"))
    )


    @Test
    fun refillFileTest1() {
        File("src/test/resources/fileToRefill.txt").delete()
        refillFile(File("src/test/resources/fileToRefill.txt"), testMap)
        assertEquals(testMap, getDataFromDB(File("src/test/resources/fileToRefill.txt")))
    }

}
