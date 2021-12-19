open class Item(var length:Int, var width:Int, val value:Int) {

    fun getArea(): Int {
        return length * width
    }
}