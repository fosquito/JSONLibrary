
class JsonMap(private val key: String, val value: JsonValue) : JsonValue() {

    override fun getValue(): Any? {
        return value
    }

    fun getKey(): String {
        return "\""+key+"\""
    }

    override fun accept(v: Visitor) {
        v.visit(this)
    }
}