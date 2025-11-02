
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Controlador
public class SistemaAgroTec {
    private final List<Maquina> maquinas = new ArrayList<>(); // La única lista polimorfica del programa

    public void init() { //El metodo obligatorio de las instrucciones
        maquinas.add(new SensorSuelo("SS-01", "Sensor Suelo A", "AgroCorp", 5.0, "terreno"));
        maquinas.add(new SensorSuelo("SS-02", "Sensor Suelo B", "AgroCorp", 4.5, "temperatura"));
        maquinas.add(new SensorSuelo("SS-03", "Sensor Suelo C", "FarmTech", 3.8, "humedad"));

        maquinas.add(new EstacionMeteorologica("EM-01", "Estación Norte", "ClimaSA", 12.0, "temperatura"));
        maquinas.add(new EstacionMeteorologica("EM-02", "Estación Sur", "ClimaSA", 11.0, "humedad"));
        maquinas.add(new EstacionMeteorologica("EM-03", "Estación Este", "MeteoMax", 10.5, "temperatura"));

        maquinas.add(new DronDeRiego("DR-01", "Dron Uno", "SkyAgro", 25.0));
        maquinas.add(new DronDeRiego("DR-02", "Dron Dos", "SkyAgro", 23.5));
        maquinas.add(new DronDeRiego("DR-03", "Dron Tres", "AeroField", 22.0));

        maquinas.add(new SensorSuelo("SS-04", "Sensor Suelo D", "AgroCorp", 4.2, "terreno"));
    }

    public List<Maquina> getMaquinas() { 
        return maquinas; 
    }

    public Maquina buscarPorId(String id) {
        for (Maquina m : maquinas) {
            if (m.getId().equalsIgnoreCase(id)) {
                return m;
            }
        }
        return null;
    }

    //el filtrado por nombre lo hará el main recorriendo esta misma lista.
    public void ordenarPorConsumo() {
        Collections.sort(maquinas);
    }
}
