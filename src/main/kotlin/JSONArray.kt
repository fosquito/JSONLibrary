import kotlin.reflect.full.memberProperties

class JsonArray : JsonValue() {

    var value: MutableList<JsonValue> = mutableListOf()

    override fun getValue(): String {
        var str = ""
        value.forEach {
            str += it.getValue().toString()+", "
        }
        return str.dropLast(2)
    }

    fun add(element: Any?) {
        if(element == null){
            value.add(JsonNull())
            return
        }
        when(element) {
            is List<*> -> {
                var jArray = JsonArray()
                element.forEach {
                    jArray.add(it)
                }
                value.add(jArray)
            }
            is String -> {
                value.add(JsonString(element))
            }
            is Number -> {
                value.add(JsonNumber(element))
            }
            is Boolean -> {
                value.add(JsonBoolean(element))
            }
            else -> {
                var jObject = JsonObject()
                jObject.add(element)
                value.add(jObject)
            }
        }
    }

    override fun print(ident: Int) {
        println("[")
        var counter = 1
        value.forEach {
            for (i in 0..ident) print("\t")
            it.print(ident+1)
            if(counter++ != value.size)
                print(", ")
            else
                println()
        }
        for (i in 1..ident) print("\t")
        print("]")
    }
}