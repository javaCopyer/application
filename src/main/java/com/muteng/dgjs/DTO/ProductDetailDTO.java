package com.muteng.dgjs.DTO;

public class ProductDetailDTO {
//productlsit
	private String title;//
	private String longtitle;//标题
	private String address;//地址
	private String minsalary;//最低工资
	private String maxsalary;//最高工资
	private String logo;//图片
	private String pictures;
	private String factoryname;


	//product_compensationwelfare
	private Long paidwayid;//计薪方式id
	private String extrawork;//加班
	private	String fullfrequentlyaward;//满勤奖
	private	Integer	performance;//绩效
	private Integer positivetime;//转正时间；
	private	String	nightgrant;//夜班补助
	private	Integer	positivebasesalary;//转正底薪
	private	Integer	payday;//发薪日
	private	Integer	beginday;//开始时间
	private	Integer	endday;//结束时间
	private	String bank;//银行
	private	String transactor;//办理方

	private String compensationwelfare;

	public void setCompensationwelfare(String compensationwelfare) {
		this.compensationwelfare = compensationwelfare;
	}
	public String getCompensationwelfare() {
		StringBuffer compensationwelfare = new StringBuffer();
		if(boardwages!=null && boardwages != "" &&  !boardwages.contains("无")){
			compensationwelfare.append("伙食情况:"+boardwages).append("</br>");
		}
		if(extrawork!=null && extrawork != "" && !extrawork.contains("不限")){
			compensationwelfare.append(extrawork).append("</br>");;
		}
		if(fullfrequentlyaward != null && fullfrequentlyaward!=""){
			compensationwelfare.append("满勤奖："+fullfrequentlyaward+"元").append("</br>");;
		}
		if(nightgrant != null && nightgrant != ""){
			compensationwelfare.append("夜班补助："+nightgrant+"元/日").append("</br>");;
		}
		if(performance == null || performance == 0){
			compensationwelfare.append("转正时间：入职即转正").append("</br>");;
		} else {
			compensationwelfare.append("转正时间：入职"+positivetime+"天转正").append("</br>");;
		}
		if(positivebasesalary != null && positivebasesalary != 0){
			compensationwelfare.append("底薪："+positivebasesalary+"元").append("</br>");;
		}
		if(payday != null && payday != 0){
			compensationwelfare.append("发薪日：每月"+payday+"日");
		}
		if(beginday !=null && beginday != 0){
			compensationwelfare.append("发"+beginday+"号");
		}
		if(endday !=null && endday != 0){
			compensationwelfare.append("至"+endday+"号的工资").append("</br>");
		}
		if(bank != null && bank != ""){
			if(bank.contains("银行")){
				compensationwelfare.append("工资卡："+bank).append("</br>");
			}else{
				compensationwelfare.append("工资卡："+bank+"银行").append("</br>");
			}
		}

		if(transactor != null && transactor != ""){
			compensationwelfare.append("银行卡："+transactor+"办理").append("</br>");
		}
		return compensationwelfare.toString();
	}


	//product_jobrequirements
	private String sex;//性别
	private Integer minage;//最大年龄
	private Integer maxage;//最小年龄
	private Integer womanminage;//女性最低年龄
	private Integer womanmaxage;//女性最高年龄
	private Long nationid;//名族id
	private Long englishcharactersid;//英文汉字id
	private Long simplearithmeticid;//简单算术id
	private String surdomute;//聋哑人
	private Long tattoosmokeid;//纹身烟疤id
	private String criminalrecord;//案底
	private String returnfactory;//接受返厂
	private String boardwages;

	private String jobrequirements;

	public String getJobrequirements() {
		StringBuffer jobrequirements = new StringBuffer();
		if(sex != null && sex != ""){
			jobrequirements.append("性别："+sex).append("</br>");
		}
		if(minage != null && minage != 0){
			jobrequirements.append("男性年龄范围："+minage+"-"+maxage).append("</br>");
		}
		if(womanminage !=null && womanminage != 0){
			jobrequirements.append("女性年龄范围："+womanminage+"-"+womanmaxage).append("</br>");
		}
		if(surdomute != null  && surdomute != ""){
			jobrequirements.append("聋哑人："+surdomute).append("</br>");
		}
		if(criminalrecord != null && criminalrecord != ""){
			jobrequirements.append("案底："+criminalrecord).append("</br>");
		}
		if(returnfactory != null && returnfactory != ""){
			jobrequirements.append("返厂："+returnfactory).append("</br>");
		}
		return jobrequirements.toString();
	}

	public void setJobrequirements(String jobrequirements) {
		this.jobrequirements = jobrequirements;
	}

	// 	product_workingcondition
	private String production;//生产产品
	private String jobcontent;//工作内容
	private Long pipelineid;//流水线id
	private Long dutyid;//站班坐班id
	private String workingcondition;

	public String getWorkingcondition() {
		StringBuffer workingcondition = new StringBuffer();
		if(production != null){
			workingcondition.append("生产产品："+production).append("</br>");
		}
		if(jobcontent != null){
			workingcondition.append("工作内容："+jobcontent).append("</br>");
		}

		return workingcondition.toString();
	}

	public void setWorkingcondition(String workingcondition) {
		this.workingcondition = workingcondition;
	}

	//product_interviewsituation
	private String idcard;//身份证要求
	private String diploma;//毕业证要求
	private String copies;//复印件要求
	private String photo;//照片要求
	private String examination;//体检

	private String interviewsituation;

	public String getInterviewsituation() {
		StringBuffer interviewsituation = new StringBuffer();
		if(idcard != null && !idcard.trim().equals("")){
			interviewsituation.append("身份证要求：携带"+idcard).append("</br>");
		}
		if(copies != null && !copies.trim().equals("")){
			interviewsituation.append("身份证复印件：携带"+copies).append("</br>");
		}
		if(diploma != null && !diploma.trim().equals("")){
			interviewsituation.append("毕业证要求：携带"+diploma).append("</br>");
		}
		if(photo != null && !photo.trim().equals("")){
			interviewsituation.append("照片要求：携带"+photo).append("</br>");
		}
		if(examination != null && !examination.trim().equals("")){
			interviewsituation.append("体检："+examination).append("</br>");
		}

		return interviewsituation.toString();
	}

	public void setInterviewsituation(String interviewsituation) {
		this.interviewsituation = interviewsituation;
	}


	//	 合同
	private String compact;//合同
	private String insurance;//保险
	private String fiveinsurance;//五险
	private String gold;//一金
	private String factorycontent;//工厂信息
	private String compactions;

	public String getCompactions() {
		StringBuffer compactions = new StringBuffer();
		if(compact != null ){
			compactions.append("合同："+compact).append("</br>");
		}
		if(fiveinsurance != null && !fiveinsurance.equals("0")){
			compactions.append("五险："+fiveinsurance+"缴纳").append("</br>");
		}
		if(gold != null && !gold.equals("0")){
			compactions.append("公积金："+gold+"缴纳").append("</br>");
		}
		return compactions.toString();
	}

	public void setCompactions(String compactions) {
		this.compactions = compactions;
	}

	//奖励
	private int shopamount;//店主奖励
	private int lifecontent;//生活补贴
	private int day;
	private Long money;//返费
	private int memberamount;//会员奖励
	private int inviterAmount;//邀请奖励

	//吃住安排
	private String restaurant;//清真餐厅
	private	String	accommodation;//住宿安排
	private Long roomid; //房间类型
	private String airconditioner;// 空调
	private String restroom;//卫生间
	private String eatAndLive;

	public String getEatAndLive() {
		StringBuffer eatAndLive = new StringBuffer();
		if(restaurant != null && !restaurant.equals("没有")){
			eatAndLive.append("清真餐厅："+restaurant).append("</br>");
		}
		if(accommodation != null ){
			eatAndLive.append("住宿安排："+accommodation).append("</br>");
		}
		if(airconditioner != null ){
			eatAndLive.append("空调："+airconditioner).append("</br>");
		}
		if(restroom != null ){
			eatAndLive.append("卫生间："+restroom).append("</br>");
		}
		return eatAndLive.toString();
	}


	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getInviterAmount() {
		return inviterAmount;
	}

	public void setInviterAmount(int inviterAmount) {
		this.inviterAmount = inviterAmount;
	}

	public void setEatAndLive(String eatAndLive) {
		this.eatAndLive = eatAndLive;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLongtitle() {
		return longtitle;
	}

	public void setLongtitle(String longtitle) {
		this.longtitle = longtitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMinsalary() {
		return minsalary;
	}

	public void setMinsalary(String minsalary) {
		this.minsalary = minsalary;
	}

	public String getMaxsalary() {
		return maxsalary;
	}

	public void setMaxsalary(String maxsalary) {
		this.maxsalary = maxsalary;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}

	public String getFactoryname() {
		return factoryname;
	}

	public void setFactoryname(String factoryname) {
		this.factoryname = factoryname;
	}

	public Long getPaidwayid() {
		return paidwayid;
	}

	public void setPaidwayid(Long paidwayid) {
		this.paidwayid = paidwayid;
	}

	public String getExtrawork() {
		return extrawork;
	}

	public void setExtrawork(String extrawork) {
		this.extrawork = extrawork;
	}

	public String getFullfrequentlyaward() {
		return fullfrequentlyaward;
	}

	public void setFullfrequentlyaward(String fullfrequentlyaward) {
		this.fullfrequentlyaward = fullfrequentlyaward;
	}

	public Integer getPerformance() {
		return performance;
	}

	public void setPerformance(Integer performance) {
		this.performance = performance;
	}

	public String getNightgrant() {
		return nightgrant;
	}

	public void setNightgrant(String nightgrant) {
		this.nightgrant = nightgrant;
	}

	public Integer getPositivebasesalary() {
		return positivebasesalary;
	}

	public void setPositivebasesalary(Integer positivebasesalary) {
		this.positivebasesalary = positivebasesalary;
	}

	public Integer getPayday() {
		return payday;
	}

	public void setPayday(Integer payday) {
		this.payday = payday;
	}

	public Integer getBeginday() {
		return beginday;
	}

	public void setBeginday(Integer beginday) {
		this.beginday = beginday;
	}

	public Integer getEndday() {
		return endday;
	}

	public void setEndday(Integer endday) {
		this.endday = endday;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getMinage() {
		return minage;
	}

	public void setMinage(Integer minage) {
		this.minage = minage;
	}

	public Integer getMaxage() {
		return maxage;
	}

	public void setMaxage(Integer maxage) {
		this.maxage = maxage;
	}

	public Integer getWomanminage() {
		return womanminage;
	}

	public void setWomanminage(Integer womanminage) {
		this.womanminage = womanminage;
	}

	public Integer getWomanmaxage() {
		return womanmaxage;
	}

	public void setWomanmaxage(Integer womanmaxage) {
		this.womanmaxage = womanmaxage;
	}

	public Long getNationid() {
		return nationid;
	}

	public void setNationid(Long nationid) {
		this.nationid = nationid;
	}

	public Long getEnglishcharactersid() {
		return englishcharactersid;
	}

	public void setEnglishcharactersid(Long englishcharactersid) {
		this.englishcharactersid = englishcharactersid;
	}

	public Long getSimplearithmeticid() {
		return simplearithmeticid;
	}

	public void setSimplearithmeticid(Long simplearithmeticid) {
		this.simplearithmeticid = simplearithmeticid;
	}

	public String getSurdomute() {
		return surdomute;
	}

	public void setSurdomute(String surdomute) {
		this.surdomute = surdomute;
	}

	public Long getTattoosmokeid() {
		return tattoosmokeid;
	}

	public void setTattoosmokeid(Long tattoosmokeid) {
		this.tattoosmokeid = tattoosmokeid;
	}

	public String getCriminalrecord() {
		return criminalrecord;
	}

	public void setCriminalrecord(String criminalrecord) {
		this.criminalrecord = criminalrecord;
	}

	public String getReturnfactory() {
		return returnfactory;
	}

	public void setReturnfactory(String returnfactory) {
		this.returnfactory = returnfactory;
	}

	public String getBoardwages() {
		return boardwages;
	}

	public void setBoardwages(String boardwages) {
		this.boardwages = boardwages;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getJobcontent() {
		return jobcontent;
	}

	public void setJobcontent(String jobcontent) {
		this.jobcontent = jobcontent;
	}

	public Long getPipelineid() {
		return pipelineid;
	}

	public void setPipelineid(Long pipelineid) {
		this.pipelineid = pipelineid;
	}

	public Long getDutyid() {
		return dutyid;
	}

	public void setDutyid(Long dutyid) {
		this.dutyid = dutyid;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public String getCopies() {
		return copies;
	}

	public void setCopies(String copies) {
		this.copies = copies;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getExamination() {
		return examination;
	}

	public void setExamination(String examination) {
		this.examination = examination;
	}

	public String getCompact() {
		return compact;
	}

	public void setCompact(String compact) {
		this.compact = compact;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getFiveinsurance() {
		return fiveinsurance;
	}

	public void setFiveinsurance(String fiveinsurance) {
		this.fiveinsurance = fiveinsurance;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getFactorycontent() {
		return factorycontent;
	}

	public void setFactorycontent(String factorycontent) {
		this.factorycontent = factorycontent;
	}

	public int getShopamount() {
		return shopamount;
	}

	public void setShopamount(int shopamount) {
		this.shopamount = shopamount;
	}

	public int getLifecontent() {
		return lifecontent;
	}

	public void setLifecontent(int lifecontent) {
		this.lifecontent = lifecontent;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public int getMemberamount() {
		return memberamount;
	}

	public void setMemberamount(int memberamount) {
		this.memberamount = memberamount;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public String getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public Long getRoomid() {
		return roomid;
	}

	public void setRoomid(Long roomid) {
		this.roomid = roomid;
	}

	public String getAirconditioner() {
		return airconditioner;
	}

	public void setAirconditioner(String airconditioner) {
		this.airconditioner = airconditioner;
	}

	public String getRestroom() {
		return restroom;
	}

	public void setRestroom(String restroom) {
		this.restroom = restroom;
	}

	public Integer getPositivetime() {
		return positivetime;
	}

	public void setPositivetime(Integer positivetime) {
		this.positivetime = positivetime;
	}
}
