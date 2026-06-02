package com.example;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mypersonaeventplannerappv7.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    // Fragments
    private final AddEventFragment addEventFragment = new AddEventFragment();
    private final MainScreen mainScreen = new MainScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize Bottom Navigation
        bottomNavigationView = findViewById(R.id.Nav_Bar);

        // Load default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, mainScreen)
                    .commit();
        }

        // Bottom Navigation Listener
        bottomNavigationView.setOnItemSelectedListener(
                new NavigationBarView.OnItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(
                            @NonNull MenuItem item) {

                        int itemId = item.getItemId();

                        if (itemId == R.id.main) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container, mainScreen)
                                    .commit();
                            return true;

                        } else if (itemId == R.id.add) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.container, addEventFragment)
                                    .commit();
                            return true;
                        }

                        return false;
                    }
                });

        // Handle edge-to-edge padding
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                });

        // Prevent bottom nav from adding extra padding
        ViewCompat.setOnApplyWindowInsetsListener(
                bottomNavigationView,
                (v, insets) -> insets
        );
    }
}