package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myapplication.R;

import java.util.List;

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.GifViewHolder> {
    private List<String> gifUrls;

    // Constructor to initialize the adapter with a list of GIF URLs
    public GifAdapter(List<String> gifUrls) {
        this.gifUrls = gifUrls;
    }

    // Create view holder for RecyclerView
    @NonNull
    @Override
    public GifViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate item_gif layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gif, parent, false);
        return new GifViewHolder(view);
    }




    // Bind data to each item in the RecyclerView
    @Override
    public void onBindViewHolder(GifViewHolder holder, int position) {
        // Set margin for the first item, reset margin for others
        if (position == 0) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.topMargin = 300; // Set top margin for the first item
            holder.itemView.setLayoutParams(layoutParams);
        } else {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.topMargin = 0; // Reset top margin for other items
            holder.itemView.setLayoutParams(layoutParams);
        }

        // Load GIF using Glide library into ImageView
        Glide.with(holder.itemView)
                .asGif()
                .load(gifUrls.get(position))
                .apply(new RequestOptions()
                        .placeholder(R.drawable.loading) // Placeholder image while loading
                        .error(R.drawable.errorgif) // Image to show if loading fails
                )
                .centerCrop() // Crop the image to fit ImageView boundaries
                .into(holder.gifImageView);

        // Set fixed image size for each item in the RecyclerView
        int imageSize = 700;
        holder.gifImageView.getLayoutParams().width = imageSize;
        holder.gifImageView.getLayoutParams().height = imageSize;
    }

    // Return the total number of items in the RecyclerView
    @Override
    public int getItemCount() {
        return gifUrls.size();
    }

    // View holder class for caching ImageView references
    public static class GifViewHolder extends RecyclerView.ViewHolder {
        ImageView gifImageView;

        public GifViewHolder(View itemView) {
            super(itemView);
            gifImageView = itemView.findViewById(R.id.gifImageView); // Reference to the ImageView in item_gif layout
        }
    }
}
