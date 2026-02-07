package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.models.recycler.Meeting;

import java.util.List;

class RecyclerViewHolderMeetings extends RecyclerView.ViewHolder {

    TextView title, owner, status, time;
    public RecyclerViewHolderMeetings(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        owner = itemView.findViewById(R.id.owner);
        status = itemView.findViewById(R.id.status);
        time = itemView.findViewById(R.id.time);
    }
}
public class RecyclerViewAdapterMeetings extends RecyclerView.Adapter<RecyclerViewHolderMeetings>{

    private List<Meeting> meetings;

    public RecyclerViewAdapterMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @NonNull
    @Override
    public RecyclerViewHolderMeetings onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_meetings_item, parent, false);
        return new RecyclerViewHolderMeetings(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderMeetings holder, int position) {
        Meeting meeting = meetings.get(position);
        holder.title.setText(meeting.getTitle());
        holder.owner.setText("Владелец: " + meeting.getOwnerName());
        holder.status.setText(meeting.getStatus());
        holder.time.setText(meeting.getTime());
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }
}
