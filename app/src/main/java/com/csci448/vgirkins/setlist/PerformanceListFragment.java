package com.csci448.vgirkins.setlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PerformanceListFragment extends Fragment {

    private RecyclerView mPerformanceRecyclerView;
    private PerformanceAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false); // TODO if problems occur split Performance and Song's usages of these into two files
        mPerformanceRecyclerView = view.findViewById(R.id.recycler_view);
        mPerformanceRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class PerformanceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mBandNameTextView;
        private TextView mDateTextView;
        private TextView mLocationTextView;
        private Performance mPerformance;

        private PerformanceHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_performance, parent, false));
            itemView.setOnClickListener(this);
            mBandNameTextView = itemView.findViewById(R.id.performance_band_name);
            mDateTextView = itemView.findViewById(R.id.performance_date);
            mLocationTextView = itemView.findViewById(R.id.performance_location);
        }

        public void bind(Performance performance){
            mPerformance = performance;
            mBandNameTextView.setText(mPerformance.getBandName());
            mDateTextView.setText(mPerformance.getDate());
            mLocationTextView.setText(mPerformance.getLocation());
        }

        @Override
        public void onClick(View view) {
            Intent intent = PerformancePagerActivity.newIntent(getActivity(), mPerformance.getId());
            startActivity(intent);
        }
    }

    private class PerformanceAdapter extends RecyclerView.Adapter<PerformanceHolder> {
        private List<Performance> mPerformances;
        public PerformanceAdapter(List<Performance> performances) {
            mPerformances = performances;
        }

        @Override
        public PerformanceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new PerformanceHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PerformanceHolder holder, int position) {
            Performance performance = mPerformances.get(position);
            holder.bind(performance);
        }

        @Override
        public int getItemCount() {
            return mPerformances.size();
        }

        public void setPerformances(List<Performance> performances) {
            mPerformances = performances;
        }
    }



    private void updateUI() {
        PerformanceLab performanceLab = PerformanceLab.get(getActivity());
        List<Performance> performances = performanceLab.getPerformances();

        if (mAdapter == null) {
            mAdapter = new PerformanceAdapter(performances);
            mPerformanceRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.setPerformances(performances);
            mAdapter.notifyDataSetChanged();
        }
    }
}
