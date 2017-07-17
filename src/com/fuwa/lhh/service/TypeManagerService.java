package com.fuwa.lhh.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.model.TypeAndACLModel;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Claue;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.QueryType;







@Service
public class TypeManagerService extends BaseService<SearchType> implements TypeManager{


	@Resource
	AuthorityManager authorityManager;

	@Resource
	DepartManager departManager;
	
	public TypeManagerService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public SearchType getTypeByName(String typeName) {
		// TODO Auto-generated method stub
		return (SearchType) getCurrentSession().createQuery("from SearchType where type_name = ?")
				.setString(0, typeName)
				.uniqueResult();
	}

	@Override
	public SearchType getTypeById(String typeId) {
		// TODO Auto-generated method stub
		return (SearchType) getCurrentSession().createQuery("from SearchType where type_id = ?")
				.setString(0, typeId)
				.uniqueResult();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ((Long) getCurrentSession().createQuery("select count(*) from SearchType ")			
				.uniqueResult()).intValue();
	}

	@Override
	public List getTypesByOrder() {
		// TODO Auto-generated method stub
		List<SearchType> searchtype_list = getCurrentSession().createQuery("select s from SearchType s order by cast(type_id as integer) asc ").list();
		List list = new ArrayList();
		for(SearchType l : searchtype_list) {
			TypeAndACLModel model = new TypeAndACLModel(l.getType_id(), l.getType_name(), l.isHave_old_code(), true, true, true);
			list.add(model);
		}
		return list;
	}

	@Override
	public List getPersonTypesByOrder(FWUser fwUser) {
		// TODO Auto-generated method stub
		String hql1 = "select s from SearchType s where s.type_id in ( "+
                "select distinct a.query_type_id from Authority a,FWUser u where 	(u.user_name =? and u.uuid =a.user_id)  "+	
                ") order by cast(s.type_id as integer) asc";
		

		String hql2 = "select s from SearchType s where s.type_id in ( "+
                "select distinct a.query_type_id from Authority a where a.group_id in (select d.uuid from Department d left join d.users u where u.user_name = ? )   "+	
                ") order by cast(s.type_id as integer) asc";
		
		String hql3 = "select s from SearchType s where s.type_id in ( "+
                "select distinct a.query_type_id from Authority a,FWUser u where 	u.user_name =? and u.uuid =a.user_id  or  a.group_id in "
                + "(select d.uuid from Department d left join d.users u where u.user_name = ? )   "+	
                ") order by cast(s.type_id as integer) asc";
		
		
		
	/*	String hql4 = "select s from SearchType s where s.type_id in ( "+
                "select distinct a.query_type_id from Authority a,FWUser u where 	u.user_name =? and u.uuid =a.user_id  or  a.group_id in "
                + "(select d.uuid from Department d left join d.users u where u.user_name = ? )   "+	
                ") order by cast(s.type_id as integer) asc";*/
		//System.out.println("special hql1-->"+hql1);
		//System.out.println("special hql2-->"+hql2);
		//System.out.println("special hql3-->"+hql3);
		/* List userList = getCurrentSession().createQuery(hql)
				.setString(0, fwUser.getUser_name())
				.list();
		 List departList = getCurrentSession().createQuery(hql2)
					.setString(0, fwUser.getUser_name())
					.list();
		 Set set = new HashSet();
		 set.addAll(userList);
		 set.addAll(departList);
		 List list =new ArrayList();
		 list.addAll(set);
		 System.out.println("getPersonTypesByOrder-->"+list.size());*/
		List<SearchType> searchtype_list = getCurrentSession().createQuery(hql3)
		.setString(0, fwUser.getUser_name())
		.setString(1, fwUser.getUser_name())
		.list();
		
		List list = new ArrayList();
		
		Department depart = departManager.getPersonDepartByUserId(fwUser.getUuid());
		for(SearchType s :searchtype_list) {
			Authority authority = null;
			TypeAndACLModel typeAndACLModel;
			if(depart != null && "dba".equals(depart.getDepartment_name())) {
				//authority = new Authority(true, true, true, true, true, true);
				typeAndACLModel = new TypeAndACLModel(s.getType_id(), s.getType_name(), s.isHave_old_code(), true, true, true);
			} else {

				authority = authorityManager.getPersonACL(fwUser.getUuid(),s.getType_id());
				if(authority == null) {
					authority = authorityManager.getUsersDepartACLById(s.getType_id(), fwUser.getUuid());
				}
				typeAndACLModel = new TypeAndACLModel();
				typeAndACLModel.setType_id(s.getType_id());
				typeAndACLModel.setType_name(s.getType_name());
				typeAndACLModel.setHave_old_code(s.isHave_old_code());
				typeAndACLModel.setBom_search(authority.isCan_bom_search());
				typeAndACLModel.setMaterial_search(authority.isCan_material_search());
				typeAndACLModel.setParent_search(authority.isCan_parent_search());
				
			}
			list.add(typeAndACLModel);
		}
		
		 return list;
				                   
	}

	@Override
	public List getTypeByNameArray(String[] array) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select s from SearchType s where type_name in :tlist")
				.setParameterList("tlist", array)
				.list();
	}
	

}
