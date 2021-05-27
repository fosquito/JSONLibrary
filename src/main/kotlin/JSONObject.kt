import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.full.memberProperties

@Target(AnnotationTarget.PROPERTY)
annotation class Ignore

class JsonObject : JsonValue() {

    var jsonObject: MutableList<JsonMap> = mutableListOf()

    fun isLast(element: JsonMap): Boolean {
        return element == jsonObject.last()
    }

    fun add (element: Any) {
        element::class.memberProperties.forEach {
            if(it.hasAnnotation<Ignore>()){
                return
            }
            else if(it.getter.call(element) == null){
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
            else if (it.returnType.classifier == Number::class
                || it.returnType.classifier == Int::class
                || it.returnType.classifier == Double::class
                || it.returnType.classifier == Float::class
                || it.returnType.classifier == Long::class
                || it.returnType.classifier == Short::class){
                jsonObject.add(JsonMap(it.name, JsonNumber(it.getter.call(element) as Number)))
            }
            else if (it.returnType.classifier == Boolean::class){
                jsonObject.add(JsonMap(it.name, JsonBoolean(it.getter.call(element) as Boolean)))
            }
            else if (it.returnType.classifier == Map::class){
                var map: Map<*, Any?> = it.getter.call(element) as Map<*, Any?>
                var j = Json(null)
                jsonObject.add(JsonMap(it.name, JsonMap(map.entries.firstOrNull()?.key.toString(), j.getMapValue(map.entries.firstOrNull()?.value))))
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

    override fun accept(v: Visitor) {
        v.visit(this)
    }
}