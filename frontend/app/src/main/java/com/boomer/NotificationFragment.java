package com.boomer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class NotificationFragment extends Fragment {
    private ArrayList<NotificationHelperClass> notifications = new ArrayList<>();
    private ValueEventListener notificationsListener;
    FirebaseDatabase database;
    private NotificationsAdapter notificationsAdapter = new NotificationsAdapter();

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         database = FirebaseDatabase.getInstance();
        notificationsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()){
                    for(DataSnapshot snap : snapshot.getChildren()){
                        Type token = new TypeToken<NotificationHelperClass>(){}.getType();
                        NotificationHelperClass notification = new Gson().fromJson(new Gson().toJson(snap.getValue()), token);
                        notificationsAdapter.notifications.add(notification);
                        notificationsAdapter.notifyDataSetChanged();
                    }
                }
                else{
                    Toast.makeText(requireContext(), "You have no notifications", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        };
        database.getReference().child("notify").addValueEventListener(notificationsListener);

        RecyclerView recyclerView = view.findViewById(R.id.notificationRecycler);
        recyclerView.setAdapter(notificationsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    public void onPause() {
        super.onPause();
        database.getReference().child("notify").removeEventListener(notificationsListener);
    }
}