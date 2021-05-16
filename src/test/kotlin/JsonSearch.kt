import org.junit.Test

class JsonSearch {

    @Test
    fun test01() {
        var colors = example1()
        val visitor = Search("")
        colors.json.accept(visitor)

        val parser = Serialize()
        visitor.searchResponse.forEach {
            it.accept(parser)
            println()
        }
    }

    @Test
    fun test02() {
        var donut = example2()
        val visitor = Search("Sugar")
        donut.json.accept(visitor)

        val parser = Serialize()
        visitor.searchResponse.forEach {
            it.accept(parser)
            println()
        }
    }
}