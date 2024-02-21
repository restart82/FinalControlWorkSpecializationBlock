package presenter;

import model.HumanFreands;
import model.pets.Cat;
import view.View;

import java.time.LocalDate;

public class Presenter {

    private View view;
    private HumanFreands humanFreands;

    public Presenter(View view) {
        this.view = view;
        humanFreands = new HumanFreands();
    }

    public void addCat(String name, int birthDay, int birthMonth, int birthYear) {
        LocalDate birthdate = LocalDate.of(birthYear, birthMonth, birthDay);
        Cat cat = new Cat(name, birthdate);
        humanFreands.addAnimal(cat);
    }

    public void showInfo() {
        String info = humanFreands.getInfo();
        System.out.println(info);
    }
}
