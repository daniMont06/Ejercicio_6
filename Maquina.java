// la superclase para todas las maquinas.
// voy a usar Comparable<Maquina> para ordenar por consumo eléctrico.
public abstract class Maquina implements Comparable<Maquina> {
    
    private String id;
    private String nombre;
    private String fabricante;
    private double consumoElectrico; // en Watts 

    // mi lindo constructor
    public Maquina(String id, String nombre, String fabricante, double consumoElectrico) {
        this.id = id;
        this.nombre = nombre;
        this.fabricante = fabricante;
        this.consumoElectrico = consumoElectrico;
    }

    // Getters 
    public String getId() { 
        return id; 
    }
    public String getNombre() { 
        return nombre; 
    }
    public String getFabricante() { 
        return fabricante; 
    }
    public double getConsumoElectrico() { 
        return consumoElectrico; 
    }

    // Setters 
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public void setFabricante(String fabricante) { 
        this.fabricante = fabricante; 
    }
    public void setConsumoElectrico(double consumoElectrico) { 
        this.consumoElectrico = consumoElectrico; 
    }

    // Permite ordenar máquinas por consumo eléctrico del menor consumo al mayor consumo.
    @Override
    public int compareTo(Maquina otra) { //Ya usé comparable
        return Double.compare(this.consumoElectrico, otra.consumoElectrico);
    }

    
    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " | Fab: " + fabricante + " | Consumo: " + consumoElectrico + " W";
    }
}

