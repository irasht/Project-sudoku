import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SudokuBoard extends JPanel implements ActionListener {
    private static final Color MYCOLOR = new Color(191, 247, 255);
    private static final int CELL = 9;
    private JPanel[][] square;
    private JButton sudokuFild[][];
    private Sudoku sudoku;
    private int numbers[][];


    public int[][] getNumbers() {
        return numbers;
    }

    public SudokuBoard() {
        super(new GridLayout(3, 3));
        sudoku = new Sudoku();
        numbers = sudoku.createSudoku();
        square = new JPanel[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                square[i][j] = new JPanel(new GridLayout(3, 3));
                square[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(square[i][j]);
            }
        }
        sudokuFild = new JButton[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = "";
                text += String.valueOf(numbers[i][j]);
                sudokuFild[i][j] = new JButton(text);
                sudokuFild[i][j].setBackground(Color.WHITE);
                sudokuFild[i][j].setPreferredSize(new Dimension(40, 40));
                sudokuFild[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                if (numbers[i][j] == 0) {
                    sudokuFild[i][j].setText(" ");
                    sudokuFild[i][j].setBackground(MYCOLOR);
                    sudokuFild[i][j].addActionListener(this);
                } else {
                    sudokuFild[i][j].setText(text);
                }
                sudokuFild[i][j].setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
                sudokuFild[i][j].setOpaque(true);
                square[i / 3][j / 3].add(sudokuFild[i][j]);
            }
        }
    }

    protected boolean checkSolution() {
        return sudoku.isSudokuValid(getNumbers());
    }


    protected boolean isComplete() {
        boolean isComplete = true;
        for (int i = 0; i < CELL; i++) {
            for (int j = 0; j < CELL; j++) {
                try {
                    Integer.parseInt(sudokuFild[i][j].getText());
                } catch (NumberFormatException e) {
                    isComplete = false;
                    break;
                }
            }
        }
        return isComplete;
    }

    protected int[][] startNewGame() {
        return getNumbers();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == sudokuFild[i][j]) {
                    String s = JOptionPane.showInputDialog("Please, enter number");
                    try {
                        int c = Integer.parseInt(s);
                        if (0 < c && 10 > c) {
                            sudokuFild[i][j].setText(s);
                            sudokuFild[i][j].setBackground(Color.WHITE);
                            sudokuFild[i][j].setForeground(Color.BLUE);
                        }
                    } catch (NumberFormatException ex) {
                        System.err.println("Please, enter number");
                    }
                    break;
                }
            }
    }
}

