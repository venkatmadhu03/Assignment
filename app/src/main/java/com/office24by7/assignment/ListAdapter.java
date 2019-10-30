package com.office24by7.assignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Viewholder> {
    List<ListValuesModel> entityModelList;
    Context context;

    public ListAdapter(List<ListValuesModel> entityModelList, Context context) {
        this.entityModelList = entityModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        if (entityModelList.get(position).getTitle()==null){
            holder.titleTv.setText("No title provided");
        }else{
            holder.titleTv.setText(entityModelList.get(position).getTitle());
        }
        if (entityModelList.get(position).getDescription() == null){
            holder.descriptionTv.setText("Description not found");
        }else{
            holder.descriptionTv.setText(entityModelList.get(position).getDescription());
        }

        Glide.with(context)
                .load(entityModelList.get(position).getImageHref())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.error_img_foreground)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return entityModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView titleTv, descriptionTv;
        ImageView imageView;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.titleTv);
            descriptionTv = itemView.findViewById(R.id.descriptionTv);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
