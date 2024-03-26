package com.example.weatherapponerobotixyossimeiri.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapponerobotixyossimeiri.databinding.ActivityMainBinding
import com.example.weatherapponerobotixyossimeiri.databinding.ForecastBinding
import com.example.weatherapponerobotixyossimeiri.models.Daily
import com.example.weatherapponerobotixyossimeiri.models.WeatherDataResponse
import com.example.weatherapponerobotixyossimeiri.strings.WeatherStrings
import com.example.weatherapponerobotixyossimeiri.utils.GenericUtils
import com.example.weatherapponerobotixyossimeiri.utils.WeatherCodeUtils
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class ForecastDataAdapter(
    private val weatherDataList: List<Daily>
) : RecyclerView.Adapter<ForecastDataAdapter.ViewHolder>() {

    lateinit var binding: ForecastBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        binding = ForecastBinding.inflate(inflater, parent, false);
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding =  ForecastBinding.bind(holder.itemView);

        // Set the day

        if (position == 0) {
            binding.dayTv.text = "Today"
        } else {
            val timeMillis = weatherDataList[position].dt*1000 // Convert Unix to timestamp
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeMillis;
            val dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH)
            binding.dayTv.text = dayOfWeek;
        }


        // Set the low value
        val low = String.format(WeatherStrings.LOW_WEATHER_WITH_DEGREES, GenericUtils.kelvinToCelsius(weatherDataList[position].temp.min))
        binding.lowTv.text = low

        // Set high value
        val high = String.format(WeatherStrings.HIGH_WEATHER_WITH_DEGREES, GenericUtils.kelvinToCelsius(weatherDataList[position].temp.max))
        binding.highTv.text = high

        // Set weather description
        val desc = weatherDataList[position].weather[0].description;
        binding.descTv.text = desc;

        // Set the weather image
        val iconUrl : String = String.format(WeatherCodeUtils.iconUrlPlaceholder, weatherDataList[position].weather[0].icon);
        Picasso.get().load(iconUrl).into(binding.iconIv);
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root);
}