import kotlin.reflect.KClass

abstract class JsonValue {

    abstract fun valueToString(): String
    abstract fun print()

    open fun <T:Any> accept(v: Visitor, jClass: KClass<T>? = null) {
        v.visit(this, jClass)
    }
}