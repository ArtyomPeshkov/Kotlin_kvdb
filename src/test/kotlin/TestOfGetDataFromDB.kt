import java.io.File
import kotlin.test.*


internal class TestOfGetDataFromDB {
    @Test
    fun getDataFromDBTest1() {
        assertEquals(
            linkedMapOf(
                Pair("key1", mutableListOf("val1")),
                Pair("key2", mutableListOf("val2")),
                Pair("key4", mutableListOf("val5")),
                Pair("lol", mutableListOf("nothing")),
                Pair("hmmm...", mutableListOf(""))
            ).toString(), getDataFromDB(File("src/test/resources/getFromHere.txt")).toString()
        )
    }

}
