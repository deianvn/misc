package bg.vfu.rizov.currencyconverter.repo

import bg.vfu.rizov.currencyconverter.model.Currency
import com.squareup.moshi.Moshi
import io.reactivex.Single
import org.joda.time.DateTime
import java.lang.IllegalStateException
import com.squareup.moshi.Types

class CurrencyRepo(
  private val moshi: Moshi,
  private val sharedPrefStorage: SharedPrefStorage,
  private val currencyApi: CurrencyApi
) {

  fun getAllCurrencies(): Single<Map<String, Currency>> = Single.create {
    val lastUpdated = sharedPrefStorage.get(SharedPrefStorage.KEY_LAST_UPDATED)
    val dateTimeAdapter = moshi.adapter(DateTime::class.java)

    val isObsolete = if (lastUpdated != null)
      dateTimeAdapter.fromJson(lastUpdated)?.plusDays(1)?.isBeforeNow ?: true
    else true

    if (isObsolete) {
      val jsonResponse = currencyApi.getCurrencies().execute()
      if (jsonResponse.isSuccessful) {
        sharedPrefStorage.put(
          SharedPrefStorage.KEY_LAST_UPDATED,
          dateTimeAdapter.toJson(DateTime.now())
        )
        val body = jsonResponse.body()?.string()
        if (body != null) {
          sharedPrefStorage.put(SharedPrefStorage.KEY_ALL_CURRENCIES, body)
        }
      } else {
        throw IllegalStateException("Could not connect to rest api")
      }
    }

    val mapType =
      Types.newParameterizedType(Map::class.java, String::class.java, Currency::class.java)
    val jsonAdapter = moshi.adapter<Map<String, Currency>>(mapType)
    val json = sharedPrefStorage.get(SharedPrefStorage.KEY_ALL_CURRENCIES)
    if (json != null) {
      val currencies = jsonAdapter.fromJson(json)
      if (currencies != null) {
        it.onSuccess(currencies)
      } else {
        throw IllegalStateException("Could not parse json")
      }
    } else {
      throw IllegalStateException("Could not load data from shared preferences")
    }
  }

  fun getSelectedCurrencies(): Single<MutableList<Currency>> = Single.create {
    val json = sharedPrefStorage.get(SharedPrefStorage.KEY_CURRENCIES)
    if (json != null) {
      val listType = Types.newParameterizedType(MutableList::class.java, Currency::class.java)
      val jsonAdapter = moshi.adapter<MutableList<Currency>>(listType)
      val currencies = jsonAdapter.fromJson(json)
      if (currencies != null) {
        it.onSuccess(currencies)
      } else {
        throw IllegalStateException("Could not parse currencies")
      }
    } else {
      it.onSuccess(mutableListOf())
    }
  }

  fun storeSelectedCurrencies(currencies: MutableList<Currency>) {
    val listType = Types.newParameterizedType(MutableList::class.java, Currency::class.java)
    val jsonAdapter = moshi.adapter<MutableList<Currency>>(listType)
    sharedPrefStorage.put(SharedPrefStorage.KEY_CURRENCIES, jsonAdapter.toJson(currencies))
  }

}
