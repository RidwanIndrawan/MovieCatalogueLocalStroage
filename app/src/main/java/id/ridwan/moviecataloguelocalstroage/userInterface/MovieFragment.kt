package id.ridwan.moviecataloguelocalstroage.userInterface


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ridwan.moviecataloguelocalstroage.R
import id.ridwan.moviecataloguelocalstroage.adapter.DataMovieAdapter
import id.ridwan.moviecataloguelocalstroage.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_movie.view.*
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var movieViewModel : MovieViewModel
    private lateinit var moviewView : View
    private lateinit var dataMovieAdapter: DataMovieAdapter

    private var page = 1
    private var load = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        moviewView = inflater.inflate(R.layout.fragment_movie, container, false)

        dataMovieAdapter = DataMovieAdapter()
        dataMovieAdapter.notifyDataSetChanged()

        moviewView.RVMovie.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        moviewView.RVMovie.adapter = dataMovieAdapter

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        if(movieViewModel.movieSize() == null){
            movieViewModel.setMovies(resources.getString(R.string.languageCode),page)
        }

        movieViewModel.getMoviees().observe(this, Observer { movies ->
            showLoading(true)
            if(movies != null){
                showLoading(false)
                if(page == 1){
                dataMovieAdapter.setMovie(movies)
                }else{ dataMovieAdapter.addMovie(movies) }
            }else{
                Toast.makeText(context,resources.getString(R.string.checkCon),Toast.LENGTH_LONG).show()
            }
        })

        moviewView.RVMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if(dy > 0) //check for scroll down
                {
                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = linearLayoutManager.childCount
                    val totalItemCount = linearLayoutManager.itemCount
                    val pastVisiblesItems = linearLayoutManager.findLastCompletelyVisibleItemPosition()
                    if (load)
                    {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            load = false;
                            //Do pagination.. i.e. fetch new data
                            page += 1
                            movieViewModel.setMovies(resources.getString(R.string.languageCode),page)
                        }
                    }
                }
            }
        })
        return moviewView
    }

    private fun showLoading(state : Boolean){
        if(state){
            progressBar.visibility = View.VISIBLE
        }else{
            progressBar.visibility = View.GONE
        }
    }

}


