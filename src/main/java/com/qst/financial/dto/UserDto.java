package com.qst.financial.dto;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String username;
	private String userpwd;
	private String cellPhone;
	private String nickname;
	private String fullName;
	private Date createTime;
	private int source;
	private String lastIp;
	private int userLevel;
	private String headImg;
	private int userLimit;
	private int userType;
	private int loginCount;
	private long rating;
	private int creditrating;
	private String email;
	private Date updateTime;
	private Date lastTime;
	private int setType;
	private String remark;
	private String fback;
	private String cardNo;
	private String address;
	private String QQ;
	private String weChat;
	private int sex;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getWeChat() {
		return weChat;
	}
	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public UserDto() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	public int getUserLimit() {
		return userLimit;
	}
	public void setUserLimit(int userLimit) {
		this.userLimit = userLimit;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	public long getRating() {
		return rating;
	}
	public void setRating(long rating) {
		this.rating = rating;
	}
	public int getCreditrating() {
		return creditrating;
	}
	public void setCreditrating(int creditrating) {
		this.creditrating = creditrating;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public int getSetType() {
		return setType;
	}
	public void setSetType(int setType) {
		this.setType = setType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFback() {
		return fback;
	}
	public void setFback(String fback) {
		this.fback = fback;
	}
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", username=" + username
				+ ", cellPhone=" + cellPhone + ", nickname=" + nickname
				+ ", fullName=" + fullName + ", createTime=" + createTime
				+ ", source=" + source + ", lastIp=" + lastIp + ", userLevel="
				+ userLevel + ", headImg=" + headImg + ", userLimit="
				+ userLimit + ", userType=" + userType + ", loginCount="
				+ loginCount + ", rating=" + rating + ", creditrating="
				+ creditrating + ", email=" + email + ", updateTime="
				+ updateTime + ", lastTime=" + lastTime + ", setType="
				+ setType + ", remark=" + remark + ", fback=" + fback
				+ ", cardNo=" + cardNo + ", address=" + address + ", QQ=" + QQ
				+ ", weChat=" + weChat + ", sex=" + sex + "]";
	}

}
