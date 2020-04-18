package com.h3b.investment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.h3b.investment.exception.ResourceNotFoundException;
import com.h3b.investment.model.Iof;
import com.h3b.investment.repository.IofRepository;

@Service
public class IofService {
	@Autowired
	private IofRepository iofRepository;
	
	public List<Iof> listIofs(Integer pageNo, Integer pageSize, String sortBy){
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Iof> pagedResult = iofRepository.findAll(paging);
		
		if(pagedResult.hasContent()) {
	        return pagedResult.getContent();
	    } else {
	    	return new ArrayList<Iof>();
	    }
	}

	public Iof getIofByNrDay(int nrDay) throws ResourceNotFoundException {
		Iof iof = iofRepository.findById(nrDay)
				.orElseThrow(
						()-> new ResourceNotFoundException ("Iof not found for this day: "+nrDay)
				);
		return iof;
	}
}
