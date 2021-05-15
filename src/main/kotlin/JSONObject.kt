import kotlin.reflect.full.memberProperties

class JsonObject : JsonValue() {

    var jsonObject: MutableList<JsonMap> = mutableListOf()

    fun add (element: Any) {
        element::class.memberProperties.forEach {
            if(it == null){
                jsonObject.add(JsonMap(it.name, JsonNull()))
            }
            else if (it.returnType.classifier == List::class){
                var jArray = JsonArray()
                var tempList: MutableList<Any> = it.getter.call(element) as MutableList<Any>
                tempList.forEach { e ->
                    jArray.add(e)
                }
                jsonObject.add(JsonMap(it.name, jArray))
            }
            else if (it.returnType.classifier == String::class){
                jsonObject.add(JsonMap(it.name, JsonString(it.getter.call(element) as String)))
            }
            else if (it.returnType.classifier == Number::class){
                jsonObject.add(JsonMap(it.name, JsonNumber(it.getter.call(element) as Number)))
            }
            else if (it.returnType.classifier == Boolean::class){
                jsonObject.add(JsonMap(it.name, JsonBoolean(it.getter.call(element) as Boolean)))
            }
            else {
                var jObject = JsonObject()
                it.getter.call(element)?.let { it1 -> jObject.add(it1) }
                jsonObject.add(JsonMap(it.name, jObject))
            }
        }
    }

    override fun getValue(): String {
        var str = ""
        jsonObject.forEach {
            str += it.value.getValue().toString()+", "
        }
        return str.dropLast(2)
    }

    override fun print(ident: Int) {

        var counter = 1
        println("{")
        jsonObject.forEach {
            for (i in 0..ident) print("\t")
            it.print(ident+1)
            if(counter++ != jsonObject.size)
                println(", ")
            else
                println()
        }
        for (i in 1..ident) print("\t")
        print("}")
    }
}