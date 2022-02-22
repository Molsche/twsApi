package myapi;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.ib.client.Order;
import com.ib.controller.ApiController;

public class MyBroker {

//    private EClientSocket __clientSocket;
//    private ApiController __MyDatastore;
//
//    // You should push these to some config
//    private static final int __clientId = 0;
//    private static final String __ibAccount = "DU3723063"; // This is your IB account
//
//    public MyBroker(EClientSocket clientSocket, ApiController MyDatastore) {
//        __clientSocket = clientSocket;
//        __MyDatastore = MyDatastore;
//    }
//
//    public void connect(String host, int port, int clientId) {
//        // ip_address, port, and client ID. Client ID is used to identify the app that connects to TWS, you can
//        // have multiple apps connect to one TWS instance
//        __clientSocket.eConnect(host,port,clientId);
//        __clientSocket.reqIds(-1);
//
//    }
//
//    public void subscribeQuoteData(int tickerId, String symbol, String exchange) {
//        Contract contract = __createContract(symbol, exchange);
//
//        // We are asking for additional shortable (236) and fundamental ratio (258) information.
//        // The false says that we don't want a snapshot, we want a streaming feed of data.
//        // https://interactivebrokers.github.io/tws-api/classIBApi_1_1EClient.html#a7a19258a3a2087c07c1c57b93f659b63
//        __clientSocket.reqMktData(tickerId, contract, "236,258", false, false, null);
//    }
//
//    public void createOrder(String symbol, String exchange, int quantity, double price, boolean buy)
//    {
//        Contract contract = __createContract(symbol, exchange);
//
//        int orderId = __MyDatastore.nextValidId(orderId);;
//
//        // https://interactivebrokers.github.io/tws-api/classIBApi_1_1Order.html
//        Order order = new Order();
//        order.clientId(__clientId);
//        order.transmit(true);
//        order.orderType("LMT");
//        order.orderId(orderId);
//        order.action(buy ? "BUY" : "SELL");
//        order.totalQuantity(quantity);
//        order.lmtPrice(price);
//        order.account(__ibAccount);
//        order.hidden(false);
//        order.minQty(100);
//
//        __clientSocket.placeOrder(orderId, contract, order);
//
//        // We can either request the next valid orderId or just increment it
//        __MyDatastore.nextValidId++;
//    }
//    
//    //create a specific order with Inputs: orderType, transmit (if "Bracket Order", da erst übermitteln, wenn letzte Order erstellt)
//    private void createSpecificOrder(String symbol, String exchange, int quantity, double price, boolean buy, boolean transmit, String orderType) 
//    {
//        Contract contract = __createContract(symbol, exchange);
//
//        int orderId = __MyDatastore.nextValidId;
//        
//        // https://interactivebrokers.github.io/tws-api/classIBApi_1_1Order.html
//        Order order = new Order();
//        order.clientId(__clientId);
//        order.transmit(transmit);
//        order.orderType(orderType);
//        order.orderId(orderId);
//        order.action(buy ? "BUY" : "SELL"); // if true -> buy, false = sell
//        order.totalQuantity(quantity);
//        order.lmtPrice(price);
//        order.account(__ibAccount);
//        order.hidden(false);
//        order.minQty(100);
//
//        __clientSocket.placeOrder(orderId, contract, order);
//
//        // We can either request the next valid orderId or just increment it
//        __MyDatastore.nextValidId++;	
//    	
//    }
//    	  	
//
//    public void disconnect() {
//        __clientSocket.eDisconnect();
//    }
//
//    protected Contract __createContract(String symbol, String exchange) {
//        // https://interactivebrokers.github.io/tws-api/classIBApi_1_1Contract.html
//        return new Contract(0, symbol, "STK", null, 0.0d, null,
//                null, exchange, "USD", null, null, null,
//                "SMART", false, null, null);
//    }
//    
//    protected Contract createSpezificContract(String symbol, String exchange, String currency) 
//    {
//    	/*
//    	 * 
//		 *  com.ib.client.Contract.Contract(int p_conId, String p_symbol, String p_secType, String p_lastTradeDateOrContractMonth, 
//		 *  double p_strike, String p_right, String p_multiplier, String p_exchange, String p_currency, String p_localSymbol, 
//		 *  String p_tradingClass, List<ComboLeg> p_comboLegs, String p_primaryExch, boolean p_includeExpired, 
//		 *  String p_secIdType, String p_secId)
//    	 */
//        return new Contract(0, symbol, "STK", null, 0.0d, null,
//                null, exchange, currency, null, null, null,
//                "SMART", false, null, null);
//    }
//    
//    public synchronized boolean isBrokerConnected() {
//		return __clientSocket.isConnected();
//	}
    

}
