package com.amonteiro.a03_kmp_mprolead_g1

import kotlin.test.Test
import kotlin.test.assertTrue

class GreetingTest {

    @Test
    fun greetingStartsWithHello() {
        val message = Greeting().greet()
        assertTrue(message.startsWith("Hello"), "Le message doit commencer par 'Hello', reçu : $message")
    }
}
