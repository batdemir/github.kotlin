package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class LicenseModel(
    var key: String? = null,
    var name: String? = null,
    var url: String? = null,
    @SerializedName("node_id")
    var nodeId: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(nodeId)
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