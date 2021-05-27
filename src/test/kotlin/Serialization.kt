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

    @Test
    fun test04() {
        val age = Json(30) // Instanciar o modelo Json
        val visitor = Search("30") // Instanciar o visitor Serialize
        age.json.accept(visitor) // Serializar o modelo Json para texto
    }
}