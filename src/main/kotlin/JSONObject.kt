
class JsonObject : JsonValue() {

    var jsonObject: MutableMap<String, JsonValue> = mutableMapOf()

    fun add (key: String, value: JsonValue) {
        jsonObject[key] = value
    }

    override fun print() {
        //require(jsonObject.isNotEmpty())
        println("{")
        jsonObject.forEach {
            print(it.key+": ")
            it.value.print
            println(", ")
        }
        print("}")
    }
}