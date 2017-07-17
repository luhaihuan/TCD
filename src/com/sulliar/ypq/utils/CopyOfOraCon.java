package com.sulliar.ypq.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CopyOfOraCon {
	
	String url = "jdbc:oracle:thin:@10.2.1.59:1521:tc"  ;
	String driveName = "oracle.jdbc.driver.OracleDriver";
	String username ="infodba";
	String password ="infodba";
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriveName() {
		return driveName;
	}
	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	Connection conn;
	
	String pnode_name="";
		
	List<String> pwnt_path_name = new ArrayList<String>();
	
	public List<String> getPwnt_path_name() {
		return pwnt_path_name;
	}
	public void setPwnt_path_name(List<String> pwnt_path_name) {
		this.pwnt_path_name = pwnt_path_name;
	}
	public String getPnode_name() {
		return pnode_name;
	}
	public void setPnode_name(String pnode_name) {
		this.pnode_name = pnode_name;
	}
	
	String customFilePath;
	
	
	public String getCustomFilePath() {
		return customFilePath;
	}
	public void setCustomFilePath(String customFilePath) {
		this.customFilePath = customFilePath;
	}

	String item_relation="";
	String itemrev_relation="";
	String addtion_relation="";
	String refence_relation="";
	String specation_relation="";
	String new_eco_relation="";
	String change_eco_relation="";
	String change_before_relation="";
	
	String new_eco_relation1="";
	String change_eco_relation1="";
	
	String reference_eco_relation="";
	String reference_eco_relation1="";
	
	public String getReference_eco_relation() {
		return reference_eco_relation;
	}
	public void setReference_eco_relation(String reference_eco_relation) {
		this.reference_eco_relation = reference_eco_relation;
	}
	public String getReference_eco_relation1() {
		return reference_eco_relation1;
	}
	public void setReference_eco_relation1(String reference_eco_relation1) {
		this.reference_eco_relation1 = reference_eco_relation1;
	}
	public String getNew_eco_relation1() {
		return new_eco_relation1;
	}
	public void setNew_eco_relation1(String new_eco_relation1) {
		this.new_eco_relation1 = new_eco_relation1;
	}
	public String getChange_eco_relation1() {
		return change_eco_relation1;
	}
	public void setChange_eco_relation1(String change_eco_relation1) {
		this.change_eco_relation1 = change_eco_relation1;
	}
	public String getChange_before_relation() {
		return change_before_relation;
	}
	public void setChange_before_relation(String change_before_relation) {
		this.change_before_relation = change_before_relation;
	}
	public String getItem_relation() {
		return item_relation;
	}
	public void setItem_relation(String item_relation) {
		this.item_relation = item_relation;
	}
	public String getItemrev_relation() {
		return itemrev_relation;
	}
	public void setItemrev_relation(String itemrev_relation) {
		this.itemrev_relation = itemrev_relation;
	}
	public String getAddtion_relation() {
		return addtion_relation;
	}
	public void setAddtion_relation(String addtion_relation) {
		this.addtion_relation = addtion_relation;
	}
	public String getRefence_relation() {
		return refence_relation;
	}
	public void setRefence_relation(String refence_relation) {
		this.refence_relation = refence_relation;
	}
	public String getSpecation_relation() {
		return specation_relation;
	}
	public void setSpecation_relation(String specation_relation) {
		this.specation_relation = specation_relation;
	}
	public String getNew_eco_relation() {
		return new_eco_relation;
	}
	public void setNew_eco_relation(String new_eco_relation) {
		this.new_eco_relation = new_eco_relation;
	}
	public String getChange_eco_relation() {
		return change_eco_relation;
	}
	public void setChange_eco_relation(String change_eco_relation) {
		this.change_eco_relation = change_eco_relation;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public Connection getConn()
	  {
	    try
	    {
	    	if(conn==null||conn.isClosed()){
	    		   
	    		System.out.println("连接服务器："+url);
	    		long lasting2 = System.currentTimeMillis();
	    		
	        	Class.forName(driveName);
	            conn = DriverManager.getConnection(url, username, password);
	            
	    		System.out.println("Connection db time is : " + (System.currentTimeMillis() - lasting2) + " ms");
	    		
	    		if(pnode_name.equals("")||pwnt_path_name.size()==0){
	    	    	
	    			PreparedStatement statement=null;
	    			ResultSet rs = null;
	    			
	    			try{
	    				String sql = "select * from PIMANVOLUME ";
	    				statement=conn.prepareStatement(sql);
	    				rs = statement.executeQuery();
	    				
	    				while(rs.next()){
	    					pnode_name = rs.getString("pnode_name");
	    					//pwnt_path_name = "Z:\\TCCNCACHEVOL";
	    					pwnt_path_name.add( rs.getString("pwnt_path_name"));
	    					
	    					//System.out.println("pnode_name:"+pnode_name+" ===== pwnt_path_name:"+pwnt_path_name);
	    				}
	    				
	    			}catch(Exception e){
	    				e.printStackTrace();
	    			}finally{
	    				closeConn(rs,statement,null);
	    			}
	    			if(false) {
	    				String[] cfps = customFilePath.split(",");
		    			for(String cfp:cfps){
		    				pwnt_path_name.add(cfp);
		    			}	
	    			}
	    			
	    	    }
	    		String sql = 
	    				"select Z.pitem_id,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (  "+
	    						"select A.puid,C.pitem_id,A.pobject_name,C.pitem_revision_id,A.pdate_released from (  "+
	    							"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where upper(pobject_name) like '%' and pobject_type = 'Fw9Item' and pactive_seq=1  "+ 
	    						")  A join (  "+
	    							"select B.puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B  "+
	    								"where upper(A.pitem_id) like 'DD0041B0-7' and upper(B.pitem_revision_id) like 'B' and A.puid = B.ritems_tagu   "+

	    						")  C on A.puid = C.puid  "+
	    					") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc  "
	    				;
	    		System.out.println(sql);
	    		PreparedStatement statement = conn.prepareStatement(sql);
	    	/*	statement.setString(1, "%");//itemName
				statement.setString(2, "Fw9Item");//内码
				statement.setString(3, "DD0041B0-7");//itemID
				statement.setString(4, "B");//itemRevID
*/	    		
				ResultSet query = statement.executeQuery();
				while (query.next()) {
					System.out.println(query.getString("pitem_id"));
					System.out.println(query.getString("puid"));
					System.out.println(query.getString("pobject_name"));
					System.out.println(query.getString("pitem_revision_id"));
					
				}
	    		
	    		
	    		
	    		
	    		if(item_relation.equals("")){
	    	    	
	    			item_relation = getRelation("IMAN_master_form");
	    			
	    	    }
	    		
	    		if(itemrev_relation.equals("")){
	    	    	
	    			itemrev_relation = getRelation("IMAN_master_form");
	    			
	    	    }
	    		
	    		if(addtion_relation.equals("")){
	    	    	
	    			addtion_relation = getRelation("TC_Attaches");
	    			
	    	    }

	    		if(refence_relation.equals("")){
	
	    			refence_relation = getRelation("IMAN_reference");
	
	    		}

	    		if(specation_relation.equals("")){
	
	    			specation_relation = getRelation("IMAN_specification");
	
	    		}

	    		if(new_eco_relation.equals("")){
	
	    			new_eco_relation = getRelation("SU4_CMHasNewItem");
	
	    		}

	    		if(change_eco_relation.equals("")){
	
	    			change_eco_relation = getRelation("CMHasSolutionItem");
	
	    		}
	    		
	    		if(change_before_relation.equals("")){
	    			
	    			change_before_relation = getRelation("CMHasImpactedItem");
	
	    		}
	    		
	    		if(new_eco_relation1.equals("")){
	    			
	    			new_eco_relation1 = getRelation("EC_affected_item_rel");
	
	    		}

	    		if(change_eco_relation1.equals("")){
	
	    			change_eco_relation1 = getRelation("EC_solution_item_rel");
	
	    		}
	    		
	    		if(reference_eco_relation1.equals("")){
	    			
	    			reference_eco_relation1 = getRelation("EC_reference_item_rel");
	
	    		}
	    		
	    		if(reference_eco_relation.equals("")){
	    			
	    			reference_eco_relation = getRelation("CMReferences");
	
	    		}
	    		
	    	}    	
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();	      
	    }    
	    
	    return conn;
	  }
	
	String getRelation(String typename){
		String relation="";
		
		PreparedStatement statement=null;
		ResultSet rs = null;
		
		try{
			String sql = "select * from PIMANTYPE where ptype_name = ?";
			statement=conn.prepareStatement(sql);
			statement.setString(1, typename);
			rs = statement.executeQuery();
			
			if(rs.next()){
				relation = rs.getString("puid");
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			closeConn(rs,statement,null);
		}
		
		return relation;
	}
	
	public void closeConn()
	  {
	    try
	    {
	      if (conn != null&&!conn.isClosed())
	        {
	    	  conn.commit();
	          conn.close();
	        }	      
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	  }
	
	public void closeConn(ResultSet res, PreparedStatement stmt, Connection conn)
	  {
	    try
	    {
	      if (res != null)
	        res.close();

	      if (stmt != null)
	        stmt.close();

	      if (conn == null||conn.isClosed())
	        return;
	      conn.commit();
	      conn.close();
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	    }
	  }
	public static void main(String[] args) {
		CopyOfOraCon con = new CopyOfOraCon();
		Connection connection = con.getConn();
		
	}
}
