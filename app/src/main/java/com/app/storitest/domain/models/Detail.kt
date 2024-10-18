package com.app.storitest.domain.models

import com.app.storitest.data.models.TransactionDetailFirestore

data class Transaction(val id: String,
                       val name: String,
                       val date: String,
                       val amount: String)

data class TransactionDetail(val name: String,
                             val date: String,
                             val amount: String,
                             val card: String,
                             val reference: String,
                             val category: String)

fun ArrayList<Map<String, String>>?.toTransactionList() = this?.map { it.toTransaction() }.orEmpty()

private fun Map<String, String>.toTransaction() = Transaction(
    id = this[TRANSACTION_ID].orEmpty(),
    name = this[TRANSACTION_NAME].orEmpty(),
    date = this[TRANSACTION_DATE].orEmpty(),
    amount = this[TRANSACTION_AMOUNT].orEmpty())

fun Result<TransactionDetailFirestore?>.toTransactionDetail() = map { it.toTransactionDetail() }

private fun TransactionDetailFirestore?.toTransactionDetail() = TransactionDetail(
    name = this?.name.orEmpty(),
    date = this?.date.orEmpty(),
    amount = this?.amount.orEmpty(),
    card = this?.card.orEmpty(),
    reference = this?.reference.orEmpty(),
    category = this?.category.orEmpty()
)

const val TRANSACTION_ID = "id"
const val TRANSACTION_NAME = "name"
const val TRANSACTION_DATE = "date"
const val TRANSACTION_AMOUNT = "amount"