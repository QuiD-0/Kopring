package com.quid.kopring

import org.junit.jupiter.api.Test

class Test {

    @Test
    fun test() {
        val a = 1
        val b = mutableListOf(1, 2, 3)
        val c = b
        val d = b.stream().toList()
        println(System.identityHashCode(a))
        println(System.identityHashCode(b))
        println(System.identityHashCode(c))
        println(System.identityHashCode(d))
    }
}