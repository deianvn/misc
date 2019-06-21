package bg.vfu.rizov.currencyconverter.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Currency(val code: String,
               val name: String,
               val rate: Double,
               val inverseRate: Double)
