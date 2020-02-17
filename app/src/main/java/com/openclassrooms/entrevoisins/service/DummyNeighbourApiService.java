package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavorites() {
        List<Neighbour> favoriteList = new ArrayList<>();
        for(int i=0; i < neighbours.size(); i++){
            Neighbour neighbour = neighbours.get(i);
            if (neighbour.isFavorite()){
                favoriteList.add(neighbour);
            }
        }
        return favoriteList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public void addFavorite(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavoriteBoolean(true);
    }

    @Override
    public void removeFavorite(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavoriteBoolean(false);
    }
}
