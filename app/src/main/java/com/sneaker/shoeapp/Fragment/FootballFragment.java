package com.sneaker.shoeapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sneaker.shoeapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FootballFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FootballFragment extends Fragment {

    public FootballFragment() {
        // Required empty public constructor
    }
    public static FootballFragment newInstance() {
        return new FootballFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_football, container, false);
    }
}