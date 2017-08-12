package generator;


public class Main {

    public static void main(String[] args) {

        SettingsReader reader = new SettingsReader("settings.xml");
        reader.fileReader();
    }
}
