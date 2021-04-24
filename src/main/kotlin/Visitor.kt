interface VisitorI {

    fun visit(a: JsonArray)
    fun visit(b: JsonBoolean)
    fun visit(j: Json): Boolean
    fun visit(n: JsonNull)
    fun visit(n: JsonNumber)
    //fun visit(o: JsonObject): Boolean
    fun visit(o: JsonObject)
    fun visit(s: JsonString)
}

class Visitor : VisitorI {

    var jsonData: MutableList<JsonValue> = mutableListOf()

    override fun visit(a: JsonArray) {
        jsonData.add(a)
    }

    override fun visit(b: JsonBoolean) {
        jsonData.add(b)
    }

    override fun visit(j: Json): Boolean {
        return j.elements.isNotEmpty()
    }

    override fun visit(n: JsonNull) {
        jsonData.add(n)
    }

    override fun visit(n: JsonNumber) {
        jsonData.add(n)
    }

    /*override fun visit(o: JsonObject): Boolean {
        return o.jsonObject.isNotEmpty()
    }*/

    override fun visit(o: JsonObject) {
        jsonData.add(o)
    }

    override fun visit(s: JsonString) {
        jsonData.add(s)
    }
}