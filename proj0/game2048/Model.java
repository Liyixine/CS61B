package game2048;

import org.hamcrest.core.Is;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;
    /** True iff game is ended. */
    private boolean gameOver;
    /* i do not know how to implement the change method in the tile function,so i uses the global variable*/
    private boolean needChange = false;
    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
        gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        int size = rawValues.length;
        board = new Board(rawValues, score);
        this.score = score;
        this.maxScore = maxScore;
        this.gameOver = gameOver;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     *  */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (gameOver) {
            maxScore = Math.max(score, maxScore);
        }
        return gameOver;
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        gameOver = false;
        board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        board.setViewingPerspective(Side.NORTH);
        board.setViewingPerspective(side);
        int size = board.size();
        int scorechange = 0;
        for (int i = 0; i < size; i++) {
            scorechange = MoveOneRow(i) + scorechange;
        }
        changed = needChange;
        score += scorechange;
        board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }
    /** implement the move of one row and returns the scores */
    public int MoveOneRow (int i){
        int scorechange = 0;
        int size = board.size();
        boolean[] IsMerge = new boolean[size];
        for (int j = size-2 ; j >= 0 ; j--) {
            scorechange += MoveOneTile(i,j,IsMerge);
        }
        return scorechange;

    }

    /** in the base that the front tile are used this method,the method can move the tile,returns the scores */
    public int MoveOneTile (int i,int j,boolean[] IsMerge){
        int size = board.size();
        int scorechange = 0;
        if(board.tile(i,j) == null){
            return 0;
        }
        Tile nowT = board.tile(i,j);
        int value = board.tile(i,j).value();
        int movestep = 0;
        for (int k = j+1; k < size; k++) {
            Tile t = board.tile(i,k);
            if(t == null){
                movestep += 1;
                continue;
            }
            if(t.value() == value && !IsMerge[k]){
                movestep += 1;
                scorechange += 2*value;
                IsMerge[k] = true;
                continue;
            }
            break;
        }
        if(!(movestep == 0)){
            needChange = true;
        }
        board.move(i,j+movestep,nowT);
        return scorechange;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        gameOver = checkGameOver(board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        int size = b.size();
        for (int i = 0;i < size;i++){
            for (int j = 0;j<size;j++ ){
                if(b.tile(i,j) == null){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        int size = b.size();
        for (int i = 0;i<size;i++){
            for (int j = 0;j<size;j++){
                if(b.tile(i,j) == null){
                    continue;
                }
                if(b.tile(i,j).value() == MAX_PIECE){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        int size = b.size();
        if (emptySpaceExists(b)){
            return  true;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(IsSameValueNear(b,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns whether the near tile in the board has same value */
    public static boolean IsSameValueNear (Board b,int i,int j){
        int value = b.tile(i,j).value();
        return  IsSameValueOne(b,i,j,i-1,j) || IsSameValueOne(b,i,j,i+1,j) || IsSameValueOne(b,i,j,i,j-1) || IsSameValueOne(b,i,j,i,j+1);
    }

    public static boolean IsSameValueOne (Board b,int i1,int j1,int i2,int j2){
        if(IsInborad(b,i1,j1)&&IsInborad(b,i2,j2)){
            if(b.tile(i1,j1) == null){
                return false;
            }
            if(b.tile(i2,j2) == null){
                return  false;
            }
            if(b.tile(i1,j1).value() == b.tile(i2,j2).value()){
                return  true;
            }
            return false;
        }
        return false;
    }

    /** Returns whether the index of i and j in the borad */
    public  static boolean IsInborad (Board b ,int i ,int j){
        int size = b.size();
        if((i>=0 && i<size) && (j>=0 && j<size)){
            return true;
        }
        return false;
    }

    @Override
     /** Returns the model as a string, used for debugging. */
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    /** Returns whether two models are equal. */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    /** Returns hash code of Model’s string. */
    public int hashCode() {
        return toString().hashCode();
    }
}
