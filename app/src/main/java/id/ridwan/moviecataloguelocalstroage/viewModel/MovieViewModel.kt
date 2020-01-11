package id.ridwan.moviecataloguelocalstroage.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ridwan.moviecataloguelocalstroage.BuildConfig
import id.ridwan.moviecataloguelocalstroage.configAPI.Config
import id.ridwan.moviecataloguelocalstroage.dataMaster.DataMovie
import id.ridwan.moviecataloguelocalstroage.dataMaster.ListMovie
import retrofit2.Call
import retrofit2.Response

class MovieViewModel : ViewModel() {

    private val movies = MutableLiveData<ArrayList<DataMovie>>()

    fun setMovies(languageCode: String, page: Int) {
        Config().instance().getMovies(BuildConfig.API_KEY, languageCode, page)
            .enqueue(object : retrofit2.Callback<ListMovie> {
                override fun onFailure(call: Call<ListMovie>, t: Throwable) {
                    Log.d("Failed", t.message)
                    movies.postValue(null)
                }

                override fun onResponse(call: Call<ListMovie>, response: Response<ListMovie>) {
                    val listMovies = response.body()?.dataMovie
                    movies.postValue(listMovies)
                }
            })

    }

    fun getMoviees():LiveData<ArrayList<DataMovie>>{
        return movies
    }

    fun movieSize():Int?{
        return this.movies.value?.size
    }
}