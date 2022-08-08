package com.example.projectdpz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListDataFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    List<Remote> mRemotes = new ArrayList<>();
    RecyclerView mRecyclerView;
    RemoteAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lihat_data, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.data);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        new LoadListData().execute();

        mAdapter = new RemoteAdapter(getContext(), mRemotes);
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return true;
    }

    class LoadListData extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected void onPostExecute(String s) {
            mAdapter.refreshDataSet(mRemotes);
        }
        @Override
        protected String doInBackground(String... strings) {
            mRemotes = RemoteLab.get(getActivity().getApplicationContext()).getRemoteData();
            Log.d("LIHAT_DATA", mRemotes.toString());
            return null;
        }
    }

}
