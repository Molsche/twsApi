package myapi;

import java.util.HashMap;
import com.ib.client.EClient;
import com.ib.controller.ApiConnection.ILogger;
import com.ib.controller.ApiController;

import application.SampleController;




public class MyDatastore extends ApiController {

	public MyDatastore(SampleController handler, ILogger inLogger, ILogger outLogger) {
		super(handler, inLogger, outLogger);
		// TODO Auto-generated constructor stub
	}
	
//	public MyDatastore(IConnectionHandler handler) {
//		super(handler);
//		// TODO Auto-generated constructor stub
//	}
//
//
//	private int firstId = 1;
//	
//    private HashMap<Integer, Tick> __ticks = new HashMap<Integer, Tick>();
//
//	public Integer nextValidId = -1;
//
//    public Tick getLatestTick(int symbolId) {
//        return __ticks.get(symbolId);
//    }
}