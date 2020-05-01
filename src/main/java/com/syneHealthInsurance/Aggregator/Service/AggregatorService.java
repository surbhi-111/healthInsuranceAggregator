package com.syneHealthInsurance.Aggregator.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syneHealthInsurance.Aggregator.Repository.AggregatorRepo;
import com.syneHealthInsurance.Aggregator.entity.Aggregator;

@Service
public class AggregatorService  {

	@Autowired
	private AggregatorRepo repo;
	
	
	public Aggregator registerCompany(Aggregator entity)
	{
		return repo.save(entity);
	}
	
	public List<Aggregator> getCompanyDetails()
	{
		return repo.findAll();
	}
	
	public List<String> getApiUrl()
	{
		return repo.getApiUrl();
	}
}
