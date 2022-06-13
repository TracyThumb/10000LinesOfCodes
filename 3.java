import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        File f = new File("C:\\Windows");
        // 列出所有文件和子目录
        File[] fs1 = f.listFiles();
        printFiles(fs1);
        // 仅列出.exe文件
        File[] fs2 = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                // 返回true表示接受该文件
                return name.endsWith(".exe");
            }
        });
        printFiles(fs2);
    }

    static void printFiles(File[] files) {
        System.out.println("==========");
        if (files != null) {
            for (File f : files) {
                System.out.println(f);
            }
        }
        System.out.println("==========");
    }
}