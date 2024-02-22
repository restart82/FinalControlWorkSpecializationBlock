package model.pack_animals;

import java.time.LocalDate;

public class Donkey extends PackAnimal{

    public Donkey(String name, LocalDate birthdate) {
        super(name, birthdate);
        animalType = "Осёл";
    }
}
