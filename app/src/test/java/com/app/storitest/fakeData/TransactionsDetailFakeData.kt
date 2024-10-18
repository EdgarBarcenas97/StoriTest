package com.app.storitest.fakeData

import com.app.storitest.data.models.TransactionDetailFirestore
import com.app.storitest.domain.models.TRANSACTION_AMOUNT
import com.app.storitest.domain.models.TRANSACTION_DATE
import com.app.storitest.domain.models.TRANSACTION_ID
import com.app.storitest.domain.models.TRANSACTION_NAME
import com.app.storitest.domain.models.Transaction
import com.app.storitest.domain.models.TransactionDetail
import com.app.storitest.ui.features.home.data.TransactionDetailUi
import com.app.storitest.ui.features.home.data.TransactionUi

const val ANY_TRANSACTIONS_ID = "MaKNASzR0ySOFA99PS4ASW5rpX2"

const val ANY_TRANSACTION_ID_1 = "MaKNASzR0ySOFA99PS4ASW5rpX2"
const val ANY_TRANSACTION_NAME_1 = "Rappi"
const val ANY_TRANSACTION_DATE_1 = "14 feb 2024, 15:20"
const val ANY_TRANSACTION_AMOUNT_1 = "$120.00"

const val ANY_TRANSACTION_ID_2 = "MaKNASzR0ySOFA99PS4IFASW5rpX3"
const val ANY_TRANSACTION_NAME_2 = "Mercado Libre"
const val ANY_TRANSACTION_DATE_2 = "15 feb 2024, 16:25"
const val ANY_TRANSACTION_AMOUNT_2 = "$560.00"

const val ANY_TRANSACTION_CARD = "**** 0490"
const val ANY_TRANSACTION_REFERENCE = "2131231231232131"
const val ANY_TRANSACTION_CATEGORY = "Food"

fun givenMovementsResponseArray() = arrayListOf(givenTransactionMap1(), givenTransactionMap2())

private fun givenTransactionMap1() = mapOf(
    TRANSACTION_ID to ANY_TRANSACTION_ID_1,
    TRANSACTION_NAME to ANY_TRANSACTION_NAME_1,
    TRANSACTION_DATE to ANY_TRANSACTION_DATE_1,
    TRANSACTION_AMOUNT to ANY_TRANSACTION_AMOUNT_1)

private fun givenTransactionMap2() = mapOf(
    TRANSACTION_ID to ANY_TRANSACTION_ID_2,
    TRANSACTION_NAME to ANY_TRANSACTION_NAME_2,
    TRANSACTION_DATE to ANY_TRANSACTION_DATE_2,
    TRANSACTION_AMOUNT to ANY_TRANSACTION_AMOUNT_2)

fun givenTransactionList() = listOf(givenTransaction1(), givenTransaction2())

private fun givenTransaction1() = Transaction(
    id = ANY_TRANSACTION_ID_1,
    name = ANY_TRANSACTION_NAME_1,
    date = ANY_TRANSACTION_DATE_1,
    amount = ANY_TRANSACTION_AMOUNT_1)

private fun givenTransaction2() = Transaction(
    id = ANY_TRANSACTION_ID_2,
    name = ANY_TRANSACTION_NAME_2,
    date = ANY_TRANSACTION_DATE_2,
    amount = ANY_TRANSACTION_AMOUNT_2)

fun givenTransactionDetailResponse() = TransactionDetailFirestore(
    name = ANY_TRANSACTION_NAME_1,
    date = ANY_TRANSACTION_DATE_1,
    amount = ANY_TRANSACTION_AMOUNT_1,
    card = ANY_TRANSACTION_CARD,
    reference = ANY_TRANSACTION_REFERENCE,
    category = ANY_TRANSACTION_CATEGORY)

fun givenTransactionDetail() = TransactionDetail(
    name = ANY_TRANSACTION_NAME_1,
    date = ANY_TRANSACTION_DATE_1,
    amount = ANY_TRANSACTION_AMOUNT_1,
    card = ANY_TRANSACTION_CARD,
    reference = ANY_TRANSACTION_REFERENCE,
    category = ANY_TRANSACTION_CATEGORY)

fun givenTransactionUiList() = listOf(givenTransactionUi1(), givenTransactionUi2())

private fun givenTransactionUi1() = TransactionUi(
    id = ANY_TRANSACTION_ID_1,
    name = ANY_TRANSACTION_NAME_1,
    date = ANY_TRANSACTION_DATE_1,
    amount = ANY_TRANSACTION_AMOUNT_1)

private fun givenTransactionUi2() = TransactionUi(
    id = ANY_TRANSACTION_ID_2,
    name = ANY_TRANSACTION_NAME_2,
    date = ANY_TRANSACTION_DATE_2,
    amount = ANY_TRANSACTION_AMOUNT_2)

fun givenTransactionDetailUi() = TransactionDetailUi(
    name = ANY_TRANSACTION_NAME_1,
    date = ANY_TRANSACTION_DATE_1,
    amount = ANY_TRANSACTION_AMOUNT_1,
    card = ANY_TRANSACTION_CARD,
    reference = ANY_TRANSACTION_REFERENCE,
    category = ANY_TRANSACTION_CATEGORY)
