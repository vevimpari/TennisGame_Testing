import static org.junit.Assert.*;

import org.junit.Test;

import jdk.nashorn.internal.ir.annotations.Ignore;

public class TennisGameTest {
	
// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Ignore
	public void testTennisGame_Start() {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);		
	}
	
	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();
		game.player2Scored();
		//Act
		String score = game.getScore();
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);		
	}
	
	@Test
	public void testTennisGame_ScoreShouldBeLove15forPlayer2() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		
		String score = game.getScore();
		assertEquals("Score is love - 15", "love - 15", score);	
	}
	
	@Test
	public void testTennisGame_ScoreShouldBe15LoveforPlayer1() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Score is 15 - love", "15 - love", score);	
	}
	
	@Test
	public void testTennisGame_ScoreShouldBe3015Player1() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		
		String score = game.getScore();
		assertEquals("Score is 30 - 15", "30 - 15", score);	
	}
	
	@Test
	public void testTennisGame_ScoreShouldBe4015Player1() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player2Scored();
		game.player1Scored();
		
		String score = game.getScore();
		assertEquals("Score is 40 - 15", "40 - 15", score);	
	}
	
	@Test
	public void testTennisGame_Player1ShouldWin() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		String score = game.getScore();
		
		assertEquals("Player1 wins staright", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2ShouldWin() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		String score = game.getScore();
		
		assertEquals("Player2 wins staright", "player2 wins", score);
	}
	
	@Test
	public void testTennisGame_Player1ShouldHaveAdvantage() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();

		String score = game.getScore();
		
		assertEquals("Player1 has advantage", "player1 has advantage", score);
		
	}
	
	@Test
	public void testTennisGame_Player1ShouldWinAfterAdvantage() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player1Scored();

		String score = game.getScore();
		
		assertEquals("Player1 has advantage", "player1 has advantage", score);
		
		game.player1Scored();

		score = game.getScore();
		
		assertEquals("Player1 wins after advantage", "player1 wins", score);
	}
	
	@Test
	public void testTennisGame_Player2ShouldHaveAdvantage() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player2Scored();

		String score = game.getScore();
		
		assertEquals("Player2 has advantage", "player2 has advantage", score);
		
	}
	
	@Test
	public void testTennisGame_Player2ShouldWinAfterAdvantage() throws TennisGameException {
		
		TennisGame game = new TennisGame();
		
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		
		game.player2Scored();

		String score = game.getScore();
		
		assertEquals("Player2 has advantage", "player2 has advantage", score);
		
		game.player2Scored();

		score = game.getScore();
		
		assertEquals("Player2 wins after advantage", "player2 wins", score);
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		//Act
		// This statement should cause an exception
		game.player1Scored();
	}
	
	@Test (expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		//Arrange
		TennisGame game = new TennisGame();
		//Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		//Act
		// This statement should cause an exception
		game.player2Scored();
	}
}
