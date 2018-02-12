package com.marc.viewlift.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.marc.viewlift.R;
import com.marc.viewlift.model.Movies;
import com.marc.viewlift.view.MainActivity;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements OnBottomReachedListener, ItemClickListener {
    private String TAG;
    private int mTag = 0;
    private Context context;
    private List<List<Movies>> list;
    private ItemClickListener itemClickListener;
    private OnBottomReachedListener onBottomReachedListener;

    public Adapter(Context context, List<List<Movies>> list) {
        TAG = getClass().getName();
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBottomReached(int position) {
        Log.d(TAG,"onBottomReached()");

    }
    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){
        Log.d(TAG, "setOnBottomReachedListener()");
        this.onBottomReachedListener = onBottomReachedListener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RelativeLayout topItem;
        private RelativeLayout bottomItem;
        private LinearLayout mGallery;
        private TextView textViewRowTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG,"ViewHolder()");
            topItem = itemView.findViewById(R.id.topItem);
            bottomItem = itemView.findViewById(R.id.bottomItem);
            mGallery = itemView.findViewById(R.id.id_items);
            textViewRowTitle = itemView.findViewById(R.id.textViewRowTitle);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick " + getAdapterPosition() + " " );
            itemClickListener.OnItemClick(getAdapterPosition());
        }
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG,"onCreateViewHolder()");

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder()");
        if (position == 0) {
            holder.topItem.setVisibility(View.VISIBLE);
            holder.bottomItem.setVisibility(View.GONE);
        } else {
            holder.topItem.setVisibility(View.GONE);
            holder.bottomItem.setVisibility(View.VISIBLE);

            holder.mGallery.removeAllViews();
            for (int i = 0; i < list.get(position-1).size(); i++) {

                LayoutInflater mInflater = LayoutInflater.from(holder.mGallery.getContext());

                View view = mInflater.inflate(R.layout.item,
                        holder.mGallery, false);

                ImageView img = view
                        .findViewById(R.id.id_index_gallery_item_image);
                Picasso.with(view.getContext())
                        .load(list.get(position-1).get(i).getImage())
                        .into(img); // load image

                TextView txt = view.findViewById(R.id.id_index_gallery_item_text);
                txt.setText(list.get(position-1).get(i).getTitle());
                txt.setTextColor(view.getContext().getResources().getColor(R.color.white));
                view.setTag(mTag);
                mTag++;
                holder.mGallery.addView(view);
            }
            holder.textViewRowTitle.setText("Row: "+position);

        }

        // bottom is reached
        if (position == getItemCount() - 1){
            onBottomReachedListener.onBottomReached(position+1);
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount()");

        return list.size();
    }

    @Override
    public void OnItemClick(int position) {
        Log.d(TAG, "OnItemClick()");

    }
    public void setOnitemClickListener(ItemClickListener itemClickListener){
        Log.d(TAG, "setOnitemClickListener()");
        this.itemClickListener = itemClickListener;
    }

}
