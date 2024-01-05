package org.petshelter.dataservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import org.petshelter.model.Shelter;

public class ShelterDataService {
    private static final String DATA_FILE_PATH = "src/main/resources/shelter_data.json";

    public static void saveShelterData(Shelter shelter) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(DATA_FILE_PATH);
            objectMapper.writeValue(file, shelter.getPets());
            System.out.println("Shelter data has been saved to " + DATA_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Error saving shelter data: " + e.getMessage());
        }
    }

    public static void loadShelterData(Shelter shelter) {
        File file = new File(DATA_FILE_PATH);
        if (file.exists()) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                shelter.setPets(objectMapper.readValue(file, new TypeReference<>() {
                }));
                System.out.println("Shelter data has been loaded from " + DATA_FILE_PATH);
            } catch (IOException e) {
                System.out.println("Error loading shelter data: " + e.getMessage());
            }
        }
    }
}