package com.example.sports_reachmobi.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sports_reachmobi.R;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class Util
{
    public static void loadImage(ImageView view, String url, CircularProgressDrawable circularProgressDrawable)
    {
        RequestOptions options = new RequestOptions()
                                    .placeholder(circularProgressDrawable)
                                    .error(R.mipmap.ic_launcher_round);
        //glides caching mechanism helps avoiding image reloading
        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(view);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context)
    {
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();
        return progressDrawable;
    }

}
