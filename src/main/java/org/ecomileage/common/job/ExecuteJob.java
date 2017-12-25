package org.ecomileage.common.job;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.ecomileage.bean.Campaign;
import org.ecomileage.bean.jpa.CampaignEntity;
import org.ecomileage.bean.jpa.TeamEntity;
import org.ecomileage.bean.jpa.TeamMemberEntity;
import org.ecomileage.business.service.CampaignService;
import org.ecomileage.business.service.TeamMemberService;
import org.ecomileage.business.service.TeamService;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.scheduling.annotation.Scheduled;

public class ExecuteJob {
	@Resource
	private CampaignService campaignService;
	
	@Resource
	private TeamMemberService teamMemberService;
	
	@Resource
	private TeamService teamService;
	
	@Scheduled(cron = "0 0 0 * 12 *")
	public void createCampain() {
		Calendar c = Calendar.getInstance();
		c = Calendar.getInstance();
		CampaignEntity cam =  campaignService.findByYear(c.get(Calendar.YEAR));
		if(cam == null) {
			CampaignEntity camBefore =  campaignService.findByYear(c.get(Calendar.YEAR));
			Campaign camNew = new Campaign();
			camNew.setTotalGoalCo2(camBefore.getTotalFactCo2());
			camNew.setTotalGoalTree(camBefore.getTotalGoalTree());
			camNew.setTotalGoalUser(camBefore.getTotalGoalUser());
			camNew.setTotalFactCo2(0f);
			camNew.setTotalFactTree(0);
			camNew.setTotalFactUser(0);
			campaignService.create(camNew);
		}
		
	}
	
	@Scheduled(cron = "0 0 * * * *")
	public void deleteGroup() {
		Calendar c = Calendar.getInstance();
		c = Calendar.getInstance();
		System.out.println(DataUtils.parseStringFromDate(c.getTime(), "yyyy-MM-dd"));
		List<TeamEntity> list = teamService.listByDeleteDate(c.getTime());
		for (TeamEntity i : list) {
			System.out.println("group delete id:"+i.getId());
			teamService.update_isDelete(i.getId());
			teamMemberService.update_isDelete_byGroupId(i.getId());
		}
	}
	
	
}