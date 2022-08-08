package com.example.projectdpz;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RemoteLab {
    private static RemoteLab sRemoteLab;
    private List<Remote> mRemotes;

    public static RemoteLab get(Context context) {
        if (sRemoteLab == null) {
            sRemoteLab = new RemoteLab(context);
        }
        return sRemoteLab;
    }

    private RemoteLab(Context context)
    {
        mRemotes = new ArrayList<>();
    }

    public void addData(Remote c) {
        JSONObject jsonObject = null;
        HashMap<String, String> params = new HashMap<>();
        params.put(Config.KEY_POWER,String.valueOf(c.getmPower()));
        RequestHandler rh = new RequestHandler();
        String JSON_STRING = rh.sendPostRequest(Config.URL_ADD_DATA,params);
        try {
            jsonObject = new JSONObject(JSON_STRING);

            Log.d("ADD_DATA", JSON_STRING);
//            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
//
//            for (int b = 0; b<result.length(); b++)
//            {
//                JSONObject ar = result.getJSONObject(b);
//            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public List<Remote> getRemoteData()
    {
        List<Remote> remotes = new ArrayList<>();
        JSONObject jsonObject = null;
        RequestHandler rh = new RequestHandler();
        String JSON_STRING = rh.sendGetRequest(Config.URL_GET_DATA);
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++)
            {
                JSONObject ar = result.getJSONObject(i);
                String id = ar.getString(Config.TAG_ID);
                String power = ar.getString(Config.TAG_POWER);
                String time = ar.getString(Config.KEY_TIME);

                Remote remote = new Remote(Integer.parseInt(id), Integer.parseInt(power), time);
                remote.setmPower(Integer.parseInt(power));
                remote.setmTime(time);

                remotes.add(remote);
            }
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        mRemotes = remotes;
        return mRemotes;
    }

    public int getLastPower() {
        RequestHandler rh = new RequestHandler();
        String sd = rh.sendGetRequest(Config.URL_GET_LAST_STATUS);
        Integer rs = 0;

        Log.d("REMOTE", sd);

        try {
            JSONObject jsonObject = new JSONObject(sd);
            JSONObject result = jsonObject.getJSONObject(Config.TAG_JSON_ARRAY);

            Log.d("REMOTE", String.valueOf(result));

            rs = result.getInt("power");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return rs;
    }
}
