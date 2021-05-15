fun example1(): Json {

    class Color(var color: String, var value: String)

    /*[{
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
    }]*/

    return Json(mutableListOf(Color("red", "#f00"), Color("green", "#0f0"), Color("blue", "#00f")))
}

fun example2(): Json {
    class Batter (var id: Int, var type: String)
    class Topping(var id: Int, var type: String)
    class Confectionery (var id: Int, var type: String, var name: String, var ppu: Number, isNew: Boolean, author: String?, var batters: MutableList<Batter>, var topping: MutableList<Topping>)

    /*{
        "id": 0001,
        "type": "Donut",
        "name": "Cake",
        "ppu": 0.55,
        "isNew": false,
        "author": null
        "batters": [
            {
                "id": 1001,
                "type": "Regular"
            },
            {
                "id": 1002,
                "type": "Chocolate"
            },
            {
                "id": 1003,
                "type": "Blueberry"
            }
         ],
        "topping": [ {
            "id": 5001,
            "type": "None"
        },
        {
            "id": 5002,
            "type": "Glazed"
        },
        {
            "id": 5005,
            "type": "Sugar"
        },
        {
            "id": 5007,
            "type": "Powdered Sugar"
        }]
    }*/

    var batters: MutableList<Batter> = mutableListOf(Batter(1001, "Regular"), Batter(1002, "Chocolate"), Batter(1003, "Blueberry"))
    var toppings: MutableList<Topping> = mutableListOf(Topping(5001, "None"), Topping(5002, "Glazed"), Topping(5005, "Sugar"), Topping(5007, "Powered Sugar"))
    return Json(Confectionery(1, "Donut", "Cake", 0.55, false, null, batters, toppings))

}