package bg.vfu.rizov.currencyconverter.main.converter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import bg.vfu.rizov.currencyconverter.R
import bg.vfu.rizov.currencyconverter.model.Currency
import kotlinx.android.synthetic.main.view_currency_converter_item.view.*

typealias SelectionCallback = (item: Currency?) -> Unit

class ConverterAdapter(
  private val currencies: MutableList<Currency>,
  private val callback: SelectionCallback
) : RecyclerView.Adapter<ConverterAdapter.ViewHolder>() {

  inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val currencyName: TextView = view.currencyName
    val currencyValue: TextView = view.currencyValue
    val removeCurrency: ImageView = view.removeCurrency

    init {
      view.setOnClickListener {
        selectCurrency(currencies[adapterPosition])
        notifyItemChanged(0)
        notifyItemChanged(adapterPosition)
      }

      removeCurrency.setOnClickListener {
        currencies.removeAt(adapterPosition)
        if (currencies.size > 0) {
          selectCurrency(currencies[0])
        } else {
          selectCurrency(null)
        }
        notifyDataSetChanged()
      }
    }
  }

  init {
    if (currencies.isNotEmpty()) {
      selectCurrency(currencies[0])
    }
  }

  fun notifyValueChanged(number: Double) {
    currentValue = number
    notifyDataSetChanged()
  }

  private var selectedCurrency: Currency? = null

  private var currentValue: Double = 0.0

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.view_currency_converter_item, parent, false) as View
    return ViewHolder(view)
  }

  override fun getItemCount() = currencies.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val currency = currencies[position]
    holder.currencyName.text = currency.code
    holder.currencyValue.text = "0.0"
    holder.currencyValue.visibility = View.VISIBLE

    selectedCurrency?.let { c ->
      holder.currencyValue.text = String.format(
        "%.2f",
        currentValue * currency.rate / c.rate
      )
    }
  }

  private fun selectCurrency(currency: Currency?) {
    selectedCurrency = currency
    callback(currency)
  }

}
