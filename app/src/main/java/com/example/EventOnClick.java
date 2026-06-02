package com.example;

import androidx.cardview.widget.CardView;

public interface EventOnClick {
    void onClick(Event event);
    void onLongClick(Event event, CardView cardView);

}
