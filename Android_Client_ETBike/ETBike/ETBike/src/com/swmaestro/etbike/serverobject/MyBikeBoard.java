package com.swmaestro.etbike.serverobject;

import java.util.Date;
import java.util.Set;




public class MyBikeBoard {
	
	private String bikeImagePath;
	private String bikeType;
	private String category;
	private String content;
	
	private String costPerTime;
	private String costPerDay;
	private String costPerWeek;
	
	private String lati;
	private String longi;
	
	private String myImagePath;
	private Set<Reply> replies;

	private String shareType;
	private String title;		
	private String tradeType;

	private String updatedTime;
	private String updatedTimestamp;
	private String writer;
	
	/*
	 * generated by kim doo
	 */
	private String bikeImagePathThumb;
	private int viewType;
	private String strMyImgPath;
	public String getStrMyImgPath() {
		return strMyImgPath;
	}


	public void setStrMyImgPath(String strMyImgPath) {
		this.strMyImgPath = strMyImgPath;
	}
	private String strBikeImagePath;
	private String strBikeImageThumbPath;
	/*
	 * added by tae woong
	 */
	private int likeCount;
	
	private String dealWith;
	
	public MyBikeBoard(String shareType ,int viewType) {
		this.shareType = shareType;
		this.viewType = viewType;
	}
	
	
	public MyBikeBoard(String strBikeImageThumbPath, String content, String tradeType, String shareType) {
		this.strBikeImageThumbPath = strBikeImageThumbPath;
		this.content = content;
		this.tradeType = tradeType;
		this.shareType = shareType;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public String getStrBikeImageThumbPath() {
		return strBikeImageThumbPath;
	}
	public String getDealWith() {
		return dealWith;
	}


	public void setDealWith(String dealWith) {
		this.dealWith = dealWith;
	}


	public void setStrBikeImageThumbPath(String strBikeImageThumbPath) {
		this.strBikeImageThumbPath = strBikeImageThumbPath;
	}
	public String getStrBikeImagePath() {
		return strBikeImagePath;
	}
	public void setStrBikeImagePath(String strBikeImagePath) {
		this.strBikeImagePath = strBikeImagePath;
	}
	public String getBikeImagePathThumb() {
		return bikeImagePathThumb;
	}
	public void setBikeImagePathThumb(String bikeImagePathThumb) {
		this.bikeImagePathThumb = bikeImagePathThumb;
	}
	public int getViewType() {
		return viewType;
	}
	public void setViewType(int viewType) {
		this.viewType = viewType;
	}
	public String getBikeImagePath() {
		return bikeImagePath;
	}
	public void setBikeImagePath(String bikeImagePath) {
		this.bikeImagePath = bikeImagePath;
	}
	public String getBikeType() {
		return bikeType;
	}
	public void setBikeType(String bikeType) {
		this.bikeType = bikeType;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCostPerTime() {
		return costPerTime;
	}
	public void setCostPerTime(String costPerTime) {
		this.costPerTime = costPerTime;
	}
	public String getCostPerDay() {
		return costPerDay;
	}
	public void setCostPerDay(String costPerDay) {
		this.costPerDay = costPerDay;
	}
	public String getCostPerWeek() {
		return costPerWeek;
	}
	public void setCostPerWeek(String costPerWeek) {
		this.costPerWeek = costPerWeek;
	}
	public String getLati() {
		return lati;
	}
	public void setLati(String lati) {
		this.lati = lati;
	}
	public String getLongi() {
		return longi;
	}
	public void setLongi(String longi) {
		this.longi = longi;
	}
	public String getMyImagePath() {
		return myImagePath;
	}
	public void setMyImagePath(String myImagePath) {
		this.myImagePath = myImagePath;
	}
	
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public String getShareType() {
		return shareType;
	}
	public void setShareType(String shareType) {
		this.shareType = shareType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getUpdatedTimestamp() {
		return updatedTimestamp;
	}
	public void setUpdatedTimestamp(String updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}

	
	
	
	
	
	
	



   
}
