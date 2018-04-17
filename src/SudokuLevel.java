
public enum SudokuLevel {

    EASY(32, 45),
    MEDIUM(45, 55),
    HARD(56, 67);

    private final int min;
    private final int max;

    SudokuLevel(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMinimum() {
        return min;
    }

    public int getMaximum() {
        return max;
    }

}