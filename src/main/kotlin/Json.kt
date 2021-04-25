
class Json {

    var elements : MutableList<JsonValue> = mutableListOf()

    fun add(element: JsonValue) {
        elements.add(element)
    }

    fun accept(v: Visitor, jClass: Any? = null) {
        if(v.visit(this, jClass))
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