package id.ridwan.moviecataloguelocalstroage.userInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import id.ridwan.moviecataloguelocalstroage.R
import id.ridwan.moviecataloguelocalstroage.adapter.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tabAdapter : TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       setupViewPager(view_pager)
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setSupportActionBar(toolbar)
        supportActionBar?.elevation = 0f


    }

    private fun setupViewPager(viewPager: ViewPager){
        tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(MovieFragment(),getString(R.string.movie))
        tabAdapter.addFragment(TVShowsFragment(),getString(R.string.tvshow))

        viewPager.adapter = tabAdapter
    }
}
