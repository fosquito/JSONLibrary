
class JsonArray : JsonValue() {

    private var value: MutableList<JsonValue> = mutableListOf()

    fun add(element: JsonValue) {
        value.add(element)
    }

    override fun print() {
        print("[")
        var counter = 1
        value.forEach {
            it.print()
            if(counter++ != value.size)
                print(", ")
        }
        print("]")
    }

    override fun accept(v: Visitor, jClass: Any?) {
        v.visit(this, jClass)
    }
}