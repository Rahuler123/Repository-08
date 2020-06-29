package com.recentPageApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RecentPage")
public class RecentPageController {
	
	@Autowired
	RecentPageRepo  RecentPageRepo;
	
	
private final RecentPageService RecentPageService;
	
	@Autowired
	public RecentPageController(RecentPageService RecentPageService) {
		this.RecentPageService = RecentPageService;
	}
	
	
	
	 private Sort.Direction getSortDirection(String direction) {
		    if (direction.equals("asc")) {
		      return Sort.Direction.ASC;
		    } else if (direction.equals("desc")) {
		      return Sort.Direction.DESC;
		    }

		    return Sort.Direction.ASC;
		  }
	
	@GetMapping
	public ResponseEntity<List<RecentPage>> findAll() {
		return ResponseEntity.ok(RecentPageService.findAll());
	}
	
	@GetMapping("/RecentPage")
	public ResponseEntity<Map<String, Object>> getAllPages(
			@RequestParam(required = false) String blogName,
		      @RequestParam(defaultValue = "0") int page,
		      @RequestParam(defaultValue = "3") int size,
		      @RequestParam(defaultValue = "blogId,desc") String[] sort) {
		try {
		      List<Order> orders = new ArrayList<Order>();

		      if (sort[0].contains(",")) {
		        
		        for (String sortOrder : sort) {
		          String[] _sort = sortOrder.split(",");
		          orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
		        }
		      } else {
		      
		        orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		      }

		      List<RecentPage> RecentPages = new ArrayList<RecentPage>();
		      Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

		      Page<RecentPage> pageTuts;
		      if (blogName == null)
		        pageTuts = RecentPageRepo.findAll(pagingSort);
		      else
		        pageTuts = RecentPageRepo.findAll(pagingSort);

		      RecentPages = pageTuts.getContent();

		      if (RecentPages.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		
		Map<String, Object> response = new HashMap<>();
		response.put("RecentPages",RecentPages);
		response.put("currentPage", pageTuts.getNumber());
		response.put("totalBlogs", pageTuts.getTotalElements());
	    response.put("totalPages", pageTuts.getTotalPages());
	    return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
	    @GetMapping("/{blogId}")
		public ResponseEntity <RecentPage> findById(@RequestBody @PathVariable Long blogId){
		Optional<RecentPage> recentPage = RecentPageService.findById(blogId);
		return ResponseEntity.ok(RecentPageService.findById(blogId).get());
			 
		}
	    
	    
	    @PostMapping("/create")
		 public ResponseEntity <RecentPage> create (@RequestBody RecentPage recentPage){
			 return ResponseEntity.status(HttpStatus.CREATED).body(RecentPageService.save(recentPage));
		}
	    
	    
	    
	    @PutMapping("/{blogId}")
		public ResponseEntity <RecentPage> update (@PathVariable Long blogId, @RequestBody RecentPage recentPage){
			return ResponseEntity.accepted().body(RecentPageService.save(recentPage));
		}
	    

		@DeleteMapping("/{blogId}")
		public ResponseEntity delete(@PathVariable Long blogId) {
			RecentPageService.deleteById(blogId);
			return ResponseEntity.accepted().build();
			
		}
}
