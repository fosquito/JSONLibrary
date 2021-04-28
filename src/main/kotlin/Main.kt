import kotlin.reflect.KClass

fun main() {
    val clazz : KClass<JsonString> = JsonString::class
    println(clazz.constructors.size)
}