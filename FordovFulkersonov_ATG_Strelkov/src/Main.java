import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner konzola = new Scanner(System.in);

        System.out.println("Zadajte nazov suboru(prosim zadavajte uplnu cestu do suboru): ");
        String subor = konzola.nextLine();

        FordovFulkersonov algoritmus = new FordovFulkersonov(subor);

        konzola.close();
    }
}
