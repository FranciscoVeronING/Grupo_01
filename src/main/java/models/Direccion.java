package models;

public class Direccion implements Cloneable{
    private  String nombre;
    private  String altura;
    private  String piso;
    private  String letra;

    /**
     * Clase utilizada para establecer la direccion destino del viaje
     * <b>Pre: </> El parametro nombre no puede ser null ni estar vacio
     * @param nombre Almacena el nombre de la direccion
     * <b>Pre:</>El parametro altura no puede ser null ni estar vacio
     * @param altura Almacena la altura de la calle de la direccion
     * <b>Pre: </>El parametro piso no puede ser null ni estar vacio
     * @param piso Almacena el piso del edificio
     * <b>Pre: </>El parametro letra no puede ser null ni estar vacio
     * @param letra Almacena la letra del piso de la direccion
     */
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

    @Override
    public Direccion clone() {
        try {
            Direccion clone = (Direccion) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
