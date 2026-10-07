import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FordovFulkersonov {
    private Graf g;
    private Vrchol[] mnozinaVrcholov;
    private ArrayList<Hrana> mnozinaHran;
    private ArrayList<Vrchol> E;
    private ArrayList<Vrchol> N;
    private ArrayList<Vrchol> zlepsujucaPolocesta;
    private int z;
    private int u;
    private boolean existuje;
    private int r;

    public FordovFulkersonov(String cesta) {
        this.g = new Graf(0,0);
        try {
            this.g = this.g.nacitajSubor(cesta);
        } catch (FileNotFoundException e) {
            System.out.println("Zadali ste nespravny subor");
        }

        this.z = Integer.MAX_VALUE;
        this.u = Integer.MAX_VALUE;
        this.existuje = false;
        this.r = Integer.MAX_VALUE;

        this.zlepsujucaPolocesta = new ArrayList<>();
        this.mnozinaHran = new ArrayList<>();
        this.mnozinaVrcholov = new Vrchol[this.g.n + 1];
        for (int i = 1; i <= this.g.n; i++) {
            this.mnozinaVrcholov[i] = new Vrchol(i);
        }
        for (int i = 1; i <= this.g.m; i++) {
            this.mnozinaHran.add(new Hrana(this.mnozinaVrcholov[this.g.H[i][0]], this.mnozinaVrcholov[this.g.H[i][1]], this.g.H[i][2]));
        }

        int f = 1;
        while (this.z == Integer.MAX_VALUE) {
            this.z = f;
            for (Hrana h : this.mnozinaHran) {
                if (h.getKoncovy() == this.mnozinaVrcholov[this.z]) {
                    this.z = Integer.MAX_VALUE;
                    break;
                }
            }
            f++;
        }
        f = 1;
        while (this.u == Integer.MAX_VALUE) {
            this.u = f;
            for (Hrana h : this.mnozinaHran) {
                if (h.getZaciatocny() == this.mnozinaVrcholov[this.u]) {
                    this.u = Integer.MAX_VALUE;
                    break;
                }
            }
            f++;
        }

        //KROK4
        while (true) {
            this.ZvacsujucaPolocesta();
            if (this.existuje && this.r > 0 && this.r < Integer.MAX_VALUE) {
                for (int i = 0; i < this.zlepsujucaPolocesta.size() - 1; i++) {
                    Vrchol w = this.zlepsujucaPolocesta.get(i);
                    Vrchol w1 = this.zlepsujucaPolocesta.get(i + 1);
                    for (Hrana h : this.mnozinaHran) {
                        if (h.getZaciatocny() == w && h.getKoncovy() == w1) {
                            //ak lezi v smere y(h) += r
                            h.setY(h.getY() + this.r);
                        } else if (h.getZaciatocny() == w1 && h.getKoncovy() == w) {
                            //ak lezi v protismere y(h) -= r
                            h.setY(h.getY() - this.r);
                        }
                    }
                }
                this.r = Integer.MAX_VALUE;
            } else {
                //KROK3
                int maxTok = 0;
                //vypocet maximalneho toku v sieti
                for (Hrana h : this.mnozinaHran) {
                    if (h.getZaciatocny() == this.mnozinaVrcholov[this.z]) {
                        maxTok += h.getY();
                    }
                }
                System.out.println("Maximalny tok v sieti je " + maxTok);
                for (Hrana h : this.mnozinaHran) {
                    h.vypisSa();
                }
                break;
            }
        }
    }

    //KROK2 NAJDENIE ZVACSUJUCEJ POLOCESTY
    public void ZvacsujucaPolocesta() {
        //KROK2.1
        this.E = new ArrayList<>(); //={z}
        this.N = new ArrayList<>(); //=V-{z}
        for (int i = 1; i < this.mnozinaVrcholov.length; i++) {
            if (this.mnozinaVrcholov[i] == this.mnozinaVrcholov[this.z]) {
                this.E.add(this.mnozinaVrcholov[i]);
                this.mnozinaVrcholov[this.z].setX(0);
            } else {
                this.N.add(this.mnozinaVrcholov[i]);
                this.mnozinaVrcholov[i].setX(Integer.MAX_VALUE);
            }
        }
        this.zlepsujucaPolocesta.clear();
        this.r = Integer.MAX_VALUE;

        //KROK2.3
        if (this.E.isEmpty()) {
            System.out.println("Neexistuje zlepsujuca polocesta");
            this.existuje = false;
            return;
        }

        while (!this.E.isEmpty()) {
            //KROK2.4
            // Poloz E = E − {i}
            Vrchol teraz = this.E.remove(0);

            if (teraz == this.mnozinaVrcholov[this.u]) {
                this.existuje = true;
                break;
            }

            for (Hrana h : this.mnozinaHran) {
                if (h.getZaciatocny() == teraz) {
                    if (this.N.contains(h.getKoncovy())) {
                        //Ak y(i, j) < c(i, j)
                        if (h.getY() < h.getCena()) {
                            //x(j) = i, E = E + {j} a N = N − {j}
                            h.getKoncovy().setX(h.getZaciatocny().getNazov());
                            this.E.add(h.getKoncovy());
                            this.N.remove(h.getKoncovy());
                        }
                    }
                }
                if (h.getKoncovy() == teraz) {
                    if (this.N.contains(h.getZaciatocny())) {
                        //Ak y(j, i) > 0
                        if (h.getY() > 0) {
                            //x(j) = −i, E = E + {j} a N = N − {j}
                            h.getZaciatocny().setX(-h.getKoncovy().getNazov());
                            this.E.add(h.getZaciatocny());
                            this.N.remove(h.getZaciatocny());
                        }
                    }
                }
            }
        }

        //KROK2.2
        if (this.mnozinaVrcholov[this.u].getX() < Integer.MAX_VALUE && this.existuje) {
            this.zlepsujucaPolocesta.add(this.mnozinaVrcholov[this.u]);
            int terazX = this.mnozinaVrcholov[this.u].getX();
            while (this.mnozinaVrcholov[this.z] != this.mnozinaVrcholov[Math.abs(terazX)]) {
                this.zlepsujucaPolocesta.add(0, this.mnozinaVrcholov[Math.abs(terazX)]);
                terazX = this.mnozinaVrcholov[Math.abs(terazX)].getX();
            }
            this.zlepsujucaPolocesta.add(0, this.mnozinaVrcholov[this.z]);

            for (int i = 0; i < this.zlepsujucaPolocesta.size() - 1; i++) {
                Vrchol v = this.zlepsujucaPolocesta.get(i);
                Vrchol v1 = this.zlepsujucaPolocesta.get(i + 1);

                for (Hrana h : this.mnozinaHran) {
                    if (h.getZaciatocny() == v && h.getKoncovy() == v1) {
                        if (this.r > h.getCena() - h.getY()) {
                            this.r = h.getCena() - h.getY();
                        }
                    } else if (h.getZaciatocny() == v1 && h.getKoncovy() == v) {
                        if (this.r > h.getY()) {
                            this.r = h.getY();
                        }
                    }
                }
            }
            return;
        }
        this.existuje = false;
    }
}