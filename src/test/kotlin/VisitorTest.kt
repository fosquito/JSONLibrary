import org.junit.Test
import kotlin.reflect.KClass
import kotlin.test.assertEquals

class VisitorTest {

    @Test
    fun test01_serialization() {
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
    fun test01_search() {
        val json = example1()
        val v = Visitor()

        //Pesquisa todos os elementos
        json.accept(v)

        //jsonData size = 15
        assertEquals(15, v.jsonData.size)
        //first element values = "red", "#f00"
        assertEquals("\"red\", \"#f00\"", v.jsonData[0].valueToString())

        v.jsonData.forEach {
            it.print()
            println()
        }
    }
}