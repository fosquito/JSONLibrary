import org.junit.Test
import kotlin.test.assertEquals

class Serialization {

    @Test
    fun test01() {
        val json = example1()

        //json size = 5
        assertEquals(5, json.elements.size)
        //first element color = red and value = #f00
        var obj = json.elements[0] as JsonObject
        assertEquals("\"red\"", obj.jsonObject[0].valueToString())
        assertEquals("\"#f00\"", obj.jsonObject[1].valueToString())
        //first element color = green and value = #0f0
        obj = json.elements[1] as JsonObject
        assertEquals("\"green\"", obj.jsonObject[0].valueToString())
        assertEquals("\"#0f0\"", obj.jsonObject[1].valueToString())

        json.print()
    }

    @Test
    fun test02() {
        val json = example2()

        //json size = 5
        assertEquals(1, json.elements.size)
        //first element id = "0001"
        var obj = json.elements[0] as JsonObject
        //assertEquals("\"0001\"", obj.jsonObject[0].valueToString())
        //assertEquals("\"#f00\"", obj.jsonObject[1].valueToString())
        //first element color = green and value = #0f0
        //obj = json.elements[1] as JsonObject
        //assertEquals("\"green\"", obj.jsonObject[0].valueToString())
        //assertEquals("\"#0f0\"", obj.jsonObject[1].valueToString())

        json.print()
    }
}