class TC(length:Int, width:Int, value:Int, numItem:Int): Item(length, width, value) {
    val numItem = numItem

    fun getName(): String {
        return "TC #$numItem"
    }
}