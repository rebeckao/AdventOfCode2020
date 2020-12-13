class Day13 {
    fun earliestDeparture(startWaiting: Int, allBuses: String): Int {
        var shortestWaitingTime = -1
        var bestBus = 0
        val buses = allBuses.split(",").filter { !it.equals("x") }.map { it.toInt() }
        for (bus in buses) {
            val minutesToWait = bus - (startWaiting % bus)
            if (shortestWaitingTime == -1 || minutesToWait < shortestWaitingTime) {
                shortestWaitingTime = minutesToWait
                bestBus = bus
            }
        }
        return bestBus * shortestWaitingTime
    }
}