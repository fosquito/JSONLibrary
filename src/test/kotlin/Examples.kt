
fun example1(): Json{

    /*{
        color: "red",
        value: "#f00"
    },
    {
        color: "green",
        value: "#0f0"
    },
    {
        color: "blue",
        value: "#00f"
    },
    {
        color: "white",
        value: "#fff"
    },
    {
        color: "black",
        value: "#000"
    }*/

    var json: Json = Json()

    var jObject: JsonObject = JsonObject()
    jObject.add(JsonString("color"), JsonString("red"))
    jObject.add(JsonString("value"), JsonString("#f00"))
    json.add(jObject)

    jObject = JsonObject()
    jObject.add(JsonString("color"), JsonString("green"))
    jObject.add(JsonString("value"), JsonString("#0f0"))
    json.add(jObject)

    jObject = JsonObject()
    jObject.add(JsonString("color"), JsonString("blue"))
    jObject.add(JsonString("value"), JsonString("#00f"))
    json.add(jObject)

    jObject = JsonObject()
    jObject.add(JsonString("color"), JsonString("white"))
    jObject.add(JsonString("value"), JsonString("#fff"))
    json.add(jObject)

    jObject = JsonObject()
    jObject.add(JsonString("color"), JsonString("black"))
    jObject.add(JsonString("value"), JsonString("#000"))
    json.add(jObject)

    return json
}