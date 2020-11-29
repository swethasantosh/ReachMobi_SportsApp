package com.example.sports_reachmobi.AdapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.model.Leagues_Item_Model;
import com.example.sports_reachmobi.view.Util;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaguesListAdapter extends RecyclerView.Adapter<LeaguesListAdapter.LeaguesViewHolder>
{
    Context context;


    public ArrayList<Leagues_Item_Model> league_item ;

    public LeaguesListAdapter( ArrayList<Leagues_Item_Model> league_item)
    {
        this.league_item = league_item;
    }

    public void updateLeagues(ArrayList<Leagues_Item_Model> newLeagues)

    {
        league_item.clear();
        league_item.addAll(newLeagues);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public LeaguesListAdapter.LeaguesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_league,parent,false);

        return new LeaguesListAdapter.LeaguesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaguesListAdapter.LeaguesViewHolder holder, int position) {
        // holder.bind(sport_item.get(position));
        holder.bind(league_item.get(position),holder);

    }

    @Override
    public int getItemCount() {
        return league_item.size();
    }

    //class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    class LeaguesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.league_thumb)
        ImageView leagueImage;
        @BindView(R.id.league_name)
        TextView leagueName;
        @BindView(R.id.league_format)
        TextView leagueFormat;

        public LeaguesViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ButterKnife.bind(this,itemView);



            //itemView.setOnClickListener(this);
        }

        void bind(Leagues_Item_Model league_model , LeaguesListAdapter.LeaguesViewHolder holder)
        {
            leagueName.setText(league_model.getLeagueName());
            //sportFormat.setText(event_model.getSportFormat());
            leagueFormat.setText(league_model.getLeagueCountry());

            //sportFormat.setText(sport_model.getSportId());

            /*Glide.with(holder.itemView.getContext())
                    .load(sport_model.getSportThumb())
                    .into(sportImage);*/
            Util.loadImage(leagueImage, league_model.getLeagueImage(), Util.getProgressDrawable(leagueImage.getContext()));

        }


       /* @Override
        public void onClick(View view)
        {
            //Intent detailActivity = new Intent(MainActivity., Sports_DetailsActivity.class);
            detailActivity.putExtra("sportName",sportName.getText());
            detailActivity.putExtra("sportId",sportFormat.getText());
            detailActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(detailActivity);
        }*/
    }
}

