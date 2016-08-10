package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import static com.gigaspaces.settlement.web.blotter.shared.Constants.BUY_SIDE_ACCOUNT;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.BUY_SIDE_AMOUNT;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.BUY_SIDE_ENTITY;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.INSTRUMENT;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.MATCH_DATE;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.MESSAGE_GENERATION_DATE;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.SELL_SIDE_AMOUNT;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.SELL_SIDE_ENTITY;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.VALUE_DATE;
import static com.gigaspaces.settlement.web.blotter.shared.Constants.XML_MESSAGE;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AutoFitWidthApproach;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.StringUtil;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MatchedDealBlotterListGrid extends ListGrid {

	public MatchedDealBlotterListGrid() {
		setWidth100();
		setHeight("25%");

		setShowAllRecords(true);
		setBodyOverflow(Overflow.VISIBLE);
		setOverflow(Overflow.VISIBLE);  
		setAutoFitWidthApproach(AutoFitWidthApproach.BOTH);  
		setCanEdit(false);
		setCanExpandRecords(true);
		setCanDragRecordsOut(false);
		setHoverWidth(200);
		setHoverHeight(20);
		setSelectionType(SelectionStyle.SINGLE);
		setBaseStyle("myOtherGridCellOver");

		ListGridField buySideEntityField = new ListGridField(BUY_SIDE_ENTITY, BUY_SIDE_ENTITY);
		ListGridField sellSideEntityField = new ListGridField(SELL_SIDE_ENTITY,
				SELL_SIDE_ENTITY);
		ListGridField instrument = new ListGridField(INSTRUMENT, INSTRUMENT);
		ListGridField buySideAmount = new ListGridField(BUY_SIDE_AMOUNT,
				BUY_SIDE_AMOUNT);
		buySideAmount.setShowGridSummary(true);
		buySideAmount.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat("#,##0.00");
					return "" + nf.format(((Number) value).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}

			}
		});

		ListGridField sellSideAmount = new ListGridField(SELL_SIDE_AMOUNT,
				SELL_SIDE_AMOUNT);
		sellSideAmount.setShowGridSummary(true);
		sellSideAmount.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				if (value == null)
					return null;
				try {
					NumberFormat nf = NumberFormat.getFormat("#,##0.00");
					return "" + nf.format(((Number) value).doubleValue());
				} catch (Exception e) {
					return value.toString();
				}

			}
		});

		ListGridField buySideAccount = new ListGridField(BUY_SIDE_ACCOUNT,
				BUY_SIDE_ACCOUNT);
		ListGridField sellSideAccount = new ListGridField(BUY_SIDE_ACCOUNT,
				BUY_SIDE_ACCOUNT);

		ListGridField valueDate = new ListGridField(VALUE_DATE, VALUE_DATE);
		valueDate.setType(ListGridFieldType.DATE);
		valueDate.setDateFormatter(DateDisplayFormat.TOUSSHORTDATE);
		valueDate.setAlign(Alignment.CENTER);
		ListGridField matchDate = new ListGridField(MATCH_DATE, MATCH_DATE);
		matchDate.setType(ListGridFieldType.DATE);
		matchDate.setCellFormatter(new CustomDateFormatter(MATCH_DATE));
		matchDate.setAlign(Alignment.CENTER);
		ListGridField xmlMessage = new ListGridField(XML_MESSAGE, XML_MESSAGE);
		xmlMessage.setHidden(true);
		ListGridField messageCreateDate = new ListGridField(MESSAGE_GENERATION_DATE, MESSAGE_GENERATION_DATE);
		messageCreateDate.setType(ListGridFieldType.DATE);
		messageCreateDate.setCellFormatter(new CustomDateFormatter(MESSAGE_GENERATION_DATE));
		messageCreateDate.setHidden(true);

		setShowGridSummary(false);
		setGroupStartOpen(GroupStartOpen.ALL);

		setFields(buySideEntityField, sellSideEntityField, instrument,
				buySideAmount, sellSideAmount, buySideAccount, sellSideAccount,
				matchDate, valueDate, xmlMessage, messageCreateDate);

		draw();
	}

	@Override
	protected Canvas getExpansionComponent(final ListGridRecord record) {
		DateTimeFormat fmt = DateTimeFormat.getFormat("MM/dd/yyyy HH:mm:ss SSS");
		
		Label label = new Label();
		label.setValign(VerticalAlignment.TOP);
		label.setContents("<b>Message Generation Date: "
				+ StringUtil.asHTML(fmt.format(record.getAttributeAsDate(MESSAGE_GENERATION_DATE)))
				+ "<br>"
				+ StringUtil.asHTML(record.getAttributeAsString(XML_MESSAGE))
				+ "</b>");
		addChild(label);
		// label.setRect(22, 6, 165, 23);

		// Label messageLabel = new Label();
		// messageLabel.setValign(VerticalAlignment.TOP);
		// messageLabel.setContents(StringUtil.asHTML(record.getAttributeAsString(XML_MESSAGE)));
		// addChild(messageLabel);
		// // messageLabel.setRect(22, 35, 327, 54);

		return label;
	}
}
