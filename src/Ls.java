import java.io.File;

public class Ls {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        File dir = new File(path);
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }
    }
}
