package com.team2137.chargedupscouting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.List;

class DebugDatabaseListViewAdapter extends BaseAdapter {
    private ArrayList<MatchData> list;

    public DebugDatabaseListViewAdapter(Dictionary<Long, MatchData> input) {
        this.list = new ArrayList<>(Collections.list(input.elements()));
    }

    public DebugDatabaseListViewAdapter(ArrayList<MatchData> input) {
        this.list = input;
    }

    public DebugDatabaseListViewAdapter(List<MatchData> input) {
        this.list = new ArrayList<>(input);
    }

    public DebugDatabaseListViewAdapter(MatchData[] input) {
        this.list = new ArrayList<>(Arrays.asList(input));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = DebugActivity.instance.get().getLayoutInflater();
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.database_listview_item, null);
        }
        MatchData data = list.get(position);
        ((TextView) convertView.findViewById(R.id.list_uniqueID)).setText(String.valueOf(data.uniqueID));
        ((TextView) convertView.findViewById(R.id.list_matchCompetition)).setText(String.valueOf(data.matchCompetition));
        ((TextView) convertView.findViewById(R.id.list_matchNumber)).setText(String.valueOf(data.matchNumber));
        ((TextView) convertView.findViewById(R.id.list_matchTeam)).setText(String.valueOf(data.matchTeam));
        ((TextView) convertView.findViewById(R.id.list_uploaded)).setText(String.valueOf(data.uploaded));

        return convertView;
    }

    public void refreshData() {
        list = new ArrayList<>(Collections.list(DebugActivity.instance.get().matchDataDatabaseHelper.getAllMatches().elements()));
    }
}
