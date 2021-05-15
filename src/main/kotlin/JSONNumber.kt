
data class JsonNumber(var value: Number) : JsonValue() {

    override fun getValue(): Any {
        return value
    }

}