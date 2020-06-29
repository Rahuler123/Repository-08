package com.recentPageApi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class RecentPageService {
	
	private final RecentPageRepo RecentPageRepo;
	
	
	@Autowired
	public RecentPageService (RecentPageRepo RecentPageRepo ) {
		this.RecentPageRepo = RecentPageRepo;
		
	}
	public List<RecentPage> findAll(){
		return RecentPageRepo.findAll();
	}
	
	public Optional <RecentPage> findById(Long blogId){
		return RecentPageRepo.findById(blogId);
		
	}
	 
	public RecentPage save (RecentPage RecentPg) {
		return RecentPageRepo.save(RecentPg);
	}
	
	
	public void deleteById(Long blogId) {
		RecentPageRepo.deleteById(blogId);
		
	}
	

}
