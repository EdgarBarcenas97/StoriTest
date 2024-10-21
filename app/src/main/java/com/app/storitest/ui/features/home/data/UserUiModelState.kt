package com.app.storitest.ui.features.home.data

data class UserUiModelState(val loading: Boolean = false,
                            val userUi: UserUi? = null,
                            val error: Throwable? = null)