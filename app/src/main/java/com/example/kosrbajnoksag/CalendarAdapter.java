package com.example.kosrbajnoksag;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
/*
public class CalendarAdapter extends ArrayAdapter<Calender> {

    private Context mContext;

    public CalendarAdapter(Context context, List<Calender> days) {
        super(context, 0, days);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_weekly, parent, false);
        }

        Calender day = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.nameTextView);
        nameTextView.setText(day.getName());

        ListView eventListView = convertView.findViewById(R.id.eventListView);
        EventAdapter adapter = new EventAdapter(mContext, day.getEvents());
        eventListView.setAdapter(adapter);

        return convertView;
    }

    public void updateData(List<Calender> days) {
        clear();
        addAll(days);
        notifyDataSetChanged();
    }

    private static class EventAdapter extends ArrayAdapter<Event> {

        private Context mContext;

        public EventAdapter(Context context, List<Calender> eventList) {
            super(context, 0, eventList);
            mContext = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_event, parent, false);
            }

            Event event = getItem(position);

            TextView nameTextView = convertView.findViewById(R.id.nameTextView);
            nameTextView.setText(event.getName());

            TextView startTimeTextView = convertView.findViewById(R.id.startTimeTextView);
            startTimeTextView.setText(event.getStartTime());

            return convertView;
        }
    }
}*/

