
interface Visitor {
    fun visit(j: JsonObject)
    fun visit(j: JsonArray)
    fun visit(j: JsonValue)
}

class Serialize : Visitor {

    override fun visit(j: JsonObject) {
        println("{")
        j.jsonObject.forEach {
            print(it.getKey()+": ")
            it.value.accept(this)
            if(!j.isLast(it))
                println(",")
        }
        println()
        println("}")
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

class Search : Visitor {

    var searchResponse: MutableList<JsonValue> = mutableListOf()

    override fun visit(j: JsonObject) {
        searchResponse.add(j)
    }

    override fun visit(j: JsonArray) {
        searchResponse.add(j)
    }

    override fun visit(j: JsonValue) {
        searchResponse.add(j)
    }

}
