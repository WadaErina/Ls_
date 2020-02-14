import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;

public class Ls {
    public static void main(String[] args) throws IOException {
        //カレントディレクトリのみで考える
        String path = System.getProperty("user.dir");
        File dir = new File(path);
        File[] files = dir.listFiles();
        File[] hiddenFiles;

//        hiddenFiles = files.isHidden();
        // 配列のままだと0番目と1番めを同時に見ることができないため、Listに変える
        List<String> argsLists = new ArrayList<>();
        Collections.addAll(argsLists, args);

        if (!argsLists.isEmpty()) {
            for (int i = 0; i < argsLists.size(); i++) {
                if (argsLists.get(i).contains("/")) {
                    System.out.println("ぜったいぱす");
                    // ぜったいぱすを取得する
                    File absolutePath = new File(args[i]);
//                } else if (argsLists.get(i).contains("-")) {
//                    System.out.println("おぷしょん" + argsLists.get(i));
//                    //おぷしょんをりすとにいれる
//                    List<String> options = new ArrayList<>();
//                    Collections.addAll(options, argsLists.get(i));
//                    System.out.println("options =" + options.toString());
                }
            }
        } else {
            // そうたいぱすをしゅとくする
            System.out.println("そうたいぱす");
            String relativePath = System.getProperty("user.dir");
        }


        if (args.length == 0) {                                                                 // パスなし、オプションなし
            for (int i = 0; i < files.length; i++) {
                System.out.print(files[i].getName() + " ");
            }
            System.out.println();
        }
        // パスなし、オプションなし以外



        for (String p : argsLists) {
            if (p.contains("/")) { // パスあり
                File abDir = new File(args[0]);
                File[] abFiles = abDir.listFiles();
            for (String p2 : argsLists) {
                if (p.contains("-") && p.contains("l")) { //　パスあり、オプションあり
                    System.out.println("ll");
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd HH:mm");
                    for (int i = 0; i < abFiles.length; i++) {
                        // ファイルの種類
                        if (abFiles[i].isFile()) {
                            System.out.print("-");
                        } else if (abFiles[i].isDirectory()) {
                            System.out.print("d");
                        } else {
                            System.out.print("l");
                        }
                        Set<PosixFilePermission> permissions = Files.getPosixFilePermissions(abFiles[i].toPath());
                        System.out.print(PosixFilePermissions.toString(permissions) + " ");

                        // ハードリンク数
                        if (abFiles[i].isDirectory()) {
                            System.out.print(String.format("%3s", abFiles[i].list().length + 2 + " "));
                        } else {
                            System.out.print(String.format("%3s", "1" + " "));
                        }

                        // 所有者と所有グループ
                        System.out.print(Files.getOwner(abFiles[i].toPath()) + " ");
                        System.out.print(Files.getFileAttributeView(abFiles[i].toPath(), PosixFileAttributeView.class).readAttributes().group().getName() + " ");

                        // ファイルサイズ
                        System.out.print(String.format("%5s", abFiles[i].length()) + " ");

                        // タイムスタンプ
                        System.out.print(simpleDateFormat.format(abFiles[i].lastModified()) + " ");

                        // ファイル名
                        System.out.print(abFiles[i].getName() + " ");

                        System.out.println();
                    }
                } else { // パスあり、オプションなし
                    System.out.println("s");
                    for (int i = 0; i < abFiles.length; i++) {
                        System.out.print(abFiles[i].getName() + " ");
                    }
                    System.out.println();
                }
            }
            } else {
                // パスなし、オプションあり
                if (p.contains("-") && p.contains("l")) {
                    System.out.println("aa");
                    // オプションがあるとき
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
                }
                if (p.contains("-") && p.contains("a")) {

                }

            }
        }

    }
}