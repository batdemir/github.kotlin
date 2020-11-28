package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class OwnerModel(
    val id: Long? = null,
    val login: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("html_url")
    val htmlUrl: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(login)
        parcel.writeString(avatarUrl)
        parcel.writeString(htmlUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<OwnerModel> {
        override fun createFromParcel(parcel: Parcel): OwnerModel {
            return OwnerModel(parcel)
        }

        override fun newArray(size: Int): Array<OwnerModel?> {
            return arrayOfNulls(size)
        }
    }
}