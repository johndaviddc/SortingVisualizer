import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Random;

public class SortingVisualizer extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int ARRAY_SIZE = 50;
    private static final int DELAY = 50; // Delay in milliseconds

    private int[] array;
    private Canvas canvas;
    private GraphicsContext gc;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        array = generateRandomArray(ARRAY_SIZE);

        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        drawArray(array);

        StackPane root = new StackPane(canvas);
        primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
        primaryStage.setTitle("Sorting Algorithm Visualization");
        primaryStage.show();

        // Start sorting algorithm
        bubbleSort();
    }

    private int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(HEIGHT - 20) + 10; // Values between 10 and HEIGHT-10
        }
        return arr;
    }

    private void drawArray(int[] arr) {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        double barWidth = (double) WIDTH / arr.length;

        for (int i = 0; i < arr.length; i++) {
            double barHeight = (double) arr[i] / (HEIGHT - 20) * (HEIGHT - 20);
            gc.fillRect(i * barWidth, HEIGHT - barHeight, barWidth, barHeight);
        }
    }

    private void bubbleSort() {
        new Thread(() -> {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;

                        try {
                            Thread.sleep(DELAY);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        drawArray(array);
                    }
                }
            }
        }).start();
    }
}
