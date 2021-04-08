package com.boomer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {
    public ArrayList<NotificationHelperClass> notifications = new ArrayList<>();
    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_list_row, parent, false);
        return new NotificationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        holder.bind(notifications.get(position));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    static class NotificationsViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private final TextView nTitle, nDesc;
        public NotificationsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.nImage);
            nTitle = itemView.findViewById(R.id.nTitle);
            nDesc = itemView.findViewById(R.id.nDesc);
        }

        public void bind(NotificationHelperClass notification){
            nTitle.setText(notification.title);
            nDesc.setText(notification.desc);
            //imageView.setImageResource(Integer.parseInt(notification.img));
        }
    }
}
