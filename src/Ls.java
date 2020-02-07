import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.text.SimpleDateFormat;
import java.nio.file.attribute.UserPrincipal;

public class Ls {
    public static void main(String[] args) {
        // currentディレクトリのファイル一覧を表示する
        String path = System.getProperty("user.dir");
        System.out.println(path);
        File dir = new File(path);
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());
        }
        //   絶対パスのディレクトリのファイル一覧を表示する
        System.out.println(args[0]);
        File dir2 = new File(args[0]);
        File[] files2 = dir2.listFiles();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd HH:mm");

        for (int i = 0; i < files2.length; i++) {
            UserPrincipal owner = Files.getOwner(path);
            System.out.println(simpleDateFormat.format(files2[i].lastModified()));
            System.out.println(files2[i].getName());
            System.out.println(files2[i].length());
            System.out.println(files2[i].getOwner());
        }
    }
}
