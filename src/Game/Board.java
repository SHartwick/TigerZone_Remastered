package Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import Game.Tile;
import Game.TerrainType;

public class Board {
	// Boundaries of the Board
	public static int CENTER_CELL = 77;
	public static int MAX_ROWS = CENTER_CELL * 2 - 1;
	public static int MAX_COLS = CENTER_CELL * 2 - 1;
	
	private int topBound = 77;
	private int bottomBound = 77;
	private int leftBound = 77;
	private int rightBound = 77;

	public Deck deck = new Deck();
	private Tile[][] board = new Tile[MAX_ROWS][MAX_COLS];
	private List<Tile> placedTiles = new ArrayList<Tile>();

	// Is this needed?
	private boolean startTile = true;

	/*******************************************/
	/******* Getter and Setter Functions *******/
	/*******************************************/

	public int getTopBound() {
		return this.topBound;
	}

	public int getBottomBound() {
		return this.bottomBound;
	}

	public int getLeftBound() {
		return this.leftBound;
	}

	public int getRightBound() {
		return this.rightBound;
	}

	public void setTopBound(int upperBound) {
		this.topBound = upperBound;
	}

	public void setBottomBound(int lowerBound) {
		this.bottomBound = lowerBound;
	}

	public void setLeftBound(int leftBound) {
		this.leftBound = leftBound;
	}

	public void setRightBound(int rightBound) {
		this.rightBound = rightBound;
	}


	/*********************************/
	/******* Testing Functions *******/
	/*********************************/

	public int printBoard() {
		
		return 1;
	}

	public List<Tile> getNeighbors(int x, int y) {
		List<Tile> n = new ArrayList<Tile>();

		if (y > 0) {
			if (board[x][y - 1] != null) {
				n.add(board[x][y - 1]);
			}
		}

		if (y < MAX_COLS) {
			if (board[x][y + 1] != null) {
				n.add(board[x][y + 1]);
			}
		}

		if (x > 0) {
			if (board[x - 1][y] != null) {
				n.add(board[x - 1][y]);
			}
		}

		if (x < MAX_ROWS) {
			if (board[x + 1][y] != null) {
				n.add(board[x + 1][y]);
			}
		}

		return n;
	}

	public boolean isValid(int x, int y, Tile tile) {

		if (board[x][y] != null) {
			return false;
		}
		
		if (placedTiles.isEmpty()) {
			return true;
		}

		else {
			List<Tile> nbors = getNeighbors(x, y);
			if (nbors.isEmpty()) {
				return false;
			}
			
			else{
				
				boolean valid = true;
				// Iterate through all its potential neighbors
				for (Tile neighbor : nbors) {

					// Check if neighbor is in same row
					if (neighbor.getRow() == x) {
						if (neighbor.getCol() > y) {
							// This is right neighbor
							if (neighbor.getLeftEdge() != tile.getRightEdge()) {
								valid = false;
								// no need to continue checking other
								// neighbors
								break;
							}
						} else {
							// This is left neighbor
							if (neighbor.getRightEdge() != tile.getLeftEdge()) {
								valid = false;
								break;
							}
						}
					}

					// If not in the same row, it must be in the same column
					if (neighbor.getCol() == y) {
						if (neighbor.getRow() > x) {
							// This is bottom neighbor
							if (neighbor.getTopEdge() != tile.getBottomEdge()) {
								valid = false;
								break;
							}

						} else {
							// This is top neighbor
							if (neighbor.getBottomEdge() != tile.getTopEdge()) {
								valid = false;
								break;
							}
						}
					}

				} // Iterate thru neighbors
				return valid;
			}

		}
	}

	public Tile rotateTile(Tile tile, int degrees) {
		Tile rotateTile = new Tile(tile.getTilePortionType());

		if (degrees != 0) {
			TerrainType[] rotateArr = new TerrainType[9];
			if (degrees == 90) {
				rotateArr[0] = tile.getTilePortionType()[2];
				rotateArr[1] = tile.getTilePortionType()[5];
				rotateArr[2] = tile.getTilePortionType()[8];
				rotateArr[3] = tile.getTilePortionType()[1];
				rotateArr[4] = tile.getTilePortionType()[4];
				rotateArr[5] = tile.getTilePortionType()[7];
				rotateArr[6] = tile.getTilePortionType()[0];
				rotateArr[7] = tile.getTilePortionType()[3];
				rotateArr[8] = tile.getTilePortionType()[6];
			}
			if (degrees == 180) {
				for (int i = 0; i < 9; i++) {
					rotateArr[i] = tile.getTilePortionType()[8 - i];
				}
			}
			if (degrees == 270) {
				rotateArr[0] = tile.getTilePortionType()[6];
				rotateArr[1] = tile.getTilePortionType()[3];
				rotateArr[2] = tile.getTilePortionType()[0];
				rotateArr[3] = tile.getTilePortionType()[7];
				rotateArr[4] = tile.getTilePortionType()[4];
				rotateArr[5] = tile.getTilePortionType()[1];
				rotateArr[6] = tile.getTilePortionType()[8];
				rotateArr[7] = tile.getTilePortionType()[5];
				rotateArr[8] = tile.getTilePortionType()[2];
			}
			rotateTile.setTilePortionType(rotateArr);
		}
		return rotateTile;
	}

	public List<Integer> getValidOrients(int x, int y, Tile tile) {
		List<Integer> validOrients = new ArrayList<Integer>();

		List<Tile> nbors = getNeighbors(x, y);

		// Add possible orientation to list
		validOrients.add(0);
		validOrients.add(90);
		validOrients.add(180);
		validOrients.add(270);

		// For each neighboring tile, check if sides match for each orientation
		// If not, remove from validOrients
		for (int i = 0; i < nbors.size(); i++) {
			Tile nTile = nbors.get(i);

			if (validOrients.isEmpty()) {
				break;
			}
			// Check if its in same row
			if (nTile.getRow() == x) {
				if (nTile.getCol() > y) {
					// This is right neighbor
					if (nTile.getLeftEdge() != tile.getRightEdge()) {
						if (validOrients.contains(0)) {
							validOrients.remove(Integer.valueOf(0));
						}
					}
					if (nTile.getLeftEdge() != tile.getBottomEdge()) {
						if (validOrients.contains(90)) {
							validOrients.remove(Integer.valueOf(90));
						}
					}
					if (nTile.getLeftEdge() != tile.getLeftEdge()) {
						if (validOrients.contains(180)) {
							validOrients.remove(Integer.valueOf(180));
						}
					}
					if (nTile.getLeftEdge() != tile.getTopEdge()) {
						if (validOrients.contains(270)) {
							validOrients.remove(Integer.valueOf(270));
						}
					}
				} else {
					// This is left neighbor
					if (nTile.getRightEdge() != tile.getLeftEdge()) {
						if (validOrients.contains(0)) {
							validOrients.remove(Integer.valueOf(0));
						}
					}
					if (nTile.getRightEdge() != tile.getTopEdge()) {
						if (validOrients.contains(90)) {
							validOrients.remove(Integer.valueOf(90));
						}
					}
					if (nTile.getRightEdge() != tile.getRightEdge()) {
						if (validOrients.contains(180)) {
							validOrients.remove(Integer.valueOf(180));
						}
					}
					if (nTile.getRightEdge() != tile.getBottomEdge()) {
						if (validOrients.contains(270)) {
							validOrients.remove(Integer.valueOf(270));
						}
					}
				}
			}

			if (nTile.getCol() == y) {
				if (nTile.getRow() > x) {
					// This is bottom neighbor
					if (nTile.getTopEdge() != tile.getBottomEdge()) {
						if (validOrients.contains(0)) {
							validOrients.remove(Integer.valueOf(0));
						}
					}
					if (nTile.getTopEdge() != tile.getLeftEdge()) {
						if (validOrients.contains(90)) {
							validOrients.remove(Integer.valueOf(90));
						}
					}
					if (nTile.getTopEdge() != tile.getTopEdge()) {
						if (validOrients.contains(180)) {
							validOrients.remove(Integer.valueOf(180));
						}
					}
					if (nTile.getTopEdge() != tile.getRightEdge()) {
						if (validOrients.contains(270)) {
							validOrients.remove(Integer.valueOf(270));
						}
					}
				} else {
					// This is top neighbor
					if (nTile.getBottomEdge() != tile.getTopEdge()) {
						if (validOrients.contains(0)) {
							validOrients.remove(Integer.valueOf(0));
						}
					}
					if (nTile.getBottomEdge() != tile.getRightEdge()) {
						if (validOrients.contains(90)) {
							validOrients.remove(Integer.valueOf(90));
						}
					}
					if (nTile.getBottomEdge() != tile.getBottomEdge()) {
						if (validOrients.contains(180)) {
							validOrients.remove(Integer.valueOf(180));
						}
					}
					if (nTile.getBottomEdge() != tile.getLeftEdge()) {
						if (validOrients.contains(270)) {
							validOrients.remove(Integer.valueOf(270));
						}
					}
				}
			}
		}

		return validOrients;
	}

	public List<Tile> getPossibleMoves(Tile tile) {
		List<Tile> possibleMoves = new ArrayList<Tile>();
		for (int i = getTopBound(); i <= getBottomBound(); i++) {
			for (int j = getLeftBound(); j <= getRightBound(); j++) {
				if (isValid(i, j, tile)) {
					tile.setRow(i);
					tile.setCol(j);
					List<Integer> validOrients = getValidOrients(i, j, tile);
					for (int k = 0; k < validOrients.size(); k++) {
						possibleMoves.add(rotateTile(tile, validOrients.get(k)));

					}
				}
			}
		}

		return possibleMoves;
	}
	
	public Tile[][] getBoard() {
		return this.board;
	}

	public List<Tile> getPlacedTile() {
		return this.placedTiles;
	}

	public Tile getTile(int x, int y) {
		if (x >= 0 && x < MAX_ROWS && y >= 0 && y < MAX_COLS) {
			return board[x][y];
		}
		return null;
	}

	public void removeTile(int x, int y, Tile tile) {
		board[x][y] = null;
		placedTiles.remove(tile);
	}
	
	/********************************/
	/******* Player Functions *******/
	/********************************/

	public int placeTile(int x, int y, int rotation, Tile tile) {
		if(rotation != 0) {
			TerrainType[] rotateArr = new TerrainType[9];
			if (rotation == 90) {
				rotateArr[0] = tile.getTilePortionType()[2];
				rotateArr[1] = tile.getTilePortionType()[5];
				rotateArr[2] = tile.getTilePortionType()[8];
				rotateArr[3] = tile.getTilePortionType()[1];
				rotateArr[4] = tile.getTilePortionType()[4];
				rotateArr[5] = tile.getTilePortionType()[7];
				rotateArr[6] = tile.getTilePortionType()[0];
				rotateArr[7] = tile.getTilePortionType()[3];
				rotateArr[8] = tile.getTilePortionType()[6];
			}
			if (rotation == 180) {
				for (int i = 0; i < 9; i++) {
					rotateArr[i] = tile.getTilePortionType()[8 - i];
				}
			}
			if (rotation == 270) {
				rotateArr[0] = tile.getTilePortionType()[6];
				rotateArr[1] = tile.getTilePortionType()[3];
				rotateArr[2] = tile.getTilePortionType()[0];
				rotateArr[3] = tile.getTilePortionType()[7];
				rotateArr[4] = tile.getTilePortionType()[4];
				rotateArr[5] = tile.getTilePortionType()[1];
				rotateArr[6] = tile.getTilePortionType()[8];
				rotateArr[7] = tile.getTilePortionType()[5];
				rotateArr[8] = tile.getTilePortionType()[2];
			}
			tile.setTilePortionType(rotateArr);
			tile.setDegrees(rotation);
		}

		if (!isValid(x, y, tile)) {

			// 0 = false
			return 0;
		}
		// add tile to board
		// give tile coords
		placedTiles.add(tile);
		board[x][y] = tile;
		tile.setCol(y);
		tile.setRow(x);
		tile.setBoard(this);

		if (x == getTopBound() && x > 0) {
			setTopBound(x - 1);
		}
		if (x == getBottomBound() && x < MAX_ROWS - 1) {
			setBottomBound(x + 1);
		}
		if (y == getLeftBound() && y > 0) {
			setLeftBound(y - 1);
		}
		if (y == getRightBound() && y < MAX_COLS - 1) {
			setRightBound(y + 1);
		}

		return 1;
		// return true;
	}

	
	public static void main(String[] args){
		Board gameBoard = new Board();
		Tile tile1 = new Tile("JJJJ-");
		gameBoard.placeTile(CENTER_CELL, CENTER_CELL, 0, tile1);
		Tile tile2 = new Tile("TJTJ-");
		//System.out.println(tile2.getCol());
		gameBoard.placeTile(CENTER_CELL+1, CENTER_CELL, 90, tile2);
		//System.out.println(tile2.getCol());
		
		UI test = new UI();
		test.createUIBoard(gameBoard);
	}

}
