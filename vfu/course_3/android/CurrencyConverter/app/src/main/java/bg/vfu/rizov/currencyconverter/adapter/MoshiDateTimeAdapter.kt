package bg.vfu.rizov.currencyconverter.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat

class MoshiDateTimeAdapter {

  @ToJson
  fun toJson(dateTime: DateTime) = ISODateTimeFormat.dateTime().withZoneUTC().print(dateTime)

  @FromJson
  fun fromJson(json: String) = ISODateTimeFormat.dateTime().parseDateTime(json)
}
