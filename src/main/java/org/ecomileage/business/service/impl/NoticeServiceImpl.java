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

import org.ecomileage.bean.Notice;
import org.ecomileage.bean.jpa.NoticeEntity;
import java.util.Date;
import org.ecomileage.business.service.NoticeService;
import org.ecomileage.business.service.mapping.NoticeServiceMapper;
import org.ecomileage.data.repository.jpa.NoticeJpaRepository;
import org.ecomileage.web.common.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * Implementation of NoticeService
 */
@Component
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Resource
	private NoticeJpaRepository noticeJpaRepository;

	private static final Integer PAGE_SIZE   = 15;

	@Resource
	private NoticeServiceMapper noticeServiceMapper;
	
	@Override
	public Notice findById(Integer id) {
		NoticeEntity noticeEntity = noticeJpaRepository.findOne(id);
		return noticeServiceMapper.mapNoticeEntityToNotice(noticeEntity);
	}

	@Override
	public NoticeEntity findOne(Integer id) {
		NoticeEntity noticeEntity = noticeJpaRepository.findOne(id);
		return noticeEntity;
	}

	@Override
	public Page<NoticeEntity> findAll(Integer pageNumber) {

		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE,
				new Sort(new Order(Direction.DESC, "id")));
		return noticeJpaRepository.findAll(request);
	}

	@Override
	public List<Notice> findAll() {
		Iterable<NoticeEntity> entities = noticeJpaRepository.findAll();
		List<Notice> beans = new ArrayList<Notice>();
		for(NoticeEntity noticeEntity : entities) {
			beans.add(noticeServiceMapper.mapNoticeEntityToNotice(noticeEntity));
		}
		return beans;
	}

	/**
	 * Count total entit
	 * @return Long
	 */
	public Long countTotal(){
		Long count = noticeJpaRepository.count();
		return count;
	}

	@Override
	public Notice save(Notice notice) {
		return update(notice) ;
	}

	@Override
	public Notice create(Notice notice) {
/*
		NoticeEntity noticeEntity = noticeJpaRepository.findOne(notice.getId());
		if( noticeEntity != null ) {
			throw new IllegalStateException("already.exists");
		}

		noticeEntity = new NoticeEntity();
		noticeServiceMapper.mapNoticeToNoticeEntity(notice, noticeEntity);
		NoticeEntity noticeEntitySaved = noticeJpaRepository.save(noticeEntity);
		return noticeServiceMapper.mapNoticeEntityToNotice(noticeEntitySaved);
*/
		NoticeEntity noticeEntity = new NoticeEntity();
		noticeServiceMapper.mapNoticeToNoticeEntity(notice, noticeEntity);
		NoticeEntity noticeEntitySaved = noticeJpaRepository.save(noticeEntity);
		return noticeServiceMapper.mapNoticeEntityToNotice(noticeEntitySaved);
	}

	@Override
	public Notice update(Notice notice) {
		NoticeEntity noticeEntity = noticeJpaRepository.findOne(notice.getId());
		noticeServiceMapper.mapNoticeToNoticeEntity(notice, noticeEntity);
		NoticeEntity noticeEntitySaved = noticeJpaRepository.save(noticeEntity);
		return noticeServiceMapper.mapNoticeEntityToNotice(noticeEntitySaved);
	}

	@Override
	public void delete(Integer id) {
		noticeJpaRepository.delete(id);
	}

	public NoticeJpaRepository getNoticeJpaRepository() {
		return noticeJpaRepository;
	}

	public void setNoticeJpaRepository(NoticeJpaRepository noticeJpaRepository) {
		this.noticeJpaRepository = noticeJpaRepository;
	}

	public NoticeServiceMapper getNoticeServiceMapper() {
		return noticeServiceMapper;
	}

	public void setNoticeServiceMapper(NoticeServiceMapper noticeServiceMapper) {
		this.noticeServiceMapper = noticeServiceMapper;
	}

	@Override
	public Page<NoticeEntity> listPaging(Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return noticeJpaRepository.listPaging(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	



	@Override
	public Page<NoticeEntity> filterAdmin( Map<String, Object> params, Integer page, Integer size) {

		Specification<NoticeEntity> spec = new Specification<NoticeEntity>() {
			@Override
			public Predicate toPredicate(Root<NoticeEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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

				return cb.and(predicates.toArray(new Predicate[] {}));
			}

		};

		Order order = new Order(Direction.DESC, "createDate");
		if (params.get("orderBy") != null) {
			String orderBy = String.valueOf(params.get("orderBy"));
			order = new Order(Direction.DESC, orderBy);
		}

		PageRequest pageable = new PageRequest(page - 1, size, new Sort(order));
		return noticeJpaRepository.findAll(spec, pageable);
	}

	@Override
	public Page<NoticeEntity> findByType(Short type, Integer page, Integer size) {
		try {
			PageRequest pageable = new PageRequest(page-1, size, new Sort(new Order(Direction.DESC, "createDate")));
			return noticeJpaRepository.findByType(type,pageable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<NoticeEntity> listByType(Short type) {
		try {
			return noticeJpaRepository.listByType(type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
