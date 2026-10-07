public class Vrchol {
    private int nazov;
    private int k;

    public Vrchol(int nazov) {
        this.nazov = nazov;
        this.k = nazov;
    }

    public void zmenZnacku(int k) {
        this.k = k;
    }

    public String vypisSa() {
        return Integer.toString(this.nazov);
    }

    public int getK() {
        return k;
    }
}
