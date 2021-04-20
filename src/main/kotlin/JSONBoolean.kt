
data class JsonBoolean(var value: Boolean) : JsonValue() {

    override fun print() {
        print(value)
    }
}