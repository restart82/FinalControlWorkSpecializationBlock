package view.commands;

import view.ConsoleUI;

public class TeachAnimal extends Command {

    public TeachAnimal(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Обучить животное новым командам";
    }

    @Override
    public void execute() {
        consoleUI.teachAnimal();
    }
}
