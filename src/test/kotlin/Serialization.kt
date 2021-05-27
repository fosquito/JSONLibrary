import org.junit.Test

class Serialization {

    @Test
    fun test01() {
        val colors = example1()
        val visitor = Serialize()

        colors.json.accept(visitor)
    }

    @Test
    fun test02() {
        val donut = example2()
        val visitor = Serialize()

        donut.json.accept(visitor)
    }

    @Test
    fun test03() {
        val j = Json(JsonMap("age", JsonNumber(30)))
        val visitor = Serialize()

        j.json.accept(visitor)
    }
}