#include <stdio.h>
#include <stdlib.h>

int main() {
    printf("Hello World\n");
    int ret = system("lspci");
    if (ret == -1) {
        printf("Call system() failed\n");
    }
    return 0;
}

