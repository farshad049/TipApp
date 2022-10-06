package com.example.tipapp.ui.util

fun calculateTipAmount(totalBill : Double, tipPercentage : Int):Double{
    return if (totalBill.toString().isNotEmpty()  && totalBill > 1 ){
        ((totalBill  * tipPercentage) / 100)
    }else{
        0.0
    }
}

fun calculateTipForEachPerson(totalBill : Double, tipPercentage : Int , splitBy : Int):Double{
    return (calculateTipAmount(totalBill  , tipPercentage) + totalBill) / splitBy

}