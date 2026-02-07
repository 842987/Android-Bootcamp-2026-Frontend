package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapters.RecyclerViewAdapterMeetings;
import com.example.myapplication.models.recycler.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapterMeetings adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meeting_fragment, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_meetings);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Meeting> meetings = new ArrayList<>();
        for(int i = 0;i < 20;i++)
            meetings.add(new Meeting("Совещание " + i, "Иван" + i, "ожидает", "15:00"));

        adapter = new RecyclerViewAdapterMeetings(meetings);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
