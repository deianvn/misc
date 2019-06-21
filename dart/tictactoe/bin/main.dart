import 'package:tictactoe/tictactoe.dart';

main(List<String> arguments) {
  final game = Game.humanVsHuman();
  print('Starting game...');
  game.play();
}
