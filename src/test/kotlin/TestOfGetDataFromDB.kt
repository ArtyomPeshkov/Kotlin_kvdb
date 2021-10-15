import java.io.File
import kotlin.test.*


internal class TestOfGetDataFromDB {
    @Test
    fun getDataFromDBTest() {
        assertEquals(
            linkedMapOf(
                Pair("key1", "val1"),
                Pair("key2", "val2"),
                Pair("key4", "val5"),
                Pair("lol", "nothing"),
                Pair("hmmm...", "")
            ).toString(), getDataFromFile(File("src/test/resources/getFromDB/getFromHere.txt")).toString()
        )
    }

}
