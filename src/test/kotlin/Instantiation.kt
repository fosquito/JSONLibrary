import org.junit.Test

@Target(AnnotationTarget.PROPERTY)
annotation class Ignore

class Instantiation {

    @Test
    fun dataClass(){
        class Cat(var name: String, var weight: Double, var isMale: Boolean, @Ignore var color: String, var dad: Cat? = null)

        val cat1 = Cat("Tom", 4.57, true, "white")
        val cat2 = Cat("Mary", 3.61, false, "gray", cat1)

        val jsonCat2 = Json(cat2)
        val visitor = Serialize()
        jsonCat2.json.accept(visitor)
    }

    @Test
    fun collection(){
        val numberArray = listOf<Any?>(22, "421", 23, true, null, 2.23)

        val arrayJson = Json(numberArray)
        val visitor = Serialize()
        arrayJson.json.accept(visitor)
    }

    @Test
    fun map(){
        val map = mapOf<Any, Any?>(Pair(20, true))

        val mapJson = Json(map)
        val visitor = Serialize()
        mapJson.json.accept(visitor)
        println()
    }

    @Test
    fun primitiveTypes(){
        val number = 2
        var j = Json(number)
        val visitor = Serialize()
        j.json.accept(visitor)
        println()

        val str = "ISCTE"
        j = Json(str)
        j.json.accept(visitor)
        println()

        val bool = false
        j = Json(bool)
        j.json.accept(visitor)
        println()

        val n = null
        j = Json(n)
        j.json.accept(visitor)
        println()
    }

    @Test
    fun enum(){
        val direction = Direction.East
        val directions = Json(direction)
        val visitor = Serialize()
        directions.json.accept(visitor)
        println()
    }
    enum class Direction{
        North, South, West, East
    }
}
