import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;

public class Ls {
    public static void main(String[] args) throws IOException {
        // 配列のままだと0番目と1番めを同時に見ることができないため、Listに変える
        List<String> argsLists = new ArrayList<>();
        Collections.addAll(argsLists, args);

        String path = null;
        String options = null;

        if (!argsLists.isEmpty()) {
            for (int i = 0; i < argsLists.size(); i++) {
                if (argsLists.get(i).contains("/")) {
                    // 絶対パスを取得する
                    path = args[i];
                } else if (argsLists.get(i).contains("-")) {
                    options = argsLists.get(i);
                }
            }
        }
        // 絶対パスが入っていないので、相対パスを取得する
        if (path == null) {
            // 相対パスを取得する
            path = System.getProperty("user.dir");
        }

        // pathをファイルの中、配列の中に入れる
        File file = new File(path);
        File[] files = file.listFiles();

        // 何のオプションかを判別する
        if (options != null) {
            if (options.contains("-") && options.contains("l")) {
                System.out.println("オプションl");
                String[] optionlFiles = makeOptionlFiles(files);
                // 他のオプションにも対応できるように、fileのみを作る
            }
            if (options.contains("-") && options.contains("a")) {

            }
            if (options.contains("-") && options.contains("t")) {

            }
            if (options.contains("-") && options.contains("r")) {

            }
        }
        //　表示する

    }

    // コマンドl
    public static String[] makeOptionlFiles(File[] files) throws IOException {
        String[] optionLFiles = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd HH:mm");
            // ファイルの種類
            Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(files[i].toPath());
            String type = PosixFilePermissions.toString(permissions);

            // ハードリンク数
            String link = null;
            if (files[i].isDirectory()) {
                link = String.format("%3s", files[i].list().length + 2 + " ");
            } else {
                link = String.format("%3s", "1" + " ");
            }

            // 所有者
            UserPrincipal owner = Files.getOwner(files[i].toPath());

            // 所有グループ
            String group = Files.getFileAttributeView(files[i].toPath(), PosixFileAttributeView.class).readAttributes().group().getName();

            // ファイルサイズ
            String size = String.format("%5s", files[i].length());

            // タイムスタンプ
            String time = simpleDateFormat.format(files[i].lastModified());

            // ファイル名
            String name = files[i].getName();

            optionLFiles[i] = type + link + owner + " " + group + size + time + " " + name;
            System.out.println(optionLFiles[i]);
        }
        return optionLFiles;
    }
}

