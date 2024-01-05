import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.petshelter.model.Pet;
import org.petshelter.model.Shelter;
import org.petshelter.service.PetShelterServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetShelterServiceImplTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Shelter shelter;
    private PetShelterServiceImpl petShelterService;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        shelter = new Shelter();
        petShelterService = new PetShelterServiceImpl(shelter);
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testAddPet() {
        Pet newPet = new Pet("TestPet", "TestBreed", 3);

        petShelterService.addPet(newPet);

        assertEquals(newPet.getName() + " has been added to the shelter.\r\n", outputStream.toString());
    }

    @Test
    public void testShowAllPets() {
        Pet pet1 = new Pet("TestPet1", "TestBreed1", 3);
        Pet pet2 = new Pet("TestPet2", "TestBreed2", 2);

        shelter.getPets().put("1", pet1);
        shelter.getPets().put("2", pet2);

        petShelterService.showAllPets();

        assertEquals("""
                List of all pets in the shelter:\r
                Pet(name=TestPet1, breed=TestBreed1, age=3)\r
                Pet(name=TestPet2, breed=TestBreed2, age=2)\r
                """, outputStream.toString());
    }

    @Test
    public void testTakePetFromShelter() {
        Pet pet = new Pet("TestPet", "TestBreed", 3);
        shelter.getPets().put("1", pet);

        petShelterService.takePetFromShelter("1");

        assertEquals("1 has been taken from the shelter.\r\n", outputStream.toString());
    }
}
