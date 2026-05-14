package com.example.mypersonaeventplannerappv7;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends Fragment {
public Button DeleteButton, AddButton, LoadButton;
public ListView listView;
public AppDatabase db;
public ArrayAdapter<String> adapter;
public ArrayList<String> eventItems;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
        Button button = view.findViewById(R.id.AddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_mainScreen_to_addEventFragment);
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = listView.findViewById(R.id.listView);
        DeleteButton = DeleteButton.findViewById(R.id.DeleteButton);
        AddButton = AddButton.findViewById(R.id.AddButton);
        LoadButton = LoadButton.findViewById(R.id.LoadButton);
        db = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "events_db")
                .allowMainThreadQueries()
                .build();
        eventItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventItems);
        listView.setAdapter(adapter);
        AddButton.setOnClickListener(v -> {}); {
            String text =
        LoadButton.setOnClickListener(v -> LoadButton());
            DeleteButton.setOnClickListener(v -> {
                db.EventDao().deleteAll();
                loadEvents();
                Toast.makeText(this, "All events deleted", Toast.LENGTH_SHORT).show();
            })

        ;}
}
    private void LoadButton() {
        eventItems.clear();
        List<Event> events = db.EventDao().getAllEvents;
        for (Event event : events) {
            eventItems.add(event.id + " - " + event.eventText);
    }
    }}
