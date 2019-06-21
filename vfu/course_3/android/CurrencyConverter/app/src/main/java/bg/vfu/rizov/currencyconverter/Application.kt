package bg.vfu.rizov.currencyconverter

import android.app.Application
import bg.vfu.rizov.currencyconverter.di.Module
import net.danlew.android.joda.JodaTimeAndroid
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class Application : Application() {

  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
    JodaTimeAndroid.init(this)
    startKoin(this, listOf(Module))
  }
}

