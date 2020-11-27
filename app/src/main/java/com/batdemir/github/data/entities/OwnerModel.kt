package com.batdemir.github.data.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class OwnerModel(
    var login: String? = null,
    @SerializedName("node_id")
    var nodeId: String? = null,
    @SerializedName("avatar_url")
    var avatarUrl: String? = null,
    var url: String? = null,
    @SerializedName("html_url")
    var htmlUrl: String? = null,
    @SerializedName("followers_url")
    var followersUrl: String? = null,
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = null,
    @SerializedName("organizations_url")
    var organizationsUrl: String? = null,
    @SerializedName("repos_url")
    var reposUrl: String? = null,
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null,
    var type: String? = null,
    @SerializedName("site_admin")
    var siteAdmin: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(login)
        parcel.writeString(nodeId)
        parcel.writeString(avatarUrl)
        parcel.writeString(url)
        parcel.writeString(htmlUrl)
        parcel.writeString(followersUrl)
        parcel.writeString(subscriptionsUrl)
        parcel.writeString(organizationsUrl)
        parcel.writeString(reposUrl)
        parcel.writeString(receivedEventsUrl)
        parcel.writeString(type)
        parcel.writeString(siteAdmin)
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