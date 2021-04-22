
data class JsonBoolean(var value: Boolean) : JsonValue() {

    override fun print() {
        print(value)
    }

    override fun accept(v: Visitor) {
        v.visit(this)
    }
}