package br.com.curtacultura.curtacultura.scenes.main

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.curtacultura.curtacultura.R
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.item_centros.view.*
import android.support.v4.content.ContextCompat.startActivity



class MainAdapter(val context: Context,
                        val centros: QuerySnapshot): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_centros, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return centros.size()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val centro = centros.documents[position]

        holder.itemView.nomeTXT.text = centro.get("nome").toString()
        holder.itemView.emailTXT.text = centro.get("email").toString()

        holder.itemView.chamadaIMG.setOnClickListener{
            try {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:" + centro.get("telefone").toString())
                (context as MainActivity).startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }

        holder.itemView.localIMG.setOnClickListener{
            try {
                val url = Uri.parse("geo:"+centro.get("latitude").toString()+","+centro.get("longitude").toString())
                val mapIntent = Intent(Intent.ACTION_VIEW, url)
                mapIntent.`package` = "com.google.android.apps.maps"
                if (mapIntent.resolveActivity(context.packageManager) != null) {
                    (context as MainActivity).startActivity(mapIntent)
                }
            }catch (e: Exception) {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
            }
        }



    }


    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

}