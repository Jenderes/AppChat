package com.example.appchatgroup;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class RequestsFragment extends Fragment {
    private View RequstsFragmentView;
    private RecyclerView mRecyclerView;

    public RequestsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RequstsFragmentView =  inflater.inflate(R.layout.fragment_requests, container, false);
        mRecyclerView = (RecyclerView)RequstsFragmentView.findViewById(R.id.chat_requests_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return RequstsFragmentView;
    }

}
