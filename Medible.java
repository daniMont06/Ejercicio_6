// Interfaz para dispositivos que pueden MEDIR algo.
// Mide según el tipo actual (terreno, temperatura o humedad).
public interface Medible {
    // Ejecuta la medición según el tipo activo y devuelve un número (double).
    double medir();

    // Informa cuál es el tipo de medición, si es terreno, temperatura o humedad.
    String tipoMedicion();
}

