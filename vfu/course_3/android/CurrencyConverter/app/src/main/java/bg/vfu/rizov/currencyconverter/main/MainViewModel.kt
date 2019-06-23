package bg.vfu.rizov.currencyconverter.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bg.vfu.rizov.currencyconverter.model.Currency
import bg.vfu.rizov.currencyconverter.repo.CurrencyRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

internal typealias RetryCallback = () -> Unit

class MainViewModel(
  private val currencyRepo: CurrencyRepo
) : ViewModel() {

  private val compositeDisposable = CompositeDisposable()


  val status = MutableLiveData<Status>()

  val allCurrencies = MutableLiveData<Map<String, Currency>>()

  val currencies = MutableLiveData<MutableList<Currency>>()

  override fun onCleared() {
    super.onCleared()
    compositeDisposable.clear()
  }

  fun init() {
    status.value = Status.loading()
    compositeDisposable.add(
      currencyRepo.getAllCurrencies()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({
          if (it != null) {
            Timber.i("All currencies received.")
            val editable = it.toMutableMap()
            editable["eur"] = Currency("EUR",
              "Euro", 1.0, 1.0)
            val all = editable.toMap()
            allCurrencies.value = all
            updateCurrencies(all)
          } else {
            Timber.w("Currencies not available")
            status.value = Status.error { init() }
          }
        }, {
          Timber.w(it)
          status.value = Status.error { init() }
        })
    )
  }

  fun addCurrency(currency: Currency) {
    val selectedCurrencies = currencies.value
    if (selectedCurrencies == null) {
      currencies.value = mutableListOf()
    } else if (selectedCurrencies.none { c -> currency.code == c.code }) {
      selectedCurrencies.add(currency)
      currencyRepo.storeSelectedCurrencies(selectedCurrencies)
      currencies.value = selectedCurrencies
    }
  }

  fun selectCurrency(code: String) {
    val selectedCurrencies = currencies.value
    if (selectedCurrencies != null) {
      val index = selectedCurrencies.indexOfFirst { it.code == code }
      if (index != -1 && index != 0) {
        val elem = selectedCurrencies[index]
        selectedCurrencies[index] = selectedCurrencies[0]
        selectedCurrencies[0] = elem
      }
    }
  }

  fun storeCurrencies() {
    currencyRepo.storeSelectedCurrencies(currencies.value ?: mutableListOf())
  }

  private fun updateCurrencies(allCurrencies: Map<String, Currency>) {
    compositeDisposable.add(
      currencyRepo.getSelectedCurrencies()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe({
          if (it != null) {
            val selectedCurrencies = it
              .mapNotNull { c -> allCurrencies[c.code.toLowerCase()] }
              .toMutableList()
            this.currencies.value = selectedCurrencies
            status.value = Status.completed()
          } else {
            Timber.w("Currencies not available.")
            status.value = Status.error { init() }
          }
        }, {
          Timber.w(it)
          status.value = Status.error { init() }
        })
    )
  }

  enum class StatusCode {
    LOADING, COMPLETED, ERROR
  }

  class Status(
    val code: StatusCode,
    val retryCallback: RetryCallback? = null
  ) {

    companion object {
      fun loading() = Status(StatusCode.LOADING)
      fun completed() = Status(StatusCode.COMPLETED)
      fun error(callback: RetryCallback) = Status(StatusCode.ERROR, callback)
    }
  }
}
