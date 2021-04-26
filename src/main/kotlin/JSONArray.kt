import kotlin.reflect.KClass

class JsonArray : JsonValue() {

    private var value: MutableList<JsonValue> = mutableListOf()

    override fun valueToString(): String {
        var str = ""
        value.forEach {
            str += it.valueToString()+", "
        }
        return str.dropLast(2)
    }

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
}