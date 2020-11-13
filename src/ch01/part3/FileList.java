package ch01.part3;

import java.io.File;
import java.util.Objects;

/**
 * @program: AlgorithmBook
 * @author: hhmy27
 * @created: 2020/06/12 17:28
 * @description: ex 1.3.43 answer
 */
public class FileList {
    public static void visit(File file, int ind) {
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < ind; i++) {
            space.append(" ");
        }
        System.out.println(space.toString() + file.getName());
        if (file.isDirectory()) {
            for (File tf : Objects.requireNonNull(file.listFiles())) {
                visit(tf, ind + 1);
            }
        }

    }


    public static void main(String[] args) {
        File file = new File("G:\\JAVAcode\\AlgorithmBook\\src");
        visit(file, 0);
    }
}
