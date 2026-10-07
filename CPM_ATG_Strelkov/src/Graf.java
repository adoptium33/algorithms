import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graf
{
    int n; // pocet vrcholov grafu
    int m; // pocet hran grafu
    int[][] H; // pole udajov o hranach

    public Graf(int paPocetVrcholov, int paPocetHran)
    {
        n = paPocetVrcholov;
        m = paPocetHran;
        H = new int[1 + m][3];
    }

    /*
    Nacitanie grafu zo suboru:
    Na kazdom riadku su tri cisla, prve a druhe cislo su cisla vrcholov
    a tretie cislo je ohodnotenie hrany.
    Pocet vrcholov aj pocet hran sa urci automaticky. Pocet hran je rovny
    poctu riadkov v subore a pocet vrcholov je rovny najvacsiemu cislu
    vrcholu v udajoch o hranach.
    */
    public static Graf nacitajSubor(String nazovSuboru)
            throws FileNotFoundException
    {
        // otvorim subor a pripravim Scanner pre nacitavanie
        Scanner s = new Scanner(new FileInputStream(nazovSuboru));

        // najskor len zistim pocet vrcholov a pocet hran
        int pocetVrcholov = 1;
        int pocetHran = 0;
        // prejdem cely subor
        while (s.hasNext())
        {
            // nacitam udaje o hrane
            int u = s.nextInt();
            int v = s.nextInt();
            int c = s.nextInt();

            // nacital som hranu, zvysim ich pocet o 1
            pocetHran++;

            // skontrolujem, ci netreba zvysit pocet vrcholov
            if (pocetVrcholov < u) pocetVrcholov = u;
            if (pocetVrcholov < v) pocetVrcholov = v;
        }
        // ukoncim nacitavanie zo suboru
        s.close();

        // vytvorim objekt grafu s potrebnym poctom vrcholov aj hran
        Graf g = new Graf(pocetVrcholov,pocetHran);

        // po druhy krat otvorim ten isty subor,
        // uz pozanm pocet vrcholov aj hran a mam alokovanu pamat
        s = new Scanner(new FileInputStream(nazovSuboru));

        // postune nacitam vsetky hrany
        // tentokrat si ich uz budem aj ukladat do pamate
        for (int j = 1; j <= pocetHran; j++)
        {
            int u = s.nextInt();
            int v = s.nextInt();
            int c = s.nextInt();

            g.H[j][0] = u;
            g.H[j][1] = v;
            g.H[j][2] = c;
        }


        return g;

    }

    public void printInfo()
    {
        System.out.println("Pocet vrcholov: " + n);
        System.out.println("Pocet hran: " + m);

    }
}
