/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petstore;

import pets.Cat;
import pets.Dog;
import pets.Gender;
import pets.Pet;

/**
 *
 * @author dalemusser
 */
public class PetStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dog dog = new Dog("Orion", 5, Gender.MALE);
        dog.assignLicense();
        String bark = dog.bark(5);
        System.out.println(bark);
        System.out.println(dog);
        
        Cat cat = new Cat("Whiskers", 2, Gender.FEMALE);
        cat.assignLicense();
        System.out.println(cat);
        
        System.out.println(Pet.getNumOfPets());
    }
    
}
