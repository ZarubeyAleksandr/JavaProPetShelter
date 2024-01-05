package org.petshelter.service;

import org.petshelter.model.Pet;
public interface PetShelterService {

    void addPet(Pet pet);
    void showAllPets();
    void takePetFromShelter(String petKey);
}
