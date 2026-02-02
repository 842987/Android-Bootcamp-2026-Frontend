package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.activities.LoginActivity;

public class ProfileFragment extends Fragment {

    private EditText email, surname, password;
    private Button buttonSave, buttonExit;

    private TextView textGreeting;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        email = view.findViewById(R.id.email);
        surname = view.findViewById(R.id.surname);
        password = view.findViewById(R.id.password);

        buttonSave = view.findViewById(R.id.btn_save);
        buttonExit = view.findViewById(R.id.btn_exit);

        textGreeting = view.findViewById(R.id.text_greeting);

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // =============
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}
