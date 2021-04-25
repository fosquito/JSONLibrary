
class JsonNull : JsonValue() {

    override fun print() {
        print("Null")
    }

    override fun accept(v: Visitor, jClass: Any?) {
        v.visit(this, jClass)
    }
}