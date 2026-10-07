import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Kruskalov {
    private ArrayList<Hrana> P;
    private Graf graf;
    private Vrchol[] mnozinaVrcholov;
    private ArrayList<Hrana> kostra;

    public Kruskalov(String cesta) {
        this.kostra = new ArrayList<>();

        /**
         * KROK 1-2
         */
        this.P = new ArrayList<>();

        //nacitame graf
        this.graf = new Graf(0,0);
        try {
            this.graf = this.graf.nacitajSubor(cesta);
        } catch (FileNotFoundException e) {
            System.out.println("Zadali ste nespravny subor");
        }

        this.mnozinaVrcholov = new Vrchol[this.graf.n + 1];
        for (int i = 1; i <= this.graf.n; i++) {
            this.mnozinaVrcholov[i] = new Vrchol(i);
        }

        //pridavame hrany do postupnosti P
        while (this.P.size() != this.graf.m) {
            int najdeneI = -1;
            int najdrahsiaCena = -1;

            //prejdeme vsetkymi hranami
            for (int i = 1; i <= this.graf.m; i++) {

                //ak cena hrany je drahsia ako doteraz najdena najdrahsia cena
                if (this.graf.H[i][2] >= najdrahsiaCena && this.graf.H[i][2] != -1) {

                    //potom doteraz najdeny index = index danej hrany
                    najdeneI = i;

                    //a doteraz najdena najdrahsia cena = cena danej hrany
                    najdrahsiaCena = this.graf.H[i][2];


                }
            }
            if (najdeneI != -1) {
                this.P.add(new Hrana(this.mnozinaVrcholov[this.graf.H[najdeneI][0]], this.mnozinaVrcholov[this.graf.H[najdeneI][1]], najdrahsiaCena));
                //a dame danej hrane cenu -1, aby nestretli sme ju 2. raz
                this.graf.H[najdeneI][2] = -1;
            } else {
                break;
            }
        }

        /**
         * KROK 3-4
         */
        Hrana prva = null;
        int kmin = 0;
        int kmax = 0;

        //kym postupnost P nie je prazdna, alebo pocet hran kostry nie je rovny poctu vrcholov grafu - 1
        while (!this.P.isEmpty() && this.kostra.size() != this.graf.n - 1) {

            //berieme prvu hranu z postupnosti P a vylucujeme ju z P
            prva = this.P.get(0);
            this.P.remove(0);

            //ak vrcholy danej hrany maju rozne znacky,
            if (prva.getZaciatocny().getK() != prva.getKoncovy().getK()) {

                //pridame hranu do kostry a vypocitame kmin a kmax pre vrcholy danej hrany
                this.kostra.add(prva);
                kmin = Math.min(prva.getZaciatocny().getK(), prva.getKoncovy().getK());
                kmax = Math.max(prva.getZaciatocny().getK(), prva.getKoncovy().getK());

                //prejdeme vsetkymi vrcholmi
                for (int i = 1; i <= this.graf.n; i++) {

                    //ak znacka nejakeho vrchola == kmax, zmenime mu znacku na kmin
                    if (this.mnozinaVrcholov[i].getK() == kmax) {
                        this.mnozinaVrcholov[i].zmenZnacku(kmin);
                    }
                }
            }

        }

        int celkovaCena = 0;
        for (Hrana h : this.kostra) {
            h.vypisSa();
            celkovaCena += h.getCena();
        }
        System.out.println(celkovaCena);
    }
}
