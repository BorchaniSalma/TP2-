package com.gl4.tp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class Adapter(private val dataSet: ArrayList<Etudiant>) :
    RecyclerView.Adapter<Adapter.ViewHolder>(), Filterable {

    var dataFilterList = ArrayList<Etudiant>()
    var initDataSet = ArrayList<Etudiant>()
    init {
        // associer le tableau des données initiales
        dataFilterList = dataSet
        initDataSet = dataSet
    }

    override public fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if(charSearch.lowercase(Locale.ROOT) === "tp" || charSearch.lowercase(Locale.ROOT) === "cours") {
                    initDataSet = ArrayList(dataSet.filter { data -> data.presences.any { presence -> presence.matiere.lowercase(
                        Locale.ROOT
                   ) === charSearch.lowercase(Locale.ROOT) && presence.presence } })
                }
                if (charSearch.isEmpty()) {
                    dataFilterList = initDataSet
                } else {
                    val resultList = ArrayList<Etudiant>()
                        for (student in dataSet) {
                            if (student.getNom().lowercase(Locale.ROOT)
                                    .contains(charSearch.lowercase(Locale.ROOT))
                            ) {
                                resultList.add(student)
                            }
                        }
                    dataFilterList = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }


            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Etudiant>
                notifyDataSetChanged()
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val image: ImageView


        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textView)
            image = view.findViewById(R.id.imageView2)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.student_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if(dataFilterList[position].getGender() !== "M") viewHolder.image.setImageResource(R.mipmap.female);
        viewHolder.textView.text = dataFilterList[position].getPrenom() + " " + dataFilterList[position].getNom();
    }

    override fun getItemCount() = dataFilterList.size

}