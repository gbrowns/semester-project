package com.boomer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class SettingsFragment extends Fragment {
    ListView listView;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.settingListView);

        ArrayList<Settings> settings = new ArrayList<>();
        //add setting titles and description
        settings.add(new Settings("Edit profile", "Reset password and update username"));
        settings.add(new Settings("Account setting and preferences", "Update your preferences"));
        settings.add(new Settings("Terms and Condition", "Get privacy policies"));
        settings.add(new Settings("Update(s)", "Whats new in Boomer"));
        settings.add(new Settings("Themes", "Change to dark/bright mode"));
        settings.add(new Settings("Logout", "End session"));

        //create instance of setting adapter
        SettingsAdapter settingsAdapter = new SettingsAdapter(requireContext(),R.layout.setting_list_row,settings);
        listView.setAdapter(settingsAdapter);
    }
}