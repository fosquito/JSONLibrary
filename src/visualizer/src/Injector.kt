import java.io.File
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation
import kotlin.reflect.jvm.isAccessible

@Target(AnnotationTarget.PROPERTY)
annotation class Inject

@Target(AnnotationTarget.PROPERTY)
annotation class InjectAdd


class Injector {

    companion object {
        val map: MutableMap<String, List<KClass<*>>> = mutableMapOf()

        init {
            val scanner = Scanner(File("di.properties"))
            while(scanner.hasNextLine()) {
                val line = scanner.nextLine()
                val parts = line.split("=")
                map[parts[0]] = parts[1].split(",")
                    .map { Class.forName(it).kotlin }
            }
            scanner.close()
        }

        fun <T:Any> create(type: KClass<T>) : T {
            val o =  type.createInstance()
            type.declaredMemberProperties.forEach {
                if(it.hasAnnotation<Inject>()) {
                    it.isAccessible = true
                    val key = it.name
                    val obj = map[key]!!.first().createInstance()
                    (it as KMutableProperty<*>).setter.call(o, obj)
                }
                else if(it.hasAnnotation<InjectAdd>()) {
                    val key = it.name
                    val obj = map[key]!!.map { a -> a.createInstance() }
                    (it.getter.call(o) as MutableList<Any>).addAll(obj)
                }
            }
            return o
        }
    }

}