package com.example.appchatgroup;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class GroupsFragment extends Fragment {

    private View groupfragmentView;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> listGroups = new ArrayList<String>();

    private DatabaseReference GroupRef;

    public GroupsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        groupfragmentView = inflater.inflate(R.layout.fragment_groups, container, false);

        GroupRef = FirebaseDatabase.getInstance().getReference().child("Groups");

        IntializeField();

        RetrieveAndDisplayGroups();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String currentGroupName = adapterView.getItemAtPosition(i).toString();

                Intent groupChatIntent = new Intent(getContext(),GroupChatActivity.class);
                groupChatIntent.putExtra("GroupName",currentGroupName);
                startActivity(groupChatIntent);
            }
        });

        return groupfragmentView;
    }

    private void IntializeField() {
          listView = (ListView)groupfragmentView.findViewById(R.id.list_view);
          arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,listGroups);
          listView.setAdapter(arrayAdapter);

    }

    private void RetrieveAndDisplayGroups() {
        GroupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<>();
                Iterator iterator = dataSnapshot.getChildren().iterator();

                while (iterator.hasNext()){
                    set.add(((DataSnapshot)iterator.next()).getKey());
                }
                listGroups.clear();
                listGroups.addAll(set);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
