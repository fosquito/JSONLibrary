
class Json {

    var elements : MutableList<JsonValue> = mutableListOf()

    fun add(element: JsonValue) {
        elements.add(element)
    }

    fun accept(v: Visitor) {
        if(v.visit(this))
            elements.forEach {
                it.accept(v)
            }
    }

    fun print(){
        elements.forEach {
            it.print()
            println()
        }
    }
}