package com.example.cropbee;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyviewHolder>
{


  Context context;
  ArrayList<Data> datalist;

  public RecyclerAdapter(Context context, ArrayList<Data> datalist)
  {
    this.context = context;
    this.datalist = datalist;
  }

  public void setMovieList(ArrayList<Data> datalist)
  {
    this.datalist = datalist;
    notifyDataSetChanged();
  }

  @Override
  public RecyclerAdapter.MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType)
  {
    View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
    return new MyviewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerAdapter.MyviewHolder holder, int position)
  {
    holder.bindItems(position);

    //Glide.with(context).load(datalist.get(position).getImageUrl()).apply(RequestOptions.centerCropTransform()).into(holder.image);
  }

  @Override
  public int getItemCount()
  {
    if (datalist != null)
    {
      return datalist.size();
    }
    return 0;

  }

  public class MyviewHolder extends RecyclerView.ViewHolder
  {
    AppCompatTextView tvMovieName;
    AppCompatImageView image;

    public MyviewHolder(View itemView)
    {
      super(itemView);
      tvMovieName = (AppCompatTextView) itemView.findViewById(R.id.countryname);
      image = (AppCompatImageView) itemView.findViewById(R.id.img_flag);
    }

    public void bindItems(int position)
    {
      Data data = datalist.get(position);

      Glide.with(itemView.getContext())
              .load(data.getAvatar())
              .into(image);

      tvMovieName.setText(data.getFirst_name());


    }
  }
}

