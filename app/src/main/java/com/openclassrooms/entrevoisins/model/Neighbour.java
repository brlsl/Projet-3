package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Parcelable {

    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    private boolean favoriteBoolean;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param favoriteBoolean
     */
    public Neighbour(Integer id, String name, String avatarUrl, boolean favoriteBoolean) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.favoriteBoolean = favoriteBoolean;
    }

    protected Neighbour(Parcel in) {
        id = in.readInt();
        name = in.readString();
        avatarUrl = in.readString();
        favoriteBoolean = in.readByte() != 0;
    }

    public static final Creator<Neighbour> CREATOR = new Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel in) {
            return new Neighbour(in);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(avatarUrl);
        // if true favorite = 1, else = 0
        parcel.writeInt(favoriteBoolean ? 1 : 0);

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setFavoriteBoolean(boolean favoriteBoolean) {
        this.favoriteBoolean = favoriteBoolean;
    }

    public boolean isFavorite() {
        return favoriteBoolean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
