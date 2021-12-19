class Box (length:Int, width:Int, value:Int, numItem:Int, contents:String = "", large: Boolean): Item(length, width, value){
    val numItem = numItem
    val contents = contents
    val large = large
    
    fun getName(): String {
        return "Large Box #$numItem"
    }
    
    fun getDetails(): String {
        if (large) return "Large Box #$numItem storing: $contents"
        return "Small Box #$numItem storing: $contents"
    }
}