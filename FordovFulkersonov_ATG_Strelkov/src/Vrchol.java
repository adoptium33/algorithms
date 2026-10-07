public class Vrchol {
    private int nazov;
    private int x;

    public Vrchol(int nazov) {
        this.nazov = nazov;
        this.x = 0;
    }

    public String vypisSa() {
        return Integer.toString(this.nazov);
    }

    public int getX() {
        return x;
    }

    public int getNazov() {
        return nazov;
    }


    public void setX(int x) {
        this.x = x;
    }
}
