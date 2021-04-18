
class JsonObject : JsonValue() {
    var jsonObject: MutableMap<String, JsonValue> = mutableMapOf()

    fun add (key: String, value: JsonValue) {
        jsonObject[key] = value
    }

    fun teste () {
        var a = 1
        var b = 2
        var c = a + b
    }
}