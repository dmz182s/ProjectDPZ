package com.example.projectdpz;

public class Config {
    //    Default Localhost IP for AVD is 10.0.2.2
    public static final String mServerUrl = "http://10.0.2.2/remote_LED/";

    public static final String URL_ADD_DATA =mServerUrl+"AddData.php";
    public static final String URL_GET_DATA =mServerUrl+"GetData.php";
    public static final String URL_GET_LAST_STATUS =mServerUrl+"GetLastStatus.php";

    public static final String KEY_ID = "id";
    public static final String KEY_POWER = "power";
    public static final String KEY_TIME = "time";

    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID = "id";
    public static final String TAG_POWER = "power";
    public static final String TAG_TIME = "time";
}