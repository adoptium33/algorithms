public class Hrana {
    private Vrchol zaciatocny;
    private Vrchol koncovy;

    public Hrana(Vrchol zaciatocny, Vrchol koncovy) {
        this.zaciatocny = zaciatocny;
        this.koncovy = koncovy;
    }

    public void vypisSa() {
        System.out.println("(" + this.zaciatocny.vypisSa() + ", " + this.koncovy.vypisSa() + ")");
    }


    public Vrchol getZaciatocny() {
        return zaciatocny;
    }

    public Vrchol getKoncovy() {
        return koncovy;
    }
}
