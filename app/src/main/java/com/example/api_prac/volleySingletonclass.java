package com.example.api_prac;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.time.Instant;

public class volleySingletonclass {

    private RequestQueue requestQueue;
    private static volleySingletonclass mInstance;


    private  volleySingletonclass(Context context){
        requestQueue= Volley.newRequestQueue(context.getApplicationContext());

    }

    public static synchronized volleySingletonclass getmInstance(Context context){
        if (mInstance==null){
            mInstance=new volleySingletonclass(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

}
