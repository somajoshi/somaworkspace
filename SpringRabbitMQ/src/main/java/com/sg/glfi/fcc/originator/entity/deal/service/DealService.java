package com.sg.glfi.fcc.originator.entity.deal.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.sg.glfi.fcc.originator.entity.deal.domain.Deal;
import com.sg.glfi.fcc.originator.entity.deal.message.DealMessageProducer;

@Path("/deals")
public class DealService {

	private static Map<String, Deal> deals = new HashMap<String, Deal>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    static {  
          
        Deal deal1 = new Deal();  
        deal1.setId("12");  
        deal1.setDealAmount(100000);  
        deal1.setDealName("nat-input-export");  
        deal1.setDealPriced(true);
        deal1.setOpportunityId(123);
        try {
			deal1.setDealExpectedClosure(sdf.parse("2015-04-20"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        deals.put(deal1.getId(), deal1);
        
        Deal deal2 = new Deal();  
        deal2.setId("13");  
        deal2.setDealAmount(300000);  
        deal2.setDealName("nat-input-import");  
        deal2.setDealPriced(true);
        deal2.setOpportunityId(124);
        try {
			deal2.setDealExpectedClosure(sdf.parse("2015-04-20"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        deals.put(deal2.getId(), deal2);           

    } 
        
    @GET  
    @Path("/listdeals")  
    @Produces("application/xml")  
    public List<Deal> listDeals(){  
        return new ArrayList<Deal>(deals.values());  
    }  
      
    @GET  
    @Path("/{dealid}")  
    @Produces("application/xml")  
    public Deal getList(@PathParam("dealid")String dealId){  
        return deals.get(dealId);          
    }  
      
    @GET  
    @Path("/json/deals/")  
    @Produces("application/json")  
    public List<Deal> listDealsJSON(){  
        return new ArrayList<Deal>(deals.values());  
    }  
 
    @GET  
    @Path("/json/{dealid}")  
    @Produces("application/json")  
    public Deal getDealJSON(@PathParam("dealid")String dealId){  
        return deals.get(dealId);          
    }
    
    @POST  
    @Path("/updatedeal")  
    @Produces("application/json")  
    public String updateDealJSON(final Deal input){  
        DealMessageProducer dmp = new DealMessageProducer();
        dmp.send(input);
    	return "success";          
    }  

}
