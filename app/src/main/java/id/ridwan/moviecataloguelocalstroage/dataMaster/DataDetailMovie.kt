package id.ridwan.moviecataloguelocalstroage.dataMaster

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataDetailMovie (
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("runtime")
    var runtime: Int?,
    @SerializedName("backdrop_path")
    var backdrop: String?
) : Parcelable