/*
 * Created on 2 thg 10 2017 ( Time 15:10:01 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.ecomileage.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import org.ecomileage.bean.Report;
import org.ecomileage.bean.jpa.ReportEntity;
import java.util.Date;
import org.ecomileage.business.service.ReportService;
import org.ecomileage.business.service.mapping.ReportServiceMapper;
import org.ecomileage.data.repository.jpa.ReportJpaRepository;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of ReportService
 */
@Component
@Transactional
public class ReportServiceImpl implements ReportService {

	@Resource
	private ReportJpaRepository reportJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private ReportServiceMapper reportServiceMapper;
	
	@Override
	public Report findById(Integer id) {
		ReportEntity reportEntity = reportJpaRepository.findOne(id);
		return reportServiceMapper.mapReportEntityToReport(reportEntity);
	}

	@Override
	public ReportEntity findOne(Integer id) {
		ReportEntity reportEntity = reportJpaRepository.findOne(id);
		return reportEntity;
	}

	@Override
	public Page<ReportEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return reportJpaRepository.findAll(request);
	}

	@Override
	public List<Report> findAll() {
		Iterable<ReportEntity> entities = reportJpaRepository.findAll();
		List<Report> beans = new ArrayList<Report>();
		for(ReportEntity reportEntity : entities) {
			beans.add(reportServiceMapper.mapReportEntityToReport(reportEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = reportJpaRepository.count();
		return count;
	}

	@Override
	public Report save(Report report) {
		return update(report) ;
	}

	@Override
	public Report create(Report report) {
/*
		ReportEntity reportEntity = reportJpaRepository.findOne(report.getId());
		if( reportEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		reportEntity = new ReportEntity();
		reportServiceMapper.mapReportToReportEntity(report, reportEntity);
		ReportEntity reportEntitySaved = reportJpaRepository.save(reportEntity);
		return reportServiceMapper.mapReportEntityToReport(reportEntitySaved);
*/
		ReportEntity reportEntity = new ReportEntity();
		reportServiceMapper.mapReportToReportEntity(report, reportEntity);
		ReportEntity reportEntitySaved = reportJpaRepository.save(reportEntity);
		return reportServiceMapper.mapReportEntityToReport(reportEntitySaved);
	}

	@Override
	public Report update(Report report) {
		ReportEntity reportEntity = reportJpaRepository.findOne(report.getId());
		reportServiceMapper.mapReportToReportEntity(report, reportEntity);
		ReportEntity reportEntitySaved = reportJpaRepository.save(reportEntity);
		return reportServiceMapper.mapReportEntityToReport(reportEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		reportJpaRepository.delete(id);
	}

	public ReportJpaRepository getReportJpaRepository() {
		return reportJpaRepository;
	}

	public void setReportJpaRepository(ReportJpaRepository reportJpaRepository) {
		this.reportJpaRepository = reportJpaRepository;
	}

	public ReportServiceMapper getReportServiceMapper() {
		return reportServiceMapper;
	}

	public void setReportServiceMapper(ReportServiceMapper reportServiceMapper) {
		this.reportServiceMapper = reportServiceMapper;
	}

	@Override
	public Page<ReportEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return reportJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	



	@Override
	public Page<ReportEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size) {

		Specification<ReportEntity> spec = new Specification<ReportEntity>() {
			@Override
			public Predicate toPredicate(Root<ReportEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (params.get("key") != null) {
				String key = String.valueOf(params.get("key"));
					if (params.get("type") != null) {
						String type = String.valueOf(params.get("type"));
						Predicate predicate = cb.equal(root.get(type), "%"+key+"%");
						predicates.add(predicate);
					}
				}

				if(params.get("startTime") != null){
					String startTime = String.valueOf(params.get("startTime"));
					Predicate predicate = cb.equal(root.<Date>get("createDate"),DataUtils.parseDate(startTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if(params.get("endTime") != null){
					String endTime = String.valueOf(params.get("endTime"));
					Predicate predicate = cb.equal(root.<Date>get("createDate"),DataUtils.parseDate(endTime, DataUtils.FormatDate.YYYYMMDD.getPattern()));
					predicates.add(predicate);
				}
				
				if (params.get("reporterName") != null && params.get("reporterName") != "") {
					String key = String.valueOf(params.get("reporterName"));
					Predicate predicate =cb.like(root.get("user").get("username"), "%"+key+"%") ;
					predicates.add(predicate);
				}
				
				if (params.get("categoryId") != null && params.get("categoryId") != "") {
					String key = String.valueOf(params.get("categoryId"));
					Predicate predicate =cb.equal(root.get("category").get("id"), key);
					predicates.add(predicate);
				}

				if (params.get("status") != null && params.get("status") != "") {
					String status = String.valueOf(params.get("status"));
					Predicate predicate = cb.equal(root.get("status"), status);
					predicates.add(predicate);
				}
				return cb.and(predicates.toArray(new Predicate[] {}));
			}

		};

		Order order = new Order(Direction.DESC, "createDate");
		if (params.get("orderBy") != null) {
			String orderBy = String.valueOf(params.get("orderBy"));
			order = new Order(Direction.DESC, orderBy);
		}

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(order));
		return reportJpaRepository.findAll(spec, pageable);
	}

}