
data class JsonNumber(var value: Number) : JsonValue() {

    override fun getValue(): Any {
        return value
    }


    override fun print(ident: Int) {
        print(value)
    }
}