package dominio;

public class RequisitosFuncionais {
    private int numquarto;
    private int tipoquarto;
    private double precodiario;
    private boolean disponivel;

    public RequisitosFuncionais(int numquarto, int tipoquarto, double precodiario) {
        this.numquarto = numquarto;
        this.tipoquarto = tipoquarto;
        this.precodiario = precodiario;
        this.disponivel = true;
    }

    public int getNumquarto() {
        return numquarto;
    }

    public void setNumquarto(int numquarto) {
        this.numquarto = numquarto;
    }

    public int getTipoquarto() {
        return tipoquarto;
    }

    public void setTipoquarto(int tipoquarto) {
        this.tipoquarto = tipoquarto;
    }

    public double getPrecodiario() {
        return precodiario;
    }

    public void setPrecodiario(double precodiario) {
        this.precodiario = precodiario;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "RequisitosFuncionais{" +
                "numquarto=" + numquarto +
                ", tipoquarto=" + tipoquarto +
                ", precodiario=" + precodiario +
                ", disponivel=" + disponivel +
                '}';
    }
}