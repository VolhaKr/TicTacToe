package com.epam.rd.autocode.concurrenttictactoe;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.epam.rd.autocode.concurrenttictactoe.UtilsSource.tableString1;

public interface Player extends Runnable {
    char EMPTY_CELL = ' ';

    static Player createPlayer(final TicTacToe ticTacToe, final char mark, PlayerStrategy strategy) {
//        AtomicBoolean gameEnd = new AtomicBoolean(false);
//        char firstGoingSymbol = 'X';

        return new PlayerImpl( ticTacToe, mark, strategy);
            //  public AtomicBoolean gameEnd = new AtomicBoolean(false);
    }
}
