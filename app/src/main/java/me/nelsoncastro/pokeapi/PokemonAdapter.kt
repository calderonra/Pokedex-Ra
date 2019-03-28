package me.nelsoncastro.pokeapi

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_element_pokemon.view.*
import me.nelsoncastro.pokeapi.models.Pokemon

class PokemonAdapter(val contexte: Context, val items: List<Pokemon>):RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_element_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.cardviewlistener.setOnClickListener {
            contexte.startActivity(Intent(contexte, PokemonViewer::class.java).putExtra("CLAVIER", items[position].type))
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cardviewlistener = with(itemView){ cardview }
        //val cardviewlistener = findViewById(R.id.cardview)

        fun bind(item: Pokemon) = with(itemView) {
            tv_pokemon_id.text = item.id.toString()
            tv_pokemon_name.text = item.name
            tv_pokemon_type.text = item.type
        }
    }
}