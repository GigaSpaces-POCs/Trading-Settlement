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

import com.gigaspaces.settlement.model.OptionMatchedDeal;

@EventDriven
@Notify
@NotifyType(write = true)
public class OptionMessageGenerator {

	private static final Log LOG = LogFactory.getLog(OptionMessageGenerator.class);

	@EventTemplate
	OptionMatchedDeal unprocessedData() {
		return new OptionMatchedDeal();
	}

	@SpaceDataEvent
	public OptionMatchedDeal eventListener(OptionMatchedDeal optionMatchedDeal) {
		LOG.info("Received new Matched Deal: " + optionMatchedDeal.getReference()+ " at " + System.currentTimeMillis());
		generateOptionMatchedDealXmlMessage(optionMatchedDeal);
		return optionMatchedDeal;
	}

	private void generateOptionMatchedDealXmlMessage(OptionMatchedDeal optionMatchedDeal) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("OptionMatchedDeal");
		root.addElement("Reference").addText(optionMatchedDeal.getReference());
		root.addElement("BuySideEntity")
				.addText(optionMatchedDeal.getBuySideEntity());
		root.addElement("SellSideEntity").addText(
				optionMatchedDeal.getSellSideEntity());
		root.addElement("BuySideAmount").addText(
				optionMatchedDeal.getBuySideAmount().toString());
		root.addElement("SellSideAmount").addText(
				optionMatchedDeal.getSellSideAmount().toString());
		root.addElement("BuySideAccount").addText(
				optionMatchedDeal.getBuySideAccount());
		root.addElement("SellSideAccount").addText(
				optionMatchedDeal.getSellSideAccount());
		root.addElement("Instrument").addText(optionMatchedDeal.getInstrument());
		root.addElement("ValueDate").addText(
				optionMatchedDeal.getValueDate().toString());
		root.addElement("OptionType").addText(optionMatchedDeal.getOptionType());
		root.addElement("StrikePrice").addText(optionMatchedDeal.getStrikePrice().toString());
		root.addElement("OptionExpirationDate").addText(
				optionMatchedDeal.getOptionExpirationDate().toString());

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

		LOG.info("Generated Option XML Message: " + writer.toString());

		optionMatchedDeal.setProperty("MessageGeneratedDate", new Date());
		optionMatchedDeal.setProperty("XmlMessage", writer.toString());
	}

}
