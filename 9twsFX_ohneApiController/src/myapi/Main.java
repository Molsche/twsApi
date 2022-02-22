package myapi;

import com.ib.client.EClientSocket;
import com.ib.client.EJavaSignal;
import com.ib.client.EReaderSignal;


// Main ohne FX

public class Main {

//	public static String host = "127.0.0.1";
//	public static int port = 7497;
//	public static int clientId = 0;
	
	
    public static void main(String[] args) throws InterruptedException {
//        // Signal processing with TWS, we will not be using it
//        EReaderSignal readerSignal = new EJavaSignal();
//
//        MyDatastore MyDatastore = new MyDatastore(null);
//      
//        MyReceiver MyReceiver = new MyReceiver(MyDatastore);
//        EClientSocket clientSocket = new EClientSocket(MyReceiver, readerSignal);
//        MyBroker ibBroker = new MyBroker(clientSocket, MyDatastore);
//        
//
//		
//        try
//        {
//        	System.out.println("Initial Socket Status: +++++  " + ibBroker.isBrokerConnected());
//            ibBroker.connect(host, port, clientId);
//
//            // Wait for nextValidId
//            for (int i=0; i<10; i++) {
//                if (ibBroker.isBrokerConnected() == true) {
//                	
//                	System.out.println("   Socket : -------  " + ibBroker.isBrokerConnected());
//                	
//                	break;
//                }
//                
//                System.out.print(i);
//                System.out.println("   Socket : -------  " + ibBroker.isBrokerConnected());
//                
//                Thread.sleep(1000);
//            }
//            
//
//            // From here you can add the logic of your application
//        }
//        catch(Exception ex)
//        {
//        	
//            System.err.println(ex + "/nIs not connected");
//
//        }
//        finally
//        {
//        	clientSocket.reqAccountSummary(9001, "All", "AccountType,NetLiquidation,TotalCashValue,SettledCash,AccruedCash,"
//        			+ "BuyingPower,EquityWithLoanValue,PreviousEquityWithLoanValue,GrossPositionValue,ReqTEquity,ReqTMargin,"
//        			+ "SMA,InitMarginReq,MaintMarginReq,AvailableFunds,ExcessLiquidity,Cushion,FullInitMarginReq,FullMaintMarginReq,"
//        			+ "FullAvailableFunds,FullExcessLiquidity,LookAheadNextChange,LookAheadInitMarginReq ,LookAheadMaintMarginReq,"
//        			+ "LookAheadAvailableFunds,LookAheadExcessLiquidity,HighestSeverity,DayTradesRemaining,Leverage");
//        	
//        	System.out.println("It was connected");
//            
//        }
//        for (int i=0; i<10; i++) {
//        	if(i==1) {
//        		ibBroker.createOrder("AAPL", "SMART", 1, 170, true);
//        		System.out.println("bought");
//        	}
//        	if(i==9) {
//        		ibBroker.createOrder("AAPL", "SMART", 1, 120, false);
//        		System.out.println("sold");
//        	}
//            System.out.println(i);
//            Thread.sleep(2000);
//        }
//        
//        ibBroker.disconnect();
//        System.out.println("Connection closed");
//        System.exit(0);
//        
    }

}