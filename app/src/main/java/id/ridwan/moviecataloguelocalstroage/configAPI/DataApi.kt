package id.ridwan.moviecataloguelocalstroage.configAPI

import id.ridwan.moviecataloguelocalstroage.dataMaster.DataDetailMovie
import id.ridwan.moviecataloguelocalstroage.dataMaster.DataDetailTVShow
import id.ridwan.moviecataloguelocalstroage.dataMaster.ListMovie
import id.ridwan.moviecataloguelocalstroage.dataMaster.ListTVShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DataApi {

    @GET("3/discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ListMovie>

    @GET("3/movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<DataDetailMovie>

    @GET("3/discover/tv")
    fun getTVShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<ListTVShow>

    @GET("3/tv/{id}")
    fun getTVShowsDetail(
        @Path("id") id: Int?,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<DataDetailTVShow>
}