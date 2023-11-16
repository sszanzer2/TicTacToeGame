package ss;

public interface Board {
	void display();
    boolean markPosition(int row, int col, char symbol);
    boolean checkWin(char symbol);
    boolean checkDraw();

}
