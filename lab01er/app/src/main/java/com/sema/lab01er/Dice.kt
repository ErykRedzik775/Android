package com.sema.lab01er;
import java.util.*
public class Dice(val numSides: Int = 6) {
    fun roll(isRangeRandom: Boolean = true): Int{
        if(isRangeRandom){
            return(1..numSides).random()
        }else{
            return Random().nextInt(numSides) + 1
        }
    }
}