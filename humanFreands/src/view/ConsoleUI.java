package view;

import presenter.Presenter;

import java.util.Scanner;

public class ConsoleUI implements View, ConsoleCommands{

    private static final String INPUT_ERROR = "Что то не то((";
    private static final String ANIMAL_TYPES =
            "1. Кошка\n2. Собака\n3. Хомяк\n4. Лошадь\n5. Осёл\n6. Верблюд\n";
    private Scanner scanner;
    private Presenter presenter;
    private boolean workFlag;
    private MainMenu mainMenu;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        presenter = new Presenter(this);
        workFlag = true;
        mainMenu = new MainMenu(this);
    }
    @Override
    public void addNewAnimal() {

        printAnswer("Введите имя питомца: ");
        String name = scanner.nextLine();

        printAnswer("Введите дату рождения питомца: ");

        printAnswer("\tдень");
        int birthDay = scanInt();

        printAnswer("\tмесяц");
        int birthMonth = scanInt();

        printAnswer("\tгод");
        int birthYear = scanInt();

        printAnswer("К какому виду относится животное?");
        printAnswer(ANIMAL_TYPES);
        int animalType = scanInt();

        if (    checkYear(birthYear)    &&
                checkMonth(birthMonth)  &&
                checkDay(birthDay)      &&
                checkAnimalType(animalType)) {
            presenter.addAnimal(name, birthDay, birthMonth, birthYear, animalType);
        }

        presenter.showInfo();
    }

    @Override
    public void showCommandList() {

        printAnswer("Команды какого животного нужно посмотреть?");
        presenter.showAnimalList();
        int animalID = scanInt();
        if (checkAnimalID(animalID)) {
            presenter.showCommandList(animalID);
        }
    }

    @Override
    public void teachAnimal() {

        printAnswer("Какое животное нужно обучить?");
        presenter.showAnimalList();
        int animalID = scanInt();
        printAnswer("Введите команду:");
        String command = scanner.nextLine();
        if (checkAnimalID(animalID)) {
            presenter.addCommand(animalID, command);
        }
    }

    @Override
    public void finish() {
        System.out.println("Пока");
        workFlag = false;
    }

    @Override
    public void printAnswer(String text) {
        System.out.println(text);
    }

    @Override
    public void start() {
        System.out.println("Привет!!");
        while (workFlag) {
            printMenu();
            execute();
        }
    }

    private void printMenu() {
        System.out.println(mainMenu.menu());
    }

    private void execute() {
        String line = scanner.nextLine();
        if (checkTextForInt(line)) {
            int numCommand = Integer.parseInt(line);
            if (checkCommand(numCommand)) {
                mainMenu.execute(numCommand);
            }
        }
    }

    private boolean checkTextForInt(String text) {
        if (text.matches("[0-9]+")) {
            return true;
        } else {
            System.out.println(INPUT_ERROR);
            return false;
        }
    }

    private boolean checkCommand(int numCommand) {
        if (numCommand <= mainMenu.getSize()) {
            return true;
        } else {
            System.out.println(INPUT_ERROR);
            return false;
        }
    }

    private boolean checkYear(int year) {
        if (year != 0) {
            return true;
        } else {
            System.out.println("Введен некорректный год!!");
            return false;
        }
    }

    private boolean checkMonth(int month) {
        if (month >= 1 && month <= 12) {
            return true;
        } else {
            System.out.println("Введен некорректный месяц!!");
            return false;
        }
    }

    private boolean checkDay(int day) {
        if (day >= 1 && day <= 31) {
            return true;
        } else {
            System.out.println("Введен некорректный день!!");
            return false;
        }
    }

    private boolean checkAnimalType(int animalType) {
        if (animalType >= 1 && animalType <= 6) {
            return true;
        } else {
            System.out.println("Нет такого вида!!");
            return false;
        }
    }

    private boolean checkAnimalID(int animalID) {
        int size = presenter.getAnimalListSize();
        if (animalID >= 1 && animalID <= size) {
            return true;
        } else {
            System.out.println("Нет такого животного!!");
            return false;
        }
    }

    private int scanInt() {
        String strNumber = scanner.nextLine();
        int result;
        if (!checkTextForInt(strNumber)) {
            result = scanInt();
        } else {
            result = Integer.parseInt(strNumber);
        }
        return result;
    }
}
