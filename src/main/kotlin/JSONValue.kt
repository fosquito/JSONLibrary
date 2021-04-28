import kotlin.reflect.KClass

abstract class JsonValue {

    abstract fun getValue(): Any?
    abstract fun print(ident: Int = 0)

    open fun <T:Any> accept(v: Visitor, jClass: KClass<T>? = null) {
        v.visit(this, jClass)
    }
}