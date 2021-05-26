
interface Visitor {
    fun visit(j: JsonObject)
    fun visit(j: JsonMap)
    fun visit(j: JsonArray)
    fun visit(j: JsonValue)
}

class Serialize : Visitor {

    var str = ""

    override fun visit(j: JsonObject) {
        println("{")
        str += "{\n"
        j.jsonObject.forEach {
            it.accept(this)
            if(!j.isLast(it)) {
                println(",")
                str += ",\n"
            }
        }
        println()
        println("}")
        str += "}\n"
    }

    override fun visit(j: JsonMap) {
        print(j.getKey()+": ")
        str += j.getKey()+": "
        j.value.accept(this)
    }

    override fun visit(j: JsonArray) {
        print("[")
        str += "["
        j.value.forEach {
            it.accept(this)
            if (!j.isLast(it)){
                print(", ")
                str += ", "
            }
        }
        println("]")
        str += "]\n"
    }

    override fun visit(j: JsonValue) {
        print(j.getValue())
        str += j.getValue()
    }
}

class Search(var text: String) : Visitor {

    var searchResponse: MutableList<JsonValue> = mutableListOf()

    override fun visit(j: JsonObject) {
        j.jsonObject.forEach {
            it.accept(this)
        }
    }

    override fun visit(j: JsonMap) {
        if (j.getKey().contains(text) || j.value.getValue().toString().contains(text)){
            searchResponse.add(j)
        }
        if(j.value is JsonObject || j.value is JsonArray){
            j.value.accept(this)
        }
    }

    override fun visit(j: JsonArray) {
        j.value.forEach {
            it.accept(this)
        }
    }

    override fun visit(j: JsonValue) {
        if(j.getValue().toString().contains(text)){
            searchResponse.add(j)
        }
    }

}
