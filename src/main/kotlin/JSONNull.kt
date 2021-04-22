
class JsonNull : JsonValue() {

    override fun print() {
        print("Null")
    }

    override fun accept(v: Visitor) {
        v.visit(this)
    }
}