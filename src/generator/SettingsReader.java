package generator;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public SettingsReader(String adress) {
        this.adress = adress;
    }

    public void fileReader(){

        String strInt = "";
        String str = "";
        int c;

        try {
            FileReader read = new FileReader(adress);

            try {
                while ((c = read.read()) != -1 ){

                    if(Character.isDigit(c)) {
                        strInt += (char)c;
                        if (!str.isEmpty()) {
                            listStr.add(str);
                            str = "";
                        }
                    }

                    else {
                        if(!strInt.isEmpty()){
                            listInt.add(Integer.parseInt(strInt));
                            strInt = "";
                        }

                        if(c == ' '){
                            continue;
                        }
                        str += (char)c;

                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if(!strInt.isEmpty())
            listInt.add(Integer.parseInt(strInt));
        if (!str.isEmpty())
            listStr.add(str);

        width = listInt.get(0);
        height = listInt.get(1);

        number = listStr.get(0);
        date = listStr.get(1);
        fio = listStr.get(2);

        numberVal = listInt.get(1);
        dateVal = listInt.get(2);
        fioVal = listInt.get(3);

    }

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
