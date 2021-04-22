
data class JsonString(var value: String) : JsonValue() {

    override fun print() {
        print("\""+value+"\"")
    }

    override fun accept(v: Visitor) {
        v.visit(this)    }
}