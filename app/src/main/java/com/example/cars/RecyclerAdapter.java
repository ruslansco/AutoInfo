package com.example.cars;

// Ahmed Alotaibi

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class RecyclerAdapter extends
        RecyclerView.Adapter<
                RecyclerView.ViewHolder> {
    private static final
    int TYPE_HEADER = 0;
    private static final
    int TYPE_CELL = 1;
    /*Called when RecyclerView needs a new
    RecyclerView.ViewHolder of the
    given type to represent an item.*/
    @Override
    public RecyclerView.ViewHolder
    onCreateViewHolder(
            ViewGroup viewGroup, int type) {
        View view;
        if (type == TYPE_HEADER){
            view = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.hvp_header_placeholder,
                            viewGroup,false);}else{
            view = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.content_card,viewGroup,
                            false);}
        return new RecyclerView.ViewHolder(view){};}
    /*Called by RecyclerView to display
    the data at the specified position.*/
    @Override
    public void
    onBindViewHolder(
            RecyclerView.
                    ViewHolder
                    viewHolder, int i){}
    /*Returns the total number of items
    in the data set held by the adapter.*/
    @Override
    public int getItemCount(){return 100;}
    /*Return the view type of the item at position
      for the purposes of view recycling.*/
    @Override
    public int
    getItemViewType(int position) {
        if (position == 0){return TYPE_HEADER;
        }else{return TYPE_CELL;}}}