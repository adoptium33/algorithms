import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner konzola = new Scanner(System.in);

        System.out.println("Zadajte nazov suboru(prosim zadavajte uplnu cestu do suboru): ");
        String subor = konzola.nextLine();

        System.out.println("Zadajte zaciatocny vrchol cesty: ");
        int zaciatocny = konzola.nextInt();

        System.out.println("Zadajte koncovy vrchol cesty: ");
        int koncovy = konzola.nextInt();

        LabelSet algoritmus = new LabelSet(subor, zaciatocny, koncovy);

        konzola.close();
    }
}
