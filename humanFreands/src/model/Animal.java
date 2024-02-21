package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {


    private int id;
    protected String name;
    protected LocalDate birthday;
    protected List<String> commands;

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

    @Override
    public String toString() {
        return  id                  +
                ". "                +
                "\nИмя: "           +
                name                +
                "\nДата рождения: " +
                birthday            +
                "\nКоманды: "       +
                commands            +
                "\n";
    }
}