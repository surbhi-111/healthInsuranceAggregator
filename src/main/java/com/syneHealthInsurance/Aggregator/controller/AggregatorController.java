package com.syneHealthInsurance.Aggregator.controller;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syneHealthInsurance.Aggregator.Service.AggregatorService;
import com.syneHealthInsurance.Aggregator.entity.Aggregator;
import com.syneHealthInsurance.Aggregator.entity.ResponseTypeEntity;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;


@RestController
@CrossOrigin(origins = "*")
public class AggregatorController {

	@Autowired
	RestTemplate restTemp;
	
	@Autowired
	AggregatorService AggreService;

	//EXTRA METHOD FOR OTHER FILTER OPERATIONS	

	//	@GetMapping(path = "/api/aggregator/byAgeAndMonthlyPremium/{age}/{monthPrimium}")
//	public Object getFilterdListByAgeAndMonthPremium(@PathVariable Integer age, @PathVariable Double monthPrimium)
//	{		
//		List<String> apiURL= AggreService.getApiUrl();
//		
//	    List<Object> ObjectList = new ArrayList<>();
//		
//	    for(String api : apiURL)
//		{
//	    	System.out.println("Inside the method of API");
//	      String newApi = api.replace("{age}", age.toString()).replace("{monthPrimium}", monthPrimium.toString());
//	    
//			System.out.println(newApi);
//			if(restTemp.getForObject(newApi,Object.class)!=null)
//			{
//	    	   ObjectList.add(restTemp.getForObject(newApi,Object.class));
//			}
//			
//		}
//    	  //Object policy =   restTemp.getForObject(url,Object.class);
//	      return ObjectList;
//		 
//	}
	
	public ResponseTypeEntity transformation(String ApiURL, String responseFormat,String responseType)
	{
		String jsonArr= null;
         
		ObjectMapper obj = new ObjectMapper();
			ResponseTypeEntity ResultObj = new ResponseTypeEntity();
 	   try {
 		   
			 jsonArr = obj.writeValueAsString(restTemp.getForObject(ApiURL,Object.class));
			 JSONArray jsonArray = new JSONArray(jsonArr);				 				 
			  System.out.println(jsonArr);
			  ObjectMapper objectMap =  new ObjectMapper();
			   
			   try {
		   			  ResponseTypeEntity resp = objectMap.readValue(responseFormat, ResponseTypeEntity.class);
		   		   
		   			  System.out.println(resp.getPlanName());
		   		      System.out.println(resp.getPlanNumber());
		  
		   		   if (jsonArray.length() > 0) 
		   		   {
						for (int i=0; i<jsonArray.length(); i++) 
						{
							JSONObject objTest = jsonArray.getJSONObject(i);
							System.out.println(objTest.get(resp.getPlanName()));
							System.out.println(objTest.get(resp.getPlanNumber()));
							System.out.println(objTest.get(resp.getLifeCover()));
							ResultObj.setPlanNumber("PLAN NUMBER - " + objTest.get(resp.getPlanNumber()));
							
							ResultObj.setPlanName("PLAN NAME - "+ objTest.get(resp.getPlanName())); 
							ResultObj.setLifeCover("PLAN AMOUNT - "+ objTest.get(resp.getLifeCover())); 
					
						}
					}
		   	    } 
		   	    catch (JsonMappingException e) {
		   			e.printStackTrace();
		   		}
		   	    catch (JsonProcessingException e) {
		   			e.printStackTrace();
		   		}
			
 	   }
 	  catch (RestClientException e1) {
			
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			
			e1.printStackTrace();
		}
 	    return ResultObj;
	}
	
	@GetMapping(path = "/api/aggregator/byAgeAndType/{age}/{type}")
	public List<ResponseTypeEntity> getFilteredListByAgeAndType(@PathVariable Integer age,@PathVariable String type)
	{
		
    	List<Aggregator> CompanyList =  AggreService.getCompanyDetails();
        List<ResponseTypeEntity> resultList = new ArrayList<ResponseTypeEntity>();	
		for(Aggregator agg: CompanyList)
		{
		 ResponseTypeEntity	resultObj = transformation(agg.getApiURL().replace("{age}",age.toString()).replace("{type}",type),
				                          agg.getResponseFormat(),agg.getResponseType());
		resultList.add(resultObj);
		resultObj=null;
		}
	      return resultList;
	}
	
	
	@GetMapping(path= "/api/aggregator/register")
	public List<Aggregator> getAllCompany()
	{
		return AggreService.getCompanyDetails();
	}
	
	@PostMapping(path="/api/aggregator/register")
	public Aggregator registerToAggregator(@RequestBody Aggregator entity)
	{
		return AggreService.registerCompany(entity);
		
	}

}
