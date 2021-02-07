package PushYourLuck

import StatelessSolver
import kotlin.random.Random

class DiceClass(val nDice: Int,
                val nSides: Int,
                var markedSides: MutableList<MutableList<Boolean>> = Array(nDice){ i -> Array(nSides){i -> false}.toMutableList() }.toMutableList(),
                var cumReward: Double = 0.0){

    init {
        println("Initializing dice!")


    }
    val diceConfig = Array(nDice){ i -> Array(nSides){i -> i + 1}.toList() }.toList()

    fun roll(): MutableList<MutableList<Boolean>> {
        // For each dice generate a random integer between (including) 0 to nSides-1
        var diceRollResults = mutableListOf<Int>()
        for (d in markedSides) {
            diceRollResults.add(Random.nextInt(0, nSides-1))
            // Use -1 to represent unmarking
        }


        for (d in markedSides.indices) {
            var rollInd = diceRollResults[d]
            if (diceRollResults.filter{it == 1}.size > 1){
                markedSides[d].set(rollInd, false)
            } else {
                markedSides[d].set(rollInd, !markedSides[d][rollInd])
            }

        }
        return markedSides
    }

    fun cashOut(): Double {
        for (d_ind in 0 until diceConfig.size){
            if (markedSides[d_ind] != Array(nSides){i -> false}.toMutableList() ){
                var runningSum = 1.0
                for (s_ind in 0 until diceConfig[d_ind].size){
                    if (markedSides[d_ind][s_ind]){
                        runningSum *= diceConfig[d_ind][s_ind]
                    }
                }
                cumReward += runningSum
            }
        }
        return cumReward
    }

}
