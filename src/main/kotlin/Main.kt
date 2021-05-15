import kotlin.reflect.KClass

fun main() {
    var j = Json(5)
    var visitor = Serialize()
    j.json.accept(visitor)
}

class teste {
    var list = mutableListOf<Any>()

    fun add (element: Any) {
        list.add(element)
    }
}