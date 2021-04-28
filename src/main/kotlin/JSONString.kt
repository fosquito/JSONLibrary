import kotlin.reflect.KClass

data class JsonString(var value: String) : JsonValue() {

    override fun getValue(): Any? {
        return "\""+value+"\""
    }

    override fun print(ident: Int) {
        print("\""+value+"\"")
    }
}