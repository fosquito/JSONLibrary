class JsonArray : JsonValue() {

    var value: MutableList<JsonValue> = mutableListOf()

    fun isLast(element: JsonValue): Boolean {
        return element == value.last()
    }

    override fun getValue(): String {
        var str = ""
        value.forEach {
            str += it.getValue().toString()+", "
        }
        return str.dropLast(2)
    }

    fun add(element: Any?) {
        if(element == null){
            value.add(JsonNull())
            return
        }
        when(element) {
            is List<*> -> {
                var jArray = JsonArray()
                element.forEach {
                    jArray.add(it)
                }
                value.add(jArray)
            }
            is String -> {
                value.add(JsonString(element))
            }
            is Number, Int, Double, Float, Long, Short -> {
                value.add(JsonNumber(element as Number))
            }
            is Boolean -> {
                value.add(JsonBoolean(element))
            }
            is Map<*, Any?> -> {
                var j = Json(null)
                value.add(JsonMap(element.entries.firstOrNull().toString(), j.getMapValue(element.entries.firstOrNull()?.value)))
            }
            else -> {
                var jObject = JsonObject()
                jObject.add(element)
                value.add(jObject)
            }
        }
    }

    override fun accept(v: Visitor) {
        v.visit(this)
    }
}