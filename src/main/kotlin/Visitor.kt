import kotlin.reflect.KClass

interface VisitorI {
    fun visit(json: Json): Boolean
    fun visit(j: JsonValue)
}

class Visitor : VisitorI {

    var jsonData: MutableList<JsonValue> = mutableListOf()

    override fun visit(json: Json): Boolean {
        return json.elements.isNotEmpty()
    }

    override fun visit(j: JsonValue) {
        jsonData.add(j)
        if(j is JsonObject)
            j.accept(this)
        if(j is JsonArray) {
            val jArr: JsonArray = j as JsonArray
            jArr.value.forEach {
                if(it is JsonArray || it is JsonObject)
                    it.accept(this)
            }
        }

    }
}
