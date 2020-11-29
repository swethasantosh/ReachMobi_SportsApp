package com.example.sports_reachmobi.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sports_reachmobi.R;
import com.example.sports_reachmobi.model.Sports_Item_Model;
import java.util.ArrayList;
import java.util.Collection;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

//public class SportListAdapter extends RecyclerView.Adapter<SportListAdapter.SportViewHolder>
public class SportListAdapter extends RecyclerView.Adapter<SportListAdapter.SportViewHolder> implements Filterable

{
    //Context context;



    //public ArrayList<Sports_Item_Model> filteredlist = new ArrayList<>();

    public ArrayList<Sports_Item_Model> unfilteredlist;


    CustomFilter customFilter;



    public ArrayList<Sports_Item_Model> sport_item ;//data



    public SportListAdapter( ArrayList<Sports_Item_Model> sport_item)
    {
        this.sport_item = sport_item;


        /*this.filteredlist = sport_item;
        this.customFilter = new CustomFilter();*/

        unfilteredlist = sport_item;
        //filteredlist = new ArrayList<>(sport_item);//backup






        customFilter = new CustomFilter();




    }

    public void updateSports(ArrayList<Sports_Item_Model> newSports)

    {
        sport_item.clear();
        sport_item.addAll(newSports);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public SportListAdapter.SportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sport,parent,false);

        return new SportListAdapter.SportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SportListAdapter.SportViewHolder holder, int position) {
       // holder.bind(sport_item.get(position));



        holder.bind(sport_item.get(position),holder);
       // holder.bind(filteredlist.get(position),holder);






    }

    @Override
    public int getItemCount()
    {


        return sport_item.size();


        //return filteredlist.size();

    }





    @Override
    public Filter getFilter()
    {
       /* if(customFilter == null)
        {
            customFilter = new CustomFilter();
        }*/
        //return null;
        return  customFilter;
    }


    public class CustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {
            FilterResults filterResults = new FilterResults();
            //ArrayList<Sports_Item_Model> newList = sport_item;
            //ArrayList<Sports_Item_Model> newList = filteredlist;

            ArrayList<Sports_Item_Model> resultList = new ArrayList<>();

            String searchString = charSequence.toString().toLowerCase();



            if (searchString.isEmpty())
            {
                resultList.addAll(unfilteredlist);//backup
                //updateSports(sport_item);
                Log.d("unfilteredlist", unfilteredlist + "values");

            }
            else {


                //for (int i = 0; i < newList.size(); i++)
                for(Sports_Item_Model model : unfilteredlist)
                {
                    if(model.getSportName().toLowerCase().contains(searchString))
                    {
                        resultList.add(model);
                    }
                    //Sports_Item_Model filtered_sport = newList.get(i);
                   /* String sportText = filtered_sport.getSportName();
                    if (sportText.toLowerCase().contains(searchString))
                    {
                        resultList.add(filtered_sport);
                    }*/

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
            //filteredlist = (ArrayList<Sports_Item_Model>) filterResults.values;

            //notifyDataSetChanged();
           // updateSports(filteredlist);
            updateSports((ArrayList<Sports_Item_Model>) filterResults.values);


        }
    }







    //class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    class SportViewHolder extends RecyclerView.ViewHolder {

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
            //sportFormat.setText(sport_model.getSportId());

            /*Glide.with(holder.itemView.getContext())
                    .load(sport_model.getSportThumb())
                    .into(sportImage);*/
            Util.loadImage(sportImage, sport_model.getSportThumb(), Util.getProgressDrawable(sportImage.getContext()));

        }


    }
}
