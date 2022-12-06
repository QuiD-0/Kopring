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
}