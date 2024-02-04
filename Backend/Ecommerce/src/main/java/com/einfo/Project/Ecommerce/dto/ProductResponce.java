package com.einfo.Project.Ecommerce.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class ProductResponce {
 
	private List<Producdto> content;
	private int pagenumber;
	private int pageSize;
	private Long  totalElement;
	private int totalpages;
	@Override
	public String toString() {
		return "ProductResponce [content=" + content + ", pagenumber=" + pagenumber + ", pageSize=" + pageSize
				+ ", totalElement=" + totalElement + ", totalpages=" + totalpages + ", lastpages=" + lastpages + "]";
	}
	private boolean lastpages;
	
}
