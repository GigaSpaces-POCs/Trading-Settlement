package com.gigaspaces.settlement.messaging;

import java.io.StringWriter;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;
import org.openspaces.events.notify.NotifyType;

import com.gigaspaces.settlement.model.MatchedDeal;

@EventDriven
@Notify
@NotifyType(write = true)
public class MessageGenerator {

	private static final Log LOG = LogFactory.getLog(MessageGenerator.class);

	@EventTemplate
	MatchedDeal unprocessedData() {
		return new MatchedDeal();
	}

	@SpaceDataEvent
	public MatchedDeal eventListener(MatchedDeal matchedDeal) {
		LOG.info("Received new Matched Deal: " + matchedDeal.getReference()+ " at " + System.currentTimeMillis());
		generateMatchedDealXmlMessage(matchedDeal);
		return matchedDeal;
	}

	private void generateMatchedDealXmlMessage(MatchedDeal matchedDeal) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("MatchedDeal");
		root.addElement("Reference").addText(matchedDeal.getReference());
		root.addElement("BuySideEntity")
				.addText(matchedDeal.getBuySideEntity());
		root.addElement("SellSideEntity").addText(
				matchedDeal.getSellSideEntity());
		root.addElement("BuySideAmount").addText(
				matchedDeal.getBuySideAmount().toString());
		root.addElement("SellSideAmount").addText(
				matchedDeal.getSellSideAmount().toString());
		root.addElement("BuySideAccount").addText(
				matchedDeal.getBuySideAccount());
		root.addElement("SellSideAccount").addText(
				matchedDeal.getSellSideAccount());
		root.addElement("Instrument").addText(matchedDeal.getInstrument());
		root.addElement("ValueDate").addText(
				matchedDeal.getValueDate().toString());

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setSuppressDeclaration(true);
		
		XMLWriter xmlWriter;
		StringWriter writer = new StringWriter();
		try {
			xmlWriter = new XMLWriter(writer, format);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOG.info("Generated XML Message: " + writer.toString());

		matchedDeal.setProperty("MessageGeneratedDate", new Date());
		matchedDeal.setProperty("XmlMessage", writer.toString());
	}

}
