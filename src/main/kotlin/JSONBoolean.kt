
data class JsonBoolean(var value: Boolean) : JsonValue() {

    override fun getValue(): Boolean {
        return value
    }

    override fun print(ident: Int) {
        print(value)
    }
}