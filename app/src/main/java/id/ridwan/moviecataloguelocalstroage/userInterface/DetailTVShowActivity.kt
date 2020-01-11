package id.ridwan.moviecataloguelocalstroage.userInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.ridwan.moviecataloguelocalstroage.R

class DetailTVShowActivity : AppCompatActivity() {

    companion object {
        const val KEY2 = "key2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)
    }
}
