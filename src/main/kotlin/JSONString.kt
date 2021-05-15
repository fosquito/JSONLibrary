
data class JsonString(var value: String) : JsonValue() {

    override fun getValue(): Any {
        return "\""+value+"\""
    }
}