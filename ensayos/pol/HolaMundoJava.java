import java.util.Scanner;

public class HolaMundoJava {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        limpiarConsola();

        int opcion;
        do {
            opcion = mostrarMenuYObtenerOpcion(scanner);
        } while (opcion != 0 && !esOpcionValida(opcion));

        while (opcion != 0) {
            if (opcion == 4) {
                limpiarConsola();
            } else {
                System.out.print("Ingrese el texto: ");
                String userInput = scanner.next();

                switch (opcion) {
                    case 1:
                        System.out.println("Binario: " + convertToBinary(userInput));
                        break;
                    case 2:
                        System.out.println("Hexadecimal: " + convertToHexadecimal(userInput));
                        break;
                    case 3:
                        System.out.println("Ensamblador (x86):");
                        System.out.println(convertToAssembly(userInput));
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            }

            do {
                opcion = mostrarMenuYObtenerOpcion(scanner);
            } while (!esOpcionValida(opcion));
        }

        scanner.close();

        limpiarConsola();
    }

    private static int mostrarMenuYObtenerOpcion(Scanner scanner) {
        menuPromt();

        while (!scanner.hasNextInt()) {
            System.out.println(ANSI_RED + "Entrada inválida. Por favor, ingrese un número." + ANSI_RESET);
            menuPromt();
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void menuPromt() {
        System.out.println("\nSelecciona la conversión deseada:");
        System.out.println("1. Binario");
        System.out.println("2. Hexadecimal");
        System.out.println("3. Ensamblador (x86)");
        System.out.println("4. Limpiar consola");
        System.out.println("0. Salir");
        System.out.print("Opción: >> ");
    }

    private static boolean esOpcionValida(int opcion) {
        if (opcion >= 0 && opcion <= 4) {
            return true;
        } else {
            System.out.println(ANSI_RED + "Seleccione una opción válida (0-4)" + ANSI_RESET);
            return false;
        }
    }

    public static String convertToBinary(String userInput) {
        StringBuilder binary = new StringBuilder();
        for (char c : userInput.toCharArray()) {
            String charBinary = Integer.toBinaryString((int) c);
            while (charBinary.length() < 8) {
                charBinary = "0" + charBinary;
            }
            binary.append(charBinary + " ");
        }
        return ANSI_GREEN + binary.toString().trim() + ANSI_RESET;
    }

    public static String convertToHexadecimal(String userInput) {
        StringBuilder hex = new StringBuilder();
        for (char c : userInput.toCharArray()) {
            hex.append(Integer.toHexString((int) c) + " ");
        }
        return ANSI_GREEN + hex.toString().trim().toUpperCase() + ANSI_RESET;
    }

    public static String convertToAssembly(String userInput) {
        StringBuilder assembly = new StringBuilder();
        String[] registers = {"al", "bl", "cl", "dl", "ah", "bh", "ch", "dh"};
        int registerIndex = 0;

        for (char c : userInput.toCharArray()) {
            int charCode = (int) c;
            assembly.append("mov " + registers[registerIndex] + ", " + charCode + "\n");
            registerIndex = (registerIndex + 1) % registers.length;
        }
        return ANSI_GREEN + assembly.toString().trim() + ANSI_RESET;
    }

    public static void limpiarConsola() throws Exception {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
    }
}
