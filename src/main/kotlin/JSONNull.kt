import kotlin.reflect.KClass

class JsonNull : JsonValue() {

    override fun valueToString(): String {
        return "Null"
    }

    override fun print(ident: Int) {
        print("Null")
    }
}