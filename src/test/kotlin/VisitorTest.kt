import org.junit.Test
import kotlin.reflect.KClass

class VisitorTest {

    @Test
    fun test1() {
        val json = example1()
        val v = Visitor()
        json.accept(v)

        json.print()

        v.jsonData.forEach {
            it.print()
            println()
        }
    }

    @Test
    fun test2() {
        val json = Json()
        var obj = JsonObject()
    }
}