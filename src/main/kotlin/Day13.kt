import java.math.BigInteger
import kotlin.math.abs

class Day13 {
    fun earliestDeparture(startWaiting: Int, allBuses: String): Int {
        var shortestWaitingTime = -1
        var bestBus = 0
        val buses = allBuses.split(",").filter { it != "x" }.map { it.toInt() }
        for (bus in buses) {
            val minutesToWait = bus - (startWaiting % bus)
            if (shortestWaitingTime == -1 || minutesToWait < shortestWaitingTime) {
                shortestWaitingTime = minutesToWait
                bestBus = bus
            }
        }
        return bestBus * shortestWaitingTime
    }

    fun earliestTimestampForConsecutiveDepartures(allBuses: String): BigInteger {
        val busMinutesAndNumbers = allBuses.split(",").withIndex().filter { it.value != "x" }
            .map { Pair(it.index, it.value.toLong()) }
        val modEquations = busMinutesAndNumbers.map { Pair(it.second - it.first, it.second) }
        return chineseRemainderTheoremResult(modEquations)
    }

    /*
        The solution to x = a_1 mod n_1, x = a_2 mod n_2 ... x = a_k mod n_k
        modEquations: A list of pairs of a, n representing the equation x = a mod n
        returns x
     */
    private fun chineseRemainderTheoremResult(modEquations: List<Pair<Long, Long>>): BigInteger {
        val productOfAll = modEquations.map { it.second }.reduce { a, b -> a * b }
        return modEquations.map { chineseRemainderTheoremPartForSingleEquation(it.first, it.second, productOfAll) }
            .reduce { a, b -> a.plus(b) }
            .mod(BigInteger.valueOf(productOfAll))
    }

    fun chineseRemainderTheoremPartForSingleEquation(aRaw: Long, n: Long, N: Long): BigInteger {
        val a = positiveModulo(aRaw, n)
        if (a == 0L) {
            return BigInteger.ZERO
        }
        val productOfOthers = N / n
        val eulerPhi = eulerPhi(n)
        val b = b(productOfOthers, eulerPhi, n)
        if ((b * productOfOthers) % n != 1L) {
            throw IllegalStateException("b ($b) should be 1 mod n ($n)!")
        }
        return BigInteger.valueOf(a)
            .multiply(BigInteger.valueOf(b))
            .multiply(BigInteger.valueOf(N))
            .divide(BigInteger.valueOf(n))
    }

    private fun positiveModulo(possiblyNegative: Long, mod: Long) =
        ((possiblyNegative % mod) + mod) % mod

    fun b(productOfOthers: Long, eulerPhi: Int, n: Long): Long {
        return BigInteger.valueOf(productOfOthers)
            .pow(eulerPhi - 1)
            .mod(BigInteger.valueOf(n))
            .toLong()
    }

    fun eulerPhi(n: Long): Int {
        var relativePrimes = 0
        for (number in 1..n) {
            if (greatestCommonDivisorPreservingSign(number, n) == 1L) {
                relativePrimes++
            }
        }
        return relativePrimes
    }

    private fun greatestCommonDivisorPreservingSign(a: Long, b: Long): Long {
        return if (b == 0L) abs(a) else greatestCommonDivisorPreservingSign(b, a % b)
    }
}