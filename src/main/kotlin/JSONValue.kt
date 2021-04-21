
abstract class JsonValue {

    abstract fun print()

    abstract fun accept(v: Visitor)
}