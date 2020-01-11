package id.ridwan.moviecataloguelocalstroage.dataMaster

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataTVShow (
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("backdrop_path")
    var backdrop: String? = null,
    @SerializedName("original_name")
    var title: String? = null,
    @SerializedName("first_air_date")
    var firstAir: String? = null
) : Parcelable