package com.quid.kopring.calculator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CalculatorTest{

    @Test
    fun add_test(){
        val calculator = Calculator(10)
        calculator.add(5)
        assertEquals(15, calculator.num)
    }

    @Test
    fun minus_test(){
        val calculator = Calculator(10)
        calculator.minus(5)
        assertEquals(5, calculator.num)
    }

    @Test
    fun multiply_test(){
        val calculator = Calculator(10)
        calculator.multiply(5)
        assertEquals(50, calculator.num)
    }

    @Test
    fun divide_test(){
        val calculator = Calculator(10)
        calculator.divide(5)
        assertEquals(2, calculator.num)
    }
}