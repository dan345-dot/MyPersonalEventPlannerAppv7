package com.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mypersonaeventplannerappv7.R;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    Context context;
    List<Event> list;
    EventOnClick eventOnClick;

    public EventListAdapter(Context context, List<Event> list, EventOnClick eventOnClick) {
        this.context = context;
        this.list = list;
        this.eventOnClick = eventOnClick;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_add_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        Event event = list.get(position);

        holder.textView_Title.setText(event.getTitle());
        holder.textView_category.setText(event.getCategory());
        holder.textView_location.setText(event.getLocation());
        holder.textView_date.setText(event.getDate());
        holder.textView_time.setText(event.getTime());

        holder.textView_Title.setSelected(true);
        holder.textView_category.setSelected(true);
        holder.textView_location.setSelected(true);
        holder.textView_date.setSelected(true);
        holder.textView_time.setSelected(true);

        // Click Listener
        holder.event_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventOnClick.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        // Long Click Listener
        holder.event_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                eventOnClick.onLongClick(
                        list.get(holder.getAdapterPosition()),
                        holder.event_container
                );
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // ViewHolder Class
    public static class EventViewHolder extends RecyclerView.ViewHolder {

        CardView event_container;

        TextView textView_Title,
                textView_location,
                textView_date,
                textView_time,
                textView_category;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Title = itemView.findViewById(R.id.Title);
            textView_category = itemView.findViewById(R.id.category);
            textView_date = itemView.findViewById(R.id.date);
            textView_time = itemView.findViewById(R.id.time);
            textView_location = itemView.findViewById(R.id.location);
        }
    }
}