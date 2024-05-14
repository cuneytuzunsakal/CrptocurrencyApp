package com.cuneytuzunsakal.crptocurrencyapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cuneytuzunsakal.crptocurrencyapp.R
import com.cuneytuzunsakal.crptocurrencyapp.model.CryptocurrencyModel

class RecyclerViewAdapter(private val cryptoList: ArrayList<CryptocurrencyModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    private val colors = arrayOf(
         "#48E817","#FFFFFF"
    )

    inner class RowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textName: TextView = itemView.findViewById(R.id.text_name)
        private val textPrice: TextView = itemView.findViewById(R.id.text_price)
        private val textDaily: TextView = itemView.findViewById(R.id.text_daily)
        private val textDailyChange: TextView = itemView.findViewById(R.id.text_daily_change)

        fun bind(cryptoModel: CryptocurrencyModel, position: Int) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 2]))
            textName.text = "Name ${cryptoModel.currency}"
            textPrice.text = "Price ${cryptoModel.price.toString()}"
            textDaily.text = "Change ${cryptoModel.daily}"
            textDailyChange.text = "Percentage ${cryptoModel.dailyPercent}%"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoList[position], position)
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }
}
