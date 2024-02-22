package model.pets;

import java.time.LocalDate;

public  class Cat extends Pet{

    public Cat(String name, LocalDate birthdate) {
        super(name, birthdate);
        animalType = "Кошка";
    }

}
