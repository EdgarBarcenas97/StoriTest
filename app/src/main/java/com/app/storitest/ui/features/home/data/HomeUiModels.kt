package com.app.storitest.ui.features.home.data

import android.os.Parcelable
import com.app.storitest.domain.models.Transaction
import com.app.storitest.domain.models.TransactionDetail
import com.app.storitest.domain.models.User
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

data class UserUi(val firstName: String,
                  val lastName: String,
                  val email: String,
                  var picture: String,
                  val transactions: List<TransactionUi>)

@Serializable
@Parcelize
data class TransactionUi(val id: String,
                         val name: String,
                         val date: String,
                         val amount: String) : Parcelable

data class TransactionDetailUi(val name: String,
                               val date: String,
                               val amount: String,
                               val card: String,
                               val reference: String,
                               val category: String)

fun User.toUserUi() = UserUi(
    firstName = firstName,
    lastName = lastName,
    email = email,
    picture = picture,
    transactions = transactions.toTransactionUiList())

private fun List<Transaction>.toTransactionUiList() = map { it.toTransactionUi() }

private fun Transaction.toTransactionUi() = TransactionUi(
    id = id,
    name = name,
    date = date,
    amount = amount)

fun TransactionDetail.toTransactionDetailUi() = TransactionDetailUi(
    name = name,
    date = date,
    amount = amount,
    card = card,
    reference = reference,
    category = category)
