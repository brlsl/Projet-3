package com.openclassrooms.entrevoisins.model;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour {

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

    public boolean isFavoriteBoolean() {
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
