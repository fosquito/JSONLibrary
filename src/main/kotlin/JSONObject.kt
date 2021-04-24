
class JsonObject : JsonValue() {

    var jsonObject: MutableMap<String, JsonValue> = mutableMapOf()

    fun add (key: String, value: JsonValue) {
        jsonObject[key] = value
    }

    fun getValue(key: JsonString): JsonValue? {
        return jsonObject[key]
    }

    override fun print() {

        fun printTab(tabs: Int, jObj: JsonObject){
            var counter: Int = 1
            println("{")
            jObj.jsonObject.forEach {
                for (i in 0..tabs) print("\t")
                print("\""+it.key+"\": ")
                when(it.value) {
                    is JsonObject -> printTab(tabs+1, it.value as JsonObject)
                    else -> it.value.print()
                }
                if(counter++ != jObj.jsonObject.size)
                    println(", ")
                else
                    println()
            }
            for (i in 1..tabs) print("\t")
            print("}")
        }

        printTab(0, this)
    }

    override fun accept(v: Visitor) {
        /*if(v.visit(this))
            jsonObject.forEach{
                it.key.accept(v)
                it.value.accept(v)
            }*/
        v.visit(this)
    }
}