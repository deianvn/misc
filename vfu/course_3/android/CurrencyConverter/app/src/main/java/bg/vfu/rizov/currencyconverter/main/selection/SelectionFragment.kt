package bg.vfu.rizov.currencyconverter.main.selection

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bg.vfu.rizov.currencyconverter.R
import bg.vfu.rizov.currencyconverter.main.BaseFragment
import bg.vfu.rizov.currencyconverter.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_selection.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SelectionFragment : BaseFragment() {

  private val mainViewModel by sharedViewModel<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_selection, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val currencies = mainViewModel.allCurrencies.value ?: return

    currencyList.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(context)
      adapter = SelectionAdapter(currencies) {
        mainViewModel.addCurrency(it)
      }
    }

  }

}
