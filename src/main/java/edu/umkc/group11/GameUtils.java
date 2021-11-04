package edu.umkc.group11;

import java.util.Stack;

public class GameUtils {

    public static boolean hasThePlayerGotTurn(Stack<String> playerStack, String player)
    {
        return playerStack.search(player) == 1;
    }
}
