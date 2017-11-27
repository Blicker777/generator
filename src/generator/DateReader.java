package generator;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DateReader {

    private final int NUMBERCOlUMNDATE = 5;

    private String adress;

    private List<List<String>> dateList;

    public DateReader(String adress) {
        this.adress = adress;
    }

    public List<List<String>> fileReaderString() {

        dateList = new ArrayList<>();

        try {
            List<String> list = Files.readAllLines(Paths.get(adress), StandardCharsets.UTF_8);
            System.out.println(list);

            for (String str: list) {
                if(!str.isEmpty())
                    dateList.add(readerString(str));
                else
                    continue;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < dateList.size(); i++){
            while(dateList.get(i).size() < NUMBERCOlUMNDATE){
                dateList.get(i).add("");
            }
        }

        return dateList;
    }


    public List<String> readerString(String stra) {

        List<String> listString = new ArrayList<>();
        String str = "";


        for (int i = 0; i < stra.length(); i++) {

            if (stra.charAt(i) == ' ' || stra.charAt(i) == '\t') {

                if (!str.isEmpty()) {
                    if(listString.size() > NUMBERCOlUMNDATE - 2)
                        break;

                    listString.add(str);
                    str = "";
                }

                continue;
            }

            str += stra.charAt(i);

        }

        if (!str.isEmpty())
            listString.add(str);

        System.out.println(listString);

        return listString;

    }

    public List<List<String>> getDateList() {
        return dateList;
    }

}
