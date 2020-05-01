package com.syneHealthInsurance.Aggregator.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.syneHealthInsurance.Aggregator.entity.Aggregator;

@Repository
public interface AggregatorRepo extends JpaRepository<Aggregator, Long> {

	@Query("SELECT api.apiURL FROM Aggregator api")
	public List<String> getApiUrl();
	
	
}
