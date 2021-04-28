
fun main() {
    val json = Json()
    var bool = JsonString("Kotlin")
    json.add(bool)

    val v = Visitor()
    json.accept(v, JsonString::class)

    json.print()
    v.jsonData.forEach {
        it.print()
    }
}