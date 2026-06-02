package com.example;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.mypersonaeventplannerappv7.R;

import java.util.ArrayList;
import java.util.List;

public class MainScreen extends Fragment {

    private Button deleteButton;
    private Button addButton;
    private Button loadButton;
    private ListView listView;

    private RoomDB db;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> eventItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize database
        db = Room.databaseBuilder(
                        requireActivity().getApplicationContext(),
                        RoomDB.class,
                        "events_db"
                )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        // Initialize list
        eventItems = new ArrayList<>();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_main_screen,
                container,
                false
        );

        // Initialize UI components
        listView = view.findViewById(R.id.addEventFragment);
        deleteButton = view.findViewById(R.id.DeleteButton);
        addButton = view.findViewById(R.id.AddButton);
        loadButton = view.findViewById(R.id.LoadButton);

        // Create adapter
        adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                eventItems
        );

        listView.setAdapter(adapter);

        // Load events when screen opens
        loadEvents();

        // Load button click
        loadButton.setOnClickListener(v -> loadEvents());

        // Delete all events
        deleteButton.setOnClickListener(v -> {

            Event Event = new Event();
            db.eventDao().delete(Event);

            loadEvents();

            Toast.makeText(
                    requireContext(),
                    "All events deleted",
                    Toast.LENGTH_SHORT
            ).show();
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        NavController navController =
                Navigation.findNavController(view);

        // Navigate to Add Event screen
        addButton.setOnClickListener(v ->
                navController.navigate(
                        R.id.action_mainScreen_to_addEventFragment
                )
        );
    }

    @Override
    public void onResume() {
        super.onResume();

        // Refresh events when returning to this fragment
        loadEvents();
    }

    // Load events from database
    private void loadEvents() {

        // Prevent crash if adapter not ready yet
        if (eventItems == null || adapter == null || db == null) {
            return;
        }

        eventItems.clear();

        List<Event> events = db.eventDao().getAllEvents();

        // Show message if no events exist
        if (events == null || events.isEmpty()) {

            eventItems.add("No events available.");

        } else {

            for (Event event : events) {

                String eventText =
                        "ID: " + event.getId() +
                                "\nDate: " + safeText(event.getDate()) +
                                "\nTitle: " + safeText(event.getTitle()) +
                                "\nLocation: " + safeText(event.getLocation()) +
                                "\nTime: " + safeText(event.getTime()) +
                                "\nCategory: " + safeText(event.getCategory());

                eventItems.add(eventText);
            }
        }

        // Refresh ListView
        adapter.notifyDataSetChanged();
    }

    // Prevent null text crashes
    private String safeText(String text) {
        return text == null ? "N/A" : text;
    }
}