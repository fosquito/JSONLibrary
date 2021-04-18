
class JsonString : JsonValue() {
    var str: String = ""

    fun add(value: String) {
        str = value;
    }
}