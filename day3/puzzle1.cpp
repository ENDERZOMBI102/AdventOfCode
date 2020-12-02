#include <fstream>
#include <iostream>

void exc(std::string line) {
    // do something
}

int main() {
    std::string line, content;
    std::ifstream file ("input");
    if ( file.is_open() ) {
        while ( getline(file, line) ) {
            exc(line);
        }
        file.close();
    } else {
        std::cout << "Unable to open file";
        return 1;
    }
}