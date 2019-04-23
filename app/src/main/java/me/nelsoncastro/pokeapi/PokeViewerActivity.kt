package me.nelsoncastro.pokeapi

import android.graphics.Movie
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.viewer_element_pokemon.*
import me.nelsoncastro.pokeapi.models.Pokemon
import com.bumptech.glide.Glide


class PokeViewerActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewer_element_pokemon)

        setSupportActionBar(toolbarviewer)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setDisplayShowHomeEnabled(true)
        collapsingtoolbarviewer.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsingtoolbarviewer.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        val reciever: Pokemon = intent?.extras?.getParcelable("Pokemon") ?: Pokemon()
        init(reciever)
    }

    fun init(pokemon: Pokemon){
        Glide.with(this)
            .load(pokemon.sprite)
            .placeholder(R.drawable.ic_launcher_background)
            .into(app_bar_image_viewer)
    }
}