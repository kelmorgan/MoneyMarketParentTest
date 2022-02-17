package com.newgen.iforms.user;

import com.cp.main.CommercialPaper;
import com.kelmorgan.ibpsformapis.apis.FormApi;
import com.newgen.dashboard.Dashboard;
import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.utils.Constants;
import com.newgen.utils.LogGen;
import org.apache.log4j.Logger;

public class MoneyMarketW implements IFormListenerFactory, Constants {
private final Logger logger = LogGen.getLoggerInstance(MoneyMarketW.class);
	@Override
	public IFormServerEventHandler getClassInstance(IFormReference ifr) {
		// TODO Auto-generated method stub
		IFormServerEventHandler objActivity  = null;
		String processName = FormApi.getProcessName(ifr);
		logger.info("processName: "+processName);
		String process = FormApi.getFieldValue(ifr,selectProcessLocal);
		logger.info("process: "+process);

		try {
		if (processName.equalsIgnoreCase(ProcessName)){
			if (process.isEmpty()){
				objActivity = new Dashboard();
			}
			else {
				switch (process){
					case commercialProcess:{
						objActivity = new CommercialPaper(ifr).getClassInstance();
						break;
					}
					case treasuryProcess:{
						break;
					}
					case omoProcess:{
						break;
					}
				}
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return objActivity;
	}

}
