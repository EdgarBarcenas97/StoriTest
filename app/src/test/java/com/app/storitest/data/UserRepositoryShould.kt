package com.app.storitest.data

import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.datasource.UserRemoteDataSource
import com.app.storitest.data.exception.UserException
import com.app.storitest.data.models.TransactionDetailFirestore
import com.app.storitest.data.models.UserFirestore
import com.app.storitest.fakeData.ANY_TRANSACTIONS_ID
import com.app.storitest.fakeData.givenTransactionDetail
import com.app.storitest.fakeData.givenTransactionDetailResponse
import com.app.storitest.fakeData.givenUser
import com.app.storitest.fakeData.givenUserFirestore
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UserRepositoryShould {

    private val userRemoteDataSource = mock<UserRemoteDataSource>()

    private lateinit var userRepository: UserRepository

    @Before
    fun setup() {
        userRepository = UserRepository(userRemoteDataSource)
    }

    @Test
    fun `Return User when getUser is called and getUser in datasource is success`() = runTest {
        val userFirestore = givenUserFirestore()
        val user = givenUser()
        val resultUserFirestore = Result.success(userFirestore)
        whenever(userRemoteDataSource.getUser()).thenReturn(flowOf(resultUserFirestore))

        val result = userRepository.getUser().lastOrNull()

        verify(userRemoteDataSource).getUser()
        assertThatEquals(result?.getOrNull(), user)
    }

    @Test
    fun `Get GetUserException when signUp is called and signUp in datasource is failure`() = runTest {
        val resultSGetUserException: Result<UserFirestore> = Result.failure(UserException.GetUserException())
        whenever(userRemoteDataSource.getUser()).thenReturn(flowOf(resultSGetUserException))

        val result = userRepository.getUser().lastOrNull()

        verify(userRemoteDataSource).getUser()
        assertThatIsInstanceOf<UserException.GetUserException>(result?.exceptionOrNull())
    }

    @Test
    fun `Return TransactionDetail when getTransactionDetail is called and getTransactionDetail in datasource is success`() = runTest {
        val transactionDetailFirestore = givenTransactionDetailResponse()
        val transactionDetail = givenTransactionDetail()
        val resultTransactionDetailFirestore = Result.success(transactionDetailFirestore)
        whenever(userRemoteDataSource.getTransactionDetail(ANY_TRANSACTIONS_ID)).thenReturn(flowOf(resultTransactionDetailFirestore))

        val result = userRepository.getTransactionDetail(ANY_TRANSACTIONS_ID).lastOrNull()

        verify(userRemoteDataSource).getTransactionDetail(ANY_TRANSACTIONS_ID)
        assertThatEquals(result?.getOrNull(), transactionDetail)
    }

    @Test
    fun `Get GetUserException when getTransactionDetail is called and getTransactionDetail in datasource is failure`() = runTest {
        val resultGetUserException: Result<TransactionDetailFirestore> = Result.failure(UserException.GetUserException())
        whenever(userRemoteDataSource.getTransactionDetail(ANY_TRANSACTIONS_ID)).thenReturn(flowOf(resultGetUserException))

        val result = userRepository.getTransactionDetail(ANY_TRANSACTIONS_ID).lastOrNull()

        verify(userRemoteDataSource).getTransactionDetail(ANY_TRANSACTIONS_ID)
        assertThatIsInstanceOf<UserException.GetUserException>(result?.exceptionOrNull())
    }
}
