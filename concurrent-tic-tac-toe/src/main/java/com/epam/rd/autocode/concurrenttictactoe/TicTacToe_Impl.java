package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToe_Impl implements TicTacToe {


    volatile char[][] gameTable = new char[3][3];
    volatile char lastMarking;
    volatile boolean gameStart = false;
    volatile boolean gameEnd = false;

    @Override
    public boolean isGameStart() {
        return gameStart;
    }

    @Override
    public void setGameStart(boolean gameStart) {
        this.gameStart = gameStart;
    }

    public boolean isGameEnd() {
        return gameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    @Override
    public synchronized void setMark(int x, int y, char mark) {
//        Lock tableLock = new ReentrantLock();
//        tableLock.lock();
        // synchronized (gameTable) {
        if (gameTable[x][y] != ' ') {
            throw new IllegalArgumentException();
        } else {
            gameTable[x][y] = mark;
            lastMarking = mark;
            //    gameIsOver();
        }
    }
    //  tableLock.unlock();
//lastMark[lastMarkNumber.intValue()] = mark;
//lastMarkNumber.getAndIncrement();


    @Override
    public synchronized char[][] table() {
        final char[][] tableCopy = new char[3][3];
        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++ ) {
                tableCopy[i][j] = gameTable[i][j];
            }
        }
        //     tableCopy= gameTable.clone();
        return tableCopy;
    }

    @Override
    public synchronized char lastMark() {
        return lastMarking;
        //lastMark[lastMarkNumber.intValue()-1];
    }

    public void fillEmptyTable() {

        for ( int i = 0; i < 3; i++ ) {
            Arrays.fill(gameTable[i], ' ');
        }
    }


}

