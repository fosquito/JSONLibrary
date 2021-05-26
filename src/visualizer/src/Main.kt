
fun main() {
//    val batters: MutableList<Batter> = mutableListOf(Batter(1001, "Regular"), Batter(1002, "Chocolate"), Batter(1003, "Blueberry"))
//    val toppings: MutableList<Topping> = mutableListOf(Topping(5001, "None"), Topping(5002, "Glazed"), Topping(5005, "Sugar"), Topping(5007, "Powered Sugar"))
//    val donut =  Json(Confectionery(1, "Donut", "Cake", 0.55, false, null, batters, toppings))
//    Injector.create(JsonTreeSkeleton::class).open(donut)

    var colors = Json(mutableListOf(Color("red", "#f00"), Color("green", "#0f0"), Color("blue", "#00f")))
    Injector.create(JsonTreeSkeleton::class).open(colors)
}

class Color(var color: String, var value: String)

class Batter (var id: Int, var type: String)
class Topping(var id: Int, var type: String)
class Confectionery (var id: Int, var type: String, var name: String, var ppu: Number, isNew: Boolean, author: String?, var batters: MutableList<Batter>, var topping: MutableList<Topping>)
