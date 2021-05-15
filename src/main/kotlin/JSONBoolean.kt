
data class JsonBoolean(var value: Boolean) : JsonValue() {

    override fun getValue(): Boolean {
        return value
    }
}