package presenter;

import model.HumanFreands;
import model.exceptions.EmptyFieldException;
import model.pack_animals.Camel;
import model.pack_animals.Donkey;
import model.pack_animals.Horse;
import model.pets.Cat;
import model.pets.Dog;
import model.pets.Hamster;
import view.View;

import java.time.LocalDate;

public class Presenter {

    private View view;
    private HumanFreands humanFreands;

    public Presenter(View view) {
        this.view = view;
        humanFreands = new HumanFreands();
    }

    public void addAnimal(String name, int birthDay, int birthMonth, int birthYear, int animalType) {
        LocalDate birthdate = LocalDate.of(birthYear, birthMonth, birthDay);
        // "1.Кошка 2.Собака 3.Хомяк 4.Лошадь 5.Осёл 6.Верблюд"

        try {
            switch (animalType) {
                case 1:
                    Cat cat = new Cat(name, birthdate);
                    humanFreands.addAnimal(cat);
                    break;
                case 2:
                    Dog dog = new Dog(name, birthdate);
                    humanFreands.addAnimal(dog);
                    break;
                case 3:
                    Hamster hamster = new Hamster(name, birthdate);
                    humanFreands.addAnimal(hamster);
                    break;
                case 4:
                    Horse horse = new Horse(name, birthdate);
                    humanFreands.addAnimal(horse);
                    break;
                case 5:
                    Donkey donkey = new Donkey(name, birthdate);
                    humanFreands.addAnimal(donkey);
                    break;
                case 6:
                    Camel camel = new Camel(name, birthdate);
                    humanFreands.addAnimal(camel);
                    break;
            }
        } catch (EmptyFieldException e) {
            System.out.println("\n\n\u001B[41m" + e.getMessage());
            System.out.println("Счетчик был обнулен, его значение составляло { " +
                    e.getCount() + " }\n\033[0m\n");
        }
    }

    public void showInfo() {
        String info = humanFreands.getInfo();
        System.out.println(info);
    }

    public void showAnimalList() {
        String text = humanFreands.getList();
        System.out.println(text);
    }

    public void showCommandList(int animalID) {
        String text = humanFreands.getCommandList(animalID);
        System.out.println(text);
    }

    public void addCommand(int animalID, String command) {
        humanFreands.addCommand(animalID, command);
    }

    public int getAnimalListSize() {
        return humanFreands.size();
    }
}
