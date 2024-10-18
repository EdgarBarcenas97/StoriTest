package com.app.storitest.ui

import com.app.storitest.core.TestDispatcherRule
import com.app.storitest.core.assertThatEquals
import com.app.storitest.core.assertThatIsInstanceOf
import com.app.storitest.data.exception.UserException
import com.app.storitest.domain.GetTransactionDetailUseCase
import com.app.storitest.domain.models.TransactionDetail
import com.app.storitest.fakeData.ANY_TRANSACTIONS_ID
import com.app.storitest.fakeData.givenTransactionDetail
import com.app.storitest.fakeData.givenTransactionDetailUi
import com.app.storitest.ui.features.detail.TransactionDetailUiModelState
import com.app.storitest.ui.features.detail.TransactionDetailViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class TransactionDetailViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val getTransactionDetailUseCase = mock<GetTransactionDetailUseCase>()

    private lateinit var transactionDetailViewModel: TransactionDetailViewModel

    @Before
    fun setup() {
        transactionDetailViewModel = TransactionDetailViewModel(getTransactionDetailUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @Test
    fun `get TransactionDetailUi from transactionDetailUiModelState when getTransactionDetail is called and getTransactionDetail is success`() = runTest {
        val transactionDetail = givenTransactionDetail()
        val transactionDetailUi = givenTransactionDetailUi()
        val resultTransactionDetail = Result.success(transactionDetail)

        whenever(getTransactionDetailUseCase.getTransactionDetail(ANY_TRANSACTIONS_ID)).thenReturn(resultTransactionDetail)

        transactionDetailViewModel.getTransactionDetail(ANY_TRANSACTIONS_ID)

        val result = transactionDetailViewModel.transactionDetailUiModelState.firstOrNull()

        verify(getTransactionDetailUseCase).getTransactionDetail(ANY_TRANSACTIONS_ID)
        assertThatIsInstanceOf<TransactionDetailUiModelState.Success>(result)
        assertThatEquals((result as TransactionDetailUiModelState.Success).transactionDetailUi, transactionDetailUi)
    }

    @Test
    fun `Get GetUserException from TransactionDetailUiModelState when getTransactionDetail is called and getTransactionDetail is failure`() = runTest {
        val resultGetUserException: Result<TransactionDetail> = Result.failure(UserException.GetUserException())

        whenever(getTransactionDetailUseCase.getTransactionDetail(ANY_TRANSACTIONS_ID)).thenReturn(resultGetUserException)

        transactionDetailViewModel.getTransactionDetail(ANY_TRANSACTIONS_ID)

        val result = transactionDetailViewModel.transactionDetailUiModelState.firstOrNull()

        verify(getTransactionDetailUseCase).getTransactionDetail(ANY_TRANSACTIONS_ID)
        assertThatIsInstanceOf<UserException.GetUserException>((result as TransactionDetailUiModelState.Error).error)
    }
}
