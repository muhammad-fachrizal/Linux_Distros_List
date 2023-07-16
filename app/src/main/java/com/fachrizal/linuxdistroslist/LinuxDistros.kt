package com.fachrizal.linuxdistroslist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LinuxDistros(
    val name: String,
    val description: String,
    val icon: String,
    val pros: String,
    val cons: String,
    val softwarePackageManagement: String,
    val availableEditions: String,
    val possibleAlternatives: String
) : Parcelable
