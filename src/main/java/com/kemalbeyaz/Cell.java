package com.kemalbeyaz;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    public static String cellIdGenerator(final int x, final int y) {
        return x + ", " + y;
    }

    private final int xAxis;
    private final int yAxis;
    private Status status;
    private final List<String> neighborsIds = new ArrayList<>(9);

    private final List<Cell> neighbors = new ArrayList<>(9);

    public Cell(int boardXAxis, int boardYAxis, int xAxis, int yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.status = Status.DEAD;
        calculateNeighborsIds(boardXAxis, boardYAxis);
    }

    public Cell(final Cell cell) {
        this.xAxis = cell.xAxis;
        this.yAxis = cell.yAxis;
        this.status = cell.status;
    }


    public boolean isAlive() {
        return status.equals(Status.ALIVE);
    }

    public boolean isDead() {
        return status.equals(Status.DEAD);
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getNeighborsIds() {
        return neighborsIds;
    }

    public void addNeighbors(final Cell neighbor) {
        this.neighbors.add(new Cell(neighbor));
    }

    public void clearNeighbors() {
        neighbors.clear();
    }

    public List<Cell> getNeighbors() {
        return neighbors;
    }

    private void calculateNeighborsIds(int boardXAxis, int boardYAxis) {
        findNeighborsIds(this.xAxis - 1, boardXAxis, boardYAxis);
        findNeighborsIds(this.xAxis, boardXAxis, boardYAxis);
        findNeighborsIds(this.xAxis + 1, boardXAxis, boardYAxis);
        this.neighborsIds.remove(cellIdGenerator(this.xAxis, this.yAxis));
    }

    private void findNeighborsIds(final int neighborXAxis, final int boardXAxis, final int boardYAxis) {
        if (!checkAxisValueInBoardAxis(neighborXAxis, boardXAxis)) {
            return;
        }

        int n1y = this.yAxis - 1;
        if (checkAxisValueInBoardAxis(n1y, boardYAxis)) {
            this.neighborsIds.add(cellIdGenerator(neighborXAxis, n1y));
        }

        if (checkAxisValueInBoardAxis(this.yAxis, boardYAxis)) {
            this.neighborsIds.add(cellIdGenerator(neighborXAxis, this.yAxis));
        }

        int n3y = this.yAxis + 1;
        if (checkAxisValueInBoardAxis(n3y, boardYAxis)) {
            this.neighborsIds.add(cellIdGenerator(neighborXAxis, n3y));
        }
    }

    private static boolean checkAxisValueInBoardAxis(final int axisValue, final int boardAxisValue) {
        return axisValue <= boardAxisValue && axisValue >= 0;
    }
}
