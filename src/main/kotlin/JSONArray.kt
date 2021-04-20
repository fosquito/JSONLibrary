
class JsonArray() : JsonValue() {

    var value: MutableList<JsonValue> = mutableListOf()

    fun add(element: JsonValue) {
        value.add(element)
    }

    override fun print() {
        print("[")
        var counter: Int = 1
        value.forEach {
            it.print
            if(counter++ != value.size)
                print(", ")
        }
        print("]")
    }
}