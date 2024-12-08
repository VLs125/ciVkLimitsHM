package org.netology_exceptions

fun main() {
}

const val DAY_LIMIT = 150_000
const val MONTH_LIMIT = 600_000
const val VK_PAY_DAY_LIMIT = 15_000
const val VK_PAY_MONTH_LIMIT = 40_000
const val VISA_AND_MIR_BASE_PERCENT_TAX = 0.075
const val MC_AND_ME_BASE_PERCENT_TAX = 0.06
const val MC_AND_ME_BASE_TAX_ADDS = 20
const val VISA_AND_MIR_MIN_TAX = 35


fun calculateTax(
    cardsType: CardsType,
    transferAmount: Int,
    isVKPayAccount: Boolean,
    isPromo: Boolean,
    prevTransfersAmount: Int = 0
): Double {

    val taxesAmount = when (cardsType) {
        CardsType.MC -> calculateTaxMCAndMA(transferAmount, prevTransfersAmount, isPromo, isVKPayAccount)
        CardsType.MA -> calculateTaxMCAndMA(transferAmount, prevTransfersAmount, isPromo, isVKPayAccount)
        CardsType.VISA -> calculateTaxVisaAndMir(transferAmount, prevTransfersAmount, isVKPayAccount)
        CardsType.MIR -> calculateTaxVisaAndMir(transferAmount, prevTransfersAmount, isVKPayAccount)
    }
    return taxesAmount
}

private fun calculateTaxMCAndMA(
    transferAmount: Int,
    prevTransfersAmount: Int,
    isPromo: Boolean,
    isVKPayAccount: Boolean
): Double {
    return if (checkLimits(transferAmount, prevTransfersAmount, isVKPayAccount)) {
        0.0
    } else if (isVKPayAccount) {
        return 0.0
    } else if (transferAmount in (300..75000) && isPromo) {
        0.0
    } else {
        (transferAmount * MC_AND_ME_BASE_PERCENT_TAX) + MC_AND_ME_BASE_TAX_ADDS
    }
}

private fun calculateTaxVisaAndMir(transferAmount: Int, prevTransfersAmount: Int, isVKpayAccount: Boolean): Double {
    if (checkLimits(transferAmount, prevTransfersAmount, isVKpayAccount)) {
        return 0.0
    } else if (isVKpayAccount) {
        return 0.0
    } else {
        val taxAmount = transferAmount * VISA_AND_MIR_BASE_PERCENT_TAX
        return if (taxAmount < VISA_AND_MIR_MIN_TAX) VISA_AND_MIR_BASE_PERCENT_TAX else taxAmount
    }
}

private fun checkLimits(transferAmount: Int, prevTransfersAmount: Int, isVKpayAccount: Boolean): Boolean {
    val sumTransfers = transferAmount + prevTransfersAmount
    return if (isVKpayAccount) {
        if (transferAmount >= VK_PAY_DAY_LIMIT) {
            true
        } else if (sumTransfers >= VK_PAY_MONTH_LIMIT) {
            true
        } else false
    } else if (transferAmount >= DAY_LIMIT) {
        true
    } else if (sumTransfers >= MONTH_LIMIT) {
        true
    } else false
}

enum class CardsType {
    MIR, VISA, MC, MA
}