import kotlin.reflect.KClass

data class JsonNumber(var value: Number) : JsonValue() {

    override fun valueToString(): String {
        return value.toString()
    }

    override fun print(ident: Int) {
        print(value)
    }
}