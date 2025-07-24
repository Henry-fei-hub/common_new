package com.delicacy.client.ui;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.HTML;
import com.smartgwt.client.data.Record;

public class AbstractPrint {
	
	public HTML generatePrintHTML(){
		return new HTML();
	}
	
	protected Map printData = new HashMap();
	protected Record processInformation = new Record();

	public Record getProcessInformation() {
		return processInformation;
	}

	public void setProcessInformation(Record processInformation) {
		this.processInformation = processInformation;
	}

	public Map getPrintData() {
		return printData;
	}

	public void setPrintData(Map printData) {
		this.printData = printData;
	}

}
