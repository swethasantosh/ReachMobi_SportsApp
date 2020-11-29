package com.example.sports_reachmobi.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.model.Event_Item_Model;
import com.example.sports_reachmobi.view.Util;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventViewHolder>
{
    Context context;


    public ArrayList<Event_Item_Model> event_item ;

    public EventsListAdapter( ArrayList<Event_Item_Model> event_item)
    {
        this.event_item = event_item;
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
        // holder.bind(sport_item.get(position));
        holder.bind(event_item.get(position),holder);

    }

    @Override
    public int getItemCount() {
        return event_item.size();
    }

    //class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    class EventViewHolder extends RecyclerView.ViewHolder {

        //@BindView(R.id.sport_thumb)
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
            //sportFormat.setText(event_model.getSportFormat());
            sportFormat.setText(event_model.getLeagueName());

            //sportFormat.setText(sport_model.getSportId());

            /*Glide.with(holder.itemView.getContext())
                    .load(sport_model.getSportThumb())
                    .into(sportImage);*/
            Util.loadImage(sportImage, event_model.getEventImage(), Util.getProgressDrawable(sportImage.getContext()));

        }

    }
}
