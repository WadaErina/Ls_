import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;
import java.util.List;

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
        String[] viewFiles = makeNoOptionFiles(files);

        // 何のオプションかを判別する
        if (options != null) {
            if (options.contains("-") && options.contains("l")) {
                viewFiles = makeOptionLFiles(files);
                // 他のオプションにも対応できるように、fileのみを作る
            }
            if (options.contains("-") && options.contains("a")) {

            }
            if (options.contains("-") && options.contains("t")) {

            }
            if (options.contains("-") && options.contains("r")) {
                viewFiles = makeOptionRFiles(viewFiles);
            }
        }

        //　表示する
        viewAllFiles(viewFiles);
    }

    // オプションl
    public static String[] makeOptionLFiles(File[] files) throws IOException {
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
        }
        return optionLFiles;
    }

    public static String[] makeNoOptionFiles(File[] files) {
        String[] noOptionLFiles = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            noOptionLFiles[i] = fileName + " ";
        }
        return noOptionLFiles;
    }

    public static void viewAllFiles(String[] viewFiles) {
        for (int i = 0; i < viewFiles.length; i++) {
            System.out.println(viewFiles[i]);
        }
    }

    public static String[] makeOptionRFiles(String[] viewFiles) {
        String[] optionRFiles = new String[viewFiles.length];

        List<String> toList = new ArrayList<>(viewFiles.length);

        for (int i = 0; i < viewFiles.length; i++) {
            toList.add(viewFiles[i]);
        }

        Collections.reverse(toList);

        for (int i = 0; i < viewFiles.length; i++) {
            optionRFiles[i] = toList.get(i);
        }

        return optionRFiles;
    }
}

