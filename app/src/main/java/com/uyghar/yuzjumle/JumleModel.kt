package com.uyghar.yuzjumle

data class JumleModel(
    val id: Int?,
    val tr: String?,
    val uy: String?,
    var talla: Boolean
) {
    override fun toString(): String {
        return tr ?: ""
    }
}