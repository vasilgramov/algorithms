package p04_eightQueens;

import java.util.ArrayList;

public class Queen {
    private int row;
    private int col;

    ArrayList<int[][]> attackedPositions;

    public Queen(int row, int col) {
        this.setRow(row);
        this.setCol(col);

        this.attackedPositions = new ArrayList<>();
    }

    public int getRow() {
        return row;
    }

    private void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    private void setCol(int col) {
        this.col = col;
    }

    public ArrayList<int[][]> getAttackedPositions() {
        return attackedPositions;
    }

    public boolean addAttackedPosition(int attackedRow, int attackedCol) {
        int[][] currentAttackedPosition = new int[1][2];
        currentAttackedPosition[0][0] = attackedRow;
        currentAttackedPosition[0][1] = attackedCol;

        return this.attackedPositions.add(currentAttackedPosition);
    }
}
