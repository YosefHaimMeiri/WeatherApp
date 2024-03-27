package com.example.weatherapponerobotixyossimeiri.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapponerobotixyossimeiri.databinding.HourlyForecastBinding
import com.example.weatherapponerobotixyossimeiri.models.Hourly
import com.example.weatherapponerobotixyossimeiri.strings.WeatherStrings
import com.example.weatherapponerobotixyossimeiri.utils.GenericUtils
import com.example.weatherapponerobotixyossimeiri.utils.TimeUtils
import com.squareup.picasso.Picasso

class HourlyDataAdapter(private val hourlyDataList : List<Hourly>) :
    RecyclerView.Adapter<HourlyDataAdapter.ViewHolder>() {
    lateinit var binding: HourlyForecastBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyDataAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        binding = HourlyForecastBinding.inflate(inflater, parent, false);
        return ViewHolder()
    }

    override fun getItemCount(): Int {
        return hourlyDataList.size
    }

    override fun onBindViewHolder(holder: HourlyDataAdapter.ViewHolder, position: Int) {
        val binding =  HourlyForecastBinding.bind(holder.itemView);

        // Set the hou
        if (position == 0) {
            binding.timeTv.text = "Now"
        } else {
            val timeMillis = hourlyDataList[position].dt*1000 // Convert Unix to timestamp
            binding.timeTv.text = TimeUtils.convertUnixTimestampToHour(timeMillis)
        }

        // Set the temperature
        val temp = String.format(WeatherStrings.WEATHER_WITH_DEGREES, GenericUtils.kelvinToCelsius(hourlyDataList[position].temp))
        binding.tempTv.text = temp


        // Set the weather image
        val iconUrl : String = String.format(GenericUtils.iconUrlPlaceholder, hourlyDataList[position].weather[0].icon);
        Picasso.get().load(iconUrl).into(binding.iconTv);
    }

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root);

}