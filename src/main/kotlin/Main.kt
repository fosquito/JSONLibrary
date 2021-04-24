
fun main() {
    var json: Json = Json()

    var jObject: JsonObject = JsonObject()
    jObject.add("color", JsonString("red"))
    jObject.add("value", JsonString("#f00"))
    json.add(jObject)

    jObject = JsonObject()
    jObject.add("color", JsonString("green"))
    jObject.add("value", JsonString("#0f0"))
    json.add(jObject)

    jObject = JsonObject()
    jObject.add("color", JsonString("blue"))
    jObject.add("value", JsonString("#00f"))
    json.add(jObject)

    jObject = JsonObject()
    jObject.add("color", JsonString("white"))
    jObject.add("value", JsonString("#fff"))
    json.add(jObject)

    jObject = JsonObject()
    jObject.add("color", JsonString("black"))
    jObject.add("value", JsonString("#000"))
    json.add(jObject)

    json.print()
}