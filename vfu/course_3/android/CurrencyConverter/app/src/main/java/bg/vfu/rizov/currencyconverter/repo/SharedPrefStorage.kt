package bg.vfu.rizov.currencyconverter.repo

import android.content.Context
import com.squareup.moshi.Moshi

class SharedPrefStorage(private val context: Context, private val moshi: Moshi) {

  companion object {

    const val KEY_LAST_UPDATED = "last_updated"
    const val KEY_ALL_CURRENCIES = "all_currencies"
    const val KEY_CURRENCIES = "currencies"

    private const val DEFAULT_APP_STORAGE = "bg.vfu.rizov.currencyconverter.storage"
  }

  fun put(key: String, message: String, storage: String = DEFAULT_APP_STORAGE) {
    context.getSharedPreferences(storage, Context.MODE_PRIVATE)
      .edit()
      .putString(key, message)
      .apply()
  }

  fun get(key: String, storage: String = DEFAULT_APP_STORAGE): String? {
    return context.getSharedPreferences(storage, Context.MODE_PRIVATE).getString(key, null)
  }

}
