package coroutines.workshop.example

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking {

    suspend fun one(): Int {
        println("Finding the answer to the most important question in universe...")
        delay(1_000)
        println("... found!")
        return 42
    }

    suspend fun two(): Int {
        println("Finding the question...")
        throw ArithmeticException()
        delay(1_000)
        println("... asked!")
        return 1
    }

    try {
        coroutineScope {

            val one = async { one() }
            val two = async { two() }

            println(one.await() + two.await())

        }
    } catch (e: Exception) {
        println("Coroutines have thrown ${e::class.java}")
    }

}
