import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class LabelSet {
    private int r; //riadiaci vrchol
    private ArrayList<Integer> E; //mnozina riadiacich vrcholov Epsilon
    private HashMap<Integer, Integer> t; //Hash map s dlzkami doteraz najdenych najlepsich ciest, klucom bude vrchol,
                                         // hodnotou dlzka doteraz najdenej najlepsej ciesty do daneho vrchola
    private HashMap<Integer, Integer> x; //Hash map s predchadzajucimi vrcholmi, klucom bude vrchol,
                                         // hodnotou predchadzajuci vrchol do daneho vrchola
    private Graf g; //Graf, ktory sme nacitali zo suboru


    public LabelSet(String subor, int zaciatocny, int koncovy) throws FileNotFoundException {
        /**
         *Krok 1, inicializacia
         */
        this.g = new Graf(0, 0);
        this.g = Graf.nacitajSubor(subor);

        this.r = zaciatocny;

        this.E = new ArrayList<>();
        this.E.add(this.r);

        this.t = new HashMap<>();
        for (int i = 1; i <= this.g.n; i++) {         //prechadzame vsetkymi vrcholmi
            if (i == zaciatocny) {                            //ak vrchol = 1, potom t(1) = 0
                this.t.put(i, 0);
            } else {                                 //inak nekonecno
                this.t.put(i, Integer.MAX_VALUE);
            }
        }

        this.x = new HashMap<>();
        for (int i = 1; i <= this.g.n; i++) {         //prechadzame vsetkymi vrcholmi
            this.x.put(i, 0);                        //predchadzajuci vrchol ku kazdemu vrcholu = 0
        }

        /**
         * Krok 3
         */
        while (this.r != koncovy && this.r != Integer.MAX_VALUE && !this.E.isEmpty()) {
            this.Krok2();

            this.E.remove((Integer) this.r);                                                  //odoberame vrchol r z mnoziny epsilon
            int najmensiaVzdialenst = Integer.MAX_VALUE;                                      //algoritmus na hladanie vrchola s najmenou znackou t, ktory zabezpecuje,
            int najmensiVrchol = Integer.MAX_VALUE;
            for (Integer cislo : this.E) {                                         //ze nas algoritmus bude label set, nie label correct
                if (this.t.get(cislo) < najmensiaVzdialenst) {
                    najmensiaVzdialenst = this.t.get(cislo);
                    najmensiVrchol = cislo;
                }
            }
            this.r = najmensiVrchol;                                                     //vyberame novy riadiaci vrchol z mnoziny epsilon
        }
        //output
        if (this.t.get(koncovy) != Integer.MAX_VALUE) {
            System.out.println("dlzka cesty od vrchola " + zaciatocny + " do vrchola " + koncovy + " je " + this.t.get(koncovy));
            System.out.print("cesta: " + koncovy);
            if (koncovy != zaciatocny) {
                System.out.print(" <- ");
            }
            int vrcholTeraz = koncovy;
            while (vrcholTeraz != zaciatocny) {
                System.out.print(this.x.get(vrcholTeraz));

                if (this.x.get(vrcholTeraz) != zaciatocny) {
                    System.out.print(" <- ");
                }

                vrcholTeraz = this.x.get(vrcholTeraz);
            }
            System.out.println();
        } else {
            System.out.println("Taka cesta neexistuje");
        }
    }

    /**
     * Krok 2
     */
    public void Krok2 () {
        for (int i = 1; i <= this.g.m; i++) {
            if (this.g.H[i][0] == this.r) {                                                //vsetky hrany, zacinajuci vrcholom r
                if (this.t.get(this.r)!= Integer.MAX_VALUE && this.t.get(this.g.H[i][1]) > this.t.get(this.r) + this.g.H[i][2]) {    //ak t(j) > t(r) + c(r, j)
                    int novaDlzka = this.t.get(this.r) + this.g.H[i][2];                   //potom t(j) = t(r) + c(r, j)
                    this.t.put(this.g.H[i][1], novaDlzka);
                    this.x.put(this.g.H[i][1], this.r);                                    //a x(j) = r
                    if (!this.E.contains(this.g.H[i][1])) {
                        this.E.add(this.g.H[i][1]);                                            //pridavame vrchol j v mnozinu epsilon
                    }
                }
            }
        }
    }
}
