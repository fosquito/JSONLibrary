import org.junit.Test
import kotlin.test.assertEquals

class Search {

    @Test
    fun test01() {
        val json = example1()
        val v = Visitor()

        //Search all elements
        json.accept(v)

        //jsonData size = 15
        assertEquals(15, v.jsonData.size)
        //first element values = "red", "#f00"
        assertEquals("\"red\", \"#f00\"", v.jsonData[0].getValue())
        //second element value = "red"
        assertEquals("\"red\"", v.jsonData[1].getValue())
        //third element value = "#f00"
        assertEquals("\"#f00\"", v.jsonData[2].getValue())
        //fourth element values = "green", "#0f0"
        assertEquals("\"green\", \"#0f0\"", v.jsonData[3].getValue())

        v.jsonData.forEach {
            it.print()
            println()
        }
    }

    @Test
    fun test02() {
        val json = example1()
        val v = Visitor()

        //Search JsonString elements
        json.accept(v)

        //jsonData size = 10
        assertEquals(10, v.jsonData.size)
        //first element value = "red"
        assertEquals("\"red\"", v.jsonData[0].getValue())
        //second element value = "#f00"
        assertEquals("\"#f00\"", v.jsonData[1].getValue())
        //third element values = "green"
        assertEquals("\"green\"", v.jsonData[2].getValue())

        v.jsonData.forEach {
            it.print()
            println()
        }
    }

    @Test
    fun test03() {
        val json = example2()
        val v = Visitor()

        //Search JsonNumber elements
        json.accept(v)

        //jsonData size = 1
        assertEquals(1, v.jsonData.size)
        //first element value = 0.55
        assertEquals(0.55, v.jsonData[0].getValue())

        v.jsonData.forEach {
            it.print()
            println()
        }
    }

    @Test
    fun test04() {
        val json = example2()
        val v = Visitor()

        //Search JsonArray elements
        json.accept(v)

        //jsonData size = 2
        assertEquals(2, v.jsonData.size)

        v.jsonData.forEach {
            it.print()
            println()
        }
    }
}