package com.app.storitest.fakeData

import com.app.storitest.core.extensions.empty
import com.app.storitest.data.models.UserFirestore
import com.app.storitest.domain.models.EMAIL_NAME
import com.app.storitest.domain.models.FIRST_NAME
import com.app.storitest.domain.models.LAST_NAME
import com.app.storitest.domain.models.PICTURE
import com.app.storitest.domain.models.User
import com.app.storitest.domain.models.UserRegister
import com.app.storitest.ui.features.auth.signup.models.UserRegisterUi
import com.app.storitest.ui.features.home.data.UserUi

const val ANY_USER_ID = "MaKNkzR0ySOFAd9PS4IFxnW5rpX2"
private const val ANY_FIRST_NAME = "Daniel"
private const val ANY_INVALID_FIRST_NAME = "koooe"
private const val ANY_LAST_NAME = "Barcenas"
private const val ANY_INVALID_LAST_NAME = "Mi"
const val ANY_USER_EMAIL = "edgar.barcenas@stori.mx"
const val ANY_INVALID_USER_EMAIL = "edgar.barcenas.mx"
const val ANY_PASSWORD = "Admin12345"
const val ANY_INVALID_PASSWORD = "Admi"
private const val ANY_OTHER_PASSWORD = "Admin123412"
const val ANY_PICTURE = "https://www.storicard.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fcredit-cards.767adfcc.webp&w=1920&q=100"
const val ANY_LOCAL_PICTURE_USER = "content://images/alexutemov/alexutemov1605/alexutemov160500480/temps.jpg"

fun givenUserMap() = mapOf(
    FIRST_NAME to ANY_FIRST_NAME,
    LAST_NAME to ANY_LAST_NAME,
    EMAIL_NAME to ANY_USER_EMAIL,
    PICTURE to ANY_PICTURE)

fun givenUserRegister() = UserRegister(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    picture = ANY_PICTURE)

fun givenUserRegisterWithLocalPictureUser() = UserRegister(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    picture = ANY_LOCAL_PICTURE_USER)

fun givenUserFirestore() = UserFirestore(
    firstName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    picture = ANY_LOCAL_PICTURE_USER,
    transactions = givenTransactionsResponseArray())

fun givenUser() = User(
    firstName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    picture = ANY_LOCAL_PICTURE_USER,
    transactions = givenTransactionList())

fun givenUserRegisterUi() = UserRegisterUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE)

fun givenUserRegisterUiWithInvalidPictureUser() = UserRegisterUi(
    fistName = ANY_INVALID_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = String.empty())

fun givenUserRegisterUiWithInvalidFirstName() = UserRegisterUi(
    fistName = ANY_INVALID_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE)

fun givenUserRegisterUiWithInvalidLastName() = UserRegisterUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_INVALID_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE)

fun givenUserRegisterUiWithInvalidEmail() = UserRegisterUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_INVALID_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE)

fun givenUserRegisterUiWithInvalidPassword() = UserRegisterUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_INVALID_PASSWORD,
    confirmPassword = ANY_PASSWORD,
    pictureIdentification = ANY_PICTURE)

fun givenUserRegisterUiWithInvalidConfirmPassword() = UserRegisterUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_INVALID_PASSWORD,
    pictureIdentification = ANY_PICTURE)

fun givenUserRegisterUiWithDifferentPasswords() = UserRegisterUi(
    fistName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    password = ANY_PASSWORD,
    confirmPassword = ANY_OTHER_PASSWORD,
    pictureIdentification = ANY_PICTURE)

fun givenUserUi() = UserUi(
    firstName = ANY_FIRST_NAME,
    lastName = ANY_LAST_NAME,
    email = ANY_USER_EMAIL,
    picture = ANY_LOCAL_PICTURE_USER,
    transactions = givenTransactionUiList())
