package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.BUNDLE_EXTRA_AVATAR_URL;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.BUNDLE_EXTRA_ID;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.BUNDLE_EXTRA_NAME;

public class NeighbourDetailActivity extends AppCompatActivity {

    // declaration variable
    private FloatingActionButton mFavoriteAddButton_FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        loadDetail();
        manageClickOnFAB();
    }

    public void loadDetail(){
        //declarations
        ImageButton mBackButton;
        ImageView mNeighbourAvatar;
        TextView mNameAvatar, mNameCardView;

        //referencing
        mBackButton = findViewById(R.id.back_button);
        mFavoriteAddButton_FAB = findViewById(R.id.add_favorite_floating_btn);
        mNeighbourAvatar = findViewById(R.id.neighbour_avatar);
        mNameAvatar = findViewById(R.id.neighbour_name_avatar);
        mNameCardView = findViewById(R.id.neighbour_name_cardview);

        // load avatar
        Glide
                .with(mNeighbourAvatar.getContext())
                .load(getIntent().getStringExtra(BUNDLE_EXTRA_AVATAR_URL))
                .into(mNeighbourAvatar);

        // load names
        mNameAvatar.setText(getIntent().getStringExtra(BUNDLE_EXTRA_NAME));
        mNameCardView.setText(getIntent().getStringExtra(BUNDLE_EXTRA_NAME));

        // when user clicks on back button
        mBackButton.setOnClickListener(view -> finish());
    }

    public void unclickableFAB(){
        mFavoriteAddButton_FAB.setEnabled(false);
        mFavoriteAddButton_FAB.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
        mFavoriteAddButton_FAB.setElevation(20);
    }

    public void clickableFAB(){
        mFavoriteAddButton_FAB.setEnabled(true);
        mFavoriteAddButton_FAB.setImageDrawable(getDrawable(R.drawable.ic_star_border_white_24dp));
    }

    public void manageClickOnFAB(){
        // instantiation with arguments
        Neighbour newNeighbour = new Neighbour(getIntent().getIntExtra(BUNDLE_EXTRA_ID,0),
                getIntent().getStringExtra(BUNDLE_EXTRA_NAME), getIntent().getStringExtra(BUNDLE_EXTRA_AVATAR_URL));

        //manage favorite button and make it clickable or not
        if (FavoriteFragment.mFavoriteNeighbours.contains(newNeighbour))
            unclickableFAB();
        else
            clickableFAB();

        // user clicks add a neighbour in favorite
        mFavoriteAddButton_FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NeighbourDetailActivity.this,"Le contact a été ajouté aux favoris",Toast.LENGTH_SHORT).show();

                // FavoriteAddButton is disabled when clicked
                unclickableFAB();

                // new neighbour is added to favorite list and the list is refreshed
                FavoriteFragment.mFavoriteNeighbours.add(newNeighbour);
                FavoriteFragment.mRecyclerView.setAdapter(new MyFavoriteNeighbourRecyclerViewAdapter(FavoriteFragment.mFavoriteNeighbours));
            }
        });
    }
}