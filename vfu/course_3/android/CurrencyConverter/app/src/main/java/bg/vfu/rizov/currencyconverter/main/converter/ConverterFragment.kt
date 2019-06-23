package bg.vfu.rizov.currencyconverter.main.converter

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bg.vfu.rizov.currencyconverter.R
import bg.vfu.rizov.currencyconverter.main.BaseFragment
import bg.vfu.rizov.currencyconverter.main.MainViewModel
import bg.vfu.rizov.currencyconverter.main.selection.SelectionFragment
import bg.vfu.rizov.currencyconverter.model.Currency
import kotlinx.android.synthetic.main.fragment_converter.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ConverterFragment : BaseFragment() {

  private val mainViewModel by sharedViewModel<MainViewModel>()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_converter, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    editButton.setOnClickListener { showFragment(SelectionFragment()) }
    val currencies = mainViewModel.currencies.value ?: return
    val converterAdapter =
      ConverterAdapter(currencies) { currency: Currency? ->
        if (currency != null) {
          currentCurrencyText.visibility = View.VISIBLE
          currentCurrencyInput.visibility = View.VISIBLE
          currentCurrencyText.text = currency.code
          currentCurrencyInput.text.clear()
        } else {
          currentCurrencyText.visibility = View.GONE
          currentCurrencyInput.visibility = View.GONE
        }
        mainViewModel.storeCurrencies()
      }

    currencyConverterList.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(context)
      adapter = converterAdapter
    }

    currentCurrencyInput.addTextChangedListener(
      object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
          val number = try {
            s.toString().toDouble()
          } catch (e: Throwable) {
            0.0
          }
          converterAdapter.notifyValueChanged(number)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

      })
  }
}
