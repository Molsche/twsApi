package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

import com.ib.controller.ApiController;
import com.ib.controller.ApiConnection.ILogger;
import com.ib.contracts.StkContract;
import com.ib.client.EWrapper;
import com.ib.client.MarketDataType;


public class SampleController {
	
	/*
	 *          reqMktDataType -> 3/DELAYED muss ich aktivieren, für real time bars etc?
	 */


    @FXML public Button b_standard_connect;
    @FXML public Button b_custom_connect;
    @FXML public Button b_disconnect;
    @FXML public RadioButton b_connected;
    @FXML public RadioButton b_datafarm;
    @FXML public RadioButton b_databank;
    @FXML public Label b_clienetId;
    @FXML public Label b_usedStrats_Num;
    @FXML public Label b_usedStrats_str;
    @FXML public Label b_bestStrat;
    @FXML public Label b_worstStrat;
    @FXML public Label b_winDaily;
    @FXML public Label b_winWeekly;
    @FXML public Label b_winMonthly;
    @FXML public Label b_threadCount;
    @FXML public Button b_showWatchlist;
    @FXML public TextField b_watchlistInput;
    @FXML public Button b_updateWatchlist;
    @FXML public TextArea b_notes;
    @FXML public Pane b_window;
    @FXML public Label b_balance;
    @FXML public Label b_performanceTotal;
    @FXML public Label b_cashInPositions;
    @FXML public Label b_averagePercent;
    @FXML public Label b_totalROI;
    @FXML public Label b_averageROI;
    @FXML public Label b_taxAndFees;
    @FXML public Label b_positionsTotal;
    @FXML public Label b_positionsOpen;
    @FXML public Label b_positionsWatched;
    @FXML public TextArea b_logWindow;


    private final List<String> m_acctList = new ArrayList<>();
	List<String> accountList() 	{ return m_acctList; }
    
    
    
    
	ILogger getInLogger()            { return m_inLogger; }
	ILogger getOutLogger()           { return m_outLogger; }

    //ApiController Konstruktor
    ApiController ApiConn = new ApiController(this, getInLogger(), getOutLogger());   
    

    // Variablen
	public String host = "127.0.0.1";
	public int port = 7497;
	public int clientId = 1;
	public boolean b_isConnected = false;

	
	private final Logger m_inLogger = new Logger( b_logWindow);
	private final Logger m_outLogger = new Logger( b_logWindow);
	
	
    @FXML
    void bCustomConnect(ActionEvent event) {
    	ApiConn.reqAccountSummary(9001, "All", "AccountType,NetLiquidation,TotalCashValue,SettledCash,AccruedCash,"
    			+ "BuyingPower,EquityWithLoanValue,PreviousEquityWithLoanValue,GrossPositionValue,ReqTEquity,ReqTMargin,"
    			+ "SMA,InitMarginReq,MaintMarginReq,AvailableFunds,ExcessLiquidity,Cushion,FullInitMarginReq,FullMaintMarginReq,"
    			+ "FullAvailableFunds,FullExcessLiquidity,LookAheadNextChange,LookAheadInitMarginReq ,LookAheadMaintMarginReq,"
    			+ "LookAheadAvailableFunds,LookAheadExcessLiquidity,HighestSeverity,DayTradesRemaining,Leverage");
    	ApiConn.reqTheTime();
    	ApiConn.reqTheAccountUpdates(false);
    	ApiConn.reqThePnL();
    }

    @FXML
    void bDisconnect(ActionEvent event) {
    	ApiConn.disconnect();
    }

    @FXML
    void bShowPositions(ActionEvent event) {

    }

    @FXML
    void bShowWatchlist(ActionEvent event) {
    	System.out.println("bla");
    	StkContract contract = new StkContract(b_watchlistInput.getText());

    	ApiConn.reqMidMarketData(contract,"232" , false, false);		// Richtiger Call, keine Antowrt???????????
    	/*
    	 * The tick type field is case sensitive - it must be BidAsk, Last, AllLast, 
    	 * MidPoint. AllLast has additional trade types such as combos, derivatives,
    	 *  and average price trades which are not included in Last.
    	 */
    	//ApiConn.reqTheTickByTickData(contract, "MidPoint", 100, false); 
    	

    }

    @FXML
    void bStandardConnect(ActionEvent event) {
    	b_watchlistInput.setText("AAPL"); 							// wieder löschen!!!!
    	ApiConn.connect(host, port, clientId, "");

    }

    @FXML
    void bUpdate(ActionEvent event) {
    	ApiConn.reqMktDataType(MarketDataType.DELAYED);
    }
    
    
    //Custom Methods --------------------------------- -------------------------------- --------------------------------

    public void tryLine(String s) {
    	
    	System.out.println(s);
    	
    	b_logWindow.appendText(s + "\n");
    }
    
    public TextArea getLogwindow() {
    	return b_logWindow;
    }
    
    public void tLine(String s) {
    	Platform.runLater(new Runnable() {
			@Override
            public void run() {
				b_logWindow.appendText(s + "\n");
			}
    	});  	
    }
    //------------------------------------------ Logger -----------------------------------.-.-.-.-.-.-.-.-.-.-.-.-
    
	private static class Logger implements ILogger {
		final private TextArea m_area;

		Logger( TextArea area) {
			m_area = area;

		}
		

		@Override public void log(final String str) {
			logLine(str);
			
		}
		
		public void logLine(String s) {
			System.out.println(s + "        logLine \n");	//wieder entfernen dann iwann bla bli bu
			m_area.appendText(s + "\n");
		}

	}

}