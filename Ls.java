public class Ls {
  public static void main(String[] args) {
  try {
      Runtime runtime = Runtime.getRuntime();
      runtime.exec("ls");
  } catch (IOException ex) {
  }
}
}
