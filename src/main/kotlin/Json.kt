
class Json {

    var elements : MutableList<JsonValue> = mutableListOf()

    val print by lazy { print() }

    fun add(element: JsonValue) {
        elements.add(element)
    }

    private fun print(){
        elements.forEach {
            it.print
            println()
        }
    }
}