import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day25Test {

    @ParameterizedTest
    @CsvSource(
        "5764801,17807724,14897079",
        "13135480,8821721,8329514"
    )
    fun encryptionKey(cardPublicKey: Long, doorPublicKey: Long, expected: Long) {
        assertEquals(expected, Day25().encryptionKey(cardPublicKey, doorPublicKey))
    }
}