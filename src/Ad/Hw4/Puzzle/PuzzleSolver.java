package Ad.Hw4.Puzzle;

import java.util.List;

public class PuzzleSolver {

    private Board board;
    private final int MAX_DEPTH;
    private int[][] currentValues;

    public PuzzleSolver(Board board, int max_depth) {
        this.board = board;
        this.MAX_DEPTH = max_depth;
        this.currentValues = board.getValues();
    }

    public boolean solve(List<Direction> path) {
        path.add(Direction.Undirected);
        return findPath(board.getValues(), path, board.getSize(), 0, findIZeroPoint(board.getValues()), findJZeroPoint(board.getValues()));
    }

    private boolean findPath(int[][] currentStatus, List<Direction> path, int size, int deep, int i_zeroPoint, int j_zeroPoint) {
        if (isPuzzleSolved()) {
            return true;
        } else if (deep > MAX_DEPTH && path.size() == 0) {
            return false;
        } else if (deep > MAX_DEPTH) {
            path.remove(path.size() - 1);
            return false;
        }
        for (Direction direction : Direction.values()) {
            if ((path.get(path.size() - 1) == Direction.UP && direction == Direction.DOWN) || (path.get(path.size() - 1) == Direction.DOWN && direction == Direction.UP)) {
                continue;
            }
            if ((path.get(path.size() - 1) == Direction.LEFT && direction == Direction.RIGHT) || (path.get(path.size() - 1) == Direction.RIGHT && direction == Direction.LEFT)) {
                continue;
            }
            switch (direction) {
                case UP: {
                    if (i_zeroPoint - 1 >= 0) {
                        currentStatus[i_zeroPoint][j_zeroPoint] = currentStatus[i_zeroPoint - 1][j_zeroPoint];
                        currentStatus[i_zeroPoint - 1][j_zeroPoint] = 0;
                        path.add(Direction.UP);
                        if (findPath(currentStatus, path, size, deep + 1, i_zeroPoint - 1, j_zeroPoint)) {
                            currentStatus[i_zeroPoint - 1][j_zeroPoint] = currentStatus[i_zeroPoint][j_zeroPoint];
                            currentStatus[i_zeroPoint][j_zeroPoint] = 0;
                            return true;
                        }
                        currentStatus[i_zeroPoint - 1][j_zeroPoint] = currentStatus[i_zeroPoint][j_zeroPoint];
                        currentStatus[i_zeroPoint][j_zeroPoint] = 0;
                    }
                    break;
                }
                case LEFT: {
                    if (j_zeroPoint - 1 >= 0) {
                        currentStatus[i_zeroPoint][j_zeroPoint] = currentStatus[i_zeroPoint][j_zeroPoint - 1];
                        currentStatus[i_zeroPoint][j_zeroPoint - 1] = 0;
                        path.add(Direction.LEFT);
                        if (findPath(currentStatus, path, size, deep + 1, i_zeroPoint, j_zeroPoint - 1)) {
                            currentStatus[i_zeroPoint][j_zeroPoint - 1] = currentStatus[i_zeroPoint][j_zeroPoint];
                            currentStatus[i_zeroPoint][j_zeroPoint] = 0;
                            return true;
                        }
                        currentStatus[i_zeroPoint][j_zeroPoint - 1] = currentStatus[i_zeroPoint][j_zeroPoint];
                        currentStatus[i_zeroPoint][j_zeroPoint] = 0;
                    }
                    break;
                }
                case DOWN: {
                    if (i_zeroPoint + 1 < size) {
                        currentStatus[i_zeroPoint][j_zeroPoint] = currentStatus[i_zeroPoint + 1][j_zeroPoint];
                        currentStatus[i_zeroPoint + 1][j_zeroPoint] = 0;
                        path.add(Direction.DOWN);
                        if (findPath(currentStatus, path, size, deep + 1, i_zeroPoint + 1, j_zeroPoint)) {
                            currentStatus[i_zeroPoint + 1][j_zeroPoint] = currentStatus[i_zeroPoint][j_zeroPoint];
                            currentStatus[i_zeroPoint][j_zeroPoint] = 0;
                            return true;
                        }
                        currentStatus[i_zeroPoint + 1][j_zeroPoint] = currentStatus[i_zeroPoint][j_zeroPoint];
                        currentStatus[i_zeroPoint][j_zeroPoint] = 0;
                    }
                    break;
                }
                case RIGHT: {
                    if (j_zeroPoint + 1 < size) {
                        currentStatus[i_zeroPoint][j_zeroPoint] = currentStatus[i_zeroPoint][j_zeroPoint + 1];
                        currentStatus[i_zeroPoint][j_zeroPoint + 1] = 0;
                        path.add(Direction.RIGHT);
                        if (findPath(currentStatus, path, size, deep + 1, i_zeroPoint, j_zeroPoint + 1)) {
                            currentStatus[i_zeroPoint][j_zeroPoint + 1] = currentStatus[i_zeroPoint][j_zeroPoint];
                            currentStatus[i_zeroPoint][j_zeroPoint] = 0;
                            return true;
                        }
                        currentStatus[i_zeroPoint][j_zeroPoint + 1] = currentStatus[i_zeroPoint][j_zeroPoint];
                        currentStatus[i_zeroPoint][j_zeroPoint] = 0;
                    }
                    break;
                }
                default:
                    break;
            }
        }
        try {
            path.remove(path.size() - 1);
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean isPuzzleSolved() {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getValues()[i][j] != board.getValuesOrder()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private int findIZeroPoint(int[][] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[i][j] == 0) {
                    return i;
                }
            }
        }
        return 0;
    }

    private int findJZeroPoint(int[][] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[i][j] == 0) {
                    return j;
                }
            }
        }
        return 0;
    }

    public int[][] getNext(Direction direction) {

        int lasti = findIZeroPoint(currentValues);
        int lastj = findJZeroPoint(currentValues);

        switch (direction) {
            case UP: {
                currentValues[lasti][lastj] = currentValues[lasti - 1][lastj];
                currentValues[lasti - 1][lastj] = 0;
                break;
            }
            case LEFT: {
                currentValues[lasti][lastj] = currentValues[lasti][lastj - 1];
                currentValues[lasti][lastj - 1] = 0;
                break;
            }
            case DOWN: {
                currentValues[lasti][lastj] = currentValues[lasti + 1][lastj];
                currentValues[lasti + 1][lastj] = 0;
                break;
            }
            case RIGHT: {
                currentValues[lasti][lastj] = currentValues[lasti][lastj + 1];
                currentValues[lasti][lastj + 1] = 0;
                break;
            }
            default:
                break;
        }
        return this.currentValues;
    }
}
