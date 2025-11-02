import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaAgroTec sistema = new SistemaAgroTec();
        sistema.init(); // cargamos las máquinas

        
        for (int seguir = 1; seguir == 1; ) {
            // Mostrar menú
            System.out.println("\n=== SISTEMA AGROTEC ===");
            System.out.println("1) Listar máquinas");
            System.out.println("2) Buscar por ID");
            System.out.println("3) Buscar por nombre");
            System.out.println("4) Ordenar por consumo");
            System.out.println("5) Operar dispositivo (medir/accionar/registrar)");
            System.out.println("0) Salir");
            System.out.print("Opción: ");

            // Leer opción de usuario
            int op;
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                op = -1; // opción inválida
            }

            // Opciones del menú 
            if (op == 1) {
                // Listar todas las máquinas (SIEMPRE con ID)
                for (Maquina m : sistema.getMaquinas()) {
                    System.out.println(lineaConId(m));
                }

            } else if (op == 2) {
                // Buscar por ID exacto
                System.out.print("ID: ");
                String id = sc.nextLine();
                Maquina m = sistema.buscarPorId(id);
                if (m == null) {
                    System.out.println("No encontrado.");
                } else {
                    System.out.println(lineaConId(m)); // Mostrar con ID
                }

            } else if (op == 3) {
                // Buscar por nombre que contenga texto 
                System.out.print("Nombre contiene: ");
                String nom = sc.nextLine().toLowerCase();
                boolean alguno = false;
                for (Maquina x : sistema.getMaquinas()) {
                    if (x.getNombre().toLowerCase().contains(nom)) {
                        System.out.println(lineaConId(x)); // Mostrar con ID
                        alguno = true;
                    }
                }
                if (!alguno) System.out.println("Sin coincidencias.");

            } else if (op == 4) {
                // Ordenar por consumo usando Comparable de Maquina
                sistema.ordenarPorConsumo();
                System.out.println("Listado ordenado por consumo (W):");
                for (Maquina x : sistema.getMaquinas()) {
                    System.out.println(lineaConId(x)); // Mostrar con ID
                }

            } else if (op == 5) {
                // Operar un dispositivo puntual según su capacidad 
                System.out.print("ID de máquina: ");
                String idOp = sc.nextLine();
                Maquina obj = sistema.buscarPorId(idOp);

                if (obj == null) {
                    System.out.println("No encontrado.");
                } else {
                    // Mostrar el seleccionado con ID
                    System.out.println("Seleccionado: " + lineaConId(obj));

                    // Si es Medible: permitir cambiar tipo, medir y registrar
                    if (obj instanceof Medible) {
                        Medible med = (Medible) obj;

                        // Mostrar tipo de medición actual
                        System.out.println("Tipo de medición actual: " + med.tipoMedicion());

                        // Ofrecer cambio de tipo
                        System.out.print("¿Cambiar tipo? (enter / terreno / temperatura / humedad): ");
                        String nuevo = sc.nextLine();
                        if (!nuevo.trim().isEmpty()) {
                            // Cambiar tipo si la clase concreta lo permite
                            if (obj instanceof SensorSuelo) {
                                ((SensorSuelo) obj).setTipoMedicion(nuevo);
                            } else if (obj instanceof EstacionMeteorologica) {
                                ((EstacionMeteorologica) obj).setTipoMedicion(nuevo);
                            }
                        }

                        // Medir y mostrar el valor
                        double valor = med.medir();
                        System.out.println("Medición: " + valor);

                        // Registrar si implementa Registrable
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
                        // Si esta en Accion: describir, ejecutar y registrar
                        Accion acc = (Accion) obj;
                        System.out.println("Acción: " + acc.describirAccion());

                        // Ejecutar acción 
                        acc.ejecutarAccion();

                        // Registrar si implementa Registrable
                        if (obj instanceof Registrable) {
                            ((Registrable) obj).registrarDatos();
                            System.out.println(((DronDeRiego) obj).getUltimoRegistro());
                        }

                    } else {
                        // Ni Medible ni Accion
                        System.out.println("Este dispositivo no es Medible ni Accionable.");
                    }
                }

            } else if (op == 0) {
                // Salida del programa 
                seguir = 0;

            } else {
                // Opción no válida
                System.out.println("Opción inválida.");
            }
        }

        // Cerrar el scanner y despedirse
        sc.close();
        System.out.println("Adiós!");
    }

    //Así se muestra con ID las máquinas
    private static String lineaConId(Maquina m) {
        return "[" + m.getId() + "] "
            + m.getNombre()
            + " | Fab: " + m.getFabricante()
            + " | Consumo: " + m.getConsumoElectrico() + " W";
    }
}

