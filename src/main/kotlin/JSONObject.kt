import kotlin.reflect.KClass

class JsonObject : JsonValue() {

    var jsonObject: MutableList<JsonMap> = mutableListOf()

    fun add (key: String, value: JsonValue) {
        jsonObject.add(JsonMap(key, value))
    }

    override fun valueToString(): String {
        var str = ""
        jsonObject.forEach {
            str += it.value.valueToString()+", "
        }
        return str.dropLast(2)
    }

    override fun print() {

        fun printTab(tabs: Int, jObj: JsonObject){
            var counter = 1
            println("{")
            jObj.jsonObject.forEach {
                for (i in 0..tabs) print("\t")
                it.print()
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

    override fun <T: Any> accept(v: Visitor, jClass: KClass<T>?) {
        v.visit(this, jClass)
    }
}