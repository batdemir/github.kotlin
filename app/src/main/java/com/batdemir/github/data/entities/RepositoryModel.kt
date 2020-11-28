package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.batdemir.github.utils.TimeExpressions
import com.google.gson.annotations.SerializedName

data class RepositoryModel(
    val id: Long? = null,
    val name: String? = null,
    val owner: OwnerModel? = null,
    val htmlUrl: String? = null,
    val description: String? = null,
    val language: String? = null,
    val license: LicenseModel? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null,
    @SerializedName("pushed_at")
    val pushedAt: String? = null,
    val isFavorite: Boolean = false
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
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
        parcel.readByte() != 0.toByte()
    ) {
    }

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
        parcel.writeValue(id)
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
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RepositoryModel

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
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