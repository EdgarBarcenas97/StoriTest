package com.app.storitest.data.models

data class TransactionDetailFirestore(val name: String? = null,
                                      val date: String? = null,
                                      val amount: String? = null,
                                      val card: String? = null,
                                      val reference: String? = null,
                                      val category: String? = null)
