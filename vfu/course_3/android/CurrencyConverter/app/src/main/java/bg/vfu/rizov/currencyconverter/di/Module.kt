package bg.vfu.rizov.currencyconverter.di

import bg.vfu.rizov.currencyconverter.adapter.MoshiDateTimeAdapter
import bg.vfu.rizov.currencyconverter.main.MainViewModel
import bg.vfu.rizov.currencyconverter.repo.CurrencyApi
import bg.vfu.rizov.currencyconverter.repo.CurrencyRepo
import bg.vfu.rizov.currencyconverter.repo.SharedPrefStorage
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val Module = module {

  single {
    Moshi.Builder()
      .add(MoshiDateTimeAdapter())
      .build()
  }

  single {
    val builder = OkHttpClient
      .Builder()
      .readTimeout(10, TimeUnit.SECONDS)
      .writeTimeout(10, TimeUnit.SECONDS)
      .connectTimeout(10, TimeUnit.SECONDS)

    builder.build() as OkHttpClient
  }

  single {
    Retrofit.Builder()
      .baseUrl("http://www.floatrates.com/daily/")
      .client(get())
      .addConverterFactory(MoshiConverterFactory.create(get()))
      .build()
  }

  single {
    get<Retrofit>()
      .create(CurrencyApi::class.java) as CurrencyApi
  }

  single {
    SharedPrefStorage(get(), get())
  }

  single {
    CurrencyRepo(get(), get(), get())
  }

  viewModel {
    MainViewModel(get())
  }

}
