package com.example.projectdpz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RemoteAdapter extends RecyclerView.Adapter<RemoteAdapter.RemoteViewHolder> {

    List<Remote> mRemotes;
    Remote remote;
    Context mContext;

    public RemoteAdapter(Context context, List<Remote> remotes)
    {
        mContext = context;
        mRemotes = remotes;
    }

    public RemoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new RemoteViewHolder(view);

//        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
//        return new RemoteViewHolder (layoutInflater.inflate(R.layout.item_list, parent,false));
    }

    @Override
    public void onBindViewHolder(RemoteViewHolder holder, int position)
    {
        remote = mRemotes.get(position);
        Integer statPower = remote.getmPower();

        if (statPower == 1){
            holder.mLamp.setImageResource(R.drawable.lamp_hidup);
            holder.mPower.setText("Lampu Hidup");
        }else
        {
            holder.mLamp.setImageResource(R.drawable.lamp_mati);
            holder.mPower.setText("Lampu Mati");
        }

        SimpleDateFormat sdfTanggal = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat sdfWaktu = new SimpleDateFormat("HH:mm");
        Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(remote.getmTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.mTime.setText(sdfTanggal.format(tanggal)+ " Jam "+ sdfWaktu.format(tanggal));
    }

    @Override
    public int getItemCount() {
        return mRemotes.size();
    }

    public void refreshDataSet(List<Remote> remotes) {
        this.mRemotes = remotes;
        notifyDataSetChanged();
    }

    public class RemoteViewHolder extends RecyclerView.ViewHolder{
        ImageView mLamp;
        TextView mPower;
        TextView mTime;

        public RemoteViewHolder(@NonNull View itemView)
        {
            super(itemView);
            mLamp = (ImageView) itemView.findViewById(R.id.imageView);
            mPower = (TextView) itemView.findViewById(R.id.Power);
            mTime = (TextView) itemView.findViewById(R.id.Waktu);
        }
    }
}