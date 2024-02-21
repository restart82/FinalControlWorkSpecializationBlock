package view.commands;

import view.ConsoleUI;

public class ShowCommandList extends Command {

    public ShowCommandList(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Увидеть список команд, которые выполняет животное";
    }

    @Override
    public void execute() {
        consoleUI.showCommandList();
    }
}
