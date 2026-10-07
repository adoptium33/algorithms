import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner konzola = new Scanner(System.in);

        System.out.println("Zadajte nazov suboru(prosim zadavajte uplnu cestu do suboru): ");
        String subor = konzola.nextLine();

        System.out.println("Zadajte nazov suboru s dlzkami(prosim zadavajte uplnu cestu do suboru): ");
        String subor1 = konzola.nextLine();

        CPM algoritmus = new CPM(subor, subor1);

        konzola.close();
    }
}