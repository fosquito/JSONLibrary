
interface Visitor {
    fun visit(j: JsonValue)
}

class Parser : Visitor {

    override fun visit(j: JsonValue) {
        if(j is JsonObject)
            j.accept(this)
        else if(j is JsonArray) {
            val jArr: JsonArray = j
            jArr.value.forEach {
                if(it is JsonArray || it is JsonObject)
                    it.accept(this)
            }
        }
    }
}
