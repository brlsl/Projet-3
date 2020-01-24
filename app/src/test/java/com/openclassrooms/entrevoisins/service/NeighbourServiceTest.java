package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
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

    @Test
    public void deleteNeighbourWithSuccess() {
        // get an existing neighbour (first one)
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        // delete the neighbour
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void getFavoriteNeighbourWithSuccess(){
        // there are 3 neighbours in the list to verify this
        List <Neighbour> neighbour = service.getFavoriteNeighbours();
        List <Neighbour> expectedFavoriteNeighbours = DummyNeighbourGenerator.FAVORITE_NEIGHBOURS;
        assertThat(neighbour, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedFavoriteNeighbours.toArray()));
    }

    @Test
    public void addNeighbourToFavoriteListWithSuccess(){
        //get an existing neighbour (first one)
        Neighbour neighbour1 = service.getNeighbours().get(0);
        //instantiate favorite List
        List <Neighbour> favoriteList = service.getFavoriteNeighbours();
        //add to favorite list
        favoriteList.add(neighbour1);
        //verify with boolean that favorite list contains neighbour1
        assertTrue(favoriteList.contains(neighbour1));

    }

    @Test
    public void deleteOnlyFavoriteNeighbourWithSuccess(){
        // get an existing neighbour
        Neighbour neighbour = service.getNeighbours().get(7);
        // get neighbour list and favorite list
        List <Neighbour> neighboursList = service.getNeighbours();
        List <Neighbour> favoriteNeighbourList = service.getFavoriteNeighbours();
        // add the neighbor in both list
        neighboursList.add(neighbour);
        favoriteNeighbourList.add(neighbour);
        // delete the neighbour in the favorite list
        service.deleteFavoriteNeighbour(neighbour);
        // check if the neighbour is only deleted in the favorite list
        assertFalse(service.getFavoriteNeighbours().contains(neighbour));
        assertTrue(service.getNeighbours().contains(neighbour));
    }






}
