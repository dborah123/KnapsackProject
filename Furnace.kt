class Furnace(length:Int, width:Int, value:Int, numItem:Int): Item(length, width, value) {
    val numItem = numItem

    fun getName(): String {
        return "Furnace #$numItem"
    }
}