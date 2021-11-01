


import java.util.*;

public class MinMax {
    private GameState state;
    private Node startNode;
    private ArrayList<Node> moveList;
    private int player;
    HashSet<List<Integer>> visitedNodes;
    HashSet<List<Integer>> possibleMoves;

    MinMax(GameState start)
    {
        state = start;
        player = start.getPlayer();
        visitedNodes = new HashSet();
        possibleMoves = new HashSet<>();
        setNode();

        moveList = createMoveList();

        setMoveListMinMax(moveList, player);
    }

    void setNode()
    {
        startNode = new Node(state, 0);
    }

    ArrayList<Node> createMoveList()
    {
        ArrayList<Node> moveList = new ArrayList<Node>();

        ArrayList<List<Integer>> positionsOf1 = new ArrayList<>();
        ArrayList<List<Integer>> positionsOf2 = new ArrayList<>();

        int[][] board = state.getBoard();

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

        ArrayList<List<Integer>> blanks;

        if(player == 1)
            blanks = FindBlanks(positionsOf2, board);
        else
            blanks = FindBlanks(positionsOf1, board);

        for(List<Integer> L : blanks)
        {
            if(hasFlanking(L.get(0), L.get(1)))
                possibleMoves.add(L);
        }

        return moveList;
    }

    void setMoveListMinMax(ArrayList<Node> moves, int player)
    {
        for (Node n: moves)
        {
            n.setBoardScore(player);
        }
    }

    Node findAMove()
    {
        Node n = new Node(state, 0);

        if(possibleMoves.size() == 1)
        {
            for(List<Integer> L : possibleMoves)
            {
                n.setChosenMove(L);
                return n;
            }
        }

        //check corners first
        for(List<Integer> L : possibleMoves)
        {

            if(L.get(0) == 0 && L.get(1) == 0)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 0 && L.get(1) == 7)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 7 && L.get(1) == 0)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 7 && L.get(1) == 7)
            {
                n.setChosenMove(L);
                return n;
            }

            if(L.get(0) == 2 && L.get(1) == 2)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 2 && L.get(1) == 5)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 5 && L.get(1) == 2)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 5 && L.get(1) == 5)
            {
                n.setChosenMove(L);
                return n;
            }



        }

        //check center
        for(List<Integer> L : possibleMoves)
        {

            if((L.get(0) >= 2 && L.get(0) <= 5) && (L.get(1) >= 2 && L.get(1) <= 5))
            {
                n.setChosenMove(L);
                return n;
            }
        }

        //check "A" and squares
        for(List<Integer> L : possibleMoves)
        {

            if(L.get(0) == 0 && L.get(1) == 2)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 2 && L.get(1) == 0)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 0 && L.get(1) == 5)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 7 && L.get(1) == 2)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 2 && L.get(1) == 7)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 5 && L.get(1) == 0)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 7 && L.get(1) == 5)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 5 && L.get(1) == 7)
            {
                n.setChosenMove(L);
                return n;
            }
        }

        //check "B" squares
        for(List<Integer> L : possibleMoves)
        {
            if(L.get(0) == 0 && L.get(1) == 4)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 0 && L.get(1) == 3)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 7 && L.get(1) == 3)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 7 && L.get(1) == 4)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 3 && L.get(1) == 0)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 4 && L.get(1) == 0)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 3 && L.get(1) == 7)
            {
                n.setChosenMove(L);
                return n;
            }
            if(L.get(0) == 4 && L.get(1) == 7)
            {
                n.setChosenMove(L);
                return n;
            }
        }
        //avoid "X" and "C" squares if possible
        for(List<Integer> L : possibleMoves)
        {
            if((L.get(0) != 1 && L.get(1) != 1)
                && (L.get(0) != 6 && L.get(1) != 1)
                && (L.get(0) != 1 && L.get(1) != 6)
                && (L.get(0) != 6 && L.get(1) != 6)
                && (L.get(0) != 0 && L.get(1) != 1)
                && (L.get(0) != 1 && L.get(1) != 0)
                && (L.get(0) != 6 && L.get(1) != 0)
                && (L.get(0) != 7 && L.get(1) != 1)
                && (L.get(0) != 7 && L.get(1) != 6)
                && (L.get(0) != 6 && L.get(1) != 7)
                && (L.get(0) != 1 && L.get(1) != 6)
                && (L.get(0) != 1 && L.get(1) != 6))
            {
                n.setChosenMove(L);
                return n;
            }
        }

        //if no other moves just pick any
        for(List<Integer> L : possibleMoves)
        {
            n.setChosenMove(L);
            return n;
        }
        return null;
    }

    boolean hasFlanking(int row, int col)
    {
        int[][] board = state.getBoard();

        if(player == 1)
        {
            //check south
            if(row + 1 < 7)
            {
                int southRow = row;
                while (southRow < 7) {
                    southRow++;

                    if (board[southRow][col] == 1)
                        break;
                    if (board[southRow][col] == 2)
                    {
                        while(southRow < 8)
                        {

                            if (board[southRow][col] == 1)
                                return true;
                            if (board[southRow][col] == 0)
                                break;

                            southRow++;
                        }
                        if(southRow == 8)
                            southRow--;
                    }
                    if (board[southRow][col] == 0)
                        break;
                }
            }

            //check north
            if(row - 1 > 0)
            {
                int northRow = row;
                while(northRow > 0)
                {
                    northRow--;
                    if(board[northRow][col] == 1)
                        break;
                    if(board[northRow][col] == 2)
                    {
                        while(northRow > -1)
                        {

                            if(board[northRow][col] == 1)
                                return true;
                            if(board[northRow][col] == 0)
                                break;

                            northRow--;
                        }
                        if(northRow == -1)
                            northRow++;
                    }
                    if(board[northRow][col] == 0)
                        break;
                }
            }

            //check east
            if(col + 1 < 7)
            {
                int eastCol = col;
                while(eastCol < 7)
                {
                    eastCol++;
                    if(board[row][eastCol] == 1)
                        break;
                    if(board[row][eastCol] == 2)
                    {
                        while (eastCol < 8)
                        {

                            if(board[row][eastCol] == 1)
                                return true;
                            if(board[row][eastCol] == 0)
                                break;

                            eastCol++;
                        }
                        if(eastCol == 8)
                            eastCol--;
                    }
                    if(board[row][eastCol] == 0)
                        break;
                }
            }

            //check west
            if(col - 1 > 0)
            {
                int westCol = col;
                while(westCol > 0)
                {
                    westCol--;
                    if(board[row][col-1] == 1)
                        break;
                    if(board[row][col-1] == 2)
                    {
                        while(westCol > -1)
                        {

                            if(board[row][westCol] == 1)
                                return true;
                            if(board[row][westCol] == 0)
                                break;

                            westCol--;
                        }
                        if(westCol == -1)
                            westCol++;
                    }
                    if(board[row][westCol] == 0)
                        break;
                }
            }

            //check northwest
            if(row - 1 > 0 && col -1 > 0)
            {
                int westCol = col;
                int northRow = row;
                while(westCol > 0 && northRow > 0)
                {
                    westCol--;
                    northRow--;

                    if(board[row-1][col-1] == 1)
                        break;
                    if(board[row-1][col-1] == 2)
                    {
                        while (westCol > -1 && northRow > -1)
                        {

                            if(board[northRow][westCol] == 1)
                                return true;
                            if(board[northRow][westCol] == 0)
                                break;

                            westCol--;
                            northRow--;
                        }
                        if(westCol == -1)
                            westCol++;
                        if(northRow == -1)
                            northRow++;

                    }
                    if(board[northRow][westCol] == 0)
                        break;
                }
            }

            //check northeast
            if(row -1 > 0 && col + 1 < 7)
            {
                int eastCol = col;
                int northRow = row;
                while(eastCol < 7 && northRow > 0)
                {
                    eastCol++;
                    northRow--;
                    if(board[row-1][col+1] == 1)
                        break;
                    if(board[row-1][col+1] == 2)
                    {
                        while (eastCol < 8 && northRow > -1)
                        {

                            if(board[northRow][eastCol] == 1)
                                return true;
                            if(board[northRow][eastCol] == 0)
                                break;

                            eastCol++;
                            northRow--;
                        }
                        if(eastCol == 8)
                            eastCol--;
                        if(northRow == -1)
                            northRow++;
                    }
                    if(board[northRow][eastCol] == 0)
                        break;
                }
            }

            //check southwest
            if(row + 1 < 7 && col - 1 > 0)
            {
                int westCol = col;
                int southRow = row;
                while(westCol > 0 && southRow < 7)
                {
                    westCol--;
                    southRow++;
                    if(board[row+1][col-1] == 1)
                        break;
                    if(board[row+1][col-1] == 2)
                    {
                        while(westCol > -1 && southRow < 8)
                        {

                            if(board[southRow][westCol] == 1)
                                return true;
                            if(board[southRow][westCol] == 0)
                                break;

                            westCol--;
                            southRow++;
                        }

                        if(westCol == -1)
                            westCol++;
                        if(southRow == 8)
                            southRow--;
                    }
                    if(board[southRow][westCol] == 0)
                        break;
                }
            }

            //check southeast
            if(row + 1 < 7 && col + 1 < 7)
            {
                int eastCol = col;
                int southRow = row;
                while(eastCol < 7 && southRow < 7)
                {
                    eastCol++;
                    southRow++;
                    if(board[row+1][col+1] == 1)
                        break;
                    if(board[row+1][col+1] == 2)
                    {
                        while(eastCol < 8 && southRow < 8)
                        {

                            if(board[southRow][eastCol] == 1)
                                return true;
                            if(board[southRow][eastCol] == 0)
                                break;

                            eastCol++;
                            southRow++;
                        }
                        if(eastCol == 8)
                            eastCol--;
                        if(southRow == 8)
                            southRow--;
                    }
                    if(board[southRow][eastCol] == 0)
                        break;
                }
            }
        }

        if(player == 2) {

            //check south
            if(row + 1 < 7)
            {
                int southRow = row;
                while (southRow < 7)
                {
                    southRow++;
                    if (board[row + 1][col] == 1)
                    {
                        while(southRow < 8)
                        {
                            if (board[southRow][col] == 2)
                                return true;
                            if (board[southRow][col] == 0)
                                break;

                            southRow++;
                        }
                        if(southRow == 8)
                            southRow--;
                    }
                    if (board[southRow][col] == 0)
                        break;
                }
            }

            //check north
            if(row - 1 > 0)
             {
                int northRow = row;
                while (northRow > 0) {
                    northRow--;
                    if (board[row - 1][col] == 1)
                    {
                        while(northRow > -1)
                        {
                            if (board[northRow][col] == 2)
                                return true;
                            if (board[northRow][col] == 0)
                                break;

                            northRow--;
                        }

                        if(northRow == -1)
                            northRow++;
                    }
                    if (board[northRow][col] == 0)
                        break;
                }
            }

            //check east
            if(col + 1 < 7)
            {
                int eastCol = col;
                while (eastCol < 7) {
                    eastCol++;
                    if (board[row][col + 1] == 1)
                    {
                        while(eastCol < 8)
                        {
                            if (board[row][eastCol] == 2)
                                return true;
                            if (board[row][eastCol] == 0)
                                break;

                            eastCol++;
                        }
                        if(eastCol == 8)
                            eastCol--;
                    }
                    if (board[row][eastCol] == 0)
                        break;
                }
            }

            //check west
            if(col - 1 > 0)
            {
                int westCol = col;
                while (westCol >0) {
                    westCol--;
                    if (board[row][col - 1] == 1)
                    {
                        while(westCol > -1)
                        {
                            if (board[row][westCol] == 2)
                                return true;
                            if (board[row][westCol] == 0)
                                break;

                            westCol--;
                        }
                        if(westCol == -1)
                            westCol++;
                    }
                    if (board[row][westCol] == 0)
                        break;
                }
            }

            //check northwest
            if(row - 1 > 0 && col -1 > 0)
            {
                int westCol = col;
                int northRow = row;
                while (westCol > 0 && northRow > 0)
                {
                    westCol--;
                    northRow--;
                    if (board[row - 1][col - 1] == 1)
                    {
                        while (westCol > -1 && northRow > -1)
                        {
                            if (board[northRow][westCol] == 2)
                                return true;
                            if (board[northRow][westCol] == 0)
                                break;

                            westCol--;
                            northRow--;
                        }

                        if(westCol == -1)
                            westCol++;
                        if(northRow == -1)
                            northRow++;

                    }
                    if (board[northRow][westCol] == 0)
                        break;
                }
            }

            //check northeast
            if(row -1 > 0 && col + 1 < 7)
            {
                int eastCol = col;
                int northRow = row;
                while (eastCol < 7 && northRow > 0)
                {
                    eastCol++;
                    northRow--;
                    if (board[row - 1][col + 1] == 1)
                    {
                        while (eastCol < 8 && northRow > -1)
                        {
                            if (board[northRow][eastCol] == 2)
                                return true;
                            if (board[northRow][eastCol] == 0)
                                break;

                            eastCol++;
                            northRow--;
                        }
                        if(eastCol == 8)
                            eastCol--;
                        if(northRow == -1)
                            northRow++;
                    }

                    if (board[northRow][eastCol] == 0)
                        break;
                }
            }

            //check southwest
            if(row + 1 < 7 && col - 1 > 0)
            {

                int westCol = col;
                int southRow = row;
                while (westCol > 0 && southRow < 7)
                {
                    westCol--;
                    southRow++;
                    if (board[row + 1][col - 1] == 1)
                    {
                        while (westCol > -1 && southRow < 8)
                        {
                            if (board[southRow][westCol] == 2)
                                return true;
                            if (board[southRow][westCol] == 0)
                                break;

                            westCol--;
                            southRow++;
                        }
                        if(westCol == -1)
                            westCol++;
                        if (southRow == 8)
                            southRow--;
                    }
                    if (board[southRow][westCol] == 0)
                        break;
                }
            }

            //check southeast
            if(row + 1 < 7 && col + 1 < 7)
             {
                int eastCol = col;
                int southRow = row;
                while (eastCol < 7 && southRow < 7)
                {
                    eastCol++;
                    southRow++;
                    if (board[row + 1][col + 1] == 1)
                    {
                        while (eastCol < 8 && southRow < 8)
                        {
                            if (board[southRow][eastCol] == 2)
                                return true;
                            if (board[southRow][eastCol] == 0)
                                break;

                            eastCol++;
                            southRow++;
                        }
                        if(eastCol == 8)
                            eastCol--;
                        if (southRow == 8)
                            southRow--;
                    }
                    if (board[southRow][eastCol] == 0)
                        break;

                }
            }
        }

        return false;

    }

    private ArrayList<List<Integer>> FindBlanks(ArrayList<List<Integer>> positions, int[][] board) {

        ArrayList<List<Integer>> blanks = new ArrayList<>();

        for(List<Integer> L : positions) {
            int row = L.get(0);
            int col = L.get(1);

            if(row + 1 <= 7)
                if (board[row + 1][col] == 0) {
                    if(!blanks.contains(Arrays.asList(row+1, col)))
                        blanks.add(Arrays.asList(row+1, col));
                }

            //check north
            if(row - 1 >= 0)
                if (board[row - 1][col] == 0) {
                    if(!blanks.contains(Arrays.asList(row-1, col)))
                        blanks.add(Arrays.asList(row-1, col));

                }
            //check east
            if(col + 1 <= 7)
                if (board[row][col + 1] == 0) {
                    if(!blanks.contains(Arrays.asList(row, col+1)))
                        blanks.add(Arrays.asList(row, col+1));
                }
            //check west
            if(col - 1 >= 0)
                if (board[row][col - 1] == 0) {
                    if(!blanks.contains(Arrays.asList(row, col-1)))
                        blanks.add(Arrays.asList(row, col-1));
                }
            //check northwest
            if(row - 1 >= 0 && col - 1 >= 0)
                if (board[row - 1][col - 1] == 0) {
                    if(!blanks.contains(Arrays.asList(row-1, col-1)))
                        blanks.add(Arrays.asList(row-1, col-1));
                }
            //check northeast
            if(row - 1 >= 0 && col + 1 <= 7)
                if (board[row - 1][col + 1] == 0) {
                    if(!blanks.contains(Arrays.asList(row-1, col+1)))
                        blanks.add(Arrays.asList(row-1, col+1));
                }
            //check southwest
            if(row + 1 <=  7 && col - 1 >= 0)
                if (board[row + 1][col - 1] == 0) {
                    if(!blanks.contains(Arrays.asList(row+1, col-1)))
                        blanks.add(Arrays.asList(row+1, col-1));
                }
            //check southeast
            if(row + 1 <= 7 && col + 1 <= 7)
                if (board[row + 1][col + 1] == 0) {
                    if(!blanks.contains(Arrays.asList(row+1, col+1)))
                        blanks.add(Arrays.asList(row+1, col+1));
                }
        }

        return blanks;
    }
}
