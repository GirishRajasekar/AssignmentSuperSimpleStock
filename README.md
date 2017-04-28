# AssignmentSuperSimpleStock

Assignment – Super Simple Stocks

Requirements
1.	Provide working source code that will :-
    a.	For a given stock, 
        i.	Given a market price as input, calculate the dividend yield
        ii.	Given a market price as input,  calculate the P/E Ratio
        iii.	Record a trade, with timestamp, quantity of shares, buy or sell indicator and trade price
        iv.	Calculate Volume Weighted Stock Price based on trades in past 15 minutes
    b.	Calculate the GBCE All Share Index using the geometric mean of prices for all stocks
    
Solution

For the above described assignment.I have created the Maven based application using Java 1.8 in Eclipse IDE.

Application Walkthrough.

  1) I have created  main class and one service class.
  2) I have created two Interface StockProcessor and TradeProcessor which contains methods to load,process,display stocks and generate trades.
  3) I have corresponding implementaion class for both the interfaces (InMemoryStockProcessorImpl and GenerateTradeProcessorImpl).
  4) I have one custom exception class,one helper class and one constant class.
  5) I have 3 bean class to hold stock,trade and display variables accordingly.
  6) I have also added JUnit test cases for all possible scenarios.
  
  Starting Point for the application is SuperSimpleStockApp Class which contains the main method.
  
     1) Using factory pattern StockProcessor object is created.
     2) Stock is loaded from Inmemeroy.
     3) Once we get the loaded stock in the list its processsed in the process method.
     4) For each stock dividend yied and P/E Ratio are calculated based on the market price 
        which is defined in the SuperStockConstants class.
     5) For each stock trades are recorded with quantity,price,date and TradeType.
     6) For generating trade GenerateTradeProcessorImpl class is used which has fetchTrade and instruction method.
        i) FechTrade generates the random trades based on the implementation provided in the fecth method.
        ii) Number of trades to generates for a stock is added in the SuperStockConstants class.
     7) Once trade is recorded Volume Weighted Stock Price based on trades in past 15 minutes is calculate.
        the time interval 15 minutes is configurable added in the SuperStockConstants class.
     8)  GBCE All Share Index using the geometric mean of prices for all stocks is calculated.
     9) Finally display method is called to show the output on the console.
     
     10) I haved added log4j .All error and debug message would goto the log file named supersimplestock.log.
     
     
Sample Console Output

----------------------------------------------------------------------------------
Dividend Yield For Stock TEA : 0.0
P/E Ratio For Stock TEA : 0.0
Number of Trade Recorded For Stock TEA : 10
Volume Weighted Price For Stock TEA : 4.143724656601686
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
Dividend Yield For Stock POP : 0.8421052631578947
P/E Ratio For Stock POP : 1.1875
Number of Trade Recorded For Stock POP : 10
Volume Weighted Price For Stock POP : 5.776571262383884
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
Dividend Yield For Stock ALE : 2.4210526315789473
P/E Ratio For Stock ALE : 0.41304347826086957
Number of Trade Recorded For Stock ALE : 10
Volume Weighted Price For Stock ALE : 4.908197350427907
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
Dividend Yield For Stock GIN : 2.1052631578947367
P/E Ratio For Stock GIN : 1.1875
Number of Trade Recorded For Stock GIN : 10
Volume Weighted Price For Stock GIN : 4.89492117162118
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
Dividend Yield For Stock JOE : 1.368421052631579
P/E Ratio For Stock JOE : 0.7307692307692307
Number of Trade Recorded For Stock JOE : 10
Volume Weighted Price For Stock JOE : 5.694827113808316
----------------------------------------------------------------------------------
GBCE All Share Index Price For All Stocks :4.206010919205896

     
     
  




