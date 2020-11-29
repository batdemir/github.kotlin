package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.batdemir.github.utils.TimeExpressions
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Repository")
data class RepositoryModel(
    @PrimaryKey
    @ColumnInfo(name = "repositoryId") var id: Long = 0,
    @ColumnInfo(name = "repositoryName") var name: String = "",
    @Embedded
    var owner: OwnerModel? = null,
    var htmlUrl: String? = null,
    var description: String? = null,
    var language: String? = null,
    @Embedded
    var license: LicenseModel? = null,
    @SerializedName("full_name")
    var fullName: String? = null,
    @SerializedName("stargazers_count")
    var stargazersCount: Int,
    @SerializedName("open_issues_count")
    var openIssuesCount: Int,
    @SerializedName("forks_count")
    var forksCount: Int,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("pushed_at")
    var pushedAt: String? = null,
    var isFavorite: Boolean = false,
    var ownerName: String = "",
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readParcelable(OwnerModel::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(LicenseModel::class.java.classLoader),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()!!
    ) {
    }

    constructor() : this(
        0,
        "",
        null,
        null,
        null,
        null,
        null,
        null,
        0,
        0,
        0,
        null,
        null,
        null,
        false,
        ""
    )

    fun getCreated(): String {
        return TimeExpressions.setStringToDate(
            createdAt!!,
            TimeExpressions.DateFormat.DEFAULT_DATE_FORMAT
        ).toString()
    }

    fun getUpdated(): String {
        return TimeExpressions.setStringToDate(
            updatedAt!!,
            TimeExpressions.DateFormat.DEFAULT_DATE_FORMAT
        ).toString()
    }

    fun getPushed(): String {
        return TimeExpressions.setStringToDate(
            pushedAt!!,
            TimeExpressions.DateFormat.DEFAULT_DATE_FORMAT
        ).toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeParcelable(owner, flags)
        parcel.writeString(htmlUrl)
        parcel.writeString(description)
        parcel.writeString(language)
        parcel.writeParcelable(license, flags)
        parcel.writeString(fullName)
        parcel.writeInt(stargazersCount)
        parcel.writeInt(openIssuesCount)
        parcel.writeInt(forksCount)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(pushedAt)
        parcel.writeByte(if (isFavorite) 1 else 0)
        parcel.writeString(ownerName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepositoryModel> {
        override fun createFromParcel(parcel: Parcel): RepositoryModel {
            return RepositoryModel(parcel)
        }

        override fun newArray(size: Int): Array<RepositoryModel?> {
            return arrayOfNulls(size)
        }
    }

}