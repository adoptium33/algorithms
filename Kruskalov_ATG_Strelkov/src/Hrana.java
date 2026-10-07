public class Hrana {
    private Vrchol zaciatocny;
    private Vrchol koncovy;
    private int cena;

    public Hrana(Vrchol zaciatocny, Vrchol koncovy, int cena) {
        this.zaciatocny = zaciatocny;
        this.koncovy = koncovy;
        this.cena = cena;
    }

    public void vypisSa() {
        System.out.println("(" + this.zaciatocny.vypisSa() + ", " + this.koncovy.vypisSa() + ") cena: " + this.cena);
    }


    public Vrchol getZaciatocny() {
        return zaciatocny;
    }

    public Vrchol getKoncovy() {
        return koncovy;
    }

    public int getCena() {
        return cena;
    }
}
