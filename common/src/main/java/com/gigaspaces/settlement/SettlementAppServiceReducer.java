package com.gigaspaces.settlement;

import java.util.ArrayList;
import java.util.List;

import org.openspaces.remoting.RemoteResultReducer;
import org.openspaces.remoting.SpaceRemotingInvocation;
import org.openspaces.remoting.SpaceRemotingResult;

public class SettlementAppServiceReducer implements RemoteResultReducer<Object, Object> {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object reduce(SpaceRemotingResult<Object>[] spaceRemotingResults,
			SpaceRemotingInvocation remotingInvocation) throws Exception {
		// TODO Auto-generated method stub
		if (remotingInvocation.getMethodName().equals("getMatchedDeals") ||
				remotingInvocation.getMethodName().equals("getTrades") ||
				remotingInvocation.getMethodName().equals("getCounterpartyTrades")) {
            List accumulatedResult = new ArrayList();
            for (SpaceRemotingResult<Object> result : spaceRemotingResults) {
                if (result.getException() != null) {
                    throw (Exception) result.getException();
                }
                Object ret = result.getResult();

                if (ret != null)
                    accumulatedResult.addAll((List) ret);
            }
            return accumulatedResult;
        }
		
		//Otherwise return first result
        if (spaceRemotingResults[0].getException() != null)
            throw (Exception) spaceRemotingResults[0].getException();
        return spaceRemotingResults[0].getResult();
	}
	
	

}
