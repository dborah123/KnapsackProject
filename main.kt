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
        println("How many large boxes do you have?")
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

        /* Preparing for knapsack algorithm */
        val total = dimensions.first * dimensions.first * 12
        val items = mutableListOf<Any>()

        //TC
        if (!TC.isNullOrBlank()) {
            items.add(
                TC(3, 5, 5, 1)
            )
        }

        // Large Box
        for (i in 0..numLBoxes!!) {
            items.add(
                Box(5, 8, 15, i, large = true)
            )
        }

        // Small Box
        for (i in 0..numSBoxes!!) {
            items.add(
                Box(2, 4, 8, i, large = false)
            )
        }

        // Furnaces
        for (i in 0..numFurnaces!!) {
            items.add(
                Furnace(3, 3, 9, i)
            )
        }

        // Workbench
        if (!workbench.isNullOrBlank()) {
            items.add(
                Workbench(2, 8, 6, 1)
            )
        }

        /* Knapsack Algorithm */
        // Initialize array of all 0's for dynamic programming
        val dpArray: Array<IntArray> = Array(items.size + 1) { IntArray(total + 1) { 0 } }
        var currItem: Item?
        var currItemArea: Int

        for (row in 0..dpArray.size) {
            for (col in 1..dpArray[row].size) {
                // Normal Case
                if (row != 0) {
                    currItem = items[0] as Item
                    currItemArea = currItem.getArea()
                    if (currItemArea + dpArray[row-1][col-currItemArea] <= col &&
                        dpArray[row-1][col] < currItemArea + dpArray[row-1][col-currItemArea]){
                        dpArray[row][col] = currItemArea + dpArray[row-1][col-currItemArea]
                    } else {
                        dpArray[row][col] = dpArray[row-1][col]
                    }

                // If on first row of array
                } else {
                    currItem = items[0] as Item
                    if (currItem.getArea() <= col) {
                        dpArray[row][col] = currItem.getArea()
                    }
                }
            }
        }

    }
}