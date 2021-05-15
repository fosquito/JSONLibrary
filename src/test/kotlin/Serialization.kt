import org.junit.Test

class Serialization {

    @Test
    fun test01() {
        var colors = example1()
        val visitor = Serialize()

        colors.json.accept(visitor)
    }

    @Test
    fun test02() {
        var donut = example2()
        val visitor = Serialize()

        donut.json.accept(visitor)
    }
}