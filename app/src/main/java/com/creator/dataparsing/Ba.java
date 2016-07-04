package com.creator.dataparsing;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.creator.dataparsing.adapter.MenuAdapter;
import com.creator.dataparsing.model.Menu;
import com.creator.dataparsing.model.MenuList;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by user1 on 7/4/2016.
 */
public class Ba extends Fragment{
    protected RecyclerView recyclerView;
    protected Gson gson = new Gson();
    protected MenuAdapter adapter ;

    protected String responseData;

    protected ArrayList<Menu> menuList ;


    protected void callMenuItem(String url, final HashMap<String,String> params){


        StringRequest jsonObjRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                        responseData = response;
                        setData(response);
                        VolleyLog.d("volley", "Error: " + response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
                error.printStackTrace();

                Log.d("MenuFragment", "onErrorResponse : 103");
            }
        });

        RequestQueue requestQueue = Controller.getInstance().getRequestQueue();
        requestQueue.cancelAll(url);
        jsonObjRequest.setTag(url);
        requestQueue.add(jsonObjRequest);
    }


    protected void setData(String response) {
        try{

            if (isAdded()){
                MenuList menuWholeList = gson.fromJson(response,MenuList.class);
                menuList = menuWholeList.getMenus();
                if (menuList == null  || menuList.size() == 0){
                 //TODO : nothing to show here.
                }else {
                    //TODO : show some visibility here.
                    adapter = new MenuAdapter(menuList,getActivity());
                }

            }

        }catch (JsonSyntaxException e){
            e.printStackTrace();
        }
    }


    public void onFragmentVisible(){

    }
}
