package com.sg.glfi.fcc.originator.entity.deal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.sg.glfi.fcc.originator.entity.deal.domain.Deal;
import com.sg.glfi.fcc.originator.entity.deal.service.DealService;


public class DealTest {
	
	@Test
	public void updateDeal() throws ParseException {
		DealService ds = new DealService();
		Deal input = new Deal();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		input.setId("12");
		input.setDealName("test-deal");
		input.setDealAmount(1000000);
		input.setDealPriced(true);
		input.setOpportunityId(123);
		input.setDealExpectedClosure(sdf.parse("2015-04-20"));
		ds.updateDealJSON(input);
	}

}
