package id.ridwan.moviecataloguelocalstroage.dataMaster

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataMovie(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("poster_path")
    var poster: String? = null,
    @SerializedName("original_title")
    var title: String? = null,
    @SerializedName("release_date")
    var date: String? = null,
    @SerializedName("vote_average")
    var vote:Float? = null
): Parcelable