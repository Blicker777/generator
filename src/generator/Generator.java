package generator;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    private final int NUMBER = 3;

    private String adress;
    private StringBuffer remainderStr;
    private int numString = 1;
    private List<List<StringBuffer>> titleList = new ArrayList<>();
    private List<List<StringBuffer>> listList  = new ArrayList<>();
    private List<StringBuffer> list = new ArrayList<>();
    private char ch = '~';
    private boolean bool;

    public Generator(String adress) {
        this.adress = adress;
    }

    public void generatorText(DateReader dateReader, SettingsReader settingsReader) {

        String separator = "";

        int num = Integer.parseInt(settingsReader.getListInt().get(0)) +
                Integer.parseInt(settingsReader.getListInt().get(1)) +
                Integer.parseInt(settingsReader.getListInt().get(2));

        for (int i = 0; i < num + 10; i++){
            separator += "-";
        }

        try {
            FileWriter writer = new FileWriter("result.txt", false);

            listColumn(settingsReader.getListStr(), settingsReader);
            for (List<StringBuffer> list: listList) {
                titleList.add(new ArrayList<StringBuffer>(list));
            }
            generatorString(settingsReader, writer, separator);
            listList.clear();

            for (int i = 0; i < dateReader.getDateList().size(); i++) {
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

        StringBuffer str = new StringBuffer();
        int max = listList.get(0).size();
        int height = settingsReader.getHeight();

        for (int i = 1; i < listList.size(); i++) {
            if(listList.get(i).size() > max)
                max = listList.get(i).size();
        }

        for (int i = 0; i < NUMBER; i++){
            for (int j = 0; j < Integer.parseInt(settingsReader.getListInt().get(i)); j++) {
                str.append(" ");
            }
            while (listList.get(i).size() < max) {
                listList.get(i).add(new StringBuffer(str));
            }
            str.setLength(0);
        }

        try {
            for (int i = 0; i < max; i++) {

                if(numString == height * (int)(numString/height) + 1) {

                    if(numString != 1) {

                        System.out.println(ch);
                        writer.write(ch + "\n");
                        showString(writer);
                        System.out.println(separator);
                        writer.write(separator + "\n");
                    }

                }

                str.append("| ");

                for (int j = 0; j < NUMBER; j++) {

                    str.append(listList.get(j).get(i)).append(" | ");

                }

                writer.write(str + "\n");
                System.out.print(str);
                str.setLength(0);

                System.out.print(numString++);
                System.out.println();

            }

            if(numString == height * (int)(numString/height) + 1) {
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

    private void listColumn(List<StringBuffer> listExample, SettingsReader settingsReader) {

        StringBuffer string;
        int numStr;

        for (int i = 0; i < NUMBER; i++) {

            numStr = Integer.parseInt(settingsReader.getListInt().get(i));

            if(listExample.size() > NUMBER && i == 2) {

                generatorColumn(listExample.get(i), numStr);
                listList.add(new ArrayList<StringBuffer>(list));
                list.clear();
                string = remainderStr;

                for(int j = 3; j < listExample.size(); j++) {

                    if(listExample.get(j).length() <= numStr - remainderStr.length() - 1) {
                        generatorColumn(string.append(listExample.get(j)), numStr);
                        listList.get(i).set(listList.get(i).size() - 1, list.get(list.size() - 1));
                        list.clear();
                        string = remainderStr;
                    }
                    else {
                        generatorColumn(listExample.get(j), numStr);
                        for (StringBuffer str: list) {
                            listList.get(i).add(new StringBuffer(str));
                        }
                        list.clear();
                        string = remainderStr;
                    }
                }

                break;
            }

            generatorColumn(listExample.get(i), numStr);
            listList.add(new ArrayList<StringBuffer>(list));
            list.clear();
            remainderStr.setLength(0);

        }

    }

    private String generatorColumn(StringBuffer str, int num){

        StringBuffer stringDate = new StringBuffer();


        if(str.length() == 0) {
            return "";
        }

        if(str.length() <= num) {
            stringDate= str;
            remainderStr = str;
            for (int i = str.length(); i < num; i++) {
                stringDate.append(" ");
            }
            list.add(new StringBuffer(stringDate));

            return "";
        }

        if(str.length() > num) {

            for (int i = 0; i < num; i++) {
                stringDate.append(str.charAt(i));
            }

            list.add(new StringBuffer(stringDate));
            stringDate.setLength(0);

            for(int i = num; i < str.length(); i++) {
                stringDate.append(str.charAt(i));
            }

        }

        return generatorColumn(stringDate, num);

    }

    public List<List<StringBuffer>> getListList() {
        return listList;
    }


}
