import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.petshelter.model.Pet;
import org.petshelter.model.Shelter;
import org.petshelter.service.PetShelterService;
import org.petshelter.service.PetShelterServiceImpl;
import org.petshelter.dataservice.ShelterDataService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShelterDataServiceTest {

    private Shelter shelter;
    private PetShelterService petShelterService;

    @BeforeEach
    public void setUp() {
        shelter = new Shelter();
        petShelterService = new PetShelterServiceImpl(shelter);
    }

    @Test
    public void testSaveAndLoadData() {
        Pet newPet = new Pet("TestPet", "TestBreed", 2);
        petShelterService.addPet(newPet);
        ShelterDataService.saveShelterData(shelter);

        shelter.getPets().clear();

        ShelterDataService.loadShelterData(shelter);
        petShelterService.showAllPets();

        assertEquals(1, shelter.getPets().size());
        assertEquals("1", shelter.getPets().keySet().iterator().next());
    }

    @Test
    public void testSaveAndLoadDataBetweenSessions() {
        Pet newPet1 = new Pet("TestPet1", "TestBreed1", 2);
        Pet newPet2 = new Pet("TestPet2", "TestBreed2", 3);

        petShelterService.addPet(newPet1);
        ShelterDataService.saveShelterData(shelter);
        petShelterService = new PetShelterServiceImpl(shelter);
        ShelterDataService.loadShelterData(shelter);
        petShelterService.addPet(newPet2);
        petShelterService.showAllPets();

        assertEquals(2, shelter.getPets().size());
    }
}