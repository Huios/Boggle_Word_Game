import java.util.ArrayList;
import java.util.List;


public class BoardFirstAutoPlayer extends AbstractAutoPlayer {

	@Override
	public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {

		ArrayList<BoardCell> list = new ArrayList<BoardCell>();
		String build = new String();
		clear();
		for(int r = 0; r < board.size(); r++){ 
			for(int c = 0; c < board.size(); c++){		
				canFindWord3(lex, board, r, c, list, build, minLength);

			}
		}

	}




	private void canFindWord3(ILexicon lex, BoggleBoard board, int r, int c, ArrayList<BoardCell> list, String build, int minLength) {	//  Helper method to find word

		if(r < 0 ||  r >= board.size() || c < 0 || c >= board.size()) //{  //checking if we are on the board, and if the cell is legal and hasn't been used yet
			return; 

		BoardCell cell = new BoardCell(r, c);
		String face = board.getFace(r, c);
		if(list.contains(cell)){
			return;
		}
		build += face;

		list.add(cell);
		if(lex.wordStatus(build) == LexStatus.WORD)
			add(build.toString());
		
		else if(lex.wordStatus(build) == LexStatus.NOT_WORD){
			list.remove(cell);
			return;
		}

		int [] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1};
		int [] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1};


		for(int k = 0; k < rdelta.length; k++) {

			canFindWord3(lex, board, r+rdelta[k], c+cdelta[k], list, build, minLength);	
}
		list.remove(cell);
	}

}
