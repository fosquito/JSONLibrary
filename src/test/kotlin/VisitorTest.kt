import org.junit.Test
import kotlin.test.assertEquals

class VisitorTest {

    @Test
    fun test1() {
        val json: Json = example1()
        var v: Visitor = Visitor()
        json.accept(v)

        //json size = 5
        assertEquals(5, v.jsonData.size)
        //first object color=red, value=#f000
        //assertEquals("red", visitor.jsonData[0])
        println()
    }
}