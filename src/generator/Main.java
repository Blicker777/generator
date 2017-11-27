package generator;


public class Main {

    public static void main(String[] args) {

        SettingsReader reader = new SettingsReader();
        reader.fileReader();
        System.out.println(reader);

        DateReader dateReader = new DateReader("source-data.tsv");
        System.out.println(dateReader.fileReaderString());

        Generator generator = new Generator("result.txt");
        generator.generatorText(dateReader, reader);
    }
}
