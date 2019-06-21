import 'dart:io';

import 'board.dart';

class Player {

  final String sign;
  
  Player.x(): sign = 'X';
  Player.o(): sign = 'O';
  
  playNext(Board board) {
    final move = calculateNextMove(board);
    board.place(sign, move);
  }

  int calculateNextMove(Board board) {
    final line = stdin.readLineSync();
    final move = int.parse(line);
    return move - 1;
  }
}