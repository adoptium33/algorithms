public class Hrana {
    private Vrchol zaciatocny;
    private Vrchol koncovy;
    private int cena;
    private int y;

    public Hrana(Vrchol zaciatocny, Vrchol koncovy, int cena) {
        this.zaciatocny = zaciatocny;
        this.koncovy = koncovy;
        this.cena = cena;
        this.y = 0;
    }

    public void vypisSa() {
        System.out.println("(" + this.zaciatocny.vypisSa() + ", " + this.koncovy.vypisSa() + ") tok: " + this.y);
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

    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }
}
