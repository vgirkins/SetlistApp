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
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by hunti on 3/15/2018.
 */

public class SongListFragment extends Fragment{
    private static final String ARG_PERFORMANCE_ID = "performance_id";
    private static final String ARG_USER_ADDING_SONGS = "user_adding_songs";
    private Performance mPerformance;
    private boolean mUserAddingSongs;

    private RecyclerView mSongRecyclerView;
    private SongAdapter mAdapter;

    public static SongListFragment newInstance(UUID performanceId, boolean userAddingSongs) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PERFORMANCE_ID, performanceId);
        args.putSerializable(ARG_USER_ADDING_SONGS, userAddingSongs);

        SongListFragment fragment = new SongListFragment();
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID performanceId = (UUID) getArguments().getSerializable(ARG_PERFORMANCE_ID);
        mUserAddingSongs = (boolean) getArguments().getSerializable(ARG_USER_ADDING_SONGS);
        mPerformance = performanceId == null ? null : PerformanceLab.get(getActivity()).getPerformance(performanceId);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        mSongRecyclerView = view.findViewById(R.id.recycler_view);
        mSongRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mArtistTextView;
        private TextView mDescriptionTextView;
        private Song mSong;

        private SongHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_song, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.song_title);
            mArtistTextView = itemView.findViewById(R.id.song_artist);
            mDescriptionTextView = itemView.findViewById(R.id.song_description);
        }

        public void bind(Song song){
            mSong = song;
            mTitleTextView.setText(mSong.getTitle().equals("") ? "No title" : mSong.getTitle());
            mArtistTextView.setText(mSong.getArtist().equals("") ? "No artist" : mSong.getArtist());
            mDescriptionTextView.setText(mSong.getDescription().equals("") ? "No description" : mSong.getDescription());
        }

        @Override
        public void onClick(View view) {
            // If they are in the adding songs activity, this should just add the song to the performance.
            // Else, it should pull up a detail view of the song.
            if (mUserAddingSongs) {
                //mSong.setPerfId(mPerformance.getId());
                //SongLab.get(getActivity()).updateSong(mSong);
                PerformanceSongXRefLab.get(getActivity()).associateSong(mPerformance.getId(), mSong.getId());
                Toast.makeText(getActivity(), "Added song to performance", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent intent = SongPagerActivity.newIntent(getActivity(), mSong.getId(), mPerformance == null ? null : mPerformance.getId());
                startActivity(intent);
            }
        }
    }

    private class SongAdapter extends RecyclerView.Adapter<SongHolder> {
        private List<Song> mSongs;
        public SongAdapter(List<Song> songs) {
            mSongs = songs;
        }

        @Override
        public SongHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new SongHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(SongHolder holder, int position) {
            Song song = mSongs.get(position);
            holder.bind(song);
        }

        @Override
        public int getItemCount() {
            return mSongs.size();
        }

        public void setSongs(List<Song> songs) {
            mSongs = songs;
        }
    }

    public void updateUI() {
        SongLab songLab = SongLab.get(getActivity());
        List<Song> songs = songLab.getSongs((mPerformance == null || mUserAddingSongs) ? null : mPerformance.getId());  // Should fetch all songs unless viewing
                                                                                                                        // songs for specific performance
        if (mAdapter == null) {
            mAdapter = new SongAdapter(songs);
            mSongRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.setSongs(songs);
            mAdapter.notifyDataSetChanged();
        }
    }
}
