
class Json {

    var elements : MutableList<JsonValue> = mutableListOf()

    fun add(element: JsonValue) {
        elements.add(element)
    }

    fun print(){
        elements.forEach {
            it.print()
            println()
        }
    }
}