package com.example.sports_reachmobi.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.model.Sports_Item_Model;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SportListAdapter extends RecyclerView.Adapter<SportListAdapter.SportViewHolder>
{
    public ArrayList<Sports_Item_Model> sport_item ;

    public SportListAdapter( ArrayList<Sports_Item_Model> sport_item)
    {
        this.sport_item = sport_item;
    }

    public void updateSports(ArrayList<Sports_Item_Model> newSports)

    {
        sport_item.clear();
        sport_item.addAll(newSports);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport,parent,false);

        return new SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportViewHolder holder, int position) {
       // holder.bind(sport_item.get(position));
        holder.bind(sport_item.get(position),holder);


    }

    @Override
    public int getItemCount() {
        return sport_item.size();
    }

    class SportViewHolder extends RecyclerView.ViewHolder
    {
        @BindView(R.id.sport_thumb)
        ImageView sportImage;
        @BindView(R.id.sport_name)
        TextView sportName;
        @BindView(R.id.sport_format)
        TextView sportFormat;

        public SportViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(Sports_Item_Model sport_model ,SportViewHolder holder)
        {
            sportName.setText(sport_model.getSportName());
            sportFormat.setText(sport_model.getSportFormat());
            /*Glide.with(holder.itemView.getContext())
                    .load(sport_model.getSportThumb())
                    .into(sportImage);*/
            Util.loadImage(sportImage, sport_model.getSportThumb(), Util.getProgressDrawable(sportImage.getContext()));

        }


    }
}
