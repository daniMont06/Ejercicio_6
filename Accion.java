// Interfaz para dispositivos que pueden ejecutar una accion, regar por ejemplo
public interface Accion {
    // Realiza la acción propia del dispositivo.
    void ejecutarAccion();

    // Describe la acción 
    String describirAccion();
}
