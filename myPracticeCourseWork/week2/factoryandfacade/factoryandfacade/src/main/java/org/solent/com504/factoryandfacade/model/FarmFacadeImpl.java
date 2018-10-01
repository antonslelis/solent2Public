/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.factoryandfacade.model;

import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author 3lelia74
 */
public class FarmFacadeImpl implements FarmFacade{
    
    private List<Animal> animalList =  new LinkedList<Animal>();
    
    public List<Animal> getAllAnimals() {
        return animalList;
    }

    public void addDog(String name) {
        Animal anim= AnimalObjectFactory.createDog();
        anim.setName(name);
        animalList.add(anim);
    }

    public void addCat(String name) {
        Animal anim= AnimalObjectFactory.createCat();
        anim.setName(name);
        animalList.add(anim);
    }

    public void addCow(String name) {
        Animal anim= AnimalObjectFactory.createCow();
        anim.setName(name);
        animalList.add(anim);
    }
    
}
