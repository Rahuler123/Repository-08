package com.recentPageApi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recentBlog")
public class RecentPage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="blogId")
	private long blogId;
	
	@Column(name="blogName")
	private String blogName;
	
	@Column(name="blogCategory")
	private String blogCategory;
	
	
	public long getBlogId() {
		return blogId;
	}
	public void setBlogId(long blogId) {
		this.blogId = blogId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getBlogCategory() {
		return blogCategory;
	}
	public void setBlogCategory(String blogCategory) {
		this.blogCategory = blogCategory;
	}
	
	@Override
	public String toString() {
		return "RecentPage [blogId=" + blogId + ", blogName=" + blogName + ", blogCategory=" + blogCategory + "]";
	}
	
	public RecentPage() {
		
	}
	
	public RecentPage( String blogName, String blogCategory) {
		this.blogName = blogName;
		this.blogCategory = blogCategory;
	}
	

}
