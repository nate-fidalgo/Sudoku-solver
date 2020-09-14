import java.util.Stack;


public class SudokuSolver {

	class SudokuState {
		
		public SudokuState( int cellnum , int board[][] )
		{			
		   for( int i = 0 ; i < 9 ; i++ )
		   {
			 this.possiblity[i] = true ;			   
		   }			
		   this.cellnum = cellnum ;
		   this.board = board ;
		}		
		
		public int[] getPossiblity() {
			
			int num = numberofchoices();
			if( num == 0 )
				return null ;
			int pchoices[] = new int[num] ;
			int j = 0 ;
			for(int i = 0 ; i < 9 ; i++)
			{
				if(possiblity[i] == true)
				{
					pchoices[j] = i+1 ;
					j++ ;
				}
			}
			
			return pchoices ;
		}

		
		public int numberofchoices()
		{
			int j = 0 ;
			for( int i = 0 ; i < 9 ; i++ )
			{
				if( possiblity[i] == true )
				{
					j++ ;
				}				
			}
			return j ;
		}
		
		public void setPossiblity(boolean choicestate , int choice) {
			this.possiblity[choice-1] = choicestate ;
		}

		public int getcellnum() 
		{
			return this.cellnum ;
		}
		
		public void setBoardCell( int val , int cellnum )
		{
		   int row = (cellnum-1)/9 ;
		   //if( row == -1 )
		//	   row = 0 ;
		   int col = cellnum%9 - 1;
		   if( col == -1 )
		       this.board[row][8] = val ;
		   else
			   this.board[row][col] = val ;
		}
		
		public int getBoardCell( int cellnum )
		{
			int row = (cellnum-1)/9  ;
			//if( row == -1 )
			//	row = 0 ;
			int col = (cellnum%9) - 1 ;
			if( col == -1 )
				col = 8 ;
			return this.board[row][col] ;
		}
		
		public int[][] getBoard()
		{
			return this.board ;
		}
		
		private boolean possiblity[] = new boolean[9] ;
		private int board[][] = null ;
		private int cellnum = -1 ;
		
	}
	
	public void calculatePossiblities( SudokuState state , int board[][] )
	{
		int cellnum = state.getcellnum() ;
		int row = (cellnum - 1) / board.length ;
	//	if( row == -1)
		//	row = 0 ;
		int col = ( cellnum % board.length ) - 1 ;
		if( col == -1 )
			col = 8 ;
		
		rowPossiblities( state , board , row ) ;
		colPossiblities( state , board , col ) ;
		subPossiblities( state , board , row , col ) ;
		
	}
	
	public void rowPossiblities( SudokuState state , int board[][] , int row ) 
	{
		for( int i = 0 ; i < board.length ; i++ )
		{
			if( board[row][i] != -1 )
			state.setPossiblity(false, board[row][i] ) ;
		}
		
	}
	
	public void colPossiblities( SudokuState state , int board[][] , int col ) 
	{
		for( int i = 0 ; i < board.length ; i++ )
		{
			if( board[i][col] != -1 )
			state.setPossiblity(false, board[i][col] ) ;
		}
		
	}
	
	public void subPossiblities( SudokuState state , int board[][] , int row , int col ) 
	{
		if( row < 3 && col < 3 )
		{
			for( int i = 0 ; i < 3 ; i++ )
				for( int j = 0 ; j < 3 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			return ;
					
		}
		
		if( (row > 2 && row < 6) && col < 3 )
		{
			for( int i = 3 ; i < 6 ; i++ )
				for( int j = 0 ; j < 3 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			return ;
		}
		
		if( row > 5 && col < 3 )
		{
			for( int i = 6 ; i < 9 ; i++ )
				for( int j = 0 ; j < 3 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			return ;
			
		}
		
		if( (col > 2 && col < 6) && row < 3 )
		{
			for( int i = 0 ; i < 3 ; i++ )
				for( int j = 3 ; j < 6 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			return ;
		}
		
		if( col > 5 && (row > 2 && row < 6) )
		{
			for( int i = 3 ; i < 6 ; i++ )
				for( int j = 6 ; j < 9 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			return ;
		}
		
		if( col > 5 && row > 5 )
		{
			for( int i = 6 ; i < 9 ; i++ )
				for( int j = 6 ; j < 9 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			
			return ;
		}
		
		if( (col > 2 && col < 6) && (row > 2 && row < 6) )
		{
			for( int i = 3 ; i < 6 ; i++ )
				for( int j = 3 ; j < 6 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			return ;
		}
		
		if( (col > 2 && col < 6) && row > 5 )
		{
			for( int i = 6 ; i < 9 ; i++ )
				for( int j = 3 ; j < 6 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			return ;
		}
		
		if( col > 5 && row < 3 )
		{
			for( int i = 0 ; i < 3 ; i++ )
				for( int j = 6 ; j < 9 ; j++ )
					if( board[i][j] != -1 )
					state.setPossiblity(false , board[i][j]) ;
			
			return ;
		}
		
		
		
	}
	
	public int nextemptycell(int board[][])
	{
		for( int i = 0 ; i < board.length ; i++ )
		   for( int j = 0 ; j < board.length ; j++ )
				if(board[i][j] == -1)
					return 9*i + j + 1 ;
		return -1;		
	}
	
	public int[][] solveSudoku( int board[][] )
	{
		Stack<SudokuState> statestack = new Stack<SudokuState>() ;
		SudokuState svar = new SudokuState(nextemptycell(board), board) ;
		while( true )
		{
			calculatePossiblities(svar , board) ;
		   	if( svar.numberofchoices() == 0 )
		   	{
		   		svar = statestack.pop();
		   		svar.setPossiblity(false , svar.getBoardCell(svar.getcellnum()) ) ;
		   		svar.setBoardCell( -1 , svar.getcellnum()) ;
		   		board = svar.getBoard() ;
		   	}
		   	else
		   	{
		   	svar.setBoardCell(svar.getPossiblity()[0] , svar.getcellnum()) ;
		   //	svar.setPossiblity(false, svar.getPossiblity()[0] ) ;
		   	statestack.push(svar) ;
			board = svar.getBoard() ;
		//	printboard(board) ;
			//System.out.println("empty cell = " + nextemptycell(board)) ;
			if(	nextemptycell(board) == -1 )
				break ;
				
			svar = new SudokuState(nextemptycell(board), board) ;
			
		    }
		}
			
		return board ;
	}
	
	public void printboard(int[][] board) {
		// TODO Auto-generated method stub
		for( int i = 0 ; i < board.length ; i++ )
			{ System.out.println();
			  for( int j = 0 ; j < board.length ; j++ )
                          {
			   if( board[i][j] == -1 )
			   System.out.print( " " + (char)0x25A1 );
			   else
                           System.out.print( " " + board[i][j]);
			
 			  }
		        }

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int board[][] = {{5,6,-1,-1,-1,-1,-1,8,-1} , {-1,-1,1,-1,8,-1,9,-1,-1} , {-1,8,-1,-1,-1,5,2,6,1} , {-1,-1,-1,6,4,-1,-1,-1,-1} , {-1,3,-1,8,-1,2,-1,5,-1},{-1,-1,-1,-1,5,1,-1,-1,-1} , {1,4,6,5,-1,-1,-1,9,-1},{-1,-1,7,-1,2,-1,6,-1,-1},{-1,2,-1,-1,-1,-1,-1,1,4} };
		
		//int board[][] = {{-1,2,-1,-1,9,-1,-1,-1,-1},{-1,6,5,8,2,-1,-1,-1,-1},{3,-1,-1,-1,5,-1,-1,-1,7},{-1,3,8,-1,-1,-1,-1,9,5},{6,7,-1,9,-1,5,-1,2,8},{5,1,-1,-1,-1,-1,6,7,-1},{9,-1,-1,-1,8,-1,-1,-1,1},{-1,-1,-1,-1,6,9,8,5,-1},{-1,-1,-1,-1,1,-1,-1,4,-1}} ;
//		int board[][] = {{-1,-1,-1,8,-1,-1,6,-1,-1},{-1,-1,-1,-1,5,-1,-1,-1,7},{5,9,3,-1,-1,-1,1,2,-1},{-1,5,9,-1,-1,-1,-1,-1,2},{4,-1,-1,7,-1,1,-1,-1,9},{7,-1,-1,-1,-1,-1,8,1,-1},{-1,7,6,-1,-1,-1,9,3,1},{9,-1,-1,-1,3,-1,-1,-1,-1},{-1,-1,4,-1,-1,9,-1,-1,-1}} ;

int board[][] = {{8,-1,-1,-1,-1,-1,-1,-1,-1},{-1,-1,3,6,-1,-1,-1,-1,-1},{-1,7,-1,-1,9,-1,2,-1,-1},{-1,5,-1,-1,-1,7,-1,-1,-1},{-1,-1,-1,-1,4,5,7,-1,-1},{-1,-1,-1,1,-1,-1,-1,3,-1},{-1,-1,1,-1,-1,-1,-1,6,8},{-1,-1,8,5,-1,-1,-1,1,-1},{-1,9,-1,-1,-1,-1,4,-1,-1}} ;



		SudokuSolver ssolve = new SudokuSolver() ;
		ssolve.printboard(board) ;
		int sboard[][] = ssolve.solveSudoku( board ) ;
		System.out.println("\n\n solved soduko game \n\n") ;
		ssolve.printboard(sboard) ;
		System.out.println();
		System.exit(0) ;
	}

}
