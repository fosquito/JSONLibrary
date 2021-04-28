
class JsonNull : JsonValue() {

    override fun getValue(): Any? {
        return null
    }

    override fun print(ident: Int) {
        print("Null")
    }
}