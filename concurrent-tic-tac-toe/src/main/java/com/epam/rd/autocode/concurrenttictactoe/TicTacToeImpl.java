package com.epam.rd.autocode.concurrenttictactoe;

import java.util.Arrays;

public class TicTacToeImpl implements TicTacToe {

    private final int TABLE_SIZE = 3;
    private volatile char[][] gameTable = new char[TABLE_SIZE][TABLE_SIZE];
    private final char EMPTY_CELL = ' ';
    private volatile char lastMarker;
    private volatile boolean gameStart = false;
    private volatile boolean gameEnd = false;

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
        if (gameTable[x][y] != EMPTY_CELL) {
            throw new IllegalArgumentException();
        } else {
            gameTable[x][y] = mark;
            lastMarker = mark;
            //    gameIsOver();
        }
    }
    //  tableLock.unlock();
//lastMark[lastMarkNumber.intValue()] = mark;
//lastMarkNumber.getAndIncrement();


    @Override
    public synchronized char[][] table() {
        final char[][] tableCopy = new char[TABLE_SIZE][TABLE_SIZE];
        for ( int i = 0; i < TABLE_SIZE; i++ ) {
            for ( int j = 0; j < TABLE_SIZE; j++ ) {
                tableCopy[i][j] = gameTable[i][j];
            }
        }
        //     tableCopy= gameTable.clone();
        return tableCopy;
    }

    @Override
    public synchronized char lastMark() {
        return lastMarker;
        //lastMark[lastMarkNumber.intValue()-1];
    }

    public void fillEmptyTable() {
        for (int i = 0; i < TABLE_SIZE; i++ ) {
            Arrays.fill(gameTable[i], ' ');
        }
    }
}

