import org.junit.Assert.*
import org.junit.Test
import org.netology_exceptions.CardsType
import org.netology_exceptions.calculateTax

class MainKtTest {

    @Test
    fun calculateTaxMasterFreeCardTest() {
        val card = CardsType.MC
        val trasfer = 300
        val monthTransfer = 0
        val isVk = false
        val isPromo = true
        val result = calculateTax(card, trasfer, isVk, isPromo)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateTaxMasterFeeCardTest() {
        val card = CardsType.MC
        val trasfer = 200
        val monthTransfer = 0
        val isVk = false
        val isPromo = true
        val result = calculateTax(card, trasfer, isVk, isPromo)
        assertEquals(32.0, result, 0.0)
    }

    @Test
    fun calculateTaxForVisaTest() {
        val card = CardsType.VISA
        val trasfer = 1000
        val monthTransfer = 0
        val isVk = false
        val isPromo = false
        val result = calculateTax(card, trasfer, isVk, isPromo)
        assertEquals(75.00, result, 0.0)
    }

    @Test
    fun calculateTaxForMirTest() {
        val card = CardsType.MIR
        val trasfer = 100
        val monthTransfer = 0
        val isVk = false
        val isPromo = false
        val result = calculateTax(card, trasfer, isVk, isPromo)
        assertEquals(35.00, result, 35.00)
    }

    @Test
    fun calculateTaxForFeeMATest() {
        val card = CardsType.MA
        val trasfer = 10000
        val monthTransfer = 0
        val isVk = false
        val isPromo = false
        val result = calculateTax(card, trasfer, isVk, isPromo)
        assertEquals(620.00, result, 0.0)
    }

    @Test
    fun calculateTaxForFreeMATest() {
        val card = CardsType.MA
        val trasfer = 10000
        val monthTransfer = 0
        val isVk = false
        val isPromo = true
        val result = calculateTax(card, trasfer, isVk, isPromo)
        assertEquals(0.0, result, 0.0)
    }

    fun calculateTaxForDefaultParamsMATest() {
        val card = CardsType.MA
        val trasfer = 10000
        val monthTransfer = 0
        val isVk = false
        val isPromo = true
        val result = calculateTax(card, trasfer, isVk, isPromo)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateTaxForVKPayTest() {
        val card = CardsType.MA
        val trasfer = 10000
        val monthTransfer = 0
        val isVk = true
        val isPromo = false
        val result = calculateTax(card, trasfer, isVk, isPromo)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateTaxForDayLimitsTest() {
        val card = CardsType.MA
        val trasfer = 200_000
        val monthTransfer = 0
        val isVk = false
        val isPromo = false
        val result = calculateTax(card, trasfer, isVk, isPromo, monthTransfer)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateTaxForMonthLimitsTest() {
        val card = CardsType.MA
        val trasfer = 300
        val monthTransfer = 650_000
        val isVk = false
        val isPromo = false
        val result = calculateTax(card, trasfer, isVk, isPromo, monthTransfer)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateTaxForVkMonthLimitsTest() {
        val card = CardsType.MA
        val trasfer = 300
        val monthTransfer = 45_000
        val isVk = true
        val isPromo = false
        val result = calculateTax(card, trasfer, isVk, isPromo, monthTransfer)
        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calculateTaxForVkDayLimitsTest() {
        val card = CardsType.MA
        val trasfer = 20_000
        val monthTransfer = 0
        val isVk = true
        val isPromo = true
        val result = calculateTax(card, trasfer, isVk, isPromo, monthTransfer )
        assertEquals(0.0, result, 0.0)
    }
}