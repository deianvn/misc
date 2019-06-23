#include <iostream>
#include <bitset>

constexpr int SIZE = 10;

void printSet(const std::bitset<SIZE>& s) {
  for (size_t i = 0; i < s.size(); i++) {
    if (s[i]) std::cout << i << " ";
  }
  std::cout << std::endl;
}

int main() {

  // Множество 1, 3, 5
  std::string s1 { "0000101010" };
  std::bitset<SIZE> set1(s1);

  // Множество 5, 7, 9
  std::string s2 { "1010100000" };
  std::bitset<SIZE> set2(s2);

  // Празно множество
  std::bitset<SIZE> set3;

  std::cout << "Set 1: ";
  printSet(set1);
  std::cout << "Set 2: ";
  printSet(set2);
  std::cout << "Set 3: ";
  printSet(set3);

  // Обединение
  std::cout << "Union of Set 1 and Set 2: ";
  printSet(set1 | set2);

  // Сечение
  std::cout << "Intersection of Set 1 and Set2: ";
  printSet(set1 & set2);

  // Проверка за празно множество
  std::cout << "Is Set 1 empty: " << (set1.count() == 0 ? "Yes" : "No") << std::endl;
  std::cout << "Is Set 2 empty: " << (set2.count() == 0 ? "Yes" : "No") << std::endl;
  std::cout << "Is Set 3 empty: " << (set3.count() == 0 ? "Yes" : "No") << std::endl;

  // Проверка дали елемент е част от множеството
  std::cout << "Set 1 contains 3: " << (set1[3] ? "Yes" : "No") << std::endl;
  std::cout << "Set 1 contains 4: " << (set1[4] ? "Yes" : "No") << std::endl;

  return 0;

}
