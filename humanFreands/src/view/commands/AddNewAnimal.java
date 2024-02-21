package view.commands;

import view.ConsoleUI;

public class AddNewAnimal extends Command {

    public AddNewAnimal(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Завести новое животное";
    }

    @Override
    public void execute() {
        consoleUI.addNewAnimal();
    }
}
