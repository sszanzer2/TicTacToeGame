package ss;

public class HumanPlayer implements Player{
    private char symbol;
    private int score;
    private String name;

    public HumanPlayer(char symbol, String name) {
        this.symbol = symbol;
        this.score = 0;
        this.name = name;
    }
    @Override
    public char getSymbol() {
        return symbol;
    }
    @Override
    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
	public void setSymbol(char c) {
		this.symbol= c;
		
	}
}
