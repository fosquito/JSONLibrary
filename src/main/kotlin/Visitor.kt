interface VisitorI {

    fun visit(array: JsonArray, jClass: Any?)
    fun visit(bool: JsonBoolean, jClass: Any?)
    fun visit(json: Json, jClass: Any?): Boolean
    fun visit(n: JsonNull, jClass: Any?)
    fun visit(num: JsonNumber, jClass: Any?)
    fun visit(obj: JsonObject, jClass: Any?): Boolean
    //fun visit(o: JsonObject)
    fun visit(str: JsonString, jClass: Any?)
}

class Visitor : VisitorI {

    var jsonData: MutableList<JsonValue> = mutableListOf()

    override fun visit(array: JsonArray, jClass: Any?) {
        if (jClass is JsonArray)
            jsonData.add(array)
    }

    override fun visit(bool: JsonBoolean, jClass: Any?) {
        if(jClass is JsonBoolean)
            jsonData.add(bool)
    }

    override fun visit(json: Json, jClass: Any?): Boolean {
        return json.elements.isNotEmpty()
    }

    override fun visit(n: JsonNull, jClass: Any?) {
        if(jClass is JsonNull)
            jsonData.add(n)
    }

    override fun visit(num: JsonNumber, jClass: Any?) {
        jsonData.add(num)
    }

    override fun visit(obj: JsonObject, jClass: Any?): Boolean {
        if (obj.jsonObject.isEmpty())
            return false
        if(jClass == obj.javaClass.kotlin)
            jsonData.add(obj)
        return true
    }

    /*override fun visit(o: JsonObject) {
        jsonData.add(o)
    }*/

    override fun visit(str: JsonString, jClass: Any?) {
        if (jClass is JsonString)
            jsonData.add(str)
    }
}