package bg.vfu.rizov.currencyconverter.main

import android.support.v4.app.Fragment
import bg.vfu.rizov.currencyconverter.R

abstract class BaseFragment : Fragment() {

  fun showFragment(fragment: Fragment) {
    activity?.supportFragmentManager
      ?.beginTransaction()
      ?.replace(R.id.fragmentLayout, fragment)
      ?.addToBackStack(null)
      ?.commit()
  }
}
