
class JsonMap(val key: String, val value: JsonValue) : JsonValue() {
    override fun valueToString(): String {
        return value.valueToString()
    }

    override fun print(ident: Int) {
        print("\"$key\": ")
        value.print(ident)
    }

}