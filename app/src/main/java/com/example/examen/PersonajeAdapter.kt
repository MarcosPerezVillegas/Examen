import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen.Personaje
import com.example.examen.R
import com.squareup.picasso.Picasso

class PersonajeAdapter(val listaPersonajes: ArrayList<Personaje>):RecyclerView.Adapter<PersonajeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.vista_individual,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNombre.text = listaPersonajes[position].nombre
        Picasso.get().load(listaPersonajes[position].imagen).into(holder.ivPersonaje)
    }

    override fun getItemCount(): Int {
        return  listaPersonajes.size
    }

    class ViewHolder(vista:View):RecyclerView.ViewHolder(vista){
        val tvNombre:TextView
        val ivPersonaje:ImageView

        init {
            tvNombre = vista.findViewById(R.id.tvNombre)
            ivPersonaje= vista.findViewById(R.id.ivPersonaje)
        }
    }

}