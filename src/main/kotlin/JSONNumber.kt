
data class JsonNumber(var value: Number) : JsonValue() {

    override fun print() {
        print(value)
    }
}