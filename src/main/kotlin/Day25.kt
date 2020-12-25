class Day25 {
    fun encryptionKey(cardPublicKey: Long, doorPublicKey: Long): Long {
        val cardLoopSize = resolveLoopSize(cardPublicKey)
        var transformedNumber = 1L
        for (loop in 0 until cardLoopSize) {
            transformedNumber *= doorPublicKey
            transformedNumber %= 20201227L
        }
        val encryptionKey = transformedNumber
        return encryptionKey
    }

    private fun resolveLoopSize(publicKey: Long): Int {
        var transformedNumber = 1L
        var loop = 0
        while (transformedNumber != publicKey) {
            loop++
            transformedNumber *= 7
            transformedNumber %= 20201227L
        }
        return loop
    }

}