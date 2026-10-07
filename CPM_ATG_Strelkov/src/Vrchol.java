public class Vrchol {
    private int nazov;

    private int d;
    private int p;

    private int z;
    private Vrchol x;

    private int k;
    private Vrchol y;

    public Vrchol(int nazov) {
        this.nazov = nazov;
        this.p = 0;
        this.d = 0;
        this.z = 0;
        this.x = null;
        this.k = 0;
        this.y = null;
    }

    public void zvacsiZnackuD() {
        this.d++;
    }

    public void zmensiZnackuD() {
        this.d--;
    }

    public String vypisSa() {
        return Integer.toString(this.nazov);
    }


    public int getD() {
        return d;
    }

    public int getZ() {
        return z;
    }

    public Vrchol getX() {
        return x;
    }

    public int getP() {
        return p;
    }

    public int getNazov() {
        return nazov;
    }

    public int getK() {
        return k;
    }

    public Vrchol getY() {
        return y;
    }



    public void setK(int k) {
        this.k = k;
    }

    public void setY(Vrchol y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setX(Vrchol x) {
        this.x = x;
    }

    public void setP(int p) {
        this.p = p;
    }
}
