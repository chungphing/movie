package com.chungphing.khcinema.ui;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by chungphing
 */

public class BindingAdapters{
    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show){
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }
//
//    @BindingAdapter("imageUrl")
//    public static void setImageUrl(ImageView imageView, String url){
//        Context context = imageView.getContext();
//        Glide.with(context).load(url).into(imageView);
//    }
}
