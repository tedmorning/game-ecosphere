package com.gameco.games.chess;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import com.gameco.*;
import com.gameco.game.*;
import com.gameco.game.move.EndSignal;
import com.gameco.game.move.EscapeSignal;
import com.gameco.games.chess.move.Check;
import com.gameco.games.chess.move.Checkmate;
import com.gameco.games.chess.move.PieceMove;
import com.gameco.games.chess.piece.*;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public class ChessGame extends Game {
	
	/**
	 * [row, columns]
	 * [0, 0] elements represents as bottom left corner.
	 */
	protected Square[][] chessboard;
	
	/**
	 * Color of player (for client only)
	 */
	protected boolean isWhite;
	protected int numInPlayers;
	
	protected List<Square> whitePiece, blackPiece;
	
	protected int turn;
	
	public ChessGame(boolean rank, List<User> players) {
		super(rank);
		this.chessboard = new Square[8][8];
		boolean color = true;
		for (int row = 0; row < 8; row++) {
			color = !color;
			for (int column = 0; column < 8; column++) {
				this.chessboard[row][column] = new Square(new Coord(column, row), color);
				color = !color;
			}
		}
		this.blackPiece = new ArrayList<Square>();
		this.whitePiece = new ArrayList<Square>();
		setPlayers(players);
		initBoard(true);
		initBoard(false);
	}
	
	protected void initBoard(boolean isWhite) {
		for (int i = 0; i < 8; i++) { // Pawn
			addFigure(new Pawn(isWhite), i, isWhite?1:6);
		}
		addFigure(new Rook(isWhite), 0, isWhite?0:7);
		addFigure(new Rook(isWhite), 7, isWhite?0:7);
		addFigure(new Knight(isWhite), 1, isWhite?0:7);
		addFigure(new Knight(isWhite), 6, isWhite?0:7);
		addFigure(new Bishop(isWhite), 2, isWhite?0:7);
		addFigure(new Bishop(isWhite), 5, isWhite?0:7);
		addFigure(new Queen(isWhite), 3, isWhite?0:7);
		addFigure(new King(isWhite), 4, isWhite?0:7);
	}
	
	protected void addFigure(Piece fig, int column, int row) {
		this.chessboard[row][column].setPiece(fig);
		fig.setPos(this.chessboard[row][column].getPos());
		if (fig.isWhite())
			this.whitePiece.add(this.chessboard[row][column]);
		else
			this.blackPiece.add(this.chessboard[row][column]);
	}

	@Override
	public GameHistory processMoveClient(GameMove move) {
		GameHistory h = new GameHistory();
		if (move.getType().equals(PieceMove.class.getSimpleName())) {
//		if (move.getClass().equals(PieceMove.class)) {
//			PieceMove m = (PieceMove) move;
			Square to = this.getSquare(PieceMove.getTo(move));
			Square from = this.getSquare(PieceMove.getFrom(move));
			if (to.getPiece() != null) {
				if (to.getPiece().isWhite()) {
					this.blackPiece.remove(to);
				}
				else {
					this.whitePiece.remove(to);
				}
			}
			to.setPiece(from.piece);
			from.setPiece(null);
		}
		else if (move.getType().equals(Checkmate.class.getSimpleName())) {
			
		}
		h.append(move);
		return h;
	}
	
	@Override
	public GameHistory processMoveServer(GameMove move) {
		if (move.getType().equals(EscapeSignal.class.getSimpleName())) {
//		if (move.getClass().equals(EscapeSignal.class)) {
//			EscapeSignal esc = (EscapeSignal) move;
			User usr = null;
			for (User u : this.players) {
				if (u.getUsername().equals(EscapeSignal.getEscaper(move))) {
//				if (u.getUsername().equals(esc.getEscaper())) {
					usr = u;
					break;
				}
			}
			this.players.remove(EscapeSignal.getEscaper(move));
			GameHistory mes = new GameHistory();
			GameMove e = EscapeSignal.newInstance(new GameMove(getNumberOfStep(), getStepDuration()), EscapeSignal.getEscaper(move));
//			EscapeSignal e = new EscapeSignal(getNumberOfStep(), getStepDuration(), EscapeSignal.getEscaper(move));
//			e.setEscaper(EscapeSignal.getEscaper(move));
			String[] loser = new String[1];
			loser[0] = usr.getUsername();
			String[] winn = new String[this.players.size()];
			this.players.toArray(winn);
			GameMove e1 = EndSignal.newInstance(new GameMove(getNumberOfStep(), getStepDuration()), winn, loser);
//			EndSignal e1 = new EndSignal(getNumberOfStep(), getStepDuration()); 
//			e1.setLosers(Arrays.asList(new User[] {usr}));
//			e1.setWinners(this.players);

			mes.getMoves().add(e);
			mes.getMoves().add(e1);
			
			this.outer.setMoves(mes);
			return mes;
		}
		else if (move.getType().equals(PieceMove.class.getSimpleName())) {
//		else if (move.getClass().equals(PieceMove.class)) {
			this.processMoveClient(move);
			GameHistory rep = new GameHistory();
			rep.append(move);
			Square king = null;
			for (Square sq : (this.turn == 0)?this.blackPiece:this.whitePiece) {
				if (sq.getPiece().getClass().equals(King.class)) {
					king = sq;
				}
			}
			if (this.isKingUnderAttack(king, (this.turn != 0)?false:true)) {
				if (getMoves(king).isEmpty()) {
					rep.append(Check.newInstance(new GameMove(getNumberOfStep(), getStepDuration())));
					this.outer.setMoves(rep);
					return rep;
				}
			}
			return rep;
		}
		return null;
	}

	@Override
	public void initiateMoveClient(GameMove move) {
		if (move.getType().equals(PieceMove.class.getSimpleName())) {
//		if (move.getClass().equals(PieceMove.class)) {
//			PieceMove m = (PieceMove) move;
			this.getMove(move);
			this.outer.setMove(PieceMove.newInstance(new GameMove(getNumberOfStep(), getStepDuration()), PieceMove.getSide(move), PieceMove.getFrom(move), PieceMove.getTo(move)));
//			this.outer.setMove(new PieceMove(getNumberOfStep(), getStepDuration(), m.getFigureCoord(), m.getDestination(), null));
		}
	}
	
	public List<Square> getMoves(Square s) {
		if (s.getPiece() == null)
			return null;
		List<Square> w = new LinkedList<Square>();
		Piece p = s.getPiece();
		int x = p.getPos().getColumn(),
				y = p.getPos().getRow();
		if (s.getPiece().getClass().equals(Pawn.class)) {
		// пешка
			if (p.isWhite()) {
				if ((p.canMove(Direction.N)) && (this.getSquare(x, y+1).getPiece() == null))
					w.add(this.getSquare(x, y+1));
				if ((p.getPos().getRow() == 1) && ((this.getSquare(x, y+1).getPiece() == null) & (this.getSquare(x, y+2).getPiece() == null)))
					w.add(this.getSquare(x, y+2));
				if (p.canMove(Direction.NW)) { // Attack left
					if ((this.getSquare(x-1, y+1).getPiece() != null) && (this.getSquare(x-1, y+1).getPiece().isWhite() ^ this.isWhite())) {
						w.add(this.getSquare(x-1, y+1));
					}
				}
				if (p.canMove(Direction.NE)) { // Attack right
					if ((this.getSquare(x+1, y+1).getPiece() != null) && (this.getSquare(x+1, y+1).getPiece().isWhite() ^ this.isWhite())) {
						w.add(this.getSquare(x+1, y+1));
					}
				}
			}
			else {
				if ((p.canMove(Direction.S)) && (this.getSquare(x, y-1).getPiece() == null))
					w.add(this.getSquare(x, y-1));
				if ((p.getPos().getRow() == 6) && ((this.getSquare(x, y-1).getPiece() == null) & (this.getSquare(x, y-1).getPiece() == null)))
					w.add(this.getSquare(x, y-2));
				if (p.canMove(Direction.SW)) { // Attack left
					if ((this.getSquare(x-1, y-1).getPiece() != null) && (this.getSquare(x-1, y-1).getPiece().isWhite() ^ this.isWhite())) {
						w.add(this.getSquare(x-1, y-1));
					}
				}
				if (p.canMove(Direction.SE)) { // Attack right
					if ((this.getSquare(x+1, y-1).getPiece() != null) && (this.getSquare(x+1, y-1).getPiece().isWhite() ^ this.isWhite())) {
						w.add(this.getSquare(x+1, y-1));
					}
				}
			}
		}
		else if (s.getPiece().getClass().equals(Bishop.class)) {
		// слон
			Coord c = p.getPos().clone();
			for (c.move(Direction.NE); Piece.canBe(c); c.move(Direction.NE))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.NW); Piece.canBe(c); c.move(Direction.NW))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.SE); Piece.canBe(c); c.move(Direction.SE))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.SW); Piece.canBe(c); c.move(Direction.SW))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
		}
		else if (s.getPiece().getClass().equals(Rook.class)) {
		// ладья
			Coord c = p.getPos().clone();
			for (c.move(Direction.N); Piece.canBe(c); c.move(Direction.N))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.S); Piece.canBe(c); c.move(Direction.S))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.W); Piece.canBe(c); c.move(Direction.W))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.E); Piece.canBe(c); c.move(Direction.E))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
		}
		else if (s.getPiece().getClass().equals(Knight.class)) {
		// Конь
			if ((x+2 < 8) & (y+1 < 8))
				if ((this.getSquare(x+2, y+1).getPiece() != null) && (this.getSquare(x+2, y+1).getPiece().isWhite() ^ this.isWhite()))
					w.add(this.getSquare(x+2, y+1));
				else if (this.getSquare(x+2, y+1).getPiece() == null)
					w.add(this.getSquare(x+2, y+1));
			
			if ((x+2 < 8) & (y-1 > -1))
				if ((this.getSquare(x+2, y-1).getPiece() != null) && (this.getSquare(x+2, y-1).getPiece().isWhite() ^ this.isWhite()))
						w.add(this.getSquare(x+2, y-1));
					else if (this.getSquare(x+2, y-1).getPiece() == null)
						w.add(this.getSquare(x+2, y-1));
			
			if ((y+2 < 8) & (x+1 < 8))
				if ((this.getSquare(x+1, y+2).getPiece() != null) && (this.getSquare(x+1, y+2).getPiece().isWhite() ^ this.isWhite()))
						w.add(this.getSquare(x+1, y+2));
					else if (this.getSquare(x+1, y+2).getPiece() == null)
						w.add(this.getSquare(x+1, y+2));
			
			if ((y+2 < 8) & (x-1 > -1))
				if ((this.getSquare(x-1, y+2).getPiece() != null) && (this.getSquare(x-1, y+2).getPiece().isWhite() ^ this.isWhite()))
						w.add(this.getSquare(x-1, y+2));
					else if (this.getSquare(x-1, y+2).getPiece() == null)
						w.add(this.getSquare(x-1, y+2));

			
			if ((x-2 > -1) & (y+1 < 8))
				if ((this.getSquare(x-2, y+1).getPiece() != null) && (this.getSquare(x-2, y+1).getPiece().isWhite() ^ this.isWhite()))
						w.add(this.getSquare(x-2, y+1));
					else if (this.getSquare(x-2, y+1).getPiece() == null)
						w.add(this.getSquare(x-2, y+1));

			if ((x-2 > -1) & (y-1 > -1))
				if ((this.getSquare(x-2, y-1).getPiece() != null) && (this.getSquare(x-2, y-1).getPiece().isWhite() ^ this.isWhite()))
						w.add(this.getSquare(x-2, y-1));
					else if (this.getSquare(x-2, y-1).getPiece() == null)
						w.add(this.getSquare(x-2, y-1));

			if ((y-2 > -1) & (x+1 < 8))
				if ((this.getSquare(x+1, y-2).getPiece() != null) && (this.getSquare(x+1, y-2).getPiece().isWhite() ^ this.isWhite()))
						w.add(this.getSquare(x+1, y-2));
					else if (this.getSquare(x+1, y-2).getPiece() == null)
						w.add(this.getSquare(x+1, y-2));

			if ((y-2 > -1) & (x-1 > -1))
				if ((this.getSquare(x-1, y-2).getPiece() != null) && (this.getSquare(x-1, y-2).getPiece().isWhite() ^ this.isWhite()))
						w.add(this.getSquare(x-1, y-2));
					else if (this.getSquare(x-1, y-2).getPiece() == null)
						w.add(this.getSquare(x-1, y-2));
		}
		else if (s.getPiece().getClass().equals(King.class)) {
		// Король
			Coord c = p.getPos().clone();
			c.move(Direction.N);
			if (Piece.canBe(c)) {
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite))
					w.add(this.getSquare(c));
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
			}
			
			
			c = p.getPos().clone();
			c.move(Direction.S);
			if (Piece.canBe(c)) {
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite))
					w.add(this.getSquare(c));
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
			}
			
			c = p.getPos().clone();
			c.move(Direction.W);
			if (Piece.canBe(c)) {
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite))
					w.add(this.getSquare(c));
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
			}
			
			c = p.getPos().clone();
			c.move(Direction.E);
			if (Piece.canBe(c)) {
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite))
					w.add(this.getSquare(c));
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
			}
			
			c = p.getPos().clone();
			c.move(Direction.SW);
			if (Piece.canBe(c)) {
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite))
					w.add(this.getSquare(c));
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
			}
			
			c = p.getPos().clone();
			c.move(Direction.SE);
			if (Piece.canBe(c)) {
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite))
					w.add(this.getSquare(c));
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
			}
			
			c = p.getPos().clone();
			c.move(Direction.NW);
			if (Piece.canBe(c)) {
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite))
					w.add(this.getSquare(c));
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
			}
			
			c = p.getPos().clone();
			c.move(Direction.NE);
			if (Piece.canBe(c)) {
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite))
					w.add(this.getSquare(c));
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
			}
			
			List<Square> possibleAttack = new LinkedList<>();
			for (Square pa : (isWhite)?this.blackPiece:this.whitePiece) {
				if ((pa.getPiece() != null) && !pa.getPiece().getClass().equals(King.class)) {
					possibleAttack.addAll(this.getMoves(pa));
				}
			}
			
			w.removeAll(possibleAttack);
			w.removeIf(new Predicate<Square>() {
				@Override
				public boolean test(Square t) {
					return isKingUnderAttack(t, getSquare(p.getPos()).getPiece().isWhite());
				}
			});
		}
		else if (s.getPiece().getClass().equals(Queen.class)) {
		// Ферзь
			Coord c = p.getPos().clone();
			for (c.move(Direction.N); Piece.canBe(c); c.move(Direction.N))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.S); Piece.canBe(c); c.move(Direction.S))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.W); Piece.canBe(c); c.move(Direction.W))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.E); Piece.canBe(c); c.move(Direction.E))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			c = p.getPos().clone();
			for (c.move(Direction.NE); Piece.canBe(c); c.move(Direction.NE))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.NW); Piece.canBe(c); c.move(Direction.NW))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.SE); Piece.canBe(c); c.move(Direction.SE))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
			
			c = p.getPos().clone();
			for (c.move(Direction.SW); Piece.canBe(c); c.move(Direction.SW))
				if ((this.getSquare(c).getPiece() != null) && (this.getSquare(c).getPiece().isWhite() ^ this.isWhite())) {
					w.add(this.getSquare(c));
					break;
				}
				else if (this.getSquare(c).getPiece() == null)
					w.add(this.getSquare(c));
				else
					break;
		}
		return w;
	}
	
	public boolean isKingUnderAttack(Square s, boolean isWhite) {
		boolean removed = false;
		Piece removedP = s.getPiece();
		if (removedP != null) {
			removed = true;
			s.setPiece(null);
		}
		List<Square> possibleAttack = new LinkedList<>();
		for (Square pa : (isWhite)?this.blackPiece:this.whitePiece) {
			if ((pa.getPiece() != null) && !pa.getPiece().getClass().equals(King.class)) {
				possibleAttack.addAll(this.getMoves(pa));
			}
		}
		if (possibleAttack.contains(s)) {
			if (removed) {
				s.setPiece(removedP);
			}
			return true;
		}
		// Есть ли рядом вражеский король
		int x0 = s.getPos().getColumn();
		int y0 = s.getPos().getRow();
		
		Square enemy = null;
		for (Square f : isWhite?blackPiece:whitePiece) {
			if ((f.getPiece() != null) && f.getPiece().getClass().equals(King.class))
				enemy = f;
		}
		int x = enemy.getPos().getColumn();
		int y = enemy.getPos().getRow();
		if ((Math.abs(x0-x)-1 < 1) & (Math.abs(y0-y)-1 < 1)) {
			if (removed) {
				s.setPiece(removedP);
			}
			return true;
		}
		if (removed) {
			s.setPiece(removedP);
		}
		return false;
	}

	public Square getSquare(Coord coord) {
		return this.chessboard[coord.getRow()][coord.getColumn()];
	}
	
	public Square getSquare(int column, int row) {
		return this.chessboard[row][column];
	}
	
	public void setPlayerColor(boolean isWhite) {
		this.isWhite = isWhite;
		this.numInPlayers = (isWhite)?0:1;
	}
	
	public boolean getPlayerColor() {
		return this.isWhite;
	}
	
	public boolean isWhite() {
		return this.isWhite;
	}
	
	/**
	 * For FXML loader.
	 */
	public Parent getView() throws IOException {
		return null;
	}

	@Override
	public void switchTurn() {
		
	}
	
	public static Image getLogo() {
		return new Image(Game.class.getResourceAsStream("/views/img/logo.jpg"));
	}
	
}
