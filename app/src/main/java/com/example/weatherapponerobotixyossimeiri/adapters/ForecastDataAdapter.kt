package com.example.weatherapponerobotixyossimeiri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapponerobotixyossimeiri.databinding.DailyForecastBinding
import com.example.weatherapponerobotixyossimeiri.models.DailyWeatherResponse
import com.example.weatherapponerobotixyossimeiri.strings.WeatherStrings
import com.example.weatherapponerobotixyossimeiri.utils.GenericUtils
import com.squareup.picasso.Picasso
import java.util.Calendar
import java.util.Locale

class ForecastDataAdapter(
    private val weatherDataList: List<DailyWeatherResponse>
) : RecyclerView.Adapter<ForecastDataAdapter.ViewHolder>() {

    lateinit var binding: DailyForecastBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        binding = DailyForecastBinding.inflate(inflater, parent, false);
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding =  DailyForecastBinding.bind(holder.itemView);

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


        // Set the range value
        val range = String.format(WeatherStrings.DEGREES_RANGE, GenericUtils.kelvinToCelsius(weatherDataList[position].temp.min), GenericUtils.kelvinToCelsius(weatherDataList[position].temp.max))
        binding.rangeTv.text = range

        // Set weather description
        val desc = weatherDataList[position].weather[0].description;
        binding.descTv.text = desc;

        // Set the weather image
        val iconUrl : String = String.format(GenericUtils.iconUrlPlaceholder, weatherDataList[position].weather[0].icon);
        Picasso.get().load(iconUrl).into(binding.iconIv);
    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root);
}