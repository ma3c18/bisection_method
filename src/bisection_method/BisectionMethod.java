package bisection_method;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class BisectionMethod {

    private static Scanner input;

    /**
     * Lee la entrada por teclado
     *
     * @return entrada del teclado
     */
    public static double readInput() {
        input = new Scanner(System.in);
        return input.nextDouble();
    }

    /**
     * Imprime el menú principal
     */
    public static void printMenu() {
        System.out.print("BISECTION METHOD" + "\n1) Polynomial" +
            "\n2) Sin(x)" + "\n3) Exit" + "\nChoose an option: ");
    }

    /**
     * Introduce los datos a la función
     *
     * @param terms términos de la función f(x)
     * @param exponents exponentes de la función f(x)
     */
    public static void enterDataToFunction(int terms[], int exponents[]) {
        System.out.println();
        for (int i = 0; i < terms.length; i++) {
            System.out.print("Enter the value of the term " + (i + 1) + ": ");
            terms[i] = (int) readInput();
            for (int j = 0; j < 1; j++) {
                System.out.print("Enter the value of the exponent of the term " +
                    (i + 1) + ": ");
                exponents[i] = (int) readInput();
            }
        }
    }

    /**
     * Imprime la función
     *
     * @param terms términos de la función f(x)
     * @param exponents exponentes de la función f(x)
     */
    public static void printFunction(int terms[], int exponents[]) {
        System.out.print("\nf(x) =");
        for (int i = 0; i < terms.length; i++) {
            for (int j = 0; j < exponents.length; j++) {
                if (i == j) {
                    if ((i > 0) && (terms[i] > 0)) {
                        System.out.print(" + " + terms[i] + "x^" +
                            exponents[j]);
                    } else {
                        if ((terms[i] < 0)) {
                            System.out.print(" - " + Math.abs(terms[i]) + "x^" +
                                exponents[j]);
                        } else {
                            System.out.print(" " + terms[i] + "x^" +
                                exponents[j]);
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    /**
     * Introduce los valores de raiz, rango inicial y final
     *
     * @return un arreglo con los valores de raiz y rangos
     */
    public static double[] enterRootAndRanges() {
        double r, a, b;
        System.out.print("\nEnter the root: ");
        r = readInput();
        System.out.print("Enter the initial range (a): ");
        a = readInput();
        System.out.print("Enter the final range (b): ");
        b = readInput();
        return new double[]{r, a, b};
    }

    /**
     * Obtiene la función f(x)
     *
     * @param x valor de x
     * @param terms terminos de la función
     * @param exponents exponentes de la función
     * @return la función de valor de x
     */
    public static double getFunction(double x, int terms[], int exponents[]) {
        double f = 0;
        for (int i = 0; i < terms.length; i++) {
            for (int j = 0; j < exponents.length; j++) {
                if (i == j) {
                    f += terms[i] * Math.pow(x, exponents[j]);
                }
            }
        }
        return f;
    }

    /**
     * Obtiene la formula del método de bisección
     *
     * @param a rango inicial
     * @param b rango final
     * @return el valor de xi
     */
    public static double getFormula(double a, double b) {
        return a + (b - a) / 2;
    }

    /**
     * Realiza el formato de decimales
     *
     * @param v valor para efectuar formato
     * @param df formato establecido
     * @return el valor con el formato establecido
     */
    public static String makeFormat(double v, DecimalFormat df) {
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(v);
    }

    /**
     * Comprueba la convergencia entre variables y hacer el cambio
     *
     * @param a rango inicial
     * @param b rango final
     * @param fa función de a f(a)
     * @param fb función de b f(b)
     * @param xi formula del método de bisección
     * @param fxi función de xi f(xi)
     * @return un arreglos con todos los intercambios de variables
     */
    public static double[] checkConvergence(double a, double b, double fa,
        double fb, double xi, double fxi) {
        if ((fxi < 0) && (fa < 0)) {
            a = xi;
            fa = fxi;
        } else {
            if ((fxi < 0) && (fb < 0)) {
                b = xi;
                fb = fxi;
            } else {
                if ((fxi > 0) && (fa > 0)) {
                    a = xi;
                    fa = fxi;
                } else {
                    if ((fxi > 0) && (fb > 0)) {
                        b = xi;
                        fb = fxi;
                    }
                }
            }
        }
        return new double[]{a, b, fa, fb, xi, fxi};
    }

    /**
     * Imprime la primera iteración para el caso 1
     *
     * @param a rango incicial
     * @param b rango final
     * @param fa valor de f(a)
     * @param fb valor de f(b)
     * @param xi valor de la formula de bisección
     * @param fxi valor de f(xi)
     * @param terms terminos de la función
     * @param exponents exponentes de la función
     * @param df1 formato de decimales
     */
    public static void printFirtIterationCase1(double a, double b, double fa,
        double fb, double xi, double fxi, int terms[], int exponents[],
        DecimalFormat df1) {
        double aux = getFormula(a, b); // Guarda el valor actual de xi
        System.out.println("1\t" + makeFormat(a, df1) + "\t" +
            makeFormat(b, df1) + "\t" +
            makeFormat(getFunction(a, terms, exponents), df1) + "\t" +
            makeFormat(getFunction(b, terms, exponents), df1) + "\t" +
            makeFormat(getFormula(a, b), df1) + "\t" +
            makeFormat(getFunction(aux, terms, exponents), df1) + "\t-");
    }

    /**
     * Imprime la primera iteración para el caso 2
     *
     * @param a rango inicial
     * @param b rango final
     * @param fa valor de f(a)
     * @param fb valor de f(b)
     * @param xi valor de la formula de bisección
     * @param fxi valor de f(xi)
     * @param df1 formato de decimales
     */
    public static void printFirstIterationCase2(double a, double b, double fa,
        double fb, double xi, double fxi, DecimalFormat df1) {
        double aux = getFormula(a, b); // Guarda el valor actual de xi
        System.out.print("1\t" + makeFormat(a, df1) + "\t" +
            makeFormat(b, df1) + "\t" +
            makeFormat(Math.sin(Math.toRadians(a)), df1) + "\t" +
            makeFormat(Math.sin(Math.toRadians(b)), df1) + "\t" +
            makeFormat(getFormula(a, b), df1) + "\t" +
            makeFormat(Math.sin(Math.toRadians(aux)), df1) + "\t-\n");
    }

    /**
     * Imprime la tabla con las iteraciones
     *
     * @param a rango inicial
     * @param b rango final
     * @param fa f(a)
     * @param fb f(b)
     * @param xi formula del método de bisección
     * @param fxi f(xi)
     * @param abs_err error absoluto
     * @param df1 formato de decimales
     */
    public static void printTable(double a, double b, double fa, double fb,
        double xi, double fxi, double abs_err, DecimalFormat df1) {
        System.out.print("\t" + makeFormat(a, df1) + "\t" + makeFormat(b, df1) +
            "\t" + makeFormat(fa, df1) + "\t" + makeFormat(fb, df1) + "\t" +
            makeFormat(xi, df1) + "\t" + makeFormat(fxi, df1) + "\t" +
            makeFormat(abs_err, df1) + "\n");
    }

    /**
     * Imprime el resultado de aproximación
     *
     * @param iCase1 iterador del caso 1
     * @param xi valor actual de formula del método de bisección
     * @param df1 formato de decimales
     * @param root raiz de aproximación actual
     */
    public static void printMessage(int iCase1, double xi, DecimalFormat df1,
        double root) {
        System.out.println("\nIn iteration " + (iCase1 + 1) +
            " with a value of xi = " + makeFormat(xi, df1) +
            " approaches the root = " + root + "\n");
    }

    public static void main(String[] args) {
        // Definir formatos de decimales
        DecimalFormat df1 = new DecimalFormat("0.000");
        DecimalFormat df2 = new DecimalFormat("0.00");

        // Definir variables
        int numTerms; // Cantidad de términos de la función
        int iteratorCase1; // Iterador para el caso 1
        int iteratorCase2 = 0; // Iterador para el caso 2
        int option; // Opción para el menú
        double a; // Rango inicial
        double b; // Rango final
        double root; // Raíz de aproximación
        double fa; // Función de a f(a)
        double fb; // Función de b f(b)
        double xi; // Formula del método de bisección (xi)
        double fxi; // Función de xi f(xi)
        double xiPre;// xi anterior (xi - 1)
        double absErr; // Error absoluto |xi - (xi -1)|
        boolean case1Exit = false; // Salida del caso 1
        boolean case2Exit = false; // Salida del caso 2
        boolean menuExit = false; // Salida del menú principal
        int terms[]; // Terminos de la función
        int exponents[]; // Exponentes de cada termino
        double case1Values[]; // Arreglo caso 1 para raíz e intervalos
        double case2Values[]; // Arreglo caso 2 para raíz e intervalos
        double changes[]; // Arreglo de cambios de variables
        final String ABS_ERR = "0.00"; // Error absoluto formateado

        do {
            printMenu();
            option = (int) readInput();
            switch (option) {
                case 1:
                    // Reiniciar variables
                    iteratorCase1 = 1;
                    a = b = root = fa = fb = xi = fxi = xiPre = absErr = 0;
                    case1Exit = false;

                    // Establecer el número de termino que tendra la función
                    System.out.print("\nEnter the number of terms of the " +
                        "function: ");
                    numTerms = (int) readInput();
                    terms = new int[numTerms];
                    exponents = new int[numTerms];

                    // Introducir los valores a la función
                    enterDataToFunction(terms, exponents);
                    printFunction(terms, exponents);

                    // Ingresar la raíz y los intervalos
                    case1Values = enterRootAndRanges();
                    root = case1Values[0];
                    a = case1Values[1];
                    b = case1Values[2];

                    // Encontrar iteraciones
                    System.out.println(
                        "\ni\ta\tb\tf(a)\tf(b)\txi\tf(xi)\t|xi - (xi - 1)|");

                    // Mostrar primera iteración
                    printFirtIterationCase1(a, b, fa, fb, xi, fxi, terms,
                        exponents, df1);

                    while (!case1Exit) {
                        // Resolución de operaciones
                        xiPre = xi;
                        fa = getFunction(a, terms, exponents);
                        fb = getFunction(b, terms, exponents);
                        xi = getFormula(a, b);
                        fxi = getFunction(xi, terms, exponents);
                        absErr = Math.abs(xi - xiPre);

                        // Cambiar valores
                        changes = checkConvergence(a, b, fa, fb, xi, fxi);
                        a = changes[0];
                        b = changes[1];
                        fa = changes[2];
                        fb = changes[3];
                        xi = changes[4];
                        fxi = changes[5];

                        // Mostrar tabla
                        System.out.print((iteratorCase1 + 1));
                        printTable(a, b, fa, fb, xi, fxi, absErr, df1);

                        // Verificar el error absoluto
                        if (ABS_ERR.equals(makeFormat(absErr, df2))) {
                            printMessage(iteratorCase1, xi, df1, root);
                            case1Exit = true;
                        } else {
                            iteratorCase1++;
                        }
                    }
                    break;
                case 2:
                    // Reiniciar variables
                    iteratorCase2 = 1;
                    a = b = root = fa = fb = xi = fxi = xiPre = absErr = 0;
                    case2Exit = false;

                    // Ingresar la raíz y los intervalos
                    case2Values = enterRootAndRanges();
                    root = case2Values[0];
                    a = case2Values[1];
                    b = case2Values[2];

                    // Encontrar iteraciones
                    System.out.println(
                        "\ni\ta\tb\tf(a)\tf(b)\txi\tf(xi)\t|xi - (xi - 1)|");

                    // Mostrar primera iteración
                    printFirstIterationCase2(a, b, fa, fb, xi, fxi, df1);

                    while (!case2Exit) {
                        // Resolución de operaciones
                        xiPre = xi;
                        fa = Math.sin(Math.toRadians(a));
                        fb = Math.sin(Math.toRadians(b));
                        xi = getFormula(a, b);
                        fxi = Math.sin(Math.toRadians(xi));
                        absErr = Math.abs(xi - xiPre);

                        // Cambiar valores
                        changes = checkConvergence(a, b, fa, fb, xi, fxi);
                        a = changes[0];
                        b = changes[1];
                        fa = changes[2];
                        fb = changes[3];
                        xi = changes[4];
                        fxi = changes[5];

                        // Mostrar tabla
                        System.out.print((iteratorCase2 + 1));
                        printTable(a, b, fa, fb, xi, fxi, absErr, df1);

                        // Verificar el error absoluto
                        if (ABS_ERR.equals(makeFormat(absErr, df2))) {
                            printMessage(iteratorCase2, xi, df1, root);
                            case2Exit = true;
                        } else {
                            iteratorCase2++;
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nThank you for using this program, you" +
                        " have a good day :)");
                    menuExit = true;
                    break;
                default:
                    System.out.println("\nEnter a valid option [1 - 3]\n");
                    break;
            }
        } while (!menuExit);
    }
}
