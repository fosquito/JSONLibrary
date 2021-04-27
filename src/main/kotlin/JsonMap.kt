
class JsonMap(val key: String, val value: JsonValue) : JsonValue() {
    override fun valueToString(): String {
        return value.valueToString()
    }

    override fun print() {
        print("\""+key+"\": "+value.valueToString())
    }

}