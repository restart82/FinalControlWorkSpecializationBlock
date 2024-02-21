package view;

import presenter.Presenter;

import java.util.Scanner;

public class ConsoleUI implements View, ConsoleCommands{

    private static final String INPUT_ERROR = "Что то не то((";
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

        System.out.println("Введите имя вашего питомца: ");
        String name = scanner.nextLine();

        System.out.println("Введите дату рождения питомца: ");

        System.out.println("\tдень");
//        String strBirthDay = scanner.nextLine();
//        int birthDay;
//        if (checkTextForInt(strBirthDay)) {
//            birthDay = Integer.parseInt(strBirthDay);
//        } else {
//            birthDay = 0;
//        }
        int birthDay = scanInt();

        System.out.println("\tмесяц");
        String strBirthMonth = scanner.nextLine();
        int birthMonth;
        if (checkTextForInt(strBirthMonth)) {
            birthMonth = Integer.parseInt(strBirthMonth);
        } else {
            birthMonth = 0;
        }

        System.out.println("\tгод");
        String strBirthYear = scanner.nextLine();
        int birthYear;
        if (checkTextForInt(strBirthYear)) {
            birthYear = Integer.parseInt(strBirthYear);
        } else {
            birthYear = 0;
        }

        if (checkYear(birthYear) && checkMonth(birthMonth) && checkDay(birthDay)) {
            presenter.addCat(name, birthDay, birthMonth, birthYear);
        }

        presenter.showInfo();

    }

    @Override
    public void showCommandList() {

    }

    @Override
    public void teachAnimal() {

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

    private int scanInt() {
        String strNumber = scanner.nextLine();
        int result;
        if (!checkTextForInt(strNumber)) {
            System.out.println(INPUT_ERROR);
            result = scanInt();
        } else {
            result = Integer.parseInt(strNumber);
        }
        return result;
    }
}
