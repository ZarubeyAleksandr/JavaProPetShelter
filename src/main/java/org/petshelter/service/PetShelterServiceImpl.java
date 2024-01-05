package org.petshelter.service;


import lombok.RequiredArgsConstructor;
import org.petshelter.model.Pet;
import org.petshelter.model.Shelter;

import static java.lang.System.out;

@RequiredArgsConstructor
public class PetShelterServiceImpl implements PetShelterService {
    private final Shelter shelter;

    @Override
    public void addPet(Pet newPet) {
        try {
            if (shelter.getPets() != null) {
                String key = Integer.toString(shelter.getPets().size() + 1);
                shelter.getPets().put(key, newPet);
                out.println(newPet.getName() + " has been added to the shelter.");
            }
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("Pet could not be added");
        }
    }

    @Override
    public void showAllPets() {
        out.println("List of all pets in the shelter:");
        shelter.getPets().values().forEach(out::println);
    }

    @Override
    public void takePetFromShelter(String petKey) {
        Pet removedPet = shelter.getPets().remove(petKey);
        if (removedPet != null) {
            out.println(petKey + " has been taken from the shelter.");
        } else {
            out.println("Pet not found in the shelter.");
        }
    }

}
