
class JsonMap(val key: String, val value: JsonValue) : JsonValue() {

    override fun getValue(): Any? {
        return value.getValue()
    }

    override fun print(ident: Int) {
        print("\"$key\": ")
        value.print(ident)
    }

}