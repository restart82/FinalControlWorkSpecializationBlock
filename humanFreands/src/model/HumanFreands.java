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

    public String getCommandList(int animalID) {
        List<String> list = animalList.get(animalID - 1).getCommands();
        String name = animalList.get(animalID - 1).getName();
        StringBuilder stringBuilder = new StringBuilder();

        if (list.isEmpty()) {
            stringBuilder.append(name);
            stringBuilder.append(" ничего не умеет(\n");
        } else {
            stringBuilder.append("Команды, которые знвет ");
            stringBuilder.append(name);
            stringBuilder.append(":\n");
            for (String command : list) {
                stringBuilder.append(command);
                stringBuilder.append(", ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public void addCommand(int animalID, String command) {
        animalList.get(animalID - 1).addCommand(command);
    }

    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Animal animal: animalList) {
            stringBuilder.append(animal);
        }
        return stringBuilder.toString();
    }

    public String getList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Animal animal: animalList) {
            stringBuilder.append(animal.getId());
            stringBuilder.append(". ");
            stringBuilder.append(animal.getName());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public int size() {
        return animalList.size();
    }
}