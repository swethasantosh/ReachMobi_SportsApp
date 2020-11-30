package com.example.sports_reachmobi.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.databinding.ItemSportBinding;
import com.example.sports_reachmobi.model.Sports_Item_Model;
import java.util.ArrayList;
import java.util.Collection;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//public class SportListAdapter extends RecyclerView.Adapter<SportListAdapter.SportViewHolder>
public class SportListAdapter extends RecyclerView.Adapter<SportListAdapter.SportViewHolder> implements Filterable

{
    //Context context;
    //public ArrayList<Sports_Item_Model> unfilteredlist = new ArrayList<>();

    public ArrayList<Sports_Item_Model> unfilteredlist;

    CustomFilter customFilter;

    public ArrayList<Sports_Item_Model> sport_item ;//data

    Context context = null;

    public SportListAdapter(Context context, ArrayList<Sports_Item_Model> sport_item)
    {
        this.context = context;
        this.sport_item = sport_item;
        unfilteredlist = new ArrayList<>(sport_item);
        //filteredlist = new ArrayList<>(sport_item);//backup
        customFilter = new CustomFilter();
    }

    public void updateSports(ArrayList<Sports_Item_Model> newSports)

    {
        if(newSports != null) {
            sport_item.clear();
            sport_item.addAll(newSports);
            if(unfilteredlist == null || unfilteredlist.size() == 0) {
                unfilteredlist.addAll(newSports);
            }
            notifyDataSetChanged();
        }

    }
    @NonNull
    @Override
    public SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SportViewHolder
                (ItemSportBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SportListAdapter.SportViewHolder holder, int position) {
        // holder.bind(sport_item.get(position));
        holder.binding.setPosition(position);
        holder.bind(sport_item.get(position));
        //holder.bind(unfilteredlist.get(position),holder);

        // holder.bind(filteredlist.get(position),holder);
    }

    @Override
    public int getItemCount()
    {
        return sport_item.size();
        //return unfilteredlist.size();

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

            ArrayList<Sports_Item_Model> resultList = new ArrayList<>();

            String searchString = charSequence.toString().toLowerCase();



            if (searchString.isEmpty())
            {
                resultList.addAll(unfilteredlist);//backup
                //updateSports(unfilteredlist);
                Log.d("unfilteredlist", unfilteredlist + "values");

            }
            else {

                for(Sports_Item_Model model : unfilteredlist)
                //for(Sports_Item_Model model : sport_item)

                {
                    if(model.getSportName().toLowerCase().contains(searchString))
                    {
                        resultList.add(model);
                    }

                }

            }
            updateSports(resultList);

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            //filteredlist = (ArrayList<Sports_Item_Model>) filterResults.values;

            //notifyDataSetChanged();
            // updateSports(filteredlist);
            updateSports((ArrayList<Sports_Item_Model>) filterResults.values);



        }
    }

    //class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    class SportViewHolder extends RecyclerView.ViewHolder {

        ItemSportBinding binding;

        public SportViewHolder(ItemSportBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Sports_Item_Model sport_model)
        {
            binding.setSportsAdapter(SportListAdapter.this);
            binding.sportName.setText(sport_model.getSportName());
            binding.sportFormat.setText(sport_model.getSportFormat());
            Util.loadImage(binding.sportThumb, sport_model.getSportThumb(), Util.getProgressDrawable(binding.sportThumb.getContext()));
            binding.executePendingBindings();
        }


    }

    public void onItemClick(View view, int position)
    {
        new AlertDialog.Builder(view.getContext()).setMessage(sport_item.get(position).getSportName()).show();

    }
}
