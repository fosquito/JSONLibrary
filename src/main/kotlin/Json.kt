import kotlin.reflect.full.memberProperties

class Json {
    lateinit var json: JsonValue

    constructor(j: Any) {

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
            is String -> {
                json = JsonString(j)
            }
            is Number, Int, Double, Float, Long, Short -> {
                json = JsonNumber(j as Number)
            }
            is Boolean -> {
                json = JsonBoolean(j)
            }
            else -> {
                var jObject = JsonObject()
                jObject.add(j)
                json = jObject
            }
        }
    }
}