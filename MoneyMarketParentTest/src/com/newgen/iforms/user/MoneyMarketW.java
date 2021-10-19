package com.newgen.iforms.user;

import com.cp.main.CommercialPaper;
import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import com.newgen.utils.Commons;
import com.newgen.utils.Constants;
import com.newgen.utils.LogGen;
import com.initiator.worksteps.*;
import org.apache.log4j.Logger;

public class MoneyMarketW implements IFormListenerFactory, Constants {
private Logger logger = LogGen.getLoggerInstance(MoneyMarketW.class);
	@Override
	public IFormServerEventHandler getClassInstance(IFormReference ifr) {
		// TODO Auto-generated method stub
		IFormServerEventHandler objActivity  = null;
		String processName = ifr.getProcessName();
		logger.info("processName: "+processName);
		String activityName =ifr.getActivityName();
		logger.info("activityName: "+activityName);
		String process = Commons.getProcess(ifr);
		logger.info("process: "+process);

		try {
		if (processName.equalsIgnoreCase(ProcessName)){
			if (Commons.isEmpty(process)){
				switch (activityName){
					case treasuryOfficerInitiator:{
						objActivity = new TreasuryOfficerInitiator();
						break;
					}
					case branchInitiator:{
						objActivity = new BranchInitiator();
						break;
					}
				}
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
