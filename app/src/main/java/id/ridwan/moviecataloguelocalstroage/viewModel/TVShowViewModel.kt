package id.ridwan.moviecataloguelocalstroage.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ridwan.moviecataloguelocalstroage.BuildConfig
import id.ridwan.moviecataloguelocalstroage.configAPI.Config
import id.ridwan.moviecataloguelocalstroage.dataMaster.DataMovie
import id.ridwan.moviecataloguelocalstroage.dataMaster.DataTVShow
import id.ridwan.moviecataloguelocalstroage.dataMaster.ListMovie
import id.ridwan.moviecataloguelocalstroage.dataMaster.ListTVShow
import retrofit2.Call
import retrofit2.Response

class TVShowViewModel : ViewModel() {

    private val tvShows = MutableLiveData<ArrayList<DataTVShow>>()

    fun setTVShows(languageCode: String, page: Int) {
        Config().instance().getTVShows(BuildConfig.API_KEY, languageCode, page)
            .enqueue(object : retrofit2.Callback<ListTVShow> {
                override fun onFailure(call: Call<ListTVShow>, t: Throwable) {
                    Log.d("Failed", t.message)
                    tvShows.postValue(null)
                }

                override fun onResponse(call: Call<ListTVShow>, response: Response<ListTVShow>) {
                    val listTVshows = response.body()?.dataTVShows
                    tvShows.postValue(listTVshows)
                }
            })

    }

    fun getTVShows(): LiveData<ArrayList<DataTVShow>> {
        return tvShows
    }

    fun tvShowSize():Int?{
        return this.tvShows.value?.size
    }
}