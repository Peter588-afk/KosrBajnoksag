package com.example.kosrbajnoksag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    private Context mContext;

    public EventAdapter(Context context, List<Event> eventList) {
        super(context, 0, eventList);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_event, parent, false);
        }

        Event event = getItem(position);

        TextView eventNameTextView = convertView.findViewById(R.id.eventNameTextView);
        TextView eventStartTimeTextView = convertView.findViewById(R.id.eventStartTimeTextView);

        eventNameTextView.setText(event.getName());
        eventStartTimeTextView.setText(event.getStartTime());

        return convertView;
    }
}
