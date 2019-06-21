package bg.vfu.rizov.currencyconverter.main

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import bg.vfu.rizov.currencyconverter.R
import bg.vfu.rizov.currencyconverter.main.converter.ConverterFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

  private val mainViewModel by viewModel<MainViewModel>()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    mainViewModel.status.observe(this, Observer {
      if (it != null) {
        when (it.code) {
          MainViewModel.StatusCode.LOADING -> showLoading()
          MainViewModel.StatusCode.COMPLETED -> showContent()
          MainViewModel.StatusCode.ERROR -> showError { it.retryCallback?.invoke() }
        }
      }
    })

    mainViewModel.currencies.observe(this, Observer {
      if (it != null) {
        showFragment(ConverterFragment())
      }
    })

    mainViewModel.init()
  }

  private fun showLoading() {
    retryButton.setOnClickListener(null)
    errorLayout.visibility = View.GONE
    fragmentLayout.visibility = View.GONE
    progress.visibility = View.VISIBLE
  }

  private fun showContent() {
    retryButton.setOnClickListener(null)
    errorLayout.visibility = View.GONE
    fragmentLayout.visibility = View.VISIBLE
    progress.visibility = View.GONE
  }

  private fun showError(callback: RetryCallback) {
    retryButton.setOnClickListener { callback() }
    errorLayout.visibility = View.VISIBLE
    fragmentLayout.visibility = View.GONE
    progress.visibility = View.GONE
  }

  private fun showFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.fragmentLayout, fragment)
    transaction.commit()
  }
}
