package com.example.elishevak.training;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FactFragment extends Fragment {
    private static final String HEADER = "header";
    private static final String CONTENT = "content";
    private static final String IMAGE = "image";

    public FactFragment() {

    }

    /**
     * @return A new instance of fragment FactFragment.
     */
    public static FactFragment newInstance(int index, Fact fact) {
        FactFragment fragment = new FactFragment();
        Bundle args = new Bundle();
        args.putString(HEADER, fact.getHeader());
        args.putString(CONTENT, fact.getContent());
        args.putInt(IMAGE, fact.getImage());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_fact, container, false);

        TextView headerText = (TextView) rootView.findViewById(R.id.fragmant_header);
        headerText.setText(getArguments().getString(HEADER));

        TextView contentText = (TextView) rootView.findViewById(R.id.fragmant_content);
        contentText.setText(getArguments().getString(CONTENT));

        ImageView imageView = (ImageView) rootView.findViewById(R.id.fragment_image);
        imageView.setImageResource(getArguments().getInt(IMAGE));
        return rootView;
    }
}
