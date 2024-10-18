package com.app.storitest.data.datasource

import com.app.storitest.fakeData.ANY_TRANSACTIONS_ID
import com.app.storitest.fakeData.ANY_USER_ID
import com.app.storitest.fakeData.givenUserMap
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class UserRemoteDataShould {

    private val firebaseAuth = mock<FirebaseAuth>()
    private val firebaseUser = mock<FirebaseUser>()
    private val firebaseStorage = mock<FirebaseStorage>()
    private val firebaseFirestore = mock<FirebaseFirestore>()
    private val collectionReference = mock<CollectionReference>()
    private val documentReference = mock<DocumentReference>()
    private val documentSnapshotTask = mock<Task<DocumentSnapshot>>()
    private val voidTask = mock<Task<Void>>()

    private lateinit var authRemoteDataSource: UserRemoteDataSource

    @Before
    fun setup() {
        authRemoteDataSource = UserRemoteDataSource(firebaseAuth, firebaseStorage, firebaseFirestore)
    }

    @Test
    fun `Call store User collection when saveUser is called`() = runTest {
        val userMap = givenUserMap()

        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)
        whenever(firebaseUser.uid).thenReturn(ANY_USER_ID)
        whenever(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION)).thenReturn(collectionReference)
        whenever(collectionReference.document(ANY_USER_ID)).thenReturn(documentReference)
        whenever(documentReference.set(userMap)).thenReturn(voidTask)
        whenever(voidTask.addOnSuccessListener(any())).thenReturn(voidTask)

        authRemoteDataSource.saveUser(userMap)

        verify(documentReference).set(userMap)
        verify(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION).document(ANY_USER_ID)).set(ANY_USER_ID)
    }

    @Test
    fun `Call get User collection when getUser is called`() = runTest {
        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)
        whenever(firebaseUser.uid).thenReturn(ANY_USER_ID)

        whenever(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION)).thenReturn(collectionReference)
        whenever(collectionReference.document(ANY_USER_ID)).thenReturn(documentReference)
        whenever(documentReference.get()).thenReturn(documentSnapshotTask)
        whenever(documentSnapshotTask.addOnSuccessListener(any())).thenReturn(documentSnapshotTask)

        authRemoteDataSource.getUser()

        verify(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION).document(ANY_USER_ID)).get()
    }

    @Test
    fun `Call get TransactionDetail data collection when getTransactionDetail is called`() = runTest {
        whenever(firebaseAuth.currentUser).thenReturn(firebaseUser)
        whenever(firebaseUser.uid).thenReturn(ANY_USER_ID)

        whenever(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION)).thenReturn(collectionReference)
        whenever(collectionReference.document(ANY_USER_ID)).thenReturn(documentReference)
        whenever(documentReference.collection(UserRemoteDataSource.TRANSACTIONS_COLLECTION)).thenReturn(collectionReference)
        whenever(collectionReference.document(ANY_TRANSACTIONS_ID)).thenReturn(documentReference)
        whenever(documentReference.get()).thenReturn(documentSnapshotTask)
        whenever(documentSnapshotTask.addOnSuccessListener(any())).thenReturn(documentSnapshotTask)

        authRemoteDataSource.getTransactionDetail(ANY_TRANSACTIONS_ID)

        verify(firebaseFirestore.collection(UserRemoteDataSource.USERS_COLLECTION).document(ANY_USER_ID)).get()
    }
}
