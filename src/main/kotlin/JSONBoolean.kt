import kotlin.reflect.KClass

data class JsonBoolean(var value: Boolean) : JsonValue() {

    override fun valueToString(): String {
        return value.toString()
    }

    override fun print() {
        print(value)
    }
}