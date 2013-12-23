import java.util.ArrayList;
import java.util.List;


public class GoodWordOnBoardFinder implements IWordOnBoardFinder {
	BoggleBoard board;

	@Override
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {

		List<BoardCell> list = new ArrayList<BoardCell>(); 

		for(int r = 0; r < board.size(); r++){ 
			for(int c = 0; c < board.size(); c++){
				if(canFormWord(board, r, c, word,  list)) 
					return list;
			}
		}
		return list;
	}



	int [] rdelta = { -1, -1, -1, 0, 0, 1, 1, 1};
	int [] cdelta = { -1, 0, 1, -1, 1, -1, 0, 1};
	
	
	

	private boolean canFormWord(BoggleBoard board, int r, int c, String word, List<BoardCell> list) {	//  Helper method to find word

		if(word.length() == 0)
			return true;
		
		if(r < 0 || c < 0 || r >= board.size() || c >= board.size() )
			return false;
		BoardCell cell = new BoardCell(r, c);

		String face = board.getFace(r, c);

		if(list.contains(cell))
			return false;
		
		



		//		if(face.toLowerCase().equals("qu")){
		//			if(n < word.length() - 1 && !(word.substring(n, n+2)).equals("qu"))
		//				return false;
		//		}

		//		if(!face.toLowerCase().equals(word.substring(n, n + 1)))
		//			return false;
		int qCheck = 0;

		if(face.toLowerCase().equals("qu"))
			qCheck++;
		
		if(face.toLowerCase().equals("qu") && word.length() == 1)
			return false;

		if(!face.equals(word.substring(0, 1 + qCheck)))
			return false;


		

		
		//		if(word.equals(face))
		//			return true;


		//
		//			if(n == word.length() - 1) {
		//				
		//			return true;
		//		}

		for(int k = 0; k < rdelta.length; k++) {

			list.add(cell);

			if(canFormWord(board, r + rdelta[k], c + cdelta[k], word.substring(1 + qCheck), list)) 
				return true;

			list.remove(cell);

		}

		return false;
	}
}

