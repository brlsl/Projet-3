package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        // get the entire list of neighbour
        List<Neighbour> neighbours = service.getNeighbours();
        // get the entire existing list
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        // check if botch list contains same neighbours
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    // we ensure the neighbour is removed from neighbour and neighbourIsFavorite lists
    @Test
    public void deleteNeighbourWithSuccess() {
        // get an existing neighbour (first one)
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        // delete the neighbour
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    // we ensure the neighbour is added to neighbourIsFavorite list
    @Test
    public void addNeighbourToFavoriteListWithSuccess(){
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavorite(neighbour);
        assertTrue(service.getFavorites().contains(neighbour));
    }

    @Test
    public void removeNeighbourToFavoriteListWithSuccess(){
        Neighbour neighbour = service.getNeighbours().get(0);
        service.addFavorite(neighbour);
        service.removeFavorite(neighbour);
        assertFalse(service.getFavorites().contains(neighbour));
    }
}
