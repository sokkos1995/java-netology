import java.io.File;
import java.io.IOException;

public class Main { 

    public static void main(String[] args) throws IOException{
        File dir = new File("example");

        if (dir.mkdir()){
            System.out.println("Directory created");
        } else {
            System.out.println("Directory already created");
        }
        // dir.mkdir(); - по аналогии с линуксом
        // возвращает true/false в зависимости от успешности создания
    }

}