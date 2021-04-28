
class JsonObject : JsonValue() {

    var jsonObject: MutableList<JsonMap> = mutableListOf()

    fun add (key: String, value: JsonValue) {
        jsonObject.add(JsonMap(key, value))
    }

    override fun getValue(): String {
        var str = ""
        jsonObject.forEach {
            str += it.value.getValue().toString()+", "
        }
        return str.dropLast(2)
    }

    override fun print(ident: Int) {

        var counter = 1
        println("{")
        jsonObject.forEach {
            for (i in 0..ident) print("\t")
            it.print(ident+1)
            if(counter++ != jsonObject.size)
                println(", ")
            else
                println()
        }
        for (i in 1..ident) print("\t")
        print("}")
    }
}