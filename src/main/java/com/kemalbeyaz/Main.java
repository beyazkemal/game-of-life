package com.kemalbeyaz;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        board.print();
        System.out.println("Board initialized!");

        board.setCellsAlive("1, 1", "1, 2", "1, 3");
        board.print();
        System.out.println("Alive cells sets.");

        for (int i = 0; i < 5; i++) {
            board.calculateNextIteration();
            board.print();
        }
    }
}
