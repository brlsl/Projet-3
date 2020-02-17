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
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import static com.openclassrooms.entrevoisins.ui.neighbour_list.MyNeighbourRecyclerViewAdapter.KEY_NEIGHBOUR;

public class NeighbourDetailActivity extends AppCompatActivity {

    // declaration variable
    private FloatingActionButton mFavoriteAddButton_FAB;
    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);

        mNeighbour = getIntent().getParcelableExtra(KEY_NEIGHBOUR);
        mApiService = DI.getNeighbourApiService();

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
                .with(this)
                .load(mNeighbour.getAvatarUrl())
                .into(mNeighbourAvatar);

        // load names
        mNameAvatar.setText(mNeighbour.getName());
        mNameCardView.setText(mNeighbour.getName());

        // when user clicks on back button
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
   public void manageClickOnFAB(){
       if (mNeighbour.isFavorite()){
           mFavoriteAddButton_FAB.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
           mFavoriteAddButton_FAB.setElevation(20);
       }

       else if(!mNeighbour.isFavorite())
           mFavoriteAddButton_FAB.setImageDrawable(getDrawable(R.drawable.ic_star_border_white_24dp));

       mFavoriteAddButton_FAB.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (mNeighbour.isFavorite()){
                   mApiService.removeFavorite(mNeighbour);
                   mFavoriteAddButton_FAB.setImageDrawable(getDrawable(R.drawable.ic_star_border_white_24dp));
                   mNeighbour.setFavoriteBoolean(false);
                   Toast.makeText(NeighbourDetailActivity.this, "Le contact a été retiré des favoris", Toast.LENGTH_SHORT).show();

               }
               else if(!mNeighbour.isFavorite()){
                   mApiService.addFavorite(mNeighbour);
                   mFavoriteAddButton_FAB.setImageDrawable(getDrawable(R.drawable.ic_star_yellow_24dp));
                   mFavoriteAddButton_FAB.setElevation(20);
                   mNeighbour.setFavoriteBoolean(true);
                   Toast.makeText(NeighbourDetailActivity.this, "Le contact a été ajouté aux favoris", Toast.LENGTH_SHORT).show();
               }
           }
       });
    }
}
