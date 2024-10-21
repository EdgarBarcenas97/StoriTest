package com.app.storitest.data.datasource

import com.app.storitest.data.datasource.remote.AuthRemoteDataSource
import com.app.storitest.fakeData.ANY_PASSWORD
import com.app.storitest.fakeData.ANY_USER_EMAIL
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRemoteDataShould {

    private val firebaseAuth = mock<FirebaseAuth>()
    private val authResultTask = mock<Task<AuthResult>>()

    private lateinit var authRemoteDataSource: AuthRemoteDataSource

    @Before
    fun setup() {
        authRemoteDataSource = AuthRemoteDataSource(firebaseAuth)
    }

    @Test
    fun `Call signInWithEmailAndPassword when signIn is called`() = runTest {
        whenever(firebaseAuth.signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(authResultTask)
        whenever(authResultTask.addOnSuccessListener(any())).thenReturn(authResultTask)

        authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)

        verify(firebaseAuth).signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_PASSWORD)
    }

    @Test
    fun `Call createUserWithEmailAndPassword when signUp is called`() = runTest {
        whenever(firebaseAuth.createUserWithEmailAndPassword(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(authResultTask)
        whenever(authResultTask.addOnSuccessListener(any())).thenReturn(authResultTask)

        authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_PASSWORD)

        verify(firebaseAuth).createUserWithEmailAndPassword(ANY_USER_EMAIL, ANY_PASSWORD)
    }

    private fun mockTask(authResult: AuthResult, exception: Exception? = null): Task<AuthResult> {
        val task = mock<Task<AuthResult>>()
        whenever(task.isComplete).thenReturn(true)
        whenever(task.exception).thenReturn(exception)
        whenever(task.isCanceled).thenReturn(false)
        whenever(task.result).thenReturn(authResult)
        return task
    }
}
