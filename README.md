# SENG201-Project

Program Arguments:
    Flag    |   Value   |   Example    |   Default   |
------------|-----------|--------------|-------------|
    -gui    |  boolean  |  -gui false  |    true     |
   -scale   |  double   |  -scale 1.5  |     1.0     |

How to compile program
1. Navigate to your folder containing the 'src' and 'res' folder through command line
2. It is reccomended to create a folder (for example named 'compiled') for your class files if you wish to construct a jar file. Run the command
   'javac -d compiled src/*.java'. '-d compiled' can be ommitted if you decide not to create a seperate folder for you class files

How to construct and run jar file from compiled class files
1. Run the command 'jar cmvf src/META-INF/MANIFEST.MF nameofjarfile.jar -C compiled . res' replacing 'nameofjarfile' with what you want to call
   your JAR file and 'compiled' with the folder path containing the compiled class files
2. Run the jar file with the command 'java -jar nameofjarfile.jar' optional program arguments can be after 'nameofjarfile.jar' as required

 
