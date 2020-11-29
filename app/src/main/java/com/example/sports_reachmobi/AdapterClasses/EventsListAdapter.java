package com.example.sports_reachmobi.AdapterClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.model.Event_Item_Model;
import com.example.sports_reachmobi.model.Sports_Item_Model;
import com.example.sports_reachmobi.view.SportListAdapter;
import com.example.sports_reachmobi.view.Util;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventViewHolder> implements Filterable
{
    Context context;


    public ArrayList<Event_Item_Model> unfilteredlist;
    CustomFilter customFilter;


    public ArrayList<Event_Item_Model> event_item ;

    public EventsListAdapter( ArrayList<Event_Item_Model> event_item)
    {
        this.event_item = event_item;

        unfilteredlist = event_item;
        customFilter = new CustomFilter();

    }

    public void updateEvents(ArrayList<Event_Item_Model> newEvents)

    {
        event_item.clear();
        event_item.addAll(newEvents);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public EventsListAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event,parent,false);

        return new EventsListAdapter.EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsListAdapter.EventViewHolder holder, int position) {
        holder.bind(event_item.get(position),holder);

    }

    @Override
    public int getItemCount() {
        return event_item.size();
    }

    @Override
    public Filter getFilter()
    {
        return  customFilter;
    }


    public class CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {
            FilterResults filterResults = new FilterResults();

            ArrayList<Event_Item_Model> resultList = new ArrayList<>();

            String searchString = charSequence.toString().toLowerCase();

            if (searchString.isEmpty())
            {
                resultList.addAll(unfilteredlist);//backup
                Log.d("unfilteredlist", unfilteredlist + "values");

            }
            else {

                for(Event_Item_Model model : unfilteredlist)
                {
                    if(model.getEventName().toLowerCase().contains(searchString))
                    {
                        resultList.add(model);
                    }

                }

            }
            filterResults.count = resultList.size();
            filterResults.values = resultList;
            Log.d("filteredlist", filterResults.values + "values");



            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            updateEvents((ArrayList<Event_Item_Model>) filterResults.values);

        }
    }



    class EventViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.event_thumb)
        ImageView sportImage;
        @BindView(R.id.sport_name)
        TextView sportName;
        @BindView(R.id.sport_format)
        TextView sportFormat;

        public EventViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }

        void bind(Event_Item_Model event_model , EventsListAdapter.EventViewHolder holder)
        {
            sportName.setText(event_model.getSportName());
            sportFormat.setText(event_model.getLeagueName());

            Util.loadImage(sportImage, event_model.getEventImage(), Util.getProgressDrawable(sportImage.getContext()));

        }

    }
}
