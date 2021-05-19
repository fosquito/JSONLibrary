import kotlin.reflect.full.memberProperties

class Json {
    lateinit var json: JsonValue

    constructor(j: Any?) {

        if(j == null){
            json = JsonNull()
            return
        }
        when(j) {
            is List<*> -> {
                var jArray = JsonArray()
                j.forEach {
                    jArray.add(it)
                }
                json = jArray
            }
            is String -> json = JsonString(j)
            is Number, Int, Double, Float, Long, Short -> json = JsonNumber(j as Number)
            is Boolean -> json = JsonBoolean(j)
            is Map<*, Any?> -> {
                json = JsonMap(j.entries.firstOrNull()?.key.toString(), getMapValue(j.entries.firstOrNull()?.value))
            }
            else -> {
                var jObject = JsonObject()
                jObject.add(j)
                json = jObject
            }
        }
    }

    fun getMapValue(element: Any?): JsonValue {

        if (element == null) return JsonNull()
        when(element) {
            is List<*> -> {
                var jArray = JsonArray()
                element.forEach {
                    jArray.add(it)
                }
                return jArray
            }
            is String -> return JsonString(element)
            is Number, Int, Double, Float, Long, Short -> return JsonNumber(element as Number)
            is Boolean -> return JsonBoolean(element)
            is Map<*, Any?> -> return JsonMap(element.entries.firstOrNull()?.key.toString(), getMapValue(element.entries.firstOrNull()?.value))
            else -> {
                var jObject = JsonObject()
                jObject.add(element)
                return jObject
            }
        }
        return JsonNull()
    }
}