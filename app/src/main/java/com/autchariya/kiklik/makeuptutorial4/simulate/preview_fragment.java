package com.autchariya.kiklik.makeuptutorial4.simulate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.autchariya.kiklik.makeuptutorial4.R;
import com.bumptech.glide.Glide;

/**
 * A simple {@link Fragment} subclass.
 */
public class preview_fragment extends Fragment {


    public preview_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        byte[] decode_image64 = getArguments().getByteArray("decode_image64");
        View rootview = inflater.inflate(R.layout.fragment_preview_fragment, container, false);
        ImageView imageView =  rootview.findViewById(R.id.preview_image);

        Glide.with(preview_fragment.this)
                .asBitmap()
                .load(decode_image64)
                //.apply(new RequestOptions().override(500,500))
                .into(imageView);

        return rootview;
    }

}
