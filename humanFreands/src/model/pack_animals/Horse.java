package model.pack_animals;

import java.time.LocalDate;

public class Horse extends PackAnimal{

    public Horse(String name, LocalDate birthdate) {
        super(name, birthdate);
        animalType = "Лошадь";
    }
}
