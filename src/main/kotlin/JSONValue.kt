
abstract class JsonValue {

    abstract fun getValue(): Any?
    abstract fun print(ident: Int = 0)

    fun accept(v: Visitor) {
        v.visit(this)
    }
}