package com.cuongpq.basemvp.view.base.customview;


import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cuongpq.basemvp.R;

public class LoadView {
    public static void loadImageGlide(String urlImage, AppCompatImageView view){
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(view)
                .load(urlImage)
                .apply(options)
                .into(view);
    }
}
