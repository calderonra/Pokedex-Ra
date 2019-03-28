package me.nelsoncastro.pokeapi

import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.viewer_element_pokemon.*
import me.nelsoncastro.pokeapi.models.Pokemon
import me.nelsoncastro.pokeapi.utilities.NetworkUtils
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class PokemonViewer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewer_element_pokemon)

        val uri:String = this.intent.extras.getString("CLAVIER")
        setSupportActionBar(toolbarviewer)
        FetchPokemonTask().execute(uri)
    }

    fun init(uri: Pokemon){
        Picasso.with(this)
            .load(uri.type)
            .resize((this.resources.displayMetrics.widthPixels/this.resources.displayMetrics.density).toInt(),256)
            .centerCrop()
            .error(R.drawable.ic_pokemon_go)
            .into(app_bar_image_viewer)
        collapsingtoolbarviewer.title = uri.name
    }

    private inner class FetchPokemonTask : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg query: String): String {

            if (query.isNullOrEmpty()) return ""

            val url = query[0]
            val pokeAPI = Uri.parse(url).buildUpon().build()
            val finalurl = try {
                URL(pokeAPI.toString())
            } catch (e: MalformedURLException){
                URL("")
            }

            return try {
                NetworkUtils().getResponseFromHttpUrl(finalurl)
            } catch (e: IOException) {
                e.printStackTrace()
                ""
            }

        }

        override fun onPostExecute(pokemonInfo: String) {
            val pokemon = if (!pokemonInfo.isEmpty()) {
                val root = JSONObject(pokemonInfo)
                val sprites = root.getString("sprites")
                val results = JSONObject(sprites)
                Pokemon(root.getInt("id"), root.getString("name").capitalize(), results.getString("front_default"))

            } else {
                Pokemon(0, R.string.n_a_value.toString(), R.string.n_a_value.toString())
            }
            init(pokemon)
        }
    }
}