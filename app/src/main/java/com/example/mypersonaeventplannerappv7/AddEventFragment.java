package com.example.mypersonaeventplannerappv7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddEventFragment extends Fragment {
    public EditText Datetext;
    public EditText TitleText;
    public EditText LocationText;
    public EditText TimeText;
    public EditText CategoryText;
    public Button AEUEButton;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        Button button = view.findViewById(R.id.AEUEButton);
        String input = Datetext.getText().toString().trim();
        String input2 = TitleText.getText().toString().trim();
        String input3 = LocationText.getText().toString().trim();
        String input4 = TimeText.getText().toString().trim();
        String input5 = CategoryText.getText().toString().trim();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_addEventFragment_to_mainScreen);
                if(input.isEmpty()){
                    db.EventDao().insert(new Event(input));
                    Datetext.setText("");
                    loadEvents;
                    Toast.makeText(AddEventFragment.this, "Please Enter a Date", Toast.LENGTH_SHORT).show();
                    return;

                if (input2.isEmpty()){
                } db.EventDao().insert(new Event(input2));
                    TitleText.setText("");
                    loadEvents;
                    Toast.makeText(AddEventFragment.this, "Please Enter a Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (input3.isEmpty()){
                } db.EventDao().insert(new Event(input3));
                LocationText.setText("");
                loadEvents;

                if (input4.isEmpty()){
                } db.EventDao().insert(new Event(input4));
                TimeText.setText("");
                loadEvents;

                if (input5.isEmpty()){
                } db.EventDao().insert(new Event(input5));
                CategoryText.setText("");
                loadEvents;
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_event, container, false);
    }
    }





