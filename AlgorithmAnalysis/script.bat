@echo off

echo Este script calculara los tiempos de ejecucion.
mkdir Ejecutables
cd AlgorithmJava/src
javac org/apache/commons/cli/*.java algorithmjava/*.java
jar -cfe ../../Ejecutables/javaout.jar algorithmjava.AlgorithmJava algorithmjava/*.class org/apache/commons/cli/*.class
cd ../../AlgorithmCpp
g++ -o ../Ejecutables/cppout.exe AlgorithmCpp.cpp AddPoly.cpp ParserArgs.cpp ReadFile.cpp SolveEquation.cpp
cd ../FileGenerator/src
javac org/apache/commons/cli/*.java filegenerator/*.java
jar -cfe filesout.jar filegenerator.FileGenerator filegenerator/*.class org/apache/commons/cli/*.class
cd ../../Ejecutables

echo RESULTADOS_GAUSS_JORDAN > resultadoGauss.txt
for /l %%x in (100, 100, 400) do (
	cd ../FileGenerator/src
	java -jar filesout.jar -v %%x
	
	cd ../../AlgorithmPython/AlgorithmPython
	echo Tiempo PYTHON >> ../../Ejecutables/resultadoGauss.txt
	python AlgorithmPython.py -f ../../Vectores -dt >> ../../Ejecutables/resultadoGauss.txt
	
	cd ../../Ejecutables
	echo Tiempo JAVA >> resultadoGauss.txt
	java -jar javaout.jar -f ../Vectores -dt >> resultadoGauss.txt

	echo Tiempo C++ >> resultadoGauss.txt
	cppout.exe -f ../Vectores -t >> resultadoGauss.txt
	echo. >> resultadoGauss.txt
)

echo RESULTADOS_SUMA > resultadoSuma.txt
for /l %%x in (100, 200, 2000) do (
	cd ../FileGenerator/src
	java -jar filesout.jar -v %%x	

	cd ../../AlgorithmPython/AlgorithmPython	
	echo Tiempo PYTHON >> ../../Ejecutables/resultadoSuma.txt
	python AlgorithmPython.py -f ../../Vectores -dt -a >> ../../Ejecutables/resultadoSuma.txt

	cd ../../Ejecutables
	echo Tiempo SUMA JAVA >> resultadoSuma.txt
	java -jar javaout.jar -f ../Vectores -dt -a >> resultadoSuma.txt

	echo Tiempo SUMA C++ >> resultadoSuma.txt
	cppout.exe -f ../Vectores -t -a >> resultadoSuma.txt
	echo. >> resultadoSuma.txt
)

pause
exit