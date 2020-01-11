package id.ridwan.moviecataloguelocalstroage.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.ridwan.moviecataloguelocalstroage.BuildConfig
import id.ridwan.moviecataloguelocalstroage.R
import id.ridwan.moviecataloguelocalstroage.dataMaster.DataMovie
import id.ridwan.moviecataloguelocalstroage.userInterface.CustomOnItemClickListener
import id.ridwan.moviecataloguelocalstroage.userInterface.DetailMovieActivity
import kotlinx.android.synthetic.main.item_row_movie.view.*

class DataMovieAdapter : RecyclerView.Adapter<DataMovieAdapter.ListViewHolder>(){

    private val listMovie = ArrayList<DataMovie>()

    fun setMovie(movie:ArrayList<DataMovie>){
        listMovie.clear()
        listMovie.addAll(movie)
        notifyDataSetChanged()
    }

    fun addMovie(movie: ArrayList<DataMovie>) {
        listMovie.addAll(movie)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movie,parent,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    inner class ListViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(dataMovie: DataMovie) {
            with(itemView){
                parentItemMovie.animation = AnimationUtils.loadAnimation(context, R.anim.item_animation_slide_from_left)
                Glide.with(itemView.context)
                    .load("${BuildConfig.IMAGE_URL}t/p/w185${dataMovie.poster}")
                    .into(itemView.posterM)
                titleM.text = dataMovie.title
                dateM.text = dataMovie.date
                ratingM.text = dataMovie.vote.toString()
                itemView.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback{
                    override fun onItemClicked(view: View, position: Int) {
                        Toast.makeText(itemView.context, dataMovie.title, Toast.LENGTH_LONG).show()

                        val intent = Intent(itemView.context, DetailMovieActivity::class.java)

                        intent.putExtra(DetailMovieActivity.KEY,dataMovie)
                        itemView.context.startActivity(intent)
                    }
                }))
            }
        }

    }
}