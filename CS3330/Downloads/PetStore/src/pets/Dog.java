/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pets;

import java.time.LocalDateTime;

/**
 *
 * @author dalemusser
 */
public class Dog extends Pet implements LicensedPet, TrainedDog {
    
    public static final String GENUS = "canis";
    
    private Boolean isHappy = false;
    private Boolean isSitting = false;
    private LocalDateTime whenLicensed;
    
    public Dog(String name, int age, Gender gender) {
        super(name, age, "dog", gender);
    }
    
    public void petDog() {
        isHappy = true;
    }
    
    public void praiseDog() {
        isHappy = true;
    }
    
    public void yellAtDog() {
        isHappy = false;
    }
    
    public Boolean getIsHappy() {
        return isHappy;
    }
    
    public String getIsHappyAsString() {
        if (isHappy) {
            return "happy";
        } else {
            return "sad";
        }
    }
    
    @Override
    public String[] getCoreVaccines() {
        
        String[] coreVaccines = {
            "Rabies 1-year",
            "Rabies 3-year",
            "Distemper",
            "Parvovirus",
            "Adenovirus"
        };
        
        return coreVaccines;
    }
    
    @Override
    public String[] getNonCoreVaccines() {
        
        String[] nonCoreVaccines = {
            "Parainfluenza",
            "Bordetella bronchiseptica",
            "Lyme disease",
            "Leptospirosis",
            "Canine influenza"
        };
        
        return nonCoreVaccines;
    }

    @Override
    public Boolean isLicensed() {
        return whenLicensed != null;
    }

    @Override
    public void assignLicense() {
        whenLicensed = LocalDateTime.now();
    }

    @Override
    public LocalDateTime whenLicensed() {
        return whenLicensed;
    }

    @Override
    public void sit() {
        isSitting = true;
    }
    
    @Override
    public void unsit() {
        isSitting = false;
    }

    @Override
    public String speak() {
        return "Yawyahwer";
    }

    @Override
    public String bark(int numBarks) {
       String barks = "";
       for (int i = 0; i < numBarks; i++) {
           barks += "bark!";
       }
       
       return barks;
    }
    
    @Override
    public String toString() {
        String info = super.toString();
        
       String updatedInfo =  String.format("%s, isHappy: %s", info, isHappy);
       
       if (whenLicensed != null) {
           updatedInfo +=  ", whenLicensed: " + whenLicensed;
       }
       
       return updatedInfo;
    }
}
