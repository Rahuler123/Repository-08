package com.recentPageApi;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecentPageRepo extends JpaRepository<RecentPage,Long>{

	Page<RecentPage> findByBlogName(String blogName, Pageable pageable);
	


}
