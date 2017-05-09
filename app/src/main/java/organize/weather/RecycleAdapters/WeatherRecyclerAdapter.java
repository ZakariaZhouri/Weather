package organize.weather.RecycleAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import organize.weather.Models.ListWeather;
import organize.weather.R;

/**
 * @author ZHOURI Zakaria
 */

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerViewHolder> {

    private List<ListWeather> mList;
    private Context mContext;

    public WeatherRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public WeatherRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherRecyclerViewHolder(view,mContext);

    }

    @Override
    public void onBindViewHolder(WeatherRecyclerViewHolder holder, int position) {
        holder.bindInformation(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setList(List<ListWeather> list) {
        this.mList = list;

    }

}
