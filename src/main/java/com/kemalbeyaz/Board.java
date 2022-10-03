package com.kemalbeyaz;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final int xAXisCellCount;
    private final int yAXisCellCount;

    private final Map<String, Cell> cells = new HashMap<>();

    public Board(final int xAXisCellCount, final int yAXisCellCount) {
        this.xAXisCellCount = xAXisCellCount;
        this.yAXisCellCount = yAXisCellCount;

        final int boardXAxisMaxVal = xAXisCellCount - 1;
        final int boardYAxisMaxVal = yAXisCellCount - 1;

        for (int x = 0; x < xAXisCellCount; x++) {
            for (int y = 0; y < yAXisCellCount; y++) {
                cells.put(Cell.cellIdGenerator(x, y), new Cell(boardXAxisMaxVal, boardYAxisMaxVal, x, y));
            }
        }
    }

    public void setCellsAlive(String... cellIds) {
        for (String cellId : cellIds) {
            Cell cell = cells.get(cellId);
            if (cell != null) {
                cell.setStatus(Status.ALIVE);
            }
        }
        copyNeighborsData();
    }

    public void calculateNextIteration() {
        cells.forEach((cellId, cell) -> {
            long aliveNeighborsCount = cell.getNeighbors().stream()
                    .filter(Cell::isAlive)
                    .count();

            if (cell.isAlive() && aliveNeighborsCount < 2) {
                cell.setStatus(Status.DEAD);
                return;
            }

            if (cell.isAlive() && aliveNeighborsCount > 3) {
                cell.setStatus(Status.DEAD);
                return;
            }

            if (cell.isDead() && aliveNeighborsCount == 3) {
                cell.setStatus(Status.ALIVE);
            }
        });

        copyNeighborsData();
    }

    public void print() {
        for (int x = 0; x < xAXisCellCount; x++) {
            for (int y = 0; y < yAXisCellCount; y++) {
                System.out.print(cells.get(Cell.cellIdGenerator(x, y)).isAlive() ? "- " : "x ");
            }
            System.out.println("");
        }
        System.out.println("- - - - - - - - - - - - - - -");
    }

    private void copyNeighborsData() {
        cells.forEach((k, cell) -> {
            cell.clearNeighbors();
            cell.getNeighborsIds().stream()
                    .map(cells::get)
                    .forEach(cell::addNeighbors);
        });
    }
}
