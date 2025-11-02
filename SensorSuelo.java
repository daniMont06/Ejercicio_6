// usamos medible y registrble de interfaz
public class SensorSuelo extends Maquina implements Medible, Registrable {
    private String tipoMedicion;     // puede ser terreno, temperatura o humedad
    private double ultimaMedicion;   // último valor medido
    private String ultimoRegistro;   

    public SensorSuelo(String id, String nombre, String fabricante, double consumoElectrico, String tipoMedicion) {
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
        if ("terreno".equalsIgnoreCase(tipoMedicion)) {
            ultimaMedicion = 35.0;   // porcentaje de humedad de suelo
        } else if ("temperatura".equalsIgnoreCase(tipoMedicion)) {
            ultimaMedicion = 22.5;   //  esta en °C
        } else if ("humedad".equalsIgnoreCase(tipoMedicion)) {
            ultimaMedicion = 60.0;   
        } else {
            ultimaMedicion = 0.0;    // si el tipo no es valido
        }
        return ultimaMedicion;
    }

    public double getUltimaMedicion() { 
        return ultimaMedicion; 
    }

    @Override
    public void registrarDatos() {
        // Guardamos solo el último registro 
        ultimoRegistro = "Registro SensorSuelo -> tipo:" + tipoMedicion + ", valor:" + ultimaMedicion;
    }

    // Permite al main leer el último registro
    public String getUltimoRegistro() { return ultimoRegistro; }

    @Override
    public String toString() {
        return super.toString() + " | Medible(tipo=" + tipoMedicion + ")";
    }
}
