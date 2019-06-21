import 'board.dart';
import 'player.dart';

class Game {
  
  final Player _playerOne, _playerTwo;
  
  final _board = Board();

  Game(this._playerOne, this._playerTwo);

  Game.humanVsHuman() : 
    _playerOne = Player.x(),
    _playerTwo = Player.o();

  play() {
    Player currentPlayer = _playerOne;
    while (!isFinished()) {
      _board.draw();
      currentPlayer.playNext(_board);
      currentPlayer = currentPlayer == _playerOne ?
        _playerTwo : _playerOne;
    }
  }

  bool isFinished() => false;
}