package id.ridwan.moviecataloguelocalstroage.dataMaster

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListTVShow (
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var dataTVShows: ArrayList<DataTVShow>?
): Parcelable