package com.sapient.statestreetscreeningapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.statestreetscreeningapplication.model.service.CategoryService;
import com.sapient.statestreetscreeningapplication.ui.bean.CategoryBean;
import com.sapient.statestreetscreeningapplication.ui.bean.CriteriaBean;

@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@CrossOrigin
	@RequestMapping("/retrieveAllCategories")
	public List<String> getAllCategoriesListByName()
	{
		return categoryService.getAllUsedCategories();
	}
	
	@CrossOrigin
	@RequestMapping("/getCategoryBean")
	public CategoryBean getCategoryBean()
	{
		return new CategoryBean();
	}
	
	@CrossOrigin
	@RequestMapping("/retrieveAllCategoryBean")
	public List<CategoryBean> retrieveAllCategoryBeans()
	{
		return categoryService.getAllCategoryBeans();
	}
	
	
	@CrossOrigin
	@RequestMapping(value = "/addCategory", method = RequestMethod.POST)
	public void  saveCategory(@RequestBody CategoryBean categoryBean)
	{
		List<String> categoryList= categoryService.getAllCategories();
		if(categoryList!=null)
		{
			if(!categoryList.contains(categoryBean.getCategoryName()))
				categoryService.saveCategoryDetails(categoryBean);		 	
		}
		else
		{
			categoryService.saveCategoryDetails(categoryBean);		
		}
		
	}
	
	@CrossOrigin
	@RequestMapping(value="/getTopicsOfDomain")
	public List<CriteriaBean> getAllCriteriaOfDomain(@RequestParam("domainName") String domainName) {
		
		long categoryID=categoryService.getCategoryByName(domainName).getCategoryId();
		return categoryService.getAllCriterias(categoryID);
		
	}
	
	

}
