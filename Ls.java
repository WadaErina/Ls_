public static void main(String[] args) {
    try {
        Runtime runtime = Runtime.getRuntime();
        Process p = runtime.exec("ls");
        InputStream is = p.getInputStream();
        // InputStreamさえ得られれば、後は必要に応じて内容を取得すればよい。
        // 以下、省略
    } catch (IOException ex) {
    }
}
