###########Environment dependence
JaveSE-1.8
fastjson-1.2.62.jar
ujmp-core-0.3.0.jar

###########directory structure
©À©¤©¤ README.md                                       // help                         
©¸©¤©¤ src                                              
    ©¸©¤©¤ mian
        ©À©¤©¤ java                                    
        ©¦    ©¸©¤©¤ uk.ac.ncl.echo_wave_algorithms
        ©¦        ©À©¤©¤ main
        ©¦        ©¦   ©À©¤©¤ Algorithms.java            // algorithms main method     
        ©¦        ©¦   ©¸©¤©¤ Main.java                  // entrance of the project
        ©¦        ©À©¤©¤ module
        ©¦        ©¦   ©À©¤©¤ Graph.java                 // create graph 
        ©¦        ©¦   ©¸©¤©¤ Node.java                  // create node 
        ©¦        ©¸©¤©¤  tool
        ©¦            ©À©¤©¤ FileUtil.java              // read file (json) 
        ©¦            ©À©¤©¤ MatrixTool.java            // matrix tool 
        ©¦            ©¸©¤©¤ RandomTool.java            // random tool
        ©¦        
        ©¦
        ©¦
        ©¸©¤©¤ resource
            ©À©¤©¤ doughnutGraph.json                  // Graph 1 
            ©À©¤©¤ normalGraph.json                    // Graph 2 
            ©¸©¤©¤ treeGraph.json                      // Graph 3 
            
###########overall
The project program by Java, and contain three packet (main, module, tool). The project creates the graph structure by json file or console input(default json file). The message passing records by matrix. The matrix implements by ujmp.jar. 

###########use
The entrance of the algorithms project is in main(), 
the main method is in Algorithms.algorithms().
User use main() to create graph and call algorithms() to execute algorithms

###########Graph
There are two method to create graph:
1.	Json
The method is Graph.createStructureByJson(String).
User pass the graph json to create graph, there are three graph json have been created( normalGraph, treeGraph, doughnutGraph).

2.	Input
The method is Graph.createStructureByInput().
User input based on the prompy in console.

###########Node

Node have four state, the algorithms act by the state.

state 0: no wake up
state 1: have received a message from its neighbor (wake up)
state 2: have sent message to neighbor excepted its father
state 3: have received all message from its neighbor excepted its father and send message to its father

the state change by the message in matrix in Algorithms.class.



