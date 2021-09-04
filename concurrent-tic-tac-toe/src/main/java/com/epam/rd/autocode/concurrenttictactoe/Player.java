package com.epam.rd.autocode.concurrenttictactoe;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.rd.autocode.concurrenttictactoe.UtilsSource.tableString1;

public interface Player extends Runnable {


    static Player createPlayer(final TicTacToe ticTacToe, final char mark, PlayerStrategy strategy) {
        AtomicBoolean gameEnd = new AtomicBoolean(false);

        return new Player() {
          //  public AtomicBoolean gameEnd = new AtomicBoolean(false);

            @Override
            public void run() {
//                Lock tableLock = new ReentrantLock();
//                Lock table1Lock = new ReentrantLock();
//              //  tableLock.lock();
//                try {
                    synchronized (ticTacToe) {
                        if (!ticTacToe.isGameStart()) {
                            if (mark == 'X') {
                                ticTacToe.setMark(strategy.computeMove(mark, ticTacToe).getRow(), strategy.computeMove(mark, ticTacToe).getColumn(), mark);
                                ticTacToe.setGameStart(true);
                            }
                        }
                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//             //       tableLock.unlock();
//                }

                if (ticTacToe.isGameStart()) {
                    synchronized (gameEnd) {
                        while (!gameEnd.get()) {
                            synchronized (ticTacToe) {
                                if (ticTacToe.lastMark() != mark) {
                                  //= redundant?  tableLock.lock();
//                                    try {
//                                        synchronized (ticTacToe) {
                                            ticTacToe.setMark(strategy.computeMove(mark, ticTacToe).getRow(), strategy.computeMove(mark, ticTacToe).getColumn(), mark);
                                            System.out.println(tableString1(ticTacToe.table()));
                                            if (gameIsOver(ticTacToe.table())) {
                                                gameEnd.set(true);
                                            }
//                                            }
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    } finally {
//                                        tableLock.unlock();
//                                    }
                                }
                            }
                        }
                    }
                }
            }
        };
    }

    static boolean gameIsOver(char[][] ticTable) {
        if (checkHorizontal(ticTable) ||
                checkVertical(ticTable) ||
                checkDiagonal(ticTable)) {
            return true;
        } else {
            return false;
        }
    }


    static boolean checkHorizontal(char[][] ticTable) {
        for ( int curRow = 0; curRow < 3; curRow++ ) {
            if (ticTable[curRow][0] != ' ' && (ticTable[curRow][0] == ticTable[curRow][1] && ticTable[curRow][1] == ticTable[curRow][2])) {
                return true;
            }
        }
        return false;
    }

    static boolean checkDiagonal(char[][] ticTable) {
        if (ticTable[0][0] != ' ' && ticTable[0][0] == ticTable[1][1] && ticTable[1][1] == ticTable[2][2]) {
            System.out.println("right horizontal");
            return true;
        }
        if (ticTable[1][1] != ' ' && ticTable[0][2] == ticTable[1][1] && ticTable[1][1] == ticTable[2][0]) {
            System.out.println("left horizontal");
            return true;
        }
        return false;
    }

    static boolean checkVertical(char[][] ticTable) {
        for ( int curCol = 0; curCol < 3; curCol++ ) {
            if (ticTable[0][curCol] != ' ' && ticTable[0][curCol] == ticTable[1][curCol] && ticTable[1][curCol] == ticTable[2][curCol]) {
                System.out.println("vertical");
                return true;
            }
        }
        return false;
    }

}
