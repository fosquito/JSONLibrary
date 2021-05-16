
interface Visitor {
    fun visit(j: JsonObject)
    fun visit(j: JsonMap)
    fun visit(j: JsonArray)
    fun visit(j: JsonValue)
}

class Serialize : Visitor {

    override fun visit(j: JsonObject) {
        println("{")
        j.jsonObject.forEach {
            it.accept(this)
            if(!j.isLast(it))
                println(",")
        }
        println()
        println("}")
    }

    override fun visit(j: JsonMap) {
        print(j.getKey()+": ")
        j.value.accept(this)
    }

    override fun visit(j: JsonArray) {
        print("[")
        j.value.forEach {
            it.accept(this)
            if(!j.isLast(it))
                print(", ")
        }
        println("]")
    }

    override fun visit(j: JsonValue) {
        print(j.getValue())
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
