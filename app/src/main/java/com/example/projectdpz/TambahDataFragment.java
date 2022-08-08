package com.example.projectdpz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TambahDataFragment extends Fragment {

    ImageView mImage_off, mImage_on;
    ToggleButton mToggleButton;

    Remote mRemote = new Remote();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tambah_data, container, false);
        mImage_off = v.findViewById(R.id.lamp_off);
        mImage_on = v.findViewById(R.id.lamp_on);
        mToggleButton = v.findViewById(R.id.switch_button);

        mToggleButton.setChecked(false);
        new AmbilStatusData().execute();

        mToggleButton.setOnClickListener((view) -> {
            if (mToggleButton.isChecked()) {
                mImage_on.setVisibility(View.VISIBLE);
                mImage_off.setVisibility(View.GONE);
                mRemote.setmPower(1);
                new SimpanData().execute();
            } else {
                mImage_off.setVisibility(View.VISIBLE);
                mImage_on.setVisibility(View.GONE);
                mRemote.setmPower(0);
                new SimpanData().execute();
            }
        });
        return v;
    }

    class AmbilStatusData extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            if (mRemote.getmPower() == 1) {
                mToggleButton.setChecked(true);
                mImage_off.setVisibility(View.GONE);
                mImage_on.setVisibility(View.VISIBLE);
            } else {
                mImage_off.setVisibility(View.VISIBLE);
                mImage_on.setVisibility(View.GONE);
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            Integer remoteStatus = RemoteLab.get(getActivity().getApplicationContext()).getLastPower();
            mRemote.setmPower(remoteStatus);
            return null;
        }
    }

    class SimpanData extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            if (mRemote.getmPower() == 1) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Lampu Nyala",
                        Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Lampu Mati",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            RemoteLab.get(getActivity().getApplicationContext()).addData(mRemote);
            return null;
        }
    }
}
