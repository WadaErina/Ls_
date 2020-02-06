import java.io.File;

public class Ls {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        System.out.println(path);
        File[] files = path.listFiles();

        String fileName = new File(path).getName();
        System.out.println();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String fileName = new File(path).getName();
            System.out.println(fileName);
        }
    }
}
