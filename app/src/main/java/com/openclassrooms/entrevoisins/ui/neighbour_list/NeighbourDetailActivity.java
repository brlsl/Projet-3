package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.graphics.drawable.Drawable;
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

    /** variables */
    ImageButton mBackButton;
    FloatingActionButton mFavoriteAddButton;
    ImageView mAvatar;
    TextView mNameAvatar, mNameCardView;
    Drawable mStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        /** FindViewbyID */
        mBackButton = findViewById(R.id.back_button);
        mFavoriteAddButton = findViewById(R.id.favorite_add_floating_btn);
        mAvatar = findViewById(R.id.neighbour_avatar);
        mNameAvatar = findViewById(R.id.neighbour_name_avatar);
        mNameCardView = findViewById(R.id.neighbour_name_cardview);

        /** when user clicks on back button */
        mBackButton.setOnClickListener(view -> finish());

        /** load names */
        mNameAvatar.setText(getIntent().getStringExtra(BUNDLE_EXTRA_NAME));
        mNameCardView.setText(getIntent().getStringExtra(BUNDLE_EXTRA_NAME));

        /** load avatar */
        Glide
                .with(mAvatar.getContext())
                .load(getIntent().getStringExtra(BUNDLE_EXTRA_AVATAR_URL))
                .into(mAvatar);


        Neighbour newNeighbour = new Neighbour(getIntent().getIntExtra(BUNDLE_EXTRA_ID,0), getIntent().getStringExtra(BUNDLE_EXTRA_NAME), getIntent().getStringExtra(BUNDLE_EXTRA_AVATAR_URL));

        if (FavoriteFragment.mFavoriteNeighbours.contains(newNeighbour))
        {
            mFavoriteAddButton.setEnabled(false);
            mFavoriteAddButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_white_24dp));
        }

        else
        {
            mFavoriteAddButton.setEnabled(true);

        }

        // user clicks adds a neighbour in favorite
        mFavoriteAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(NeighbourDetailActivity.this,"Le contact a été ajouté aux favoris",Toast.LENGTH_SHORT).show();

                // désactive le FavoriteAddButton lorsqu'on a cliqué dessus
                mFavoriteAddButton.setEnabled(false);
                mFavoriteAddButton.setImageDrawable(getDrawable(R.drawable.ic_star_border_white_24dp));

                FavoriteFragment.mFavoriteNeighbours.add(newNeighbour);
                FavoriteFragment.mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(FavoriteFragment.mFavoriteNeighbours));

                }
        });

    }

}
