import org.junit.Test

import org.junit.Assert.*
import org.netology_exceptions.CardsType
import org.netology_exceptions.calculateTax

class MainKtTest {

    @Test
    fun calculateTax() {
        val card = CardsType.MC
        val trasfer = 300
        val monthTransfer = 0
        val isVk = false
        val isPromo = true
        val result = calculateTax(card, trasfer, monthTransfer, isVk, isPromo)
        assertEquals(0.0, result, 0.0)
    }
}