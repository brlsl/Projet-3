package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFavoriteNeighbourRecyclerViewAdapter extends RecyclerView.Adapter<MyFavoriteNeighbourRecyclerViewAdapter.ViewHolder> {

    private final List<Neighbour> mFav_neighbours_list;

    //constants for the method putExtra()
    public static final String BUNDLE_EXTRA_ID = "BUNDLE_EXTRA_ID";
    public static final String BUNDLE_EXTRA_NAME = "BUNDLE_EXTRA_NAME";
    public static final String BUNDLE_EXTRA_AVATAR_URL = "BUNDLE_EXTRA_AVATAR_URL";

    public MyFavoriteNeighbourRecyclerViewAdapter(List<Neighbour> items) {
        mFav_neighbours_list = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Neighbour neighbour = mFav_neighbours_list.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        // user clicks on a neighbour, the new activity launches
        // id, name and avatarUrl  are sent to the new activity with puExtra()
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent detail_intent = new Intent(context, ActivityNeighbourDetail.class);
                detail_intent.putExtra(BUNDLE_EXTRA_ID, mFav_neighbours_list.get(position).getId());
                detail_intent.putExtra(BUNDLE_EXTRA_NAME, mFav_neighbours_list.get(position).getName());
                detail_intent.putExtra(BUNDLE_EXTRA_AVATAR_URL, mFav_neighbours_list.get(position).getAvatarUrl());
                context.startActivity(detail_intent);
            }
        });

        //user deletes user in favorites list
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteFavoriteNeighbourEvent(neighbour));
            }
        });

    }


    @Override
    public int getItemCount() {
        return mFav_neighbours_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
