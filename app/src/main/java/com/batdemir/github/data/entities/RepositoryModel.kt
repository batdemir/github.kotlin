package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.batdemir.github.utils.TimeExpressions
import com.google.gson.annotations.SerializedName
import java.util.*

data class RepositoryModel(
    var id: Long = 0,
    @SerializedName("node_id")
    var nodeId: String? = null,
    var name: String? = null,
    @SerializedName("full_name")
    var fullName: String? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    var description: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
    @SerializedName("pushed_at")
    var pushedAt: String? = null,
    @SerializedName("stargazers_count")
    var stargazersCount: Int = 0,
    @SerializedName("watchers_count")
    var watchersCount: Int = 0,
    var language: String? = null,
    @SerializedName("forks_count")
    var forksCount: Int = 0,
    @SerializedName("open_issues_count")
    var openIssuesCount: Int = 0,
    @SerializedName("default_branch")
    var defaultBranch: String? = null,
    var license: LicenseModel? = null,
    var owner: OwnerModel? = null,
    var isFavorite: Boolean = false,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readParcelable(LicenseModel::class.java.classLoader),
        parcel.readParcelable(OwnerModel::class.java.classLoader),
        parcel.readByte() != 0.toByte()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val repoModel = other as RepositoryModel
        if (id != repoModel.id) return false
        return if (nodeId != repoModel.nodeId) false else name == repoModel.name
    }

    override fun hashCode(): Int {
        var result = (id xor (id ushr 32)).toInt()
        result = 31 * result + nodeId.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(nodeId)
        parcel.writeString(name)
        parcel.writeString(fullName)
        parcel.writeString(htmlUrl)
        parcel.writeString(description)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
        parcel.writeString(pushedAt)
        parcel.writeInt(stargazersCount)
        parcel.writeInt(watchersCount)
        parcel.writeString(language)
        parcel.writeInt(forksCount)
        parcel.writeInt(openIssuesCount)
        parcel.writeString(defaultBranch)
        parcel.writeParcelable(license, flags)
        parcel.writeParcelable(owner, flags)
        parcel.writeByte(if (isFavorite) 1 else 0)
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

    fun getUpdatedDate(): String {
        return if (updatedAt.isNullOrEmpty()) "" else {
            val calendar = Calendar.getInstance()
            calendar.time = TimeExpressions.setStringToDate(
                updatedAt!!,
                TimeExpressions.DateFormat.DEFAULT_DATE_FORMAT
            )
            return calendar.time.toString()
        }
    }

    fun getPushedDate(): String {
        return if (pushedAt.isNullOrEmpty()) "" else {
            val calendar = Calendar.getInstance()
            calendar.time = TimeExpressions.setStringToDate(
                pushedAt!!,
                TimeExpressions.DateFormat.DEFAULT_DATE_FORMAT
            )
            return calendar.time.toString()
        }
    }
}