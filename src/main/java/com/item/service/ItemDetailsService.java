package com.item.service;

import java.util.List;

import com.item.model.Item;
import com.item.model.ItemDetails;

public interface ItemDetailsService {
	
	boolean saveItemDetails(ItemDetails itemDetails);
	
	ItemDetails loadItemDetails(int itemId);
	

}
