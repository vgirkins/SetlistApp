package com.csci448.vgirkins.setlist;

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

/**
 * Created by hunti on 3/15/2018.
 */

public class SongListFragment extends Fragment{

    private RecyclerView mSongRecyclerView;
    private SongAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_list, container, false);
        mSongRecyclerView = view.findViewById(R.id.song_recycler_view);
        mSongRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private class SongHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mArtistTextView;
        private Song mSong;

        private SongHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_song, parent, false));
            mTitleTextView = itemView.findViewById(R.id.song_title);
            mArtistTextView = itemView.findViewById(R.id.song_artist);
        }

        public void bind(Song song){
            mSong = song;
            mTitleTextView.setText(mSong.getTitle());
            mArtistTextView.setText(mSong.getArtist());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(), mSong.getTitle(), Toast.LENGTH_SHORT).show();
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
    }

    private void updateUI() {
        SongLab songLab = SongLab.get(getActivity());
        List<Song> songs = songLab.getSongs();

        mAdapter = new SongAdapter(songs);
        mSongRecyclerView.setAdapter(mAdapter);
    }
}