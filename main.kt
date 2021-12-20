import java.lang.Math.max

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
            println("One TC")
            items.add(
                TC(3, 5, 5, 1)
            )
        }

        // Large Box
        for (i in 1..numLBoxes!!) {
            println("$numLBoxes Lboxes")
            items.add(
                Box(5, 8, 15, i, large = true)
            )
        }

        // Small Box
        for (i in 1..numSBoxes!!) {
            println("$numSBoxes Sboxes")
            items.add(
                Box(2, 4, 8, i, large = false)
            )
        }

        // Furnaces
        for (i in 1..numFurnaces!!) {
            println("$numFurnaces Furn")
            items.add(
                Furnace(3, 3, 9, i)
            )
        }

        // Workbench
        if (!workbench.isNullOrBlank()) {
            println("One WB")
            items.add(
                Workbench(2, 8, 6, 1)
            )
        }

        /* Knapsack Algorithm */
        // Initialize array of all 0's for dynamic programming
        val dpArray: Array<IntArray> = Array(items.size) { IntArray(total) { 0 } }

        var currItem: Item?
        var currItemArea: Int

        println("dpArray.size = ${dpArray.indices}, dpArray[0].size = ${dpArray[0].size}")

        for (row in dpArray.indices) {
            for (col in 1 until dpArray[0].size) {
                // Normal Case
                if (row != 0) {
                    currItem = items[row] as Item
                    currItemArea = currItem.getArea()
                    if (currItemArea <= col){
//                        dpArray[row-1][col] < currItem.value + dpArray[row-1][col-currItemArea]
                        dpArray[row][col] =
                            (currItem.value + dpArray[row - 1][col - currItemArea])
                            .coerceAtLeast(dpArray[row - 1][col])
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

        /* Determining what items are in the floor plan */
        var col: Int = dpArray[0].size - 1
        var row: Int = dpArray.size - 1
        var itemToBeCast:Item

        while (col >= 0 && row >= 0) {
            if (dpArray[row][col] == 0) break
            if ( row == 0 || dpArray[row][col] > dpArray[row-1][col]) {
                when (items[row]) {
                    is TC -> (items[row] as TC).getName()
                    is Box -> (items[row] as Box).getName()
                    is Furnace -> (items[row] as Furnace).getName()
                    else -> (items[row] as Workbench).getName()
                }
                col -= (items[row] as Item).getArea()
            }
            row -= 1
        }
        println("Thanks you!")
    }
}