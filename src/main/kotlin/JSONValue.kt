
abstract class JsonValue {

    abstract fun getValue(): Any?

    open fun accept(v: Visitor) {
        v.visit(this)
    }
}