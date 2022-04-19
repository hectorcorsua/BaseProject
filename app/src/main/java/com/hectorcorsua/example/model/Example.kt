package com.hectorcorsua.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Example(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
): Parcelable
