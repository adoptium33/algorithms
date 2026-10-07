import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CPM {
    private Graf g;
    private ArrayList<Vrchol> P;
    private Vrchol[] mnozinaVrcholov;
    private ArrayList<Hrana> mnozinaHran;
    private int dobaTrvania;

    public CPM(String subor, String subor1) throws IOException {
        this.P = new ArrayList<>();
        this.mnozinaHran = new ArrayList<>();
        this.dobaTrvania = 0;


        this.g = new Graf(0,0);
        try {
            this.g = this.g.nacitajSubor(subor);
        } catch (FileNotFoundException e) {
            System.out.println("Zadali ste nespravny subor");
        }
        System.out.println(this.g.n);

        this.mnozinaVrcholov = new Vrchol[this.g.n + 1];
        for (int i = 1; i <= this.g.n; i++) {
            this.mnozinaVrcholov[i] = new Vrchol(i);
        }


        BufferedReader s = new BufferedReader(new FileReader(subor1));
        for (int i = 1; i <= this.g.n; i++) {
            String u = s.readLine();
            if (u != null) {
                this.mnozinaVrcholov[i].setP(Integer.parseInt(u));
            }
        }
        s.close();

        for (int i = 1; i <= this.g.m; i++) {
            this.mnozinaHran.add(new Hrana(this.mnozinaVrcholov[this.g.H[i][0]], this.mnozinaVrcholov[this.g.H[i][1]]));
        }


        //MONOTONNE OCISLOVANIE
        //d(v)=ideg(v)
        for (Hrana h : this.mnozinaHran) {
            this.mnozinaVrcholov[h.getKoncovy().getNazov()].zvacsiZnackuD();
        }

        //prvky z mnoziny V0 zaradme do lubovolnej postupnosti P
        for (int i = 1; i <= this.g.n; i++) {
            if (this.mnozinaVrcholov[i].getD() == 0) {
                this.P.add(this.mnozinaVrcholov[i]);
            }
        }
        //Vyberame vi z postupnosti P
        int i = 0;
        while (i < this.P.size()) {
            Vrchol vi = this.P.get(i);
            //prejdeme vystupnou hviezdou vrchola vi
            for (Hrana h : this.mnozinaHran) {
                if (h.getZaciatocny() == vi) {
                    if (h.getKoncovy().getD() > 0) {
                        //d(w) = d(w) - 1
                        h.getKoncovy().zmensiZnackuD();
                        //ak d(w) = 0 pridame w do P
                        if (h.getKoncovy().getD() == 0) {
                            P.add(h.getKoncovy());
                        }
                    }
                }
            }
            i++;

            if (this.P.size() == this.g.n) {
                break;
            }
        }


        //NAJSKOR MOZNE ZACIATKY
        for (Vrchol r : this.P) {
            if (r == this.P.getLast()) {
                break;
            }
            //prejdeme vystupnou hviezdou vrchola r
            for (Hrana h : this.mnozinaHran) {
                if (h.getZaciatocny() == r) {
                    //ak z(w) < z(r) + p(r)
                    if (h.getKoncovy().getZ() < r.getZ() + r.getP()) {
                        //potom z(w) = z(r) + p(r)
                        h.getKoncovy().setZ(r.getZ() + r.getP());
                        //a x(w) = r
                        h.getKoncovy().setX(r);
                    }
                }
            }
        }

        //DOBA TRVANIA PROJEKTU
        int max = 0;
        //prejdeme vsetkymi vrcholmi
        for (Vrchol w : this.P) {
            //odeg ktorych = 0
            boolean odegJeNula = true;
            for (Hrana h : this.mnozinaHran) {
                if (h.getZaciatocny() == w) {
                    odegJeNula = false;
                    break;
                }
            }
            //ak odeg = 0
            if (odegJeNula) {
                //pozreme  ci z(w) + p(w) je maximalne
                if (max < w.getZ() + w.getP()) {
                    //ak ano nastavime ho ako maximalne
                    max = w.getZ() + w.getP();
                }
            }
        }
        //a toto maximalne je nasa doba trvania
        this.dobaTrvania = max;

        //NAJNESKOR NUTNE KONCY
        for (Vrchol v : this.P) {
            v.setK(this.dobaTrvania);
        }
        //prejdeme od konca vsetkymi vrcholmi vystupnej hviezdy
        for (int j = this.P.size() - 2; j >= 0; j--) {
            for (Hrana h : this.mnozinaHran) {
                if (h.getZaciatocny() == this.P.get(j)) {
                    //ak k(r) > k(w) - p(w)
                    if (this.P.get(j).getK() > h.getKoncovy().getK() - h.getKoncovy().getP()) {
                        //potom k(r) = k(w) - p(w)
                        this.P.get(j).setK(h.getKoncovy().getK() - h.getKoncovy().getP());
                        //a y(r) = w
                        this.P.get(j).setY(h.getKoncovy());
                    }
                }
            }
        }

        //VYPIS ROZVRHU V TVARE TABULKY
        System.out.println("CINNOST  ZACIATOK  TRVANIE  KONIEC");
        for (Vrchol v : this.P) {
            System.out.printf("%-9s%-10s%-9s%-6s", v.getNazov(), v.getZ(), v.getP(), v.getK());
            System.out.println();
        }
        System.out.println("Doda trvania projektu: " + this.dobaTrvania);

        //VYPIS KRITICKEJ CESTY
        System.out.println("KRITICKA CESTA");
        Vrchol vrchol = this.P.get(0);
        while (true) {
            System.out.print(vrchol.getNazov());

            vrchol = vrchol.getY();

            if (vrchol == null) {
                break;
            } else {
                System.out.print("->");
            }
        }
    }
}
