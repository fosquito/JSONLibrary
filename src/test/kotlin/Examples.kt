
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

    val json = Json()

    var jObject = JsonObject()
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

    return json
}

fun example2(): Json {

    /*{
        "id": "0001",
        "type": "donut",
        "name": "Cake",
        "ppu": 0.55,
        "batters": {
            "batter": [ {
                "id": "1001",
                "type": "Regular"
            },
            {
                "id": "1002",
                "type": "Chocolate"
            },
            {
                "id": "1003",
                "type": "Blueberry"
            } ]
        },
        "topping": [ {
            "id": "5001",
            "type": "None"
        },
        {
            "id": "5002",
            "type": "Glazed"
        },
        {
            "id": "5005",
            "type": "Sugar"
        },
        {
            "id": "5007",
            "type": "Powdered Sugar"
        },
        {
            "id": "5006",
            "type": "Chocolate with Sprinkles"
        } ]
    }*/

    val json = Json()
    var jArray = JsonArray()
    var jObject1 = JsonObject()
    val jObject2 = JsonObject()

    jObject2.add("id", JsonString("0001"))
    jObject2.add("type", JsonString("donut"))
    jObject2.add("name", JsonString("Cake"))
    jObject2.add("ppu", JsonNumber(0.55))

    jObject1.add("id", JsonString("1001"))
    jObject1.add("type", JsonString("Regular"))
    jArray.add(jObject1)

    jObject1 = JsonObject()
    jObject1.add("id", JsonString("1002"))
    jObject1.add("type", JsonString("Chocolate"))
    jArray.add(jObject1)

    jObject1 = JsonObject()
    jObject1.add("id", JsonString("1003"))
    jObject1.add("type", JsonString("Blueberry"))
    jArray.add(jObject1)

    jObject1 = JsonObject()
    jObject1.add("batter", jArray)
    jObject2.add("batters", jObject1)

    jObject1 = JsonObject()
    jArray = JsonArray()
    jObject1.add("id", JsonString("5001"))
    jObject1.add("type", JsonString("None"))
    jArray.add(jObject1)

    jObject1 = JsonObject()
    jObject1.add("id", JsonString("5002"))
    jObject1.add("type", JsonString("Glazed"))
    jArray.add(jObject1)

    jObject2.add("topping", jArray)
    json.add(jObject2)

    return json
}