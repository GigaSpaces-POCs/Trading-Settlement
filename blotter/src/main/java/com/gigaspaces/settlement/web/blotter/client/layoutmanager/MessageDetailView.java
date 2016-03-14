package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.types.VerticalAlignment;

public class MessageDetailView extends Canvas {

	public MessageDetailView() {
		
		Label messageLabel = new Label();
		messageLabel.setValign(VerticalAlignment.TOP);
		messageLabel.setContents("yyyy");
		messageLabel.setTitle("Test");
//		messageLabel.setStyleName(styleName)
		addChild(messageLabel);
		messageLabel.setRect(22, 35, 327, 54);
		
		Label label = new Label();
		label.setValign(VerticalAlignment.TOP);
		label.setTitle("Test");
		label.setContents("yyyy");
		addChild(label);
		label.setRect(22, 6, 165, 23);
		
	}
}
