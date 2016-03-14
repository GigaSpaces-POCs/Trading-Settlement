package com.gigaspaces.settlement.web.blotter.client.layoutmanager;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class SearchForm extends DynamicForm{

	private IButton searchButton;
	private TextItem counterpartyTextItem;
	
	public SearchForm(){
		setWidth("250px");
		setNumCols(4);
		counterpartyTextItem = new TextItem();
		counterpartyTextItem.setTextAlign(Alignment.LEFT);
		counterpartyTextItem.setTitleAlign(Alignment.RIGHT);
		counterpartyTextItem.setName("searchEntity");
		counterpartyTextItem.setTitle("Search By Counterparty: ");
		counterpartyTextItem.setWrapTitle(false);
		
		setFields(counterpartyTextItem);
	}
	
    public IButton getSearchButton() {
		return searchButton;
	}

	public void setSearchButton(IButton searchButton) {
		this.searchButton = searchButton;
	}

	public void addFindListener(ClickHandler handler) {
		searchButton.addClickHandler(handler);
    }
	
	public TextItem getCounterpartyTextItem() {
		return counterpartyTextItem;
	}

	public void setCounterpartyTextItem(TextItem entityTextItem) {
		this.counterpartyTextItem = entityTextItem;
	}
}
