import org.junit.Test

class JsonSearch {

    @Test
    fun test01() {
        var colors = example1()
        val visitor = Search()

        colors.json.accept(visitor)

    }
}