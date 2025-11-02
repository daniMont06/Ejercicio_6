// esta usa accion y registable de interfaz
public class DronDeRiego extends Maquina implements Accion, Registrable {
    private boolean enOperacion;     
    private String ultimoRegistro;   

    public DronDeRiego(String id, String nombre, String fabricante, double consumoElectrico) {
        super(id, nombre, fabricante, consumoElectrico);
        this.enOperacion = false;  // inicia detenido
        this.ultimoRegistro = "";
    }

    public boolean isEnOperacion() { 
        return enOperacion; 
    }

    @Override
    public void ejecutarAccion() {
        // Alterna estado como acción simple
        enOperacion = !enOperacion;
    }

    @Override
    public String describirAccion() {
        return "Aplicar riego con rociadores";
    }

    @Override
    public void registrarDatos() {
        String estado = enOperacion ? "EN_OPERACION" : "DETENIDO";
        ultimoRegistro = "Registro Dron -> accion:'" + describirAccion() + "', estado:" + estado;
    }

    public String getUltimoRegistro() { 
        return ultimoRegistro; 
    }

    @Override
    public String toString() {
        return super.toString() + " | Accion(\"" + describirAccion() + "\"), enOperacion=" + enOperacion;
    }
}

