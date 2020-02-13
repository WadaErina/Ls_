import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

public class Ls {
    public static void main(String[] args) throws IOException {
        System.out.println(args[1]);
        if (args[0].contains("~/") || args[1].contains("~/")) {                  // 絶対パスが送られてきたとき
            File dir = new File(args[0]);
            File[] files = dir.listFiles();

            if (args.length == 0) {                                                                 // lsコマンドのみのとき
                for (int i = 0; i < files.length; i++) {
                    System.out.print(files[i].getName() + " ");
                }
                System.out.println();
            } else if (args[0].contains("l") && args[0].contains("-")) {                            // オプションがあるとき
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd HH:mm");
                for (int i = 0; i < files.length; i++) {
                    // ファイルの種類
                    if (files[i].isFile()) {
                        System.out.print("-");
                    } else if (files[i].isDirectory()) {
                        System.out.print("d");
                    } else {
                        System.out.print("l");
                    }
                    Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(files[i].toPath());
                    System.out.print(PosixFilePermissions.toString(permissions) + " ");

                    // ハードリンク数
                    if (files[i].isDirectory()) {
                        System.out.print(String.format("%3s", files[i].list().length + 2 + " "));
                    } else {
                        System.out.print(String.format("%3s", "1" + " "));
                    }

                    // 所有者と所有グループ
                    System.out.print(Files.getOwner(files[i].toPath()) + " ");
                    System.out.print(Files.getFileAttributeView(files[i].toPath(), PosixFileAttributeView.class).readAttributes().group().getName() + " ");

                    // ファイルサイズ
                    System.out.print(String.format("%5s", files[i].length()) + " ");

                    // タイムスタンプ
                    System.out.print(simpleDateFormat.format(files[i].lastModified()) + " ");

                    // ファイル名
                    System.out.print(files[i].getName() + " ");

                    System.out.println();
                }
            } else if (args[0].contains("a") && args[0].contains("-")) {

            }
        } else {      // カレントディレクトリから
            String path = System.getProperty("user.dir");
            File dir = new File(path);
            File[] files = dir.listFiles();

            if (args.length == 0) {                                                                 // lsコマンドのみのとき
                for (int i = 0; i < files.length; i++) {
                    System.out.print(files[i].getName() + " ");
                }
                System.out.println();
            } else if (args[0].contains("l") && args[0].contains("-")) {                            // オプションがあるとき
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd HH:mm");
                for (int i = 0; i < files.length; i++) {
                    // ファイルの種類
                    if (files[i].isFile()) {
                        System.out.print("-");
                    } else if (files[i].isDirectory()) {
                        System.out.print("d");
                    } else {
                        System.out.print("l");
                    }
                    Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(files[i].toPath());
                    System.out.print(PosixFilePermissions.toString(permissions) + " ");

                    // ハードリンク数
                    if (files[i].isDirectory()) {
                        System.out.print(String.format("%3s", files[i].list().length + 2 + " "));
                    } else {
                        System.out.print(String.format("%3s", "1" + " "));
                    }

                    // 所有者と所有グループ
                    System.out.print(Files.getOwner(files[i].toPath()) + " ");
                    System.out.print(Files.getFileAttributeView(files[i].toPath(), PosixFileAttributeView.class).readAttributes().group().getName() + " ");

                    // ファイルサイズ
                    System.out.print(String.format("%5s", files[i].length()) + " ");

                    // タイムスタンプ
                    System.out.print(simpleDateFormat.format(files[i].lastModified()) + " ");

                    // ファイル名
                    System.out.print(files[i].getName() + " ");

                    System.out.println();
                }
            } else if (args[0].contains("a") && args[0].contains("-")) {

            }
        }
    }
}