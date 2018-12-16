/*
 * Задание - Вариант 5
 *
 * Да се напише програма по зададената по-долу тема.
 * Програмата трябва да има възможности за:
 * избор на функцията, която да се изпълни от текстово меню;
 * създаване на нов файл с данни, като данните се въвеждат от клавиатурата;
 * отваряне на съществуващ файл с данни;
 * създаване на текстов файл, на първия ред на който се изписват имената на полетата,
 * а на всеки следващ по един запис от с данните;
 * добавя нови записи в активния файл;
 * създава зададените в темата справки и извежда резултата в подходящ вид на екрана.
 *
 * ТЕМА 5
 *
 * Файлът да съдържа информация за дадени страни и има следната структура:
 * име на страната (текст);
 * континент (цяло число, което е код на съответния континент);
 * столица (текст);
 * жители на столицата (число);
 * площ в кв. км. (число);
 * население в милиони жители (число).
 * Намерете страната с най-голям брой жители на столицата, както и средната гъстота
 * на населението на страните. Определете какъв процент от жителите живеят в
 * столицата за всяка страна поотделно, за всеки континет и като цяло за всички страни.
 */

#include <iostream>
#include <fstream>
#include <sstream>
#include <array>
#include <vector>
#include <string>
#include <algorithm>
#include <limits>
#include <stdexcept>
#include <experimental/iterator>

namespace io {

  std::string nextString() {
    std::cout << "> ";
    std::string str;
    std::getline(std::cin, str);
    return str;
  }

  std::string nextString(char blacklistedChar) {
    std::string str;
    while (true) {
      str = nextString();
      if (str.find(blacklistedChar) != std::string::npos) {
        std::cout << "Input can not contain: " << blacklistedChar << std::endl;
      } else {
        break;
      }
    }
    return str;
  }

  int nextInt(int min = 0,
              int max = std::numeric_limits<int>::max()) {
    int num;
    std::string str;
    while (true) {
      str = nextString();
      try {
        num = std::stoi(str);
        if (num < min || num >= max)
          std::cout << "Number must be in range (" << min << ":" << max << "]" << std::endl;
        else break;
      } catch (std::invalid_argument& e) {
        std::cout << "Number expected. Please try again." << std::endl;
      }
    }
    return num;
  }

  double nextDouble() {
    double num;
    std::string str;
    while (true) {
      str = nextString();
      try {
        num = std::stod(str);
        if (num <= .0)
          std::cout << "Number must be in positive." << std::endl;
        else break;
      } catch (std::invalid_argument& e) {
        std::cout << "Number expected. Please try again." << std::endl;
      }
    }
    return num;
  }

  std::string nextString(std::istream& stream, const char delim) {
    std::string str;
    if (std::getline(stream, str, delim)) {
      return str;
    }
    throw std::exception {};
  }

  int nextInt(std::istream& stream, const char delim) {
    std::string str;
    if (std::getline(stream, str, delim)) {
      return std::stoi(str);
    }
    throw std::exception {};
  }

  double nextDouble(std::istream& stream, const char delim) {
    std::string str;
    if (std::getline(stream, str, delim)) {
      return std::stod(str);
    }
    throw std::exception {};
  }

  bool fileExists(std::string path) {
    std::ifstream file { path.c_str() };
    return file.good();
  }

};

const std::array<std::string, 7> CONTINENTS = {
  "Europe",
  "Asia",
  "Afrika",
  "Australia",
  "North America",
  "South America",
  "Antarctica"
};

struct Country {
  std::string name;
  int continent;
  std::string capital;
  int capitalPopulation;
  int area;
  double population;

  void read() {
    std::cout << "Enter country:\n";
    name = io::nextString(';');
    for (std::size_t i = 0; i < CONTINENTS.size(); ++i) {
      std::cout << i + 1 << " - " << CONTINENTS[i] + "  ";
    }
    std::cout << "\nEnter continent:\n";
    continent = io::nextInt(1, 8);
    std::cout << "Enter capital:\n";
    capital = io::nextString(';');
    std::cout << "Enter capital population:\n";
    capitalPopulation = io::nextInt();
    std::cout << "Enter area (square meters):\n";
    area = io::nextInt();
    std::cout << "Enter population (in millions):\n";
    population = io::nextDouble();
  }
};

std::ostream& operator<<(std::ostream& stream, Country& country) {
  stream << country.name << ";"
         << country.continent << ";"
         << country.capital << ";"
         << country.capitalPopulation << ";"
         << country.area << ";"
         << country.population;
  return stream;
}

std::istream& operator>>(std::istream& stream, Country& country) {
  try {
    country.name = io::nextString(stream, ';');
    country.continent = io::nextInt(stream, ';');
    country.capital = io::nextString(stream, ';');
    country.capitalPopulation = io::nextInt(stream, ';');
    country.area = io::nextInt(stream, ';');
    country.population = io::nextDouble(stream, ';');
  } catch (std::exception& e) {
    stream.setstate(std::ios_base::failbit);
  } catch (std::invalid_argument& e) {
    stream.setstate(std::ios_base::failbit);
  }
  return stream;
}

using ActionCallback = std::function<bool()>;

struct Action {
  int choice;
  std::string text;
  ActionCallback callback = nullptr;
};

class Menu {
public:
  Menu(std::string title, std::vector<Action> actions) :
    title(title),
    actions(actions) {}

  void show();
  void print();
private:
  std::string title;
  std::vector<Action> actions;
  bool process();
};

void Menu::show() {
  print();
  while (process());
}

void Menu::print() {
  std::cout << "\n" << title << "\n";
  std::for_each(std::begin(actions),
                std::end(actions),
                [](Action& action) {
                  std::cout << "  " << action.choice << " - "<< action.text << "\n";
                });
}

bool Menu::process() {
  std::cout << "\n" << title << " ";
  int choice = io::nextInt();
  auto action = std::find_if(std::begin(actions), std::end(actions),
    [choice](const Action& a) {
      return choice == a.choice;
    });

  if (action == std::end(actions)) {
    std::cout << "Action not found. Please try again.\n" << std::endl;
    return true;
  } else {
    if (action->callback) {
      return action->callback();
    }
  }

  return false;
}

class StatMenu : public Menu {
public:

  StatMenu(std::string& workingFile) : Menu("Statistics", std::vector<Action> {
    Action { 1, "Help", [this, &workingFile]() {
      this->print();
      return true;
    }},
    Action { 0, "Back" }
  }) {}
};

class MainMenu : public Menu {
public:

  MainMenu(std::string& workingFile) : Menu("Main Menu", std::vector<Action> {
    Action { 1, "Help", [this, &workingFile]() {
      this->print();
      return true;
    }},
    Action { 2, "Show working file", [this, &workingFile]() {
      if (!workingFile.empty()) {
        std::cout << "Working file is " << workingFile << std::endl;
      } else {
        std::cout << "No working file selected." << std::endl;
      }
      return true;
    }},
    Action { 3, "Change working file", [this, &workingFile]() {
      this->changeWorkingFile(workingFile);
      return true;
    }},
    Action { 4, "Add data", [this, &workingFile]() {
      this->addData(workingFile);
      return true;
    }},
    Action { 5, "Print data", [this, &workingFile]() {
      this->printData(workingFile);
      return true;
    }},
    Action { 6, "Statistics", [this, &workingFile]() {
      StatMenu statMenu { workingFile };
      statMenu.show();
      return true;
    }},
    Action { 0, "Quit" }
  }) {}

private:

  void changeWorkingFile(std::string& workingFile);
  void addData(std::string& workingFile);
  void printData(std::string& workingFile);

  std::vector<std::string> properties = {
    "Country",
    "Continent",
    "Capital",
    "Capital population",
    "Area",
    "Population"
  };
};

void MainMenu::changeWorkingFile(std::string& workingFile) {
  std::cout << "Enter new file name:\n";
  std::string newFile = io::nextString();
  if (io::fileExists(newFile)) {
    std::cout << "File " << newFile << " successfully opened." << std::endl;
    workingFile = newFile;
  } else {
    // Try to write headers
    std::ofstream out(newFile.c_str());
    if (out) {
      std::copy(std::begin(properties),
                std::end(properties),
                std::experimental::ostream_joiner<std::string>(out, ";"));
      out << "\n";
      out.close();
      workingFile = newFile;
      std::cout << "File " << newFile << " successfully created." << std::endl;
    } else {
      std::cout << "Could not create file." << std::endl;
    }
  }
}

void MainMenu::addData(std::string& workingFile) {
  if (workingFile.empty()) {
    std::cout << "No working file selected." << std::endl;
    return;
  }
  Country country {};
  country.read();
  std::ofstream file;
  file.open(workingFile.c_str(), std::ofstream::out | std::ofstream::app);
  file << country << std::endl;
  file.close();
}

void MainMenu::printData(std::string& workingFile) {
  if (workingFile.empty()) {
    std::cout << "No working file selected." << std::endl;
    return;
  }
  std::ifstream file(workingFile.c_str());
  if (!file.is_open()) {
    std::cout << "Could not open file." << std::endl;
    return;
  }
  std::string line;
  std::getline(file, line);
  std::cout << line << "\n";
  Country country;
  while (std::getline(file, line)) {
    if (line.empty()) continue;
    std::istringstream iss(line);
    iss >> country;
    std::cout << country << "\n";
  }
}

class App {
public:
  void run();
private:
  // Application state
  std::string workingFile;
};

void App::run() {
  auto menu = std::make_unique<MainMenu>(workingFile);
  menu->show();
}

int main() {
  App app{};
  app.run();
  return EXIT_SUCCESS;
}
