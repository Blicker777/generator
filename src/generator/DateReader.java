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

    private List<List<StringBuffer>> dateList;

    public DateReader(String adress) {
        this.adress = adress;
    }

    public List<List<StringBuffer>> fileReaderString() {

        dateList = new ArrayList<>();

        try {
            List<String> list = Files.readAllLines(Paths.get(adress), StandardCharsets.UTF_16);
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
                dateList.get(i).add(new StringBuffer(""));
            }
        }

        return dateList;
    }


    public List<StringBuffer> readerString(String stra) {

        List<StringBuffer> listString = new ArrayList<>();
        StringBuffer str = new StringBuffer();


        for (int i = 0; i < stra.length(); i++) {

            if (stra.charAt(i) == ' ' || stra.charAt(i) == '\t') {

                if (str.length() != 0) {
                    if(listString.size() > NUMBERCOlUMNDATE - 2)
                        break;

                    listString.add(new StringBuffer(str));
                    str.setLength(0);
                }

                continue;
            }

            str.append(stra.charAt(i));

        }

        if (str.length() != 0)
            listString.add(new StringBuffer(str));

        System.out.println(listString);

        return listString;

    }

    public List<List<StringBuffer>> getDateList() {
        return dateList;
    }

}
