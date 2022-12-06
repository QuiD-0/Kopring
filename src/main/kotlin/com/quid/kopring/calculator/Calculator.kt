package com.quid.kopring.calculator

class Calculator (
    var num : Int
){
    fun add(operand : Int){
        this.num += operand
    }

    fun minus(operand: Int){
        this.num -= operand
    }

    fun multiply(operand: Int){
        this.num *= operand
    }

    fun divide(operand: Int){
        this.num /= operand
    }
}