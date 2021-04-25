
data class JsonNumber(var value: Number) : JsonValue() {

    override fun print() {
        print(value)
    }

    override fun accept(v: Visitor, jClass: Any?) {
        v.visit(this, jClass)
    }
}