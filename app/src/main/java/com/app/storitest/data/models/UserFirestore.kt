package com.app.storitest.data.models

data class UserFirestore(val firstName: String? = null,
                         val lastName: String? = null,
                         val email: String? = null,
                         var picture: String? = null,
                         val transactions: ArrayList<Map<String, String>>? = null)
