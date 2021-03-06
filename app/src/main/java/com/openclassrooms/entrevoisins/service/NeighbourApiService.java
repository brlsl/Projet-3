package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    List<Neighbour> getFavorites();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    void addFavorite(Neighbour neighbour);

    void removeFavorite(Neighbour neighbour);

}
