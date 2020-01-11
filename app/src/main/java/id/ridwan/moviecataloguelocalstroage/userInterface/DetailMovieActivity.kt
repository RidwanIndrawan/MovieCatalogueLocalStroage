package id.ridwan.moviecataloguelocalstroage.userInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.ridwan.moviecataloguelocalstroage.BuildConfig
import id.ridwan.moviecataloguelocalstroage.R
import id.ridwan.moviecataloguelocalstroage.dataMaster.DataMovie
import id.ridwan.moviecataloguelocalstroage.viewModel.DetailMovieViewModel
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {

    companion object{
        const val KEY = "key"
    }

    private lateinit var detailMovieViewModel : DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        backButton()

        val dataMovie = intent.getParcelableExtra<DataMovie>(KEY)

        Glide.with(this)
            .load("${BuildConfig.IMAGE_URL}t/p/w500${dataMovie.poster}")
            .apply(RequestOptions()
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_warning_black_24dp))
            .into(posterDetailM)
        titleDetailM.text = dataMovie.title
        dateDetailM.text = dataMovie.date
        ratingDetailM.text = dataMovie.vote.toString()

        detailMovieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailMovieViewModel::class.java)
        detailMovieViewModel.setMovie(dataMovie.id,resources.getString(R.string.languageCode))
        detailMovieViewModel.getDetailMovie()?.observe(this, Observer { detailMovie ->
            showLoading(true)
            if(detailMovie == null){
                Toast.makeText(this, resources.getString(R.string.checkCon), Toast.LENGTH_LONG).show()
            } else {
                showLoading(false)
                Glide.with(this)
                    .load("${BuildConfig.IMAGE_URL}t/p/original${detailMovie.backdrop}")
                    .into(backdropM)
                runtimeDetailM.text = detailMovie.runtime.toString()
                synopsisDetailM.text = detailMovie.overview
            }
        })
    }

    private fun showLoading(state:Boolean){
        if(state){
            progBackdrop.visibility = View.VISIBLE
            progRuntime.visibility = View.VISIBLE
            progRating.visibility = View.VISIBLE
            progSynopsis.visibility = View.VISIBLE
        } else {
            progBackdrop.visibility = View.GONE
            progRuntime.visibility = View.GONE
            progRating.visibility = View.GONE
            progSynopsis.visibility = View.GONE
        }
    }

    private fun backButton(){
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setSupportActionBar(toolbarDetailM)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        toolbarDetailM.setNavigationOnClickListener {
            finish()
        }
    }
}
