package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {


    private int id;
    protected String name;
    protected LocalDate birthday;
    protected List<String> commands;
    protected String animalType;
    protected String animalCLass;

    public Animal(String name, LocalDate birthday) {
        id = -1;
        this.name = name;
        this.birthday = birthday;
        this.commands = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getAnimalCLass() {
        return animalCLass;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id);
        stringBuilder.append(". \tИмя:");
        stringBuilder.append(name);
        stringBuilder.append("\n\tДата рождения: ");
        stringBuilder.append(birthday);
        stringBuilder.append("\n\tКоманды: ");
        stringBuilder.append(commands);
        stringBuilder.append("\n\t");
        stringBuilder.append(animalCLass);
        stringBuilder.append(", ");
        stringBuilder.append(animalType);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}