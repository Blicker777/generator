package generator;


public class Main {

    public static void main(String[] args) {

        SettingsReader reader = new SettingsReader();
        reader.fileReader();
        System.out.println(reader);
    }
}
