import kotlin.reflect.KClass

interface VisitorI {
    fun <T: Any> visit(json: Json, jClass: KClass<T>?): Boolean
    fun <T: Any> visit(obj: JsonObject, jClass: KClass<T>?)
    fun <T: Any> visit(j: JsonValue, jClass: KClass<T>?, key: String? = null)
}

class Visitor : VisitorI {

    var jsonData: MutableList<JsonValue> = mutableListOf()

    override fun <T: Any> visit(json: Json, jClass: KClass<T>?): Boolean {
        return json.elements.isNotEmpty()
    }

    override fun <T: Any> visit(obj: JsonObject, jClass: KClass<T>?) {
        if (obj.jsonObject.isEmpty()) return
        if(obj.javaClass.name.equals(jClass?.simpleName) || jClass == null)
            jsonData.add(obj)
        obj.jsonObject.forEach{
            visit(it.value, jClass, it.key)
        }
    }

    override fun <T: Any> visit(j: JsonValue, jClass: KClass<T>?, key: String?) {
        if(key == null) {
            if (j.javaClass.name.equals(jClass?.simpleName) || jClass == null)
                jsonData.add(j)
        }
        else{
            if(j.javaClass.name.equals(jClass?.simpleName) || jClass == null)
                jsonData.add(JsonMap(key, j))
        }
        if(j is JsonObject || j is JsonArray)
            j.accept(this, jClass)
    }
}
