import 'dart:io';

class Board {
  
  final data = List<String>.generate(
    9,
    (index) => (index + 1).toString(),
    growable: false
  );
  
  String probe(int x, int y) => data[x + (3 * y)];

  place(String sign, int location)
    => data[location] = sign;
  
  draw() {
    for (var i = 0; i < data.length; i++) {
      stdout.write(' ${data[i]} ');
      if ((i + 1) % 3 == 0) print('');
    }
    print('');
  }
}