import java.util.Random;

public class Main {
    public static final int SIZE = 8;

    public static int[][] getMatrix() {
        // Функция генерирует матрицу
        int[][] colors = new int[SIZE][SIZE];

        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                colors[i][j] = random.nextInt(256);
            }
        }         
        return colors;
    }

    public static int[][] rotateMatrix(int[][] colors, int angle) {
        // Функция поворачивает матрицу
        int[][] rotatedColors = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                switch (angle) {
                    case 90:
                        rotatedColors[i][j] = colors[colors.length - 1 - j][i];
                        break;
                    case 180:
                        rotatedColors[i][j] = colors[colors.length - 1 - i][colors.length - 1 - j];
                        break;
                    case 270:
                        rotatedColors[i][j] = colors[j][colors.length - 1 - i];
                        break;
                }
            }
        }       
        return rotatedColors;   
    }

    public static void printMatrix(int[][] colors) {
        // Функция выводит матрицу на экран
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.format("%4d", colors[i][j]);
            }
            System.out.println();
        }          
        System.out.println();
    }

    public static void main(String[] args) {
        int[][] colors = new int[SIZE][SIZE];
        int[][] rotatedColors = new int[SIZE][SIZE];

        System.out.println("Original matrix");
        colors = getMatrix();
        printMatrix(colors);
           
        System.out.println("New matrix - rotation 90");
        rotatedColors = rotateMatrix(colors, 90);
        printMatrix(rotatedColors);

        System.out.println("New matrix - rotation 180");
        rotatedColors = rotateMatrix(colors, 180);
        printMatrix(rotatedColors);
        
        System.out.println("New matrix - rotation 270");
        rotatedColors = rotateMatrix(colors, 270);
        printMatrix(rotatedColors);        
    }
}