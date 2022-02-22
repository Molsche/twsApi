package myapi;


import com.ib.client.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MyReceiver implements EWrapper {
	
	private MyConnection my_client;

    private MyDatastore __MyDatastore;

    public MyReceiver(MyDatastore MyDatastore) {
        __MyDatastore = MyDatastore;
    }
    
	private MyConnection client() {
		return my_client; 
		}
	
	
	private void startMsgProcessingThread() {
		final EReaderSignal signal = new EJavaSignal();		
		final EReader reader = new EReader(client(), signal);
		
		reader.start();
		
		new Thread(() -> {
            while (client().isConnected()) {
                signal.waitForSignal();
                try {
                    reader.processMsgs();
                } catch (IOException e) {
                    error(e);
                }
            }
        }).start();
	}
    @Override
    public void tickPrice(int tickerId, int field, double price, TickAttrib attrib) {

    }

    @Override
    public void tickSize(int tickerId, int field, int size) {

    }

    @Override
    public void tickOptionComputation(int i, int i1, double v, double v1, double v2, double v3, double v4, double v5, double v6, double v7) {

    }

    @Override
    public void tickGeneric(int tickerId, int tickType, double value) {

        
    }

    @Override
    public void tickString(int i, int i1, String s) {

    }

    @Override
    public void tickEFP(int i, int i1, double v, String s, double v1, int i2, String s1, double v2, double v3) {

    }

    @Override
    public void openOrder(int i, Contract contract, Order order, OrderState orderState) {

    }

    @Override
    public void openOrderEnd() {

    }

    @Override
    public void updateAccountValue(String s, String s1, String s2, String s3) {

    }

    @Override
    public void updatePortfolio(Contract contract, double v, double v1, double v2, double v3, double v4, double v5, String s) {

    }

    @Override
    public void updateAccountTime(String s) {

    }

    @Override
    public void accountDownloadEnd(String s) {

    }

    @Override
    public void nextValidId(int id) {

    }

    @Override
    public void contractDetails(int i, ContractDetails contractDetails) {

    }

    @Override
    public void bondContractDetails(int i, ContractDetails contractDetails) {

    }

    @Override
    public void contractDetailsEnd(int i) {

    }

    @Override
    public void execDetails(int i, Contract contract, Execution execution) {

    }

    @Override
    public void execDetailsEnd(int i) {

    }

    @Override
    public void updateMktDepth(int i, int i1, int i2, int i3, double v, int i4) {

    }

    @Override
    public void updateNewsBulletin(int i, int i1, String s, String s1) {

    }

    @Override
    public void managedAccounts(String s) {

    }

    @Override
    public void receiveFA(int i, String s) {

    }


    @Override
    public void scannerParameters(String s) {

    }

    @Override
    public void scannerData(int i, int i1, ContractDetails contractDetails, String s, String s1, String s2, String s3) {

    }

    @Override
    public void scannerDataEnd(int i) {

    }

    @Override
    public void realtimeBar(int i, long l, double v, double v1, double v2, double v3, long l1, double v4, int i1) {

    }

    @Override
    public void currentTime(long l) {

    }

    @Override
    public void fundamentalData(int i, String s) {

    }

    @Override
    public void deltaNeutralValidation(int i, DeltaNeutralContract deltaNeutralContract) {

    }

    @Override
    public void tickSnapshotEnd(int i) {

    }

    @Override
    public void marketDataType(int i, int i1) {

    }

    @Override
    public void commissionReport(CommissionReport commissionReport) {

    }

    @Override
    public void position(String s, Contract contract, double v, double v1) {

    }

    @Override
    public void positionEnd() {

    }

    @Override
    public void accountSummary(int reqId, String account, String tag,
            String value, String currency) {
        System.out.println("Acct Summary. ReqId: " + reqId + ", Acct: " + account + ", Tag: " + tag + ", Value: " + value + ", Currency: " + currency);
    }

    @Override
    public void accountSummaryEnd(int reqId) {
        System.out.println("AccountSummaryEnd. Req Id: "+reqId+"\n");
    }

    @Override
    public void verifyMessageAPI(String s) {

    }

    @Override
    public void verifyCompleted(boolean b, String s) {

    }

    @Override
    public void verifyAndAuthMessageAPI(String s, String s1) {

    }

    @Override
    public void verifyAndAuthCompleted(boolean b, String s) {

    }

    @Override
    public void displayGroupList(int i, String s) {

    }

    @Override
    public void displayGroupUpdated(int i, String s) {

    }

    @Override
    public void error(Exception e) {
        System.err.println("IBBroker error " + e.getMessage());
    }

    @Override
    public void error(String str) {
        System.err.println("IBBroker error " + str);
    }

    @Override
    public void error(int id, int errorCode, String errorMsg)
    {
        System.err.println("IB Error: " + id + " " + errorCode + " " + errorMsg);

        // Some data farm is connected/disconnected, I just need to ignore it
        if (Arrays.asList(2103, 2104, 2105, 2106, 2108).contains(errorCode))
            return;

        if (errorCode == 200) {
            System.err.println("IBBroker error: " + id + "," + errorCode + "," + errorMsg);
            return;
        }

        // Order cancelled, can't modify a filled order, order held while securities are located, cancel attempted
        // when order is not in a cancellable state, Unable to modify this order as its still being processed
        if (Arrays.asList(201, 202, 104, 404, 161, 2102).contains(errorCode))
            return;

        if (errorMsg == "" || errorMsg == null)
            return;

        // Happens when IB kills the order for whatever reason, and then I try to modify it
        if (errorCode == 103)
            return;

        // Duplicate ticker id
        if (errorCode == 322 && errorMsg.contains("Duplicate ticker id"))
            return;

        // Requested market data is not subscribed, not sure why I get this - maybe 2
        // seconds isn't enough to subscribe and unsubscribe
        if (errorCode == 354 || errorCode == 300)
            return;

        // ib disconnected, ib connection restored
        if (errorCode == 1100 || errorCode == 1102)
            return;

        if (errorCode == 0 && errorMsg.contains("Warning: Approaching max rate of 50 messages per second")) {
            System.out.println("IB approaching max rate of messages per second");
            return;
        }

        // Final fail
        
        //System.exit(0);											// ------------------------------------ ??? Ausgeklammert (//) 06.09.21
    }

    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectAck() {

    }

    @Override
    public void positionMulti(int i, String s, String s1, Contract contract, double v, double v1) {

    }

    @Override
    public void positionMultiEnd(int i) {

    }

    @Override
    public void accountUpdateMulti(int i, String s, String s1, String s2, String s3, String s4) {

    }

    @Override
    public void accountUpdateMultiEnd(int i) {

    }

    @Override
    public void securityDefinitionOptionalParameter(int i, String s, int i1, String s1, String s2, Set<String> set, Set<Double> set1) {

    }

    @Override
    public void securityDefinitionOptionalParameterEnd(int i) {

    }

    @Override
    public void softDollarTiers(int i, SoftDollarTier[] softDollarTiers) {

    }

	@Override
	public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice,
			int permId, int parentId, double lastFillPrice, int clientId, String whyHeld, double mktCapPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price,
			int size, boolean isSmartDepth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalData(int reqId, Bar bar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void familyCodes(FamilyCode[] familyCodes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void symbolSamples(int reqId, ContractDescription[] contractDescriptions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalDataEnd(int reqId, String startDateStr, String endDateStr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickNews(int tickerId, long timeStamp, String providerCode, String articleId, String headline,
			String extraData) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void smartComponents(int reqId, Map<Integer, Entry<String, Character>> theMap) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickReqParams(int tickerId, double minTick, String bboExchange, int snapshotPermissions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newsProviders(NewsProvider[] newsProviders) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void newsArticle(int requestId, int articleType, String articleText) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalNews(int requestId, String time, String providerCode, String articleId, String headline) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalNewsEnd(int requestId, boolean hasMore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void headTimestamp(int reqId, String headTimestamp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void histogramData(int reqId, List<HistogramEntry> items) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalDataUpdate(int reqId, Bar bar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rerouteMktDataReq(int reqId, int conId, String exchange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rerouteMktDepthReq(int reqId, int conId, String exchange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void marketRule(int marketRuleId, PriceIncrement[] priceIncrements) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pnl(int reqId, double dailyPnL, double unrealizedPnL, double realizedPnL) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pnlSingle(int reqId, int pos, double dailyPnL, double unrealizedPnL, double realizedPnL, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalTicks(int reqId, List<HistoricalTick> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalTicksBidAsk(int reqId, List<HistoricalTickBidAsk> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalTicksLast(int reqId, List<HistoricalTickLast> ticks, boolean done) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickByTickAllLast(int reqId, int tickType, long time, double price, int size,
			TickAttribLast tickAttribLast, String exchange, String specialConditions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickByTickBidAsk(int reqId, long time, double bidPrice, double askPrice, int bidSize, int askSize,
			TickAttribBidAsk tickAttribBidAsk) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickByTickMidPoint(int reqId, long time, double midPoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderBound(long orderId, int apiClientId, int apiOrderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completedOrder(Contract contract, Order order, OrderState orderState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completedOrdersEnd() {
		// TODO Auto-generated method stub
		
	}
}