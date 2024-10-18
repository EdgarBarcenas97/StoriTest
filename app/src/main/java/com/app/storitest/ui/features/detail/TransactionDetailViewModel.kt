package com.app.storitest.ui.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.storitest.core.CoroutinesDispatchers
import com.app.storitest.domain.GetTransactionDetailUseCase
import com.app.storitest.domain.models.TransactionDetail
import com.app.storitest.ui.features.home.data.toTransactionDetailUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.withContext

@HiltViewModel
class TransactionDetailViewModel @Inject constructor(private val getTransactionDetailUseCase: GetTransactionDetailUseCase,
                                                     private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private val _transactionDetailUiModelState = MutableStateFlow<TransactionDetailUiModelState?>(null)

    val transactionDetailUiModelState: StateFlow<TransactionDetailUiModelState?>
        get() = _transactionDetailUiModelState

    fun getTransactionDetail(transactionId: String) {
        emitUserDataUiState(TransactionDetailUiModelState.Loading)
        viewModelScope.launch(coroutinesDispatchers.io) {
            val result = getTransactionDetailUseCase.getTransactionDetail(transactionId)
            withContext(coroutinesDispatchers.main) {
                if (result.isSuccess) {
                    result.getOrNull()?.let { getTransactionDetailSuccess(it) }
                } else {
                    result.exceptionOrNull()?.let { getTransactionDetailError(it) }
                }
            }
        }
    }

    private fun getTransactionDetailSuccess(transactionDetail: TransactionDetail) {
        emitUserDataUiState(TransactionDetailUiModelState.Success(transactionDetail.toTransactionDetailUi()))
    }

    private fun getTransactionDetailError(throwable: Throwable) {
        throwable.printStackTrace()
        emitUserDataUiState(TransactionDetailUiModelState.Error(throwable))
    }

    private fun emitUserDataUiState(signUpUiState: TransactionDetailUiModelState) {
        _transactionDetailUiModelState.value = signUpUiState
    }
}
