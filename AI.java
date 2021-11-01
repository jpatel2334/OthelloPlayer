import java.util.Arrays;
import java.util.ListIterator;

public class AI {

	public AI() {
	}

	public int[] computeMove(GameState state) {
		System.out.println("AI returning canned move for game state - " + state);
		/* This is where you need to make changes! This hard-coded move will cause an error
		 * as it won't be valid after the first move. You need to figure out what the valid
		 * moves are based on the board state, and then return one of those.
		 */

		MinMax minMax = new MinMax(state);
		/*
		the class is named minmax but i could not find a good way to score a set board configuration
		The find move checks against a tier list of good to bad positions 
		 */

		Node n = minMax.findAMove();


		return new int[] {n.getChosenMove().get(0), n.getChosenMove().get(1)};
	}


}
