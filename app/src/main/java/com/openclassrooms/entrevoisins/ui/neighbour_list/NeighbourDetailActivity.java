package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.BUNDLE_EXTRA_AVATAR_URL;
import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.BUNDLE_EXTRA_NAME;

public class NeighbourDetailActivity extends AppCompatActivity {

    // variables
    ImageButton mBackButton;
    FloatingActionButton mFavoriteAddButton;
    ImageView mAvatar;
    TextView mNameAvatar, mNameCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        mBackButton = findViewById(R.id.back_button);
        mFavoriteAddButton = findViewById(R.id.favorite_add_floating_btn);
        mAvatar = findViewById(R.id.neighbour_avatar);
        mNameAvatar = findViewById(R.id.neighbour_name_avatar);
        mNameCardView = findViewById(R.id.neighbour_name_cardview);

        // inutilisé pour le moment
        Neighbour neighbour = new Neighbour(1, "name", "avatar" );

        //when user clicks on back button
        mBackButton.setOnClickListener(view -> finish());

        //charger le nom en test
        mNameAvatar.setText(getIntent().getStringExtra(BUNDLE_EXTRA_NAME));
        mNameCardView.setText(getIntent().getStringExtra(BUNDLE_EXTRA_NAME));

        // charger l'avatar en test
        Glide
                .with(mAvatar.getContext())
                .load(getIntent().getStringExtra(BUNDLE_EXTRA_AVATAR_URL))
                .into(mAvatar);

    }

}
