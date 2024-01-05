
import org.petshelter.model.Pet;
import org.petshelter.model.Shelter;
import org.petshelter.service.PetShelterService;
import org.petshelter.service.PetShelterServiceImpl;
import org.petshelter.dataservice.ShelterDataService;
import java.util.Scanner;

import static java.lang.System.out;

public class PetShelterApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Shelter shelter = new Shelter();
    private static final PetShelterService petShelterService = new PetShelterServiceImpl(shelter);

    public static void main(String[] args) {
        ShelterDataService.loadShelterData(shelter);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    out.println("Enter pet details:");
                    out.print("Name: ");
                    String name = scanner.nextLine();
                    out.print("Breed: ");
                    String breed = scanner.nextLine();
                    out.print("Age: ");
                    int age = scanner.nextInt();
                    Pet newPet = new Pet(name, breed, age);
                    petShelterService.addPet(newPet);
                    break;
                case 2:
                    petShelterService.showAllPets();
                    break;
                case 3:
                    out.println("Enter the key of the pet to take from the shelter:");
                    String petKey = scanner.nextLine();
                    petShelterService.takePetFromShelter(petKey);
                    break;
                case 4:
                    ShelterDataService.saveShelterData(shelter);
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Shelter Application Menu:");
        System.out.println("1. Add Pet");
        System.out.println("2. Show All Pets");
        System.out.println("3. Take Pet from Shelter");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}
