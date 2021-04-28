
class JsonArray : JsonValue() {

    var value: MutableList<JsonValue> = mutableListOf()

    override fun getValue(): String {
        var str = ""
        value.forEach {
            str += it.getValue().toString()+", "
        }
        return str.dropLast(2)
    }

    fun add(element: JsonValue) {
        value.add(element)
    }

    override fun print(ident: Int) {
        println("[")
        var counter = 1
        value.forEach {
            for (i in 0..ident) print("\t")
            it.print(ident+1)
            if(counter++ != value.size)
                print(", ")
            else
                println()
        }
        for (i in 1..ident) print("\t")
        print("]")
    }
}