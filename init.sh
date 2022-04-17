reset
echo '+-----------------------------------------------------------------------+'
echo '|             VMS - Vehicle Monitoring System Simulator                 |'
echo '| https://github.com/LucasFonsecadosSantos/VMS-VehiclesMonitoringSystem |'
echo '+-----------------------------------------------------------------------+'
echo '|                                                                       |'
echo '| Developed by:                                                         |'
echo '| -> Lucas Fonseca dos Santos                                           |'
echo '| -> Bruna Capeleti                                                     |'
echo '| -> Joicy Reis                                                         |'
echo '|                                                                       |'
echo '+-----------------------------------------------------------------------+'
sleep 3
echo '                                                                         '
echo '[..] Compiling first level Java source codes packages...'
#cd simulacao/
javac *.java
echo '                                                                         '
echo '[OK] First level of packages tree compiled.'
echo '[..] Compiling first level Java source codes packages...'
javac **/*.java
echo '                                                                         '
echo '[OK] Second level of packages tree compiled.'
echo '[..] Starting application...'
sleep 3
java simulation.Main