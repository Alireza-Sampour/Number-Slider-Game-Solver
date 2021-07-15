package Ad.Hw4.Puzzle;

public class Board {

    private static Board board = null;
    private final int size;
    private int[][] values;
    private int[][] valuesOrder;
    public boolean is_set;

    private Board() {
        this.size = 3;
        this.is_set = false;
        setValuesOrder();
        values = valuesOrder;
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public int getSize() {
        return size;
    }

    public int[][] getValues() {
        return values;
    }

    public int[][] getValuesOrder() {
        return valuesOrder;
    }

    public void setValues(int[][] values) {
        this.is_set = true;
        this.values = values;
    }

    public void setValuesOrder() {
        this.valuesOrder = new int[size][size];
        int counter = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                valuesOrder[i][j] = counter++;
            }
        }
        valuesOrder[valuesOrder.length - 1][valuesOrder.length - 1] = 0;
    }
}
