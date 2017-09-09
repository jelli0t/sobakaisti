/**
 * 
 */
package org.sobakaisti.util;

/**
 * @author jelli0t
 *
 */
public class Pagination {

	public static final int DEFAULT_ITEMS_PER_PAGE = 15;
	
	private int maxPages;
	private int currentPage = 1;
	private int initialItem;
	private int itemsPerPage;
	private int maxItems;
	
	public Pagination() {};
		
	public Pagination(int maxPages, int currentPage, int itemsPerPage) {
		this.maxPages = maxPages;
		this.currentPage = currentPage;
		this.itemsPerPage = itemsPerPage;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getItemsPerPage() {
		if(itemsPerPage == 0) {
			this.itemsPerPage = DEFAULT_ITEMS_PER_PAGE;
		}
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public void setMaxItems(int maxItems) {
		this.maxPages = (int) Math.ceil((double) maxItems / (double) getItemsPerPage());		
		this.maxItems = maxItems;
	}	
	
	public int getInitialItem() {
		if(getCurrentPage() == 1) {
			this.initialItem = 0;
		}else {
			this.initialItem = getItemsPerPage() * (getCurrentPage()-1);
		}
		return initialItem;
	}

	public void setInitialItem(int initialItem) {
		this.initialItem = initialItem;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("maxPages:"+maxPages+", ");
		sb.append("currentPage:"+currentPage+", ");
		sb.append("initialItem:"+getInitialItem()+", ");
		sb.append("itemsPerPage:"+getItemsPerPage()+", ");
		sb.append("maxItems:"+maxItems);
		return sb.append('}').toString();
	}
}
