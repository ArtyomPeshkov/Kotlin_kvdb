import java.io.File
import kotlin.test.*

internal class TestOfRewriteFile {
    private var testList: List<String> = listOf(
        "abc", "kkk",
        "", "         ",
        "lolssda10ek", ".s\"/.123'",
        "hmmm...", "",
        "lol", "nothing"
    )


    @Test
    fun rewriteFileTestNormal() {
        testList=listOf(
            "abc", "kkk",
            "", "         ",
            "lolssda10ek", ".s\"/.123'",
            "hmmm...", "",
            "lol", "nothing"
        )
        rewriteFile(File("src/test/resources/rewriteFunctionsTest/fileToRewrite.txt"), testList)
        assertEquals(testList, File("src/test/resources/rewriteFunctionsTest/fileToRewrite.txt").readLines())
    }

    @Test
    fun rewriteFileTestEmpty() {
        testList=listOf()
        rewriteFile(File("src/test/resources/rewriteFunctionsTest/fileToRewrite.txt"), testList)
        assertEquals(testList, File("src/test/resources/rewriteFunctionsTest/fileToRewrite.txt").readLines())
    }


}
