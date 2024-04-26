package models;

public class Direccion {
    private  String nombre;
    private  String altura;
    private  String piso;
    private  String letra;

    public Direccion(String nombre, String altura, String piso, String letra) {
        this.nombre = nombre;
        this.altura = altura;
        this.piso = piso;
        this.letra = letra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Direccion{");
        sb.append("nombre='").append(nombre).append('\'');
        sb.append(", altura='").append(altura).append('\'');
        sb.append(", piso='").append(piso).append('\'');
        sb.append(", letra='").append(letra).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
