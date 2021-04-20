
fun main() {
    val string1: JsonString = JsonString("a")
    val string2: JsonString = JsonString("129")
    val string3: JsonString = JsonString("Polaroid")

    val number1: JsonNumber = JsonNumber(0)
    val number2: JsonNumber = JsonNumber(51)
    val number3: JsonNumber = JsonNumber(1.34)

    var object1: JsonObject = JsonObject()
    var object2: JsonObject = JsonObject()

    var array1: JsonArray = JsonArray()
    var array2: JsonArray = JsonArray()

    var trueBool: JsonBoolean = JsonBoolean(true)
    var falseBool: JsonBoolean = JsonBoolean(false)

    var jNull: JsonNull = JsonNull()

    var json: Json = Json()
    json.add(string1)
    json.add(number3)
    json.add(object1)
    json.add(array1)
    json.add(trueBool)
    json.add(jNull)

    json.print
}