import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Node {
/*
this class is largely useless, i intended to use a minimax algorithm to solve this problem but finding a way to score the board proved too time consuming for this assignment
 */
    private GameState gameState;
    private int minMax;
    private int move;
    private List<Integer> chosenMove;

    Node(GameState gameState, int move)
    {
        this.gameState = gameState;
        this.move = move;
        minMax = -2;
    }


    public void setChosenMove(List<Integer> chosenMove) {
        this.chosenMove = chosenMove;
    }

    public List<Integer> getChosenMove() {
        return chosenMove;
    }

    void setBoardScore(int player)
    {
        ArrayList<List<Integer>> positionsOf1 = new ArrayList<>();
        ArrayList<List<Integer>> positionsOf2 = new ArrayList<>();

        int[][] board = gameState.getBoard();

        for(int row = 0; row < board.length; row++)
        {
            for(int col = 0; col < board[row].length; col++)
            {
                if(board[row][col] == 1)
                {
                    positionsOf1.add(Arrays.asList(row,col));
                }
                if(board[row][col] == 2)
                {
                    positionsOf2.add(Arrays.asList(row,col));
                }

            }
        }

        if(player == 1)
        {
            if(positionsOf1.size() > positionsOf2.size())
                minMax = 10;
            if(positionsOf1.size() < positionsOf2.size())
                minMax = -10;
        }
        if(player == 2)
        {
            if(positionsOf1.size() > positionsOf2.size())
                minMax = -10;
            if(positionsOf1.size() < positionsOf2.size())
                minMax = 10;
        }
    }

}
