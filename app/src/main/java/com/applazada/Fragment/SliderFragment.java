package com.applazada.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applazada.Activity.HomeActivity;
import com.applazada.Models.Photo;
import com.applazada.R;
import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.List;


public class SliderFragment extends Fragment {

    private View mView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       mView = inflater.inflate(R.layout.fragment_slider, container, false);
       Bundle bundle = getArguments();
        Photo photo = (Photo) bundle.get("photo");
        Context context = mView.getContext();
        ShapeableImageView item_slider = mView.findViewById(R.id.slider_img);
        Glide.with(context).load(photo.getImg_url()).placeholder(R.drawable.ic_launcher_background).into(item_slider);
       return mView;
    }
}