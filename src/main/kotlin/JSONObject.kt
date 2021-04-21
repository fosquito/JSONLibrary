
class JsonObject : JsonValue() {

    var jsonObject: MutableMap<String, JsonValue> = mutableMapOf()

    fun add (key: String, value: JsonValue) {
        jsonObject[key] = value
    }

    override fun print() {
        var counter: Int = 1
        println("{")
        jsonObject.forEach {
            print("\t")
            print(it.key+": ")
            it.value.print()
            if(counter++ != jsonObject.size)
                println(", ")
            else
                println()
        }
        print("}")
    }
}