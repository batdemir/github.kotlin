package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Owner")
data class OwnerModel(
    @PrimaryKey
    @ColumnInfo(name = "ownerId") var id: Long = 0,
    var login: String? = null,
    @SerializedName("avatar_url")
    var avatarUrl: String? = null,
    @SerializedName("html_url")
    @ColumnInfo(name = "ownerHtmlUrl") var htmlUrl: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
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