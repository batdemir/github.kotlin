package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable

data class LicenseModel(
    var name: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LicenseModel> {
        override fun createFromParcel(parcel: Parcel): LicenseModel {
            return LicenseModel(parcel)
        }

        override fun newArray(size: Int): Array<LicenseModel?> {
            return arrayOfNulls(size)
        }
    }
}