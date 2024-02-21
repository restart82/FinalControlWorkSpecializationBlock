package model;

import java.util.ArrayList;
import java.util.List;

public class HumanFreands {

    private List<Animal> animalList;
    private int count = 1;

    public HumanFreands() {
        animalList = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animal.setId(count++);
        animalList.add(animal);
    }

    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Animal animal: animalList) {
            stringBuilder.append(animal);
        }
        return stringBuilder.toString();
    }
}
