import java.io.File
import kotlin.test.*

internal class TestDataBaseCreator {
    private fun dataBaseCreator(names: List<String?>): String? {
        names.forEach { dataBaseName ->
            while (dataBaseName == null || dataBaseName.isBlank() || dataBaseName.findAnyOf(
                    listOf(
                        ".",
                        "\\",
                        "/",
                        ":",
                        ",",
                        " ",
                        "\"",
                        "'"
                    )
                ) != null
            ) {
                return@forEach
            }
            return dataBaseName
        }
        return null
    }


    @Test
    fun checkCorrectInputTest1() {
        val names:List<String?> = listOf(null,"af.af","sdc sds","sa,sad",null,"lol","any")
        assertEquals("lol", dataBaseCreator(names))
    }

}
