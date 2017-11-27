package generator;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    private final int NUMBER = 3;

    private String adress;
    private String remainderStr = "";
    private int numString = 1;
    private List<List<String>> titleList = new ArrayList<>();
    private List<List<String>> listList  = new ArrayList<List<String>>();
    private List<String> list = new ArrayList<>();
    private char ch = '~';
    private boolean bool;

    public Generator(String adress) {
        this.adress = adress;
    }
    public List<List<String>> getListList() {
        return listList;
    }



    public void generatorText(DateReader dateReader, SettingsReader settingsReader) {

        String title = "";
        String separator = "";
        int height = settingsReader.getHeight();

        int num = Integer.parseInt(settingsReader.getListInt().get(0)) +
                Integer.parseInt(settingsReader.getListInt().get(1)) +
                Integer.parseInt(settingsReader.getListInt().get(2));

        for (int i = 0; i < num + 10; i++){
            separator += "-";
        }

        try {
            FileWriter writer = new FileWriter("result.txt", false);

            listColumn(settingsReader.getListStr(), settingsReader);
            generatorString(settingsReader, writer, separator);
            for (List<String> list: listList) {
                titleList.add(new ArrayList<String>(list));
            }
            listList.clear();

            for (int i = 0; i < dateReader.getDateList().size(); i++) {
                if(i == dateReader.getDateList().size() - 1)
                    bool = false;
                listColumn(dateReader.getDateList().get(i), settingsReader);
                generatorString(settingsReader, writer, separator);
                listList.clear();

            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showString(FileWriter writer){

        String str;
        int maxi = titleList.get(0).size();

        for (int i = 0; i < maxi; i++) {
            str = ("| ");
            for (int j = 0; j < NUMBER; j++) {

                str += titleList.get(j).get(i) + " | ";
            }

            try {
                writer.write(str + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.print(str);
            str = "";

            System.out.println();
            numString++;

        }

    }

    private void generatorString(SettingsReader settingsReader, FileWriter writer, String separator) {

        String str = "";
        int max = listList.get(0).size();
        int height = settingsReader.getHeight();

        for (int i = 1; i < listList.size(); i++) {
            if(listList.get(i).size() > max)
                max = listList.get(i).size();
        }

        for (int i = 0; i < NUMBER; i++){
            for (int j = 0; j < Integer.parseInt(settingsReader.getListInt().get(i)); j++) {
                str += " ";
            }
            while (listList.get(i).size() < max) {
                listList.get(i).add(str);
            }
            str = "";
        }

        try {
            for (int i = 0; i < max; i++) {

                if(numString == height * (int)(numString/height)) {

                    System.out.println(ch);
                    writer.write(ch + "\n");
                    showString(writer);
                    System.out.println(separator);
                    writer.write(separator + "\n");
                }

                str = ("| ");

                for (int j = 0; j < NUMBER; j++) {

                    str += listList.get(j).get(i) + " | ";

                }

                writer.write(str + "\n");
                System.out.print(str);
                str = "";

                System.out.print(numString++);
                System.out.println();

            }

            if(numString == height * (int)(numString/height)) {
                return;
            }

            else {
                System.out.println(separator);
                writer.write(separator  + "\n");

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void listColumn(List<String> listExample, SettingsReader settingsReader) {

        List<String> listString;
        String string;
        int numStr;
        boolean size = true;

        for (int i = 0; i < NUMBER; i++) {

            numStr = Integer.parseInt(settingsReader.getListInt().get(i));

            if(listExample.size() > NUMBER && i == 2) {

                generatorColumn(listExample.get(i), numStr);
                listList.add(new ArrayList<String>(list));
                list.clear();
                string = remainderStr;

                for(int j = 3; j < listExample.size(); j++) {

                    if(listExample.get(j).length() <= numStr - remainderStr.length() - 1) {
                        generatorColumn(string + " " + listExample.get(j), numStr);
                        listList.get(i).set(listList.get(i).size() - 1, list.get(list.size() - 1));
                        list.clear();
                        string = remainderStr;
                    }
                    else {
                        generatorColumn(listExample.get(j), numStr);
                        for (String str: list) {
                            listList.get(i).add(str);
                        }
                        list.clear();
                        string = remainderStr;
                    }
                }

                break;
            }

            generatorColumn(listExample.get(i), numStr);
            listList.add(new ArrayList<String>(list));
            list.clear();
            remainderStr = "";

        }

    }

    private String generatorColumn(String str, int num){

        String stringDate = "";

        if(str.isEmpty()) {
            return "";
        }

        if(str.length() <= num) {
            stringDate = str;
            remainderStr = str;
            for (int i = str.length(); i < num; i++) {
                stringDate += " ";
            }
            list.add(stringDate);

            return "";
        }

        if(str.length() > num) {

            for (int i = 0; i < num; i++) {
                stringDate += str.charAt(i);
            }

            list.add(stringDate);
            stringDate = "";

            for(int i = num; i < str.length(); i++) {
                stringDate += str.charAt(i);
            }

        }

        return generatorColumn(stringDate, num);

    }

}
