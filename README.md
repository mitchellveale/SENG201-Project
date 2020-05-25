# SENG201-Project

Program Arguments:
    Flag    |   Value   |   Example    |   Default   |
------------|-----------|--------------|-------------|
    -gui    |  boolean  |  -gui false  |    true     |
   -scale   |  double   |  -scale 1.5  |     1.0     |

How to play GUI:
Click on any crop field to view its status
Click on any animal pen to view its status
Click on the barn to view your inventory
Click on the store to view the store
click on the house to go to the next day, tend to farm land or use a lotto ticket


How to compile program
1. Navigate to your folder containing the 'src' and 'resources' folder through command line
2. Create a 'classes' folder and then run the command 'javac -d classes -sourcepath src src/SENGProject/GameManager.java'

How to construct and run jar file from compiled class files
1. Run the command 'jar cmvf src/META-INF/MANIFEST.MF nameofjarfile.jar -C classes . resources' replacing 'nameofjarfile' with what you want to call
   your JAR file
2. Run the jar file with the command 'java -jar nameofjarfile.jar' optional program arguments can be after 'nameofjarfile.jar' as required

 
