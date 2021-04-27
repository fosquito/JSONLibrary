import kotlin.reflect.KClass

data class JsonString(var value: String) : JsonValue() {

    override fun valueToString(): String {
        return "\""+value+"\""
    }

    override fun print() {
        print("\""+value+"\"")
    }
}