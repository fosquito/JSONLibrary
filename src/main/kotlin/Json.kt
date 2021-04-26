import kotlin.reflect.KClass

class Json {

    var elements : MutableList<JsonValue> = mutableListOf()

    fun add(element: JsonValue) {
        elements.add(element)
    }

    fun accept(v: Visitor, jClass: KClass<Any>? = null) {
        if(v.visit(this, jClass))
            elements.forEach {
                it.accept(v, jClass)
            }
    }

    fun print(){
        elements.forEach {
            it.print()
            println()
        }
    }
}