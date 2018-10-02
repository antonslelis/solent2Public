package org.solent.com504.factoryandfacade.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.com504.factoryandfacade.model.Animal;
import org.solent.com504.factoryandfacade.model.AnimalObjectFactory;
import org.solent.com504.factoryandfacade.model.FarmFacade;
import org.solent.com504.factoryandfacade.model.FarmFacadeImpl;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author cgallen
 */
public class FarmFacadeTest {

    @Test
    public void FarmFacadeTest() {

        FarmFacade farmFacade = AnimalObjectFactory.createFarmFacade();
        assertNotNull(farmFacade);
        
        // WHAT TESTS WOULD YOU CREATE HERE TO SET UP AND TEST THE FARM FACADE?

    }
    @Test
    public void testGetAllAnimals(){
        FarmFacade farmFacade = AnimalObjectFactory.createFarmFacade();
        List<Animal> al=farmFacade.getAllAnimals();
        assertNotNull(al);
        farmFacade.addCat("Polly");
        farmFacade.addDog("Marty");
        for( Animal an:al){
            System.out.println("animal '" + an.getName()
                    + "' makes this sound '" + an.getSound()
                    + "' because it is a '" + an.getClass().getSimpleName());
        }
    }
    @Test
    public void testAddCat(){
        FarmFacade farmFacade = AnimalObjectFactory.createFarmFacade();
        String name="Tac";
        farmFacade.addCat(name);
        List<Animal> al=farmFacade.getAllAnimals();
        for( Animal an:al){
            System.out.println("animal '" + an.getName()
                    + "' makes this sound '" + an.getSound()
                    + "' because it is a '" + an.getClass().getSimpleName());
        }
    }
    @Test
     public void testAddDog(){
        FarmFacade farmFacade = AnimalObjectFactory.createFarmFacade();
        String name="God";
        farmFacade.addDog(name);
        List<Animal> al=farmFacade.getAllAnimals();
        for( Animal an:al){
            System.out.println("animal '" + an.getName()
                    + "' makes this sound '" + an.getSound()
                    + "' because it is a '" + an.getClass().getSimpleName());
        }
     }
     @Test
     public void testAddCow(){
        FarmFacade farmFacade = AnimalObjectFactory.createFarmFacade();
        String name="Woc";
        farmFacade.addCow(name);
        List<Animal> al=farmFacade.getAllAnimals();
        for( Animal an:al){
            System.out.println("animal '" + an.getName()
                    + "' makes this sound '" + an.getSound()
                    + "' because it is a '" + an.getClass().getSimpleName());
        }

        
    }
}
