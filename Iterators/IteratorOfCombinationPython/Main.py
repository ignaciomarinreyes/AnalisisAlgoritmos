from IteratorOfCombinationPython import *
import sys

if __name__ == "__main__":
    combinacion = []
    it = IteratorOfCombinationPython(len(sys.argv) - 1)
    while not it.ultimaCombinacion():
        combinacion = it.siguienteCombinacion()
        for i in range(0, len(sys.argv) - 1):
            if combinacion[i] == 1:
                print(sys.argv[i + 1] + " ", end="")
        print()
