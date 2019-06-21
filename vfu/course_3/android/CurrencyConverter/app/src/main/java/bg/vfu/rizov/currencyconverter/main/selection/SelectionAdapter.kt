package bg.vfu.rizov.currencyconverter.main.selection

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import bg.vfu.rizov.currencyconverter.R
import bg.vfu.rizov.currencyconverter.model.Currency
import kotlinx.android.synthetic.main.view_currency_item.view.*

typealias SelectionCallback = (item: Currency) -> Unit

class SelectionAdapter(
  currencies: Map<String, Currency>,
  val callback: SelectionCallback
) : RecyclerView.Adapter<SelectionAdapter.ViewHolder>() {

  private val currencies = currencies.entries.map { it.value }
    .toList()
    .sortedBy { it.name.trim() }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val currency: TextView = view.currency
    val currencyName: TextView = view.currencyName
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.view_currency_item, parent, false) as View
    return ViewHolder(view)
  }

  override fun getItemCount() = currencies.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.currency.text = currencies[position].code
    holder.currencyName.text = currencies[position].name
    holder.itemView.setOnClickListener {
      callback(currencies[position])
    }
  }

}
