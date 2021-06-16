package com.neoris.tcl.services;

import com.neoris.tcl.models.HfmRollupEntries;
import com.neoris.tcl.security.models.User;
import com.neoris.tcl.websocket.IWebSocketService;

public interface IRollUpProcessService {

	// void processRollUps(List<HfmRollupEntries> lstRollUps, List<HfmRollupEntries> lstSelectedRollups);

	void setWebSocketService(IWebSocketService webSocketService);

	void setService(IHfmRollupEntriesService service);

	void setUser(User user);

	void processRollUp(HfmRollupEntries hfmRollupEntries);

}