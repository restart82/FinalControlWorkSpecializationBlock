package model.pack_animals;

import model.Animal;

import java.time.LocalDate;

public abstract class PackAnimal extends Animal {

    public PackAnimal(String name, LocalDate birthdate) {
        super(name, birthdate);
    }
}
