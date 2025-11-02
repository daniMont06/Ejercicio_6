// Estación Meteorológica tambien usa medible y registrable
public class EstacionMeteorologica extends Maquina implements Medible, Registrable {
    private String tipoMedicion;     
    private double ultimaMedicion;   
    private String ultimoRegistro;   

    public EstacionMeteorologica(String id, String nombre, String fabricante, double consumoElectrico, String tipoMedicion) {
        super(id, nombre, fabricante, consumoElectrico);
        this.tipoMedicion = tipoMedicion;
        this.ultimaMedicion = 0.0;
        this.ultimoRegistro = "";
    }

    public void setTipoMedicion(String tipo) { 
        this.tipoMedicion = tipo; 
    }
    @Override public String tipoMedicion() { 
        return tipoMedicion; 
    }

    @Override
    public double medir() {
        if ("temperatura".equalsIgnoreCase(tipoMedicion)) {
            ultimaMedicion = 24.0;   //  en grados °C
        } else if ("humedad".equalsIgnoreCase(tipoMedicion)) {
            ultimaMedicion = 55.0;   
        } else if ("terreno".equalsIgnoreCase(tipoMedicion)) {
            ultimaMedicion = 0.0;    // Estación típica no mide suelo 
        } else {
            ultimaMedicion = 0.0;    // tipo desconocido
        }
        return ultimaMedicion;
    }

    public double getUltimaMedicion() { 
        return ultimaMedicion; 
    }

    @Override
    public void registrarDatos() {
        ultimoRegistro = "Registro Estacion -> tipo:" + tipoMedicion + ", valor:" + ultimaMedicion;
    }

    public String getUltimoRegistro() { return ultimoRegistro; }

    @Override
    public String toString() {
        return super.toString() + " | Medible(tipo=" + tipoMedicion + ")";
    }
}

