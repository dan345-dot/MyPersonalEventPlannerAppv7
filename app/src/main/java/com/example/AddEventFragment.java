package com.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.mypersonaeventplannerappv7.R;

public class AddEventFragment extends Fragment {

    private EditText Datetext;
    private EditText TitleText;
    private EditText LocationText;
    private EditText TimeText;
    private EditText CategoryText;

    private Button AEUEButton;

    private RoomDB db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(
                        requireActivity().getApplicationContext(),
                        RoomDB.class,
                        "events_db"
                )
                .allowMainThreadQueries()
                .build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_add_event,
                container,
                false
        );

        Datetext = view.findViewById(R.id.date);
        TitleText = view.findViewById(R.id.Title);
        LocationText = view.findViewById(R.id.location);
        TimeText = view.findViewById(R.id.time);
        CategoryText = view.findViewById(R.id.category);
        AEUEButton = view.findViewById(R.id.AEUEButton);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        NavController navController =
                Navigation.findNavController(view);

        AEUEButton.setOnClickListener(v -> {

            String date = Datetext.getText().toString().trim();
            String title = TitleText.getText().toString().trim();
            String location = LocationText.getText().toString().trim();
            String time = TimeText.getText().toString().trim();
            String category = CategoryText.getText().toString().trim();

            // Validation
            if (date.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Please enter a date",
                        Toast.LENGTH_SHORT).show();
                return;
            }


            if (title.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Please enter a title",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (location.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Please enter a location",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (time.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Please enter a time",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (category.isEmpty()) {
                Toast.makeText(requireContext(),
                        "Please enter a category",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // FIXED ERROR HERE
            Event event = new Event();

            event.date = date;
            event.title = title;
            event.location = location;
            event.time = time;
            event.category = category;

            // INSERT INTO DATABASE
            db.eventDao().insert(event);

            // Clear fields
            Datetext.setText("");
            TitleText.setText("");
            LocationText.setText("");
            TimeText.setText("");
            CategoryText.setText("");

            Toast.makeText(
                    requireContext(),
                    "Event added successfully",
                    Toast.LENGTH_SHORT
            ).show();

            navController.navigate(
                    R.id.action_addEventFragment_to_mainScreen
            );
        });
    }
}