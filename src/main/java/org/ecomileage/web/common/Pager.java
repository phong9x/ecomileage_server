package org.ecomileage.web.common;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.ecomileage.web.common.PageCustom;


public class Pager<T> {

	private Page<T> m_page;
	
	private PageCustom<T> c_list;
	
	public Pager(Page<T> list)
	{
		m_page = list;
	}
	
	public Pager(PageCustom<T> list)
	{
		c_list = list;
	}
	
	public Pager(){}
	
	public void setSetting(Model model, HttpServletRequest request)
	{
		int current = m_page.getNumber() + 1;
		int begin = Math.max(1, current - 4);
		int end = Math.min(begin + 9, m_page.getTotalPages());		
		model.addAttribute("list", m_page.getContent());
	    model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex", end);
	    model.addAttribute("currentIndex", current);
	    model.addAttribute("totalCount", m_page.getTotalElements());
	    model.addAttribute("size", m_page.getSize());
	    model.addAttribute("totalPages", m_page.getTotalPages());
	    String param_url= request.getQueryString();
		if(param_url != null && param_url.contains("page=")){
			param_url=param_url.replaceAll("page.*?(?=&|\\?|$)[&]|page.*?(?=&|\\?|$)", "");
		}
		model.addAttribute("param_url", param_url);
		model.addAttribute("pagination_navigator", request.getRequestURI());
		
	    if (m_page.getTotalElements()==0)
	    	model.addAttribute("page", null);
	    else
	    	model.addAttribute("page", m_page);
	}
	
	public void setSettingSecondList(Model model, String url)
	{
		int current = m_page.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		
		int end = Math.min(begin + 10, m_page.getTotalPages());
		
		model.addAttribute("list2", m_page.getContent());
	    model.addAttribute("beginIndex2", begin);
	    model.addAttribute("endIndex2", end);
	    model.addAttribute("currentIndex2", current);
	    model.addAttribute("totalCount2", m_page.getTotalElements());
	    model.addAttribute("param_url2", url);
	    model.addAttribute("size2", m_page.getSize());
	    model.addAttribute("totalPages2", m_page.getTotalPages());
	    
	    if (m_page.getTotalElements()==0)
	    	model.addAttribute("page2", null);
	    else
	    	model.addAttribute("page2", m_page);
	}
	
	public void setManualSetting(Model model, HttpServletRequest request)
	{
		String param_url= request.getQueryString();
		if(param_url != null && param_url.contains("page=")){
			param_url=param_url.replaceAll("page.*?(?=&|\\?|$)[&]|page.*?(?=&|\\?|$)", "");
		}
		int begin = Math.max(1, c_list.getCurrent() - 4);
		int end = Math.min(begin + 9, c_list.getTotalPages());
		
		model.addAttribute("param_url", param_url);
		model.addAttribute("beginIndex", begin);
	    model.addAttribute("endIndex",end);
		model.addAttribute("list", c_list.getList());
		model.addAttribute("currentIndex", c_list.getCurrent());
		model.addAttribute("totalCount",c_list.getTotalCount());
		model.addAttribute("size",c_list.getSize());
		model.addAttribute("totalPages",c_list.getTotalPages());
	}

}
