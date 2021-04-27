
class JsonMap(val key: String, val value: JsonValue) : JsonValue() {
    override fun valueToString(): String {
        TODO("Not yet implemented")
    }

    override fun print() {
        print("\""+key+"\": "+value.valueToString())
    }

}