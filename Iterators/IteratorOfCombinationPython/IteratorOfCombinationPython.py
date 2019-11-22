class IteratorOfCombinationPython:

    def __init__(self, N):
        self.valorMaximo = 1
        self.valorMinimo = 0
        self.combinacion = []
        for i in range(N):
            self.combinacion.append(0)

    def ultimaCombinacion(self):
        for i in self.combinacion:
            if i == 0:
                return False
        return True

    def siguienteCombinacion(self):
        for i in range(0, len(self.combinacion)):
            if self.combinacion[i] == self.valorMaximo:
                self.combinacion[i] = self.valorMinimo
            else:
                self.combinacion[i] = self.combinacion[i] + 1
                return self.combinacion
