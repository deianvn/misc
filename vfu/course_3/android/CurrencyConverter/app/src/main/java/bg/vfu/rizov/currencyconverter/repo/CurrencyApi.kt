package bg.vfu.rizov.currencyconverter.repo

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyApi {

  @GET("eur.json")
  fun getCurrencies(): Call<ResponseBody>

}
