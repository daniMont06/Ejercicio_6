
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaAgroTec sistema = new SistemaAgroTec();
        sistema.init();

        // Menú
        for (int seguir = 1; seguir == 1; ) {
            System.out.println("\n SISTEMA AGROTEC ");
            System.out.println("1) Listar máquinas");
            System.out.println("2) Buscar por ID");
            System.out.println("3) Buscar por nombre ");
            System.out.println("4) Ordenar por consumo");
            System.out.println("5) Operar dispositivo ");
            System.out.println("0) Salir");
            System.out.print("Opción: ");

            int op;
            try { op = Integer.parseInt(sc.nextLine()); } 
            catch (Exception e) { 
                op = -1; 
            }

            if (op == 1) {
                for (Maquina m : sistema.getMaquinas()) System.out.println(m);

            } else if (op == 2) {
                System.out.print("ID: ");
                String id = sc.nextLine();
                Maquina m = sistema.buscarPorId(id);
                System.out.println(m == null ? "No encontrado." : m.toString());

            } else if (op == 3) {
                // Filtrar por nombre recorriendo la MISMA lista del controlador (sin crear una nueva)
                System.out.print("Nombre contiene: ");
                String nom = sc.nextLine().toLowerCase();
                boolean alguno = false;
                for (Maquina x : sistema.getMaquinas()) {
                    if (x.getNombre().toLowerCase().contains(nom)) {
                        System.out.println(x);
                        alguno = true;
                    }
                }
                if (!alguno) System.out.println("Sin coincidencias.");

            } else if (op == 4) {
                sistema.ordenarPorConsumo();
                System.out.println("Ordenado por consumo (W):");
                for (Maquina x : sistema.getMaquinas()) System.out.println(x);

            } else if (op == 5) {
                System.out.print("ID de máquina: ");
                String idOp = sc.nextLine();
                Maquina obj = sistema.buscarPorId(idOp);

                if (obj == null) {
                    System.out.println("No encontrado.");
                } else {
                    System.out.println("Seleccionado: " + obj);

                    if (obj instanceof Medible) {
                        Medible med = (Medible) obj;
                        System.out.println("Tipo de medición actual: " + med.tipoMedicion());
                        System.out.print("¿Cambiar tipo? (enter / terreno / temperatura / humedad): ");
                        String nuevo = sc.nextLine();
                        if (!nuevo.trim().isEmpty()) {
                            if (obj instanceof SensorSuelo) {
                                ((SensorSuelo) obj).setTipoMedicion(nuevo);
                            } else if (obj instanceof EstacionMeteorologica) {
                                ((EstacionMeteorologica) obj).setTipoMedicion(nuevo);
                            }
                        }
                        double valor = med.medir();
                        System.out.println("Medición: " + valor);

                        if (obj instanceof Registrable) {
                            ((Registrable) obj).registrarDatos();
                            // Mostrar el ÚLTIMO registro (no lista)
                            if (obj instanceof SensorSuelo) {
                                System.out.println(((SensorSuelo) obj).getUltimoRegistro());
                            } else if (obj instanceof EstacionMeteorologica) {
                                System.out.println(((EstacionMeteorologica) obj).getUltimoRegistro());
                            }
                        }

                    } else if (obj instanceof Accion) {
                        Accion acc = (Accion) obj;
                        System.out.println("Acción: " + acc.describirAccion());
                        acc.ejecutarAccion();

                        if (obj instanceof Registrable) {
                            ((Registrable) obj).registrarDatos();
                            System.out.println(((DronDeRiego) obj).getUltimoRegistro());
                        }

                    } else {
                        System.out.println("Este dispositivo no es Medible ni Accionable.");
                    }
                }

            } else if (op == 0) {
                seguir = 0; // Salir (sin while / sin break)

            } else {
                System.out.println("Opción inválida.");
            }
        }

        sc.close();
        System.out.println("Adiós!");
    }
}
