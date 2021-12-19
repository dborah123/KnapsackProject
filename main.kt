/*

 */

fun getDimensions(): Pair<Int, Int> {
    var input: Array<String>?

    println("Please enter in the dimensions of your space (format: '[length]x[width]')")

    while (true) {
        print("Enter: ")
        input = readLine()?.split("x")?.toTypedArray()

        if (input != null && input.size == 2) {
            break
        }
        println("Error in formatting (format: '[length]x[width]')...Please input again")
    }

    return Pair(input!![0].toInt(), input!![1].toInt())
}

fun main() {
    var runAgain = true
    var TC: String?
    var workbench: String?

    var numLBoxes: Int?
    var numSBoxes: Int?
    var numFurnaces: Int?

    while (runAgain){
        /* Get Dimensions From User */
        val dimensions = getDimensions()

        /* Get TC */
        while (true) {
            println("Does your floor plan include TC's? y or n")
            print("Enter: ")

            TC = readLine()

            if (TC == null || TC.length != 1){
                print("Please enter y or n" )
            } else {
                break
            }
        }

        /* Get number of large boxes */
        println("How many large boxes?")
        while (true) {
            print("Enter: ")

            numLBoxes = readLine()?.toInt()

            if (numLBoxes != null) {
                break
            } else {
                println("Please enter a valid number")
            }
        }

        /* Get number of small boxes */
        println("How many small boxes?")
        while (true) {
            print("Enter: ")

            numSBoxes = readLine()?.toInt()

            if (numSBoxes != null) {
                break
            } else {
                println("Please enter a valid number")
            }
        }

        /* Get number of furnaces */
        println("How many furnaces?")
        while (true) {
            print("Enter: ")

            numFurnaces = readLine()?.toInt()

            if (numFurnaces != null) {
                break
            } else {
                println("Please enter a valid number")
            }
        }

        /* Workbench */
        println("Workbench? y or n")
        while (true) {
            print("Enter: ")

            workbench = readLine()

            if (TC == null || TC.length != 1) {
                println("Please enter a valid number")
            } else {
                break
            }
        }

        /* Initializing Lists for objects */
        if (!TC.isNullOrBlank()) {
            val TCObj: TC = TC(3, 5, 10, 1)
        }

        // Large Box
        var LBoxList: MutableList<Box> = mutableListOf()
        for (i in 0..numLBoxes!!) LBoxList.add(Box(5, 8, 8, i, large = true))

        // Small Box
        var SBoxList: MutableList<Box> = mutableListOf()
        for (i in 0..numSBoxes!!) SBoxList.add(Box(2, 4, 4, i, large = false))

        // Furnaces
        var FurnaceList: MutableList<Furnace> = mutableListOf()
        for (i in 0..numFurnaces!!) FurnaceList.add(Furnace(3, 3, 5, i))

        // Workbench
        if (!workbench.isNullOrBlank()) {
            val WorkbenchObj: Workbench = Workbench(2, 8, 7, 1)
        }
    }
}