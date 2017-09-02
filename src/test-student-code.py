import os

filenames = ["12345","10-million.txt"]

os.system("javac -cp .:stdlib.jar *.java")
for f in filenames:
    s = "time java -cp .:stdlib.jar TestDistinct " + f
    os.system(s)
