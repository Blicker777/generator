package generator;


import java.util.ArrayList;
import java.util.List;

public class SettingsReader {

    private String adress;

    private int width;
    private int height;
    private String number;
    private String date;
    private String fio;
    private int numberVal;
    private int dateVal;
    private int fioVal;

    List<Integer> listInt = new ArrayList<>();
    List<String> listStr = new ArrayList<>();



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getNumberVal() {
        return numberVal;
    }

    public int getDateVal() {
        return dateVal;
    }

    public int getFioVal() {
        return fioVal;
    }
}
