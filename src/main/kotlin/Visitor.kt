interface Visitor {

    fun visit(j: JsonValue): Boolean = true
}