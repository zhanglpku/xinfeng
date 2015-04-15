package clientTest;

import javax.xml.namespace.QName;

import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

public class clientTest {

	public static void main(String[] args) {
		String url = "http://localhost:8080/xinfeng/services/SensorControlService";
		String qName = "http://webservice.xinfeng.pku.com";
		String result = null;
		try {
			// 使用RPC方式调用WebService  
			RPCServiceClient serviceClient = new RPCServiceClient();
			Options options = serviceClient.getOptions();
			// 指定调用WebService的URL  
			EndpointReference targetEPR = new EndpointReference(url);
			options.setTo(targetEPR);

			//getSensorCommands(String equipId)
			QName opAddEntry = new QName(qName, "getSensorCommands");
			Object[] opAddEntryArgs = new Object[] { "111" };
			Class[] classes = new Class[] { String.class };
//			result = (String) serviceClient.invokeBlocking(opAddEntry,
//					opAddEntryArgs, classes)[0];
//			System.out.println(result + "9999");
			
			//saveSensorData(String equipId, String commands)
			opAddEntry = new QName(qName, "saveSensorData"); 
			opAddEntryArgs = new Object[] { "id","{\"name\":\"reiz\",\"sex\":\"nan\"}" };  
			result = (String) serviceClient.invokeBlocking(opAddEntry,
					opAddEntryArgs, classes)[0];
			System.out.println(result + "9999");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
