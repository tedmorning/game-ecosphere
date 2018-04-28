package com.gameco.games.chess.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.gameco.client.IClientContext;
//import com.gameco.client.IClientContext;
import com.gameco.game.GameMove;
import com.gameco.games.chess.ChessGame;
import com.gameco.games.chess.Piece;
import com.gameco.games.chess.Square;
import com.gameco.games.chess.move.PieceMove;
import com.gameco.games.chess.piece.Bishop;
import com.gameco.games.chess.piece.King;
import com.gameco.games.chess.piece.Knight;
import com.gameco.games.chess.piece.Pawn;
import com.gameco.games.chess.piece.Queen;
import com.gameco.games.chess.piece.Rook;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class SimpleChessController implements InvalidationListener {

	protected Canvas all;
	protected Scene scene;
	
	protected IClientContext clientContext;
	
	double m2;
	double x0;
	double y0;
	double step;
	
	Square selected = null;
	List<Square> avaibleMove = new LinkedList<Square>();

	protected EventHandler<Event> onAction;
	protected EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent e) {
			resize();
			double x = e.getX(),
					y = e.getY();
			int row = (int) ((y-y0)/step);
			
			if (game.isWhite())
				row = 7 - row;
			
			int column = (int) ((x-x0)/step);
			
			if ((row < 0) | (x < x0) | (row > 7) | (column < 0) | (y < y0) | (column > 7))
				return;
			
			Square nSelected = game.getSquare(column, row);
			if (selected == null) { // Нет выбранной фигуры
				if (game.getSquare(column, row).getPiece() != null) { // Существует фигура в клетке
					if (game.getSquare(column, row).getPiece().isWhite() == game.isWhite()) { // Фигура моего цвета
						selected = nSelected;
						avaibleMove = game.getMoves(selected);
						//selected.getPiece();
					}
				}
			}
			else { // Есть выбранная фигура
				if (selected.equals(nSelected)) { // Тажа самая фигура, снять выделение
					selected = null;
					avaibleMove = null;
				}
				else {
					if (nSelected.getPiece() != null) { // Тут другая фигура
						if (nSelected.getPiece().isWhite() == game.isWhite()) { // моя фигура
							selected = nSelected;
							avaibleMove = game.getMoves(selected);
						}
						else if ((nSelected.getPiece() == null) || (nSelected.getPiece().isWhite() != game.isWhite())) { // Потенциально можно пойти
							if (avaibleMove.contains(nSelected)) { // Делаем допустимы ход
								GameMove move = PieceMove.newInstance(new GameMove(game.getNumberOfStep(), game.getStepDuration()), clientContext.getUser(), selected.getPos(), nSelected.getPos());
//								PieceMove move = new PieceMove(, selected.getPos(), nSelected.getPos(), null);
								game.setPlayerColor(!game.getPlayerColor());
								game.initiateMoveClient(move);
								avaibleMove = null;
								selected = null;
							}
						}
					}
					else { //пустая клетка
						if (avaibleMove.contains(nSelected)) { // Делаем допустимы ход
							GameMove move = PieceMove.newInstance(new GameMove(game.getNumberOfStep(), game.getStepDuration()), clientContext.getUser(), selected.getPos(), nSelected.getPos());
//							PieceMove move = new PieceMove(game.getNumberOfStep(), game.getStepDuration(), selected.getPos(), nSelected.getPos(), null);
							game.initiateMoveClient(move);
							game.setPlayerColor(!game.getPlayerColor());
							avaibleMove = null;
							selected = null;
						}
					}
				}
			}
			draw();
		}
	};
	
	protected ChessGame game;
	
	// Сначала цвет клекти, тип фигуры, цвет фигуры, метка цели
	protected HashMap<String, List<Image>> pieces;
	
	protected Image[][] empty;
	
	protected void resize() {
		this.all.setWidth(scene.getWidth());
		this.all.setHeight(scene.getHeight());
		double h = all.getHeight(),
				w = all.getWidth();
		
		double m = Math.min(h, w);
		m2 = m/2-20;
		
		x0 = w/2-m2;
		y0 = h/2-m2;
		step = 2*m2 / 8;
	}
	
	public void draw() {
		resize();
		double h = all.getHeight(),
				w = all.getWidth();
		GraphicsContext context = all.getGraphicsContext2D();
		context.clearRect(0, 0, w, h);
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				Square sq = game.getSquare(column, row);
				Piece p = sq.getPiece();
				if (p == null) { // Пустая клетка
					if (sq.isWhite()) {
						if ((this.avaibleMove != null) && (this.avaibleMove.contains(sq)))
							context.drawImage(empty[0][1], x0+column*step, y0+(game.isWhite()?(7-row):row)*step, step, step);
						else
							context.drawImage(empty[0][0], x0+column*step, y0+(game.isWhite()?(7-row):row)*step, step, step);
					}
					else {
						if ((this.avaibleMove != null) && (this.avaibleMove.contains(sq)))
							context.drawImage(empty[1][1], x0+column*step, y0+(game.isWhite()?(7-row):row)*step, step, step);
						else
							context.drawImage(empty[1][0], x0+column*step, y0+(game.isWhite()?(7-row):row)*step, step, step);
					}
				}
				else { // Есть фигура
					if (((selected != null) && (selected.equals(sq))) | ((avaibleMove != null) && (avaibleMove.contains(sq)))) {
						context.drawImage(getPiece(p, sq.isWhite(), p.isWhite(), true), x0+column*step, y0+(game.isWhite()?(7-row):row)*step, step, step);
					}
					if ((selected != null) && ((selected.equals(sq)) | (this.avaibleMove.contains(sq))))
						context.drawImage(getPiece(p, sq.isWhite(), p.isWhite(), true), x0+column*step, y0+(game.isWhite()?(7-row):row)*step, step, step);
					else
						context.drawImage(getPiece(p, sq.isWhite(), p.isWhite(), false), x0+column*step, y0+(game.isWhite()?(7-row):row)*step, step, step);
				}
			}
		}
		// Метки на доске
		for (int column = 0; column < 8; column++) {
			context.strokeText((char) ('a'+column)+"", x0+step*column+step/2, y0-5);
		}
		for (int column = 0; column < 8; column++) {
			context.strokeText((char) ('a'+column)+"", x0+step*column+step/2, y0+13+8*step);
		}
		for (int row = 0; row < 8; row++) {
			context.strokeText(String.format("%d", (game.isWhite()?(8-row):(row+1))), x0-15, y0+row*step+step/2);
		}
		for (int row = 0; row < 8; row++) {
			context.strokeText(String.format("%d", (game.isWhite()?(8-row):(row+1))), x0+8*step+7, y0+row*step+step/2);
		}
		
	}
	
	public SimpleChessController(ChessGame game, Scene scene, Canvas canvas) {
		this.game = game;
		this.game.setPlayerColor(true);
		this.scene = scene;
		this.all = canvas;
		
		canvas.setOnMouseClicked(onClick);
		
		this.pieces = new HashMap<String, List<Image>>();
		this.pieces.put(Pawn.class.getName(), new ArrayList<Image>(8));
		this.pieces.put(Rook.class.getName(), new ArrayList<Image>(8));
		this.pieces.put(Bishop.class.getName(), new ArrayList<Image>(8));
		this.pieces.put(Knight.class.getName(), new ArrayList<Image>(8));
		this.pieces.put(Queen.class.getName(), new ArrayList<Image>(8));
		this.pieces.put(King.class.getName(), new ArrayList<Image>(8));
		
		this.pieces.get(Pawn.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/pawn/whitePawnWhite.jpg")));
		this.pieces.get(Pawn.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/pawn/whitePawnWhiteTarget.jpg")));
		this.pieces.get(Pawn.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/pawn/whitePawnBlack.jpg")));
		this.pieces.get(Pawn.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/pawn/whitePawnBlackTarget.jpg")));
		this.pieces.get(Pawn.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/pawn/blackPawnWhite.jpg")));
		this.pieces.get(Pawn.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/pawn/blackPawnWhiteTarget.jpg")));
		this.pieces.get(Pawn.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/pawn/blackPawnBlack.jpg")));
		this.pieces.get(Pawn.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/pawn/blackPawnBlackTarget.jpg")));
		
		this.pieces.get(Bishop.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/bishop/whiteBishopWhite.jpg")));
		this.pieces.get(Bishop.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/bishop/whiteBishopWhiteTarget.jpg")));
		this.pieces.get(Bishop.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/bishop/whiteBishopBlack.jpg")));
		this.pieces.get(Bishop.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/bishop/whiteBishopBlackTarget.jpg")));
		this.pieces.get(Bishop.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/bishop/blackBishopWhite.jpg")));
		this.pieces.get(Bishop.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/bishop/blackBishopWhiteTarget.jpg")));
		this.pieces.get(Bishop.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/bishop/blackBishopBlack.jpg")));
		this.pieces.get(Bishop.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/bishop/blackBishopBlackTarget.jpg")));
		
		this.pieces.get(King.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/king/whiteKingWhite.jpg")));
		this.pieces.get(King.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/king/whiteKingWhiteTarget.jpg")));
		this.pieces.get(King.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/king/whiteKingBlack.jpg")));
		this.pieces.get(King.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/king/whiteKingBlackTarget.jpg")));
		this.pieces.get(King.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/king/blackKingWhite.jpg")));
		this.pieces.get(King.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/king/blackKingWhiteTarget.jpg")));
		this.pieces.get(King.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/king/blackKingBlack.jpg")));
		this.pieces.get(King.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/king/blackKingBlackTarget.jpg")));
		
		this.pieces.get(Knight.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/knight/whiteKnightWhite.jpg")));
		this.pieces.get(Knight.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/knight/whiteKnightWhiteTarget.jpg")));
		this.pieces.get(Knight.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/knight/whiteKnightBlack.jpg")));
		this.pieces.get(Knight.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/knight/whiteKnightBlackTarget.jpg")));
		this.pieces.get(Knight.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/knight/blackKnightWhite.jpg")));
		this.pieces.get(Knight.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/knight/blackKnightWhiteTarget.jpg")));
		this.pieces.get(Knight.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/knight/blackKnightBlack.jpg")));
		this.pieces.get(Knight.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/knight/blackKnightBlackTarget.jpg")));
		
		this.pieces.get(Queen.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/queen/whiteQueenWhite.jpg")));
		this.pieces.get(Queen.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/queen/whiteQueenWhiteTarget.jpg")));
		this.pieces.get(Queen.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/queen/whiteQueenBlack.jpg")));
		this.pieces.get(Queen.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/queen/whiteQueenBlackTarget.jpg")));
		this.pieces.get(Queen.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/queen/blackQueenWhite.jpg")));
		this.pieces.get(Queen.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/queen/blackQueenWhiteTarget.jpg")));
		this.pieces.get(Queen.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/queen/blackQueenBlack.jpg")));
		this.pieces.get(Queen.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/queen/blackQueenBlackTarget.jpg")));
		
		this.pieces.get(Rook.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/rook/whiteRookWhite.jpg")));
		this.pieces.get(Rook.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/rook/whiteRookWhiteTarget.jpg")));
		this.pieces.get(Rook.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/rook/whiteRookBlack.jpg")));
		this.pieces.get(Rook.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/rook/whiteRookBlackTarget.jpg")));
		this.pieces.get(Rook.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/rook/blackRookWhite.jpg")));
		this.pieces.get(Rook.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/rook/blackRookWhiteTarget.jpg")));
		this.pieces.get(Rook.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/rook/blackRookBlack.jpg")));
		this.pieces.get(Rook.class.getName()).add(new Image(this.getClass().getResourceAsStream("/views/img/rook/blackRookBlackTarget.jpg")));
		
		this.empty = new Image[2][2];
		this.empty[0][0] = new Image(this.getClass().getResourceAsStream("/views/img/whitesquare.jpg"));
		this.empty[0][1] = new Image(this.getClass().getResourceAsStream("/views/img/whiteTarget.jpg"));
		this.empty[1][0] = new Image(this.getClass().getResourceAsStream("/views/img/blacksquare.jpg"));
		this.empty[1][1] = new Image(this.getClass().getResourceAsStream("/views/img/blackTarget.jpg"));
	}
	
	protected Image getPiece(Piece piece, boolean isWhiteSquare, boolean isWhitePiece, boolean isTarget) {
		int index = 4*(isWhiteSquare?0:1) + 2*(isWhitePiece?0:1) + (isTarget?1:0);
		return this.pieces.get(piece.getClass().getName()).get(index);
	}
	
	public void setClientContext(IClientContext context) {
		this.clientContext = context;
	}

	@Override
	public void invalidated(Observable observable) {
		draw();
	}

}
