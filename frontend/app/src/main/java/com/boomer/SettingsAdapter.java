package com.boomer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class SettingsAdapter extends ArrayAdapter<Settings> {
    private int resource;
    private Context context;
    private List<Settings> list;

    public SettingsAdapter(@NonNull Context context, int resource, @NonNull List<Settings> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.list = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.setting_list_row, parent, false);
        }
        TextView title = convertView.findViewById(R.id.sTitle);
        TextView desc = convertView.findViewById(R.id.sDesc);
        title.setText(list.get(position).getTitle());
        desc.setText(list.get(position).getDesc());

        convertView.setOnClickListener(view -> {
            if (position == 5){
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.signOut();
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });
        return convertView;
    }

    //    private final int mResource;
//    public SettingsAdapter(@NonNull SettingsFragment context, String resource, @NonNull ArrayList<Settings> object) {
//        super(context, resource, object);
//        this.mFragment = context;
//        this.mResource = resource;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//
//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        convertView = layoutInflater.inflate(mResource, parent, false);
//
//        //ImageView imageView = convertView.findViewById(R.id.sIcon);
//        TextView sTitle = convertView.findViewById(R.id.sTitle);
//        TextView sDesc = convertView.findViewById(R.id.sDesc);
//
//        //imageView.setImageResource(getItem(position).getIcon());
//        sTitle.setText(getItem(position).getTitle());
//        sDesc.setText(getItem(position).getDesc());
//
//        return convertView;
//    }
}
