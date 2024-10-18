package com.app.storitest.domain

import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.UserRepository
import com.app.storitest.data.exception.UserException
import com.app.storitest.domain.models.TransactionDetail
import com.app.storitest.fakeData.ANY_TRANSACTIONS_ID
import com.app.storitest.fakeData.givenTransactionDetail
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetTransactionDetailUseCaseShould {

    private val userRepository = mock<UserRepository>()

    private lateinit var getTransactionDetailUseCase: GetTransactionDetailUseCase

    @Before
    fun setup() {
        getTransactionDetailUseCase = GetTransactionDetailUseCase(userRepository)
    }

    @Test
    fun `Return TransactionDetail when getTransactionDetail is called and getTransactionDetail repository are success`() = runTest {
        val transactionDetail = givenTransactionDetail()
        val resultTransactionDetail = Result.success(transactionDetail)
        whenever(userRepository.getTransactionDetail(ANY_TRANSACTIONS_ID)).thenReturn(flowOf(resultTransactionDetail))

        val result = getTransactionDetailUseCase.getTransactionDetail(ANY_TRANSACTIONS_ID).lastOrNull()

        verify(userRepository).getTransactionDetail(ANY_TRANSACTIONS_ID)
        assertThatEquals(result?.getOrNull(), transactionDetail)
    }

    @Test
    fun `Get GetUserException when getTransactionDetail is called and getTransactionDetail in repository is failure`() = runTest {
        val resultGetUserException: Result<TransactionDetail> = Result.failure(UserException.GetUserException())
        whenever(userRepository.getTransactionDetail(ANY_TRANSACTIONS_ID)).thenReturn(flowOf(resultGetUserException))

        val result = getTransactionDetailUseCase.getTransactionDetail(ANY_TRANSACTIONS_ID).lastOrNull()

        verify(userRepository).getTransactionDetail(ANY_TRANSACTIONS_ID)
        assertThatIsInstanceOf<UserException.GetUserException>(result?.exceptionOrNull())
    }
}
