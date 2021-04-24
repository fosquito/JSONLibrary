import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class VisitorTest {

    //Json Construction Test
    @Test
    fun test1() {
        val json: Json = example1()
        var v: Visitor = Visitor()
        json.accept(v)

        //json size = 5
        assertEquals(5, v.jsonData.size)
        //object 1 color=red, value=#f00
        var o: JsonObject = v.jsonData[0] as JsonObject
        assertEquals(JsonString(value = "red"), o.jsonObject["color"])
        assertEquals(JsonString(value = "#f00"), o.jsonObject["value"])
        //object 2 color=green, value=#0f0
        o = v.jsonData[1] as JsonObject
        assertEquals(JsonString(value = "green"), o.jsonObject["color"])
        assertEquals(JsonString(value = "#0f0"), o.jsonObject["value"])

        v.jsonData.forEach {
            it.print()
        }
    }

    @Test
    fun test2() {
        val json: Json = example2()
        var v: Visitor = Visitor()
        json.accept(v)

        v.jsonData.forEach {
            it.print()
        }
    }
}