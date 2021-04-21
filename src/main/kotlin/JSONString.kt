
data class JsonString(var value: String) : JsonValue() {

    override fun print() {
        print("\""+value+"\"")
    }
}