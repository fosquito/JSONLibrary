import kotlin.reflect.KClass

fun main() {
    var a = teste()
    a.add("um")
    a.add("2")
    var j = Json(a)
}

class teste {
    var list = mutableListOf<Any>()

    fun add (element: Any) {
        list.add(element)
    }
}