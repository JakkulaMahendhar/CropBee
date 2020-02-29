package com.example.cropbee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{


  ArrayList<Data> datalist;
  RecyclerView recyclerView;
  ArrayList replacelist;
  RecyclerAdapter recyclerAdapter;


  ArrayList<MyListData> myListData;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    datalist = new ArrayList<>();

    myListData = new ArrayList<MyListData>();


    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    Call<ResponseBody> call = apiService.getMovies();
    call.enqueue(new Callback<ResponseBody>()
    {
      @Override
      public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
      {
        Log.d("Response", response.body().toString());
        try
        {
          JSONObject jsonObject = new JSONObject(response.body().string());
          JSONArray jsonArray = jsonObject.getJSONArray("data");
          for (int i = 0; i < jsonArray.length(); i++)
          {
            JSONObject actor = jsonArray.getJSONObject(i);
            Data data = new Data();
            data.setFirst_name(actor.getString("first_name"));
            data.setAvatar(actor.getString("avatar"));
            datalist.add(data);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerAdapter = new RecyclerAdapter(MainActivity.this, datalist);
            recyclerView.setAdapter(recyclerAdapter);

          }
        }
        catch (JSONException e)
        {
          e.printStackTrace();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }

      }

      @Override
      public void onFailure(Call<ResponseBody> call, Throwable t)
      {
        Log.d("TAG", "Response = " + t.toString());
      }
    });
  }


}

