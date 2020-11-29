package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "License")
data class LicenseModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "licenseId") var id: Long = 0,
    @ColumnInfo(name = "licenseName") var name: String = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
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