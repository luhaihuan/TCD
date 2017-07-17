package com.sulliar.ypq.service;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.aspectj.util.FileUtil;

import com.fuwa.lhh.model.GCQBOMModel;
import com.fuwa.lhh.model.MaterialAttrs;
import com.fuwa.lhh.model.MaterialTreeModel;
import com.fuwa.lhh.model.QDQBOMModel;
import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.model.Stamp;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.StampManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.lhh.service.UserManager;
import com.fuwa.lhh.util.ImageFactory;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sulliar.ypq.model.ImageModel;
import com.sulliar.ypq.model.ItemModel;
import com.sulliar.ypq.model.PrinterModel;
import com.sulliar.ypq.utils.Cn2Spell;
import com.sulliar.ypq.utils.OraCon;


public class FileDataManagerService implements FileDataManager {

	OraCon con = null;
	UserDataManager userDataManager;


	@Resource
	UserManager userManager;

	@Resource
	TypeManager typeManager ;


	@Resource
	AuthorityManager authorityManager;

	@Resource
	StampManager stampManager;

	@Resource
	DepartManager departManager;


	public UserDataManager getUserDataManager() {
		return userDataManager;
	}


	public void setUserDataManager(UserDataManager userDataManager) {
		this.userDataManager = userDataManager;
	}


	public OraCon getCon() {
		return con;
	}


	public void setCon(OraCon con) {
		this.con = con;
	}

	ACLDataManager acl;	

	public ACLDataManager getAcl() {
		return acl;
	}


	public void setAcl(ACLDataManager acl) {
		this.acl = acl;
	}

	String doActionPath ;


	public String getDoActionPath() {
		return doActionPath;
	}


	public void setDoActionPath(String doActionPath) {
		this.doActionPath = doActionPath;
	}

	@Override
	public List<ItemModel> getSearchFiles(String item_id_new,String item_id,String item_id_old, String item_name,
			String item_revision, String type,String username) {
		// TODO Auto-generated method stub
		List<ItemModel> ims = new ArrayList<ItemModel>();
		SearchType dataType = typeManager.getTypeByName(type);	
		FWUser fwUser = userManager.getUserByName(username);
		switch (type) {
		//basic 16
		case "驱动桥新产品":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("驱动桥新产品");
			}
			break; 
		case "驱动桥零件图（含总图）":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("驱动桥零件图（含总图）");
			}
			break; 
		case "驱动桥设计文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("驱动桥设计文件");
			}
			break;

		case "驱动桥工艺文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("驱动桥工艺文件");
			}
			break;

		case "驱动桥工艺体系文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("驱动桥工艺体系文件");
			}
			break;


		case "标准文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("标准文件");
			}
			break;


		case "驱动桥技术文件通知单":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("驱动桥技术文件通知单");
			}
			break;


		case "项目过程文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("项目过程文件");
			}
			break;

		case "工艺过程及工序卡文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("工艺过程及工序卡文件");
			}
			break;



		case "质量报告文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("质量报告文件");
			}
			break;


		case "试验报告文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("试验报告文件");
			}
			break;

		case "检验指导书文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("检验指导书文件");
			}
			break;


		case "技术要求通知单文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("技术要求通知单文件");
			}
			break;



		case "变更通知书文件":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("变更通知书文件");
			}
			break;



		case "工程变更申请单文件":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("工程变更申请单文件");
			}
			break;


		case "产品需求文件":
			getzt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("产品需求文件");
			}
			break;


		case "工艺更改通知单文件":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("工艺更改通知单文件");
			}
			break;
			//新增的两个文件    	
		case "驱动桥变更通知单文件":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("驱动桥变更通知单文件");
			}
			break;

		case "驱动桥工艺更改通知单":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("驱动桥工艺更改通知单");
			}
			break;

			//未完成 14

			//special
		case "零件图":
			getljt(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("零件图");
			}
			break;

		case "车轴总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("车轴总成图");
			}
			break;


		case "空悬总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("空悬总成图");
			}
			break;



		case "组合产品图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("组合产品图");
			}
			break;


		case "板悬总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("板悬总成图");
			}
			break;



		case "鞍座总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("鞍座总成图");
			}
			break;



		case "滑板总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("滑板总成图");
			}
			break;


		case "牵引销总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("牵引销总成图");
			}
			break;


		case "储气筒总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("储气筒总成图");
			}
			break;


		case "手刹总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("手刹总成图");
			}
			break;


		case "转锁总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("转锁总成图");
			}
			break;



		case "滑架总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("滑架总成图");
			}
			break;

		case "转盘总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("转盘总成图");
			}
			break;


		case "支腿总成图":
			getItemRevDsAndFloderItemRevDs(ims, item_id_new,item_id,item_id_old, item_name, item_revision,dataType,fwUser);  
			for(ItemModel im :ims){
				im.setSearchType("支腿总成图");
			}
			break;

		

		default:
			//getotherfile(ims, item_id, item_name, item_revision,type,username,fwUser);

			break;


		}


		return ims;
	}


	private void getzt(List<ItemModel> ims,String item_id_new, String item_id,String item_id_old, String item_name,
			String item_revision, SearchType searchType, FWUser fwUser) {
		// TODO Auto-generated method stub
		System.out.println(searchType.getType_name()+"--->"+searchType.getType_code1()+"-->item_id-->"+item_id+"-->item_id_new-->"+item_id_new+"-->item_id_old-->"+item_id_old);
		//List<tempModelItemRevision>  itemRevs =findItemRevByType(item_id, item_revision, item_name, "SU4_ProductRevision");


		List<tempModelItemRevision>  itemRevs = null;
		if(searchType.getType_code1().equals("Fw9ItemRevision"))
			itemRevs =findItemRevByTypeOfQDQ(item_id_new, item_revision, item_name, searchType.getType_code1());
		else {

			if(!item_id.equals(""))
				itemRevs =findItemRevByType(item_id, item_revision, item_name, searchType.getType_code1());
			else if(!item_id_new.equals(""))
				itemRevs =findItemRevByTypeNew(item_id_new, item_revision, item_name, searchType.getType_code1());
			else
				itemRevs =findItemRevByTypeOld(item_id_old, item_revision, item_name, searchType.getType_code1());
		}

		//查找总图下的数据集
		System.out.println("not into find ds -->"+itemRevs.size());
		for(tempModelItemRevision ir:itemRevs){
			System.out.println(" into find ds -->"+itemRevs.size());
			findDatasetByItemRev(ir,"'"+con.getRelation(searchType.getRev_ds_rel())+"'");
			 /*String[] split = searchType.getRev_ds_rel().split(",");
			 for(String s : split) {
				 findDatasetByItemRev(ir,"'"+s+"'"); 
				 if(searchType.getType_code1().equals("Fw9_drive_newcpRevision")) {
					findDatasetByItemRevRelation(ir);	
				  }
			 }*/


		}	
		
		
		

		for(tempModelItemRevision ir:itemRevs){

			boolean isFord = false;
			String itemid = ir.getItemid();
			String itemid_new = ir.getItemid_new();
			String itemid_old = ir.getItemid_old();
			String remark = ir.getRemark();
			String revid = ir.getRevid();
			String name = ir.getName();
			String object_code = ir.getObject_code();

			//System.out.println("dataSetsize--->"+ir.getDss().size());
			Department depart = departManager.getPersonDepartByUserId(fwUser.getUuid());
			Authority authority = null;
			if(depart != null && "dba".equals(depart.getDepartment_name())) {
				authority = new Authority(true, true, true, true, true, true);
			} else if(depart != null && "福特".equals(depart.getDepartment_name())) {
				List<MaterialAttrs> qdqNormalAttrs = getQDQNormalAttrs(itemid, revid);
				
				
				for(MaterialAttrs attr : qdqNormalAttrs) {
					if(attr.getTitle().equals("客户名称") && attr.getContent().equals("FORD")) {						
						isFord = true;
						break;
					} 
					
				}
				
				
				
				
				
			} else {

				authority = authorityManager.getPersonACL(fwUser.getUuid(),searchType.getType_id());
				if(authority == null) {
					authority = authorityManager.getUsersDepartACLById(searchType.getType_id(), fwUser.getUuid());
				}
			}
			
			
			for(tempModelDS ds:ir.getDss()){
				String dsname = ds.getDsname();
				String dstype = ds.getDstype();
				String dsfp = ds.getFilepath();

				ItemModel itemModel = new ItemModel();
				itemModel.setItem_id_new(itemid_new);
				itemModel.setItem_id(itemid);
				itemModel.setItem_id_old(itemid_old);
				itemModel.setItem_name(name);
				itemModel.setItem_revision(revid);
				itemModel.setObject_code(object_code);
				itemModel.setDataset_name(dsname);
				itemModel.setDataset_type(dstype);
				itemModel.setFilePath(dsfp);
				itemModel.setNodePath(con.getPnode_name());
				if(searchType.getType_code1().equals("Fw9ItemRevision"))
					itemModel.setRemark(remark);

				//ACLModel model = acl.findACLByTypeAndUser(type, username);

				if(depart != null && "福特".equals(depart.getDepartment_name()) && searchType.getType_code1().equals("Fw9ItemRevision")) {
					if(isFord) {
						itemModel.setCanRead(true);
						itemModel.setCanPrint(false);
						itemModel.setCanDownload(false);
						ims.add(itemModel);
					}
					continue;
				}
				


				if(authority!=null){

					if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
						itemModel.setCanDownload(authority.isCan_download_excel());
						itemModel.setCanPrint(authority.isCan_print_excel());
						itemModel.setCanRead(true);
					}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
						itemModel.setCanDownload(authority.isCan_download_word());
						itemModel.setCanPrint(authority.isCan_print_word());
						itemModel.setCanRead(true);
					}else if(dstype.equals("PDF")){
						itemModel.setCanDownload(authority.isCan_download_pdf());
						itemModel.setCanPrint(authority.isCan_print_pdf());
						itemModel.setCanRead(true);
					}else{
						itemModel.setCanDownload(false);
						itemModel.setCanPrint(false);
						itemModel.setCanRead(false);
					}

				}else{
					itemModel.setCanDownload(false);
					itemModel.setCanPrint(false);
					itemModel.setCanRead(true);
				}
				System.out.println("downloadFilePath---->"+itemModel.getFilePath());
				ims.add(itemModel);
			}


			/*if(searchType.getType_code1().equals("Fw9ItemRevision")&&ir.getDss().isEmpty()) {
				if(depart != null && "福特".equals(depart.getDepartment_name()))
				continue;
				
				ItemModel itemModel = new ItemModel();
				itemModel.setItem_id_new(itemid_new);
				itemModel.setItem_id(itemid);
				itemModel.setItem_id_old(itemid_old);
				itemModel.setItem_name(name);
				itemModel.setItem_revision(revid);
				itemModel.setRemark(remark);
				itemModel.setCanDownload(false);
				itemModel.setCanPrint(false);
				itemModel.setCanRead(false);
				ims.add(itemModel);
			}*/
			
			
		}

		solveNoResult(itemRevs, ims);
	}

	private void getItemRevDsAndFloderItemRevDs(List<ItemModel> ims,String item_id_new, String item_id,String item_id_old, String item_name,
			String item_revision, SearchType searchType, FWUser fwUser) {
		// TODO Auto-generated method stub
		System.out.println(searchType.getType_name()+"--->"+searchType.getType_code1()+"-->item_id-->"+item_id+"-->item_id_new-->"+item_id_new+"-->item_id_old-->"+item_id_old);
		//List<tempModelItemRevision>  itemRevs =findItemRevByType(item_id, item_revision, item_name, "SU4_ProductRevision");


		List<tempModelItemRevision>  itemRevs = null;

		if(searchType.getType_code1().equals("Fw9ItemRevision"))
			itemRevs =findItemRevByType(item_id_new, item_revision, item_name, searchType.getType_code1());
		else {

			if(!item_id.equals(""))
				itemRevs =findItemRevByType(item_id, item_revision, item_name, searchType.getType_code1());
			else if(!item_id_new.equals(""))
				itemRevs =findItemRevByTypeNew(item_id_new, item_revision, item_name, searchType.getType_code1());
			else
				itemRevs =findItemRevByTypeOld(item_id_old, item_revision, item_name, searchType.getType_code1());
		}

		//查找总图下的数据集
		System.out.println("not into find ds -->"+itemRevs.size());
		for(tempModelItemRevision ir:itemRevs){
			System.out.println(" into find ds -->"+itemRevs.size());


			//findDatasetByItemRev(ir,"'"+con.getRelation(searchType.getRev_ds_rel())+"'");
			String[] split = searchType.getRev_ds_rel().split(",");
			List<tempModelDS> dss = new ArrayList<tempModelDS>();
			for(String s : split) {
				findDatasetByItemRev(ir,"'"+con.getRelation(s)+"'",dss);
				//dss.addAll(ir.getDss());
			}
			//ir.setDss(dss);

		}	

		Department depart = departManager.getPersonDepartByUserId(fwUser.getUuid());
		Authority authority = null;
		if("dba".equals(depart.getDepartment_name())) {
			authority = new Authority(true, true, true, true, true, true);
		} else {

			authority = authorityManager.getPersonACL(fwUser.getUuid(),searchType.getType_id());
			if(authority == null) {
				authority = authorityManager.getUsersDepartACLById(searchType.getType_id(), fwUser.getUuid());
			}
		}
				

		for(tempModelItemRevision ir:itemRevs){

			String itemid = ir.getItemid();
			String itemid_new = ir.getItemid_new();
			String itemid_old = ir.getItemid_old();
			String revid = ir.getRevid();
			String name = ir.getName();
			String object_code = ir.getObject_code();

			/*			parent_item_id = itemid;
			parent_item_id_new = itemid_new;
			parent_item_id_old = itemid_old;
			parent_item_id_rev = revid;
			parent_item_name = name;*/

			System.out.println("dataSetsize--->"+ir.getDss().size());
			for(tempModelDS ds:ir.getDss()){
				String dsname = ds.getDsname();
				String dstype = ds.getDstype();
				String dsfp = ds.getFilepath();

				ItemModel itemModel = new ItemModel();

				itemModel.setItem_id_new(itemid_new);
				itemModel.setItem_id(itemid);

				itemModel.setItem_id_old(itemid_old);
				itemModel.setItem_name(name);
				itemModel.setItem_revision(revid);
				itemModel.setDataset_name(dsname);
				itemModel.setDataset_type(dstype);
				itemModel.setFilePath(dsfp);
				itemModel.setNodePath(con.getPnode_name());
				itemModel.setObject_code(object_code);




				if(authority!=null){

					if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
						itemModel.setCanDownload(authority.isCan_download_excel());
						itemModel.setCanPrint(authority.isCan_print_excel());
						itemModel.setCanRead(true);
					}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
						itemModel.setCanDownload(authority.isCan_download_word());
						itemModel.setCanPrint(authority.isCan_print_word());
						itemModel.setCanRead(true);
					}else if(dstype.equals("PDF")){
						itemModel.setCanDownload(authority.isCan_download_pdf());
						itemModel.setCanPrint(authority.isCan_print_pdf());
						itemModel.setCanRead(true);
					}else{
						itemModel.setCanDownload(false);
						itemModel.setCanPrint(false);
						itemModel.setCanRead(false);
					}

				}else{
					itemModel.setCanDownload(false);
					itemModel.setCanPrint(false);
					itemModel.setCanRead(true);
				}
				System.out.println("downloadFilePath---->"+itemModel.getFilePath());
				ims.add(itemModel);
			}


		}


		//ecn,eco,pcn和关联文件夹item itemrev,itemrev,dataset
		String folderRelation ="";
		if(searchType.getType_code1().equals("Fw9_ECNRevision"))
			folderRelation = "'CMHasSolutionItem'";

		else if(searchType.getType_code1().equals("Fw9_PCNRevision"))
			folderRelation = "'CMHasSolutionItem'";

		else if(searchType.getType_code1().equals("Fw9_ECRRevision"))
			folderRelation = "'CMHasProblemItem','CMHasImpactedItem'";

		else if(searchType.getType_code1().equals("Fw9_Drive_ENRevision"))
			folderRelation = "'CMHasSolutionItem'";

		else if(searchType.getType_code1().equals("Fw9_Drive_PCNRevision"))
			folderRelation = "'CMHasSolutionItem'";
		
		else if(searchType.getType_code1().equals("Fw9_drive_newcpRevision"))
			folderRelation = "'Fw9_newfloder','Fw9_drivejihsuxieyi'";

		else
			folderRelation = "Fw9_AssociatedDrawings";
		//找item版本文件夹下item版本
		if(!folderRelation.equals("Fw9_AssociatedDrawings"))
			for(tempModelItemRevision ir:itemRevs){ 
				String parent_item_id = ir.getItemid();
				String parent_item_id_new = ir.getItemid_new();
				String parent_item_id_old = ir.getItemid_old();
				String parent_item_id_rev = ir.getRevid();
				String parent_item_name = ir.getName();
				String parent_object_code = ir.getObject_code();
				List<tempModelItemRevision> findItemRevByItemRev= findItemRevByItemRevFolder(ir,folderRelation);

				for(tempModelItemRevision ir2:findItemRevByItemRev) {
					findDatasetByItemRevRelation(ir2);


					String itemid = parent_item_id;
					String itemid_new = parent_item_id_new;
					String itemid_old = parent_item_id_old;
					String revid = parent_item_id_rev;
					String name = parent_item_name;
					String child_item_id = ir2.getItemid();
					String child_item_rev = ir2.getRevid();
					String object_code = parent_object_code;



					System.out.println("dataSetsize--->"+ir.getDss().size());
					for(tempModelDS ds:ir2.getDss()){
						String dsname = ds.getDsname();
						String dstype = ds.getDstype();
						String dsfp = ds.getFilepath();

						ItemModel itemModel = new ItemModel();

						itemModel.setItem_id_new(itemid_new);
						itemModel.setItem_id(itemid);

						itemModel.setItem_id_old(itemid_old);
						itemModel.setItem_name(name);
						itemModel.setItem_revision(revid);
						itemModel.setDataset_name(dsname);
						itemModel.setDataset_type(dstype);
						itemModel.setFilePath(dsfp);
						itemModel.setNodePath(con.getPnode_name());
						itemModel.setChild_id(child_item_id);
						itemModel.setChild_rev_id(child_item_rev);
						itemModel.setObject_code(object_code);





						if(authority!=null){

							if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
								itemModel.setCanDownload(authority.isCan_download_excel());
								itemModel.setCanPrint(authority.isCan_print_excel());
								itemModel.setCanRead(true);
							}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
								itemModel.setCanDownload(authority.isCan_download_word());
								itemModel.setCanPrint(authority.isCan_print_word());
								itemModel.setCanRead(true);
							}else if(dstype.equals("PDF")){
								itemModel.setCanDownload(authority.isCan_download_pdf());
								itemModel.setCanPrint(authority.isCan_print_pdf());
								itemModel.setCanRead(true);
							}else{
								itemModel.setCanDownload(false);
								itemModel.setCanPrint(false);
								itemModel.setCanRead(false);
							}

						}else{
							itemModel.setCanDownload(false);
							itemModel.setCanPrint(false);
							itemModel.setCanRead(true);
						}
						System.out.println("downloadFilePath---->"+itemModel.getFilePath());
						ims.add(itemModel);
					}






				}

			}

		System.out.println("prepare find folder component-->item_rev_size-->"+itemRevs.size());
		for(tempModelItemRevision ir:itemRevs) {
			System.out.println("item_id-->"+ir.getItemid()+"--->item_rev_id-->"+ir.getRevid());
		}
		//item关联文件下的item,itemrev,dataset
		if(folderRelation.equals("Fw9_AssociatedDrawings")) {
			for(tempModelItemRevision ir:itemRevs) {
				String parent_item_id = ir.getItemid();
				String parent_item_id_new = ir.getItemid_new();
				String parent_item_id_old = ir.getItemid_old();
				String parent_item_id_rev = ir.getRevid();
				String parent_item_name = ir.getName();
				String loacl_item_id = ir.getItemid();
				String parent_object_code = ir.getObject_code();
				ItemModel localItem = findLocalItem(loacl_item_id);
				/////////////////////////////////itemRev
				List<tempModelItemRevision>  folderItemRevs =findFolderItemRev(localItem,folderRelation);

				System.out.println(" find folder component-->folderItemRevs_size-->"+folderItemRevs.size());
				for(tempModelItemRevision tempIr:folderItemRevs) {
					System.out.println("folderItemRev_item_id-->"+tempIr.getItemid()+"--->folderItemRev_item_rev_id-->"+tempIr.getRevid());
				}

				String itemid = parent_item_id;
				String itemid_new = parent_item_id_new;
				String itemid_old = parent_item_id_old;
				String revid = parent_item_id_rev;
				String name = parent_item_name;
				String object_code = parent_object_code;

				for(tempModelItemRevision ir2:folderItemRevs) {
					findDatasetByItemRevRelation(ir2);


					System.out.println(" find folder component-->folderItemRevs_size_dataset-->"+ir2.getDss().size());
					for(tempModelDS tempIr:ir2.getDss()) {
						System.out.println("folderItemRev_dataset_name-->"+tempIr.getDsname());
					}



					String child_item_id = ir2.getItemid();
					String child_item_rev = ir2.getRevid();
					System.out.println("dataSetsize--->"+ir.getDss().size());
					for(tempModelDS ds:ir2.getDss()){
						String dsname = ds.getDsname();
						String dstype = ds.getDstype();
						String dsfp = ds.getFilepath();

						ItemModel itemModel = new ItemModel();

						itemModel.setItem_id_new(itemid_new);
						itemModel.setItem_id(itemid);

						itemModel.setItem_id_old(itemid_old);
						itemModel.setItem_name(name);
						itemModel.setItem_revision(revid);
						itemModel.setDataset_name(dsname);
						itemModel.setDataset_type(dstype);
						itemModel.setFilePath(dsfp);
						itemModel.setNodePath(con.getPnode_name());
						itemModel.setChild_id(child_item_id);
						itemModel.setChild_rev_id(child_item_rev);
						itemModel.setObject_code(object_code);





						if(authority!=null){

							if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
								itemModel.setCanDownload(authority.isCan_download_excel());
								itemModel.setCanPrint(authority.isCan_print_excel());
								itemModel.setCanRead(true);
							}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
								itemModel.setCanDownload(authority.isCan_download_word());
								itemModel.setCanPrint(authority.isCan_print_word());
								itemModel.setCanRead(true);
							}else if(dstype.equals("PDF")){
								itemModel.setCanDownload(authority.isCan_download_pdf());
								itemModel.setCanPrint(authority.isCan_print_pdf());
								itemModel.setCanRead(true);
							}else{
								itemModel.setCanDownload(false);
								itemModel.setCanPrint(false);
								itemModel.setCanRead(false);
							}

						}else{
							itemModel.setCanDownload(false);
							itemModel.setCanPrint(false);
							itemModel.setCanRead(true);
						}
						System.out.println("folder itemRev---->"+itemModel.getFilePath());
						ims.add(itemModel);
					}






				}
				/////////////////////////////////dataset
				List<tempModelDS> folderDataSets = findFolderDataSet(localItem,folderRelation);
				System.out.println(" find folder component-->dataset-->"+folderDataSets.size());
				for(tempModelDS tempIr:folderDataSets) {
					System.out.println("folderDataset-->"+tempIr.getDsname());
				}


				for(tempModelDS ds:folderDataSets){
					String dsname = ds.getDsname();
					String dstype = ds.getDstype();
					String dsfp = ds.getFilepath();

					ItemModel itemModel = new ItemModel();

					itemModel.setItem_id_new(itemid_new);
					itemModel.setItem_id(itemid);

					itemModel.setItem_id_old(itemid_old);
					itemModel.setItem_name(name);
					itemModel.setItem_revision(revid);
					itemModel.setDataset_name(dsname);
					itemModel.setDataset_type(dstype);
					itemModel.setFilePath(dsfp);
					itemModel.setNodePath(con.getPnode_name());
					itemModel.setObject_code(object_code);





					if(authority!=null){

						if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
							itemModel.setCanDownload(authority.isCan_download_excel());
							itemModel.setCanPrint(authority.isCan_print_excel());
							itemModel.setCanRead(true);
						}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
							itemModel.setCanDownload(authority.isCan_download_word());
							itemModel.setCanPrint(authority.isCan_print_word());
							itemModel.setCanRead(true);
						}else if(dstype.equals("PDF")){
							itemModel.setCanDownload(authority.isCan_download_pdf());
							itemModel.setCanPrint(authority.isCan_print_pdf());
							itemModel.setCanRead(true);
						}else{
							itemModel.setCanDownload(false);
							itemModel.setCanPrint(false);
							itemModel.setCanRead(false);
						}

					}else{
						itemModel.setCanDownload(false);
						itemModel.setCanPrint(false);
						itemModel.setCanRead(true);
					}
					System.out.println("folder dataSet---->"+itemModel.getFilePath());
					ims.add(itemModel);
				}

				/////////////////////////////////item itemRev
				List<tempModelItemRevision>  folderItemItemRevs = findFolderItemItemRev(localItem,folderRelation);
				System.out.println(" find folder component-->folderItemItemRevs-->"+folderItemItemRevs.size());
				for(tempModelItemRevision tempIr:folderItemItemRevs) {
					System.out.println("folderItemItemRevs-->item_id->"+tempIr.getItemid()+"-->item_rev_id-->"+tempIr.getRevid());
				}
				for(tempModelItemRevision ir2:folderItemItemRevs) {
					findDatasetByItemRevRelation(ir2);

					String child_item_id = ir2.getItemid();
					String child_item_rev = ir2.getRevid();
					System.out.println("dataSetsize--->"+ir.getDss().size());
					for(tempModelDS ds:ir2.getDss()){
						String dsname = ds.getDsname();
						String dstype = ds.getDstype();
						String dsfp = ds.getFilepath();

						ItemModel itemModel = new ItemModel();

						itemModel.setItem_id_new(itemid_new);
						itemModel.setItem_id(itemid);

						itemModel.setItem_id_old(itemid_old);
						itemModel.setItem_name(name);
						itemModel.setItem_revision(revid);
						itemModel.setDataset_name(dsname);
						itemModel.setDataset_type(dstype);
						itemModel.setFilePath(dsfp);
						itemModel.setNodePath(con.getPnode_name());
						itemModel.setChild_id(child_item_id);
						itemModel.setChild_rev_id(child_item_rev);
						itemModel.setObject_code(object_code);




						if(authority!=null){

							if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
								itemModel.setCanDownload(authority.isCan_download_excel());
								itemModel.setCanPrint(authority.isCan_print_excel());
								itemModel.setCanRead(true);
							}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
								itemModel.setCanDownload(authority.isCan_download_word());
								itemModel.setCanPrint(authority.isCan_print_word());
								itemModel.setCanRead(true);
							}else if(dstype.equals("PDF")){
								itemModel.setCanDownload(authority.isCan_download_pdf());
								itemModel.setCanPrint(authority.isCan_print_pdf());
								itemModel.setCanRead(true);
							}else{
								itemModel.setCanDownload(false);
								itemModel.setCanPrint(false);
								itemModel.setCanRead(false);
							}

						}else{
							itemModel.setCanDownload(false);
							itemModel.setCanPrint(false);
							itemModel.setCanRead(true);
						}
						System.out.println("folder itemitemRev---->"+itemModel.getFilePath());
						ims.add(itemModel);
					}






				}

			}

		}
		solveNoResult(itemRevs, ims);


	}
	
	void solveNoResult(List<tempModelItemRevision> itemRevs,List<ItemModel> ims){
		
			for(tempModelItemRevision ir:itemRevs){
				if(ir.getDss().isEmpty()) {
					String itemid = ir.getItemid();
					String itemid_new = ir.getItemid_new();
					String itemid_old = ir.getItemid_old();
					String remark = ir.getRemark();
					String revid = ir.getRevid();
					String name = ir.getName();
					String object_code = ir.getObject_code();

					ItemModel itemModel = new ItemModel();
					itemModel.setItem_id_new(itemid_new);
					itemModel.setItem_id(itemid);
					itemModel.setItem_id_old(itemid_old);
					itemModel.setItem_name(name);
					itemModel.setItem_revision(revid);
					itemModel.setRemark(remark);
					itemModel.setCanDownload(false);
					itemModel.setCanPrint(false);
					itemModel.setCanRead(false);
					ims.add(itemModel);
				}
			}
		
	}

	private List<tempModelItemRevision> findFolderItemItemRev(
			ItemModel localItem, String folderRelation) {
		// TODO Auto-generated method stub
		String sql = "select H.puid,K.pobject_name,J.pitem_id,H.pitem_revision_id,P.pdate_released from( "+
				"select B.puid,B.pobject_name  from ( "+
				"select A.RSECONDARY_OBJECTU from pimanrelation A,PIMANTYPE B "+
				" where A.rprimary_objectu = ? and A.rrelation_typeu = B.puid and B.ptype_name = ? "+
				" ) A join pworkspaceobject B on A.RSECONDARY_OBJECTU = B.puid ) K,PITEM J,PITEMREVISION H,PWORKSPACEOBJECT P "+
				"where K.puid = J.puid and J.puid = H.ritems_tagu  and P.puid = H.puid and P.pdate_released is not null";
		PreparedStatement statement=null;
		ResultSet rs = null;

		List<tempModelItemRevision> itemRevisions =new ArrayList<tempModelItemRevision>();
		try{


			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, localItem.getPuid());
			statement.setString(2, folderRelation);
			System.out.println("findFolderItemItemRev-->"+sql);			
			rs = statement.executeQuery();	
			int count = 0;

			while(rs.next()){
				count++;
				String puid = rs.getString("puid");
				String pobject_name = rs.getString("pobject_name");
				String pitem_id = rs.getString("pitem_id");
				String pitem_revision_id = rs.getString("pitem_revision_id");
				tempModelItemRevision itemRevision =new tempModelItemRevision();
				itemRevision.setPuid(puid);
				itemRevision.setItemid(pitem_id);
				itemRevision.setRevid(pitem_revision_id);
				itemRevision.setName(pobject_name);
				itemRevisions.add(itemRevision);

			}
			System.out.println("findFolderItemItemRev itemrev size-->"+count);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		return itemRevisions;
	}


	private List<tempModelDS> findFolderDataSet(ItemModel localItem,
			String folderRelation) {

		String sql = 
				"select pfile_name,psd_path_name,dsname,dstype from PIMANFILE  L,(  "+
						"select pvalu_0,dspuid,dsname,dstype from PREF_LIST_0  J,(  "+
						"select B.puid dspuid,B.pobject_name dsname,B.pobject_type dstype from ( "+
						"select A.RSECONDARY_OBJECTU from pimanrelation A,PIMANTYPE B "+
						" where A.rprimary_objectu = ? and A.rrelation_typeu = B.puid and B.ptype_name = ? "+
						") A join pworkspaceobject B on A.RSECONDARY_OBJECTU = B.puid and B.pobject_type in ('MSExcelX','MSWordX','MSExcel','MSWord','PDF') "+
						")  K where J.puid = K.dspuid "+
						")  M where L.puid = M.pvalu_0 ";

		PreparedStatement statement=null;
		ResultSet rs = null;
		List<tempModelDS> dss = new ArrayList<tempModelDS>();
		try{

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, localItem.getPuid());			
			statement.setString(2, folderRelation);				
			rs = statement.executeQuery();		


			while(rs.next()){
				System.out.println("having in search ds----------->");
				String s1 = rs.getString("pfile_name");
				String s2 = rs.getString("psd_path_name");
				String s3 = rs.getString("dsname");
				String s4 = rs.getString("dstype");

				tempModelDS model = new tempModelDS();
				model.setDsname(s3);
				model.setDstype(s4);
				System.out.println("dsname-->"+s3+"-->dstype-->"+s4);

				for(String fp:con.getPwnt_path_name()){
					File tf = new File(fp+"\\"+s2+"\\"+s1);
					System.out.println("Path-->"+fp+"\\"+s2+"\\"+s1);
					if(tf.exists()){
						model.setFilepath(fp+"\\"+s2+"\\"+s1);
						System.out.println("filePath-->"+model.getFilepath());
						break;
					}
				}

				dss.add(model);

			}


		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		System.out.println("prepare in search ds----------->findFolderDataSet loacl_item_id-->"+localItem.getItem_id()+"-->datasetSize-->"+dss.size());
		return dss;

	}


	private List<tempModelItemRevision> findFolderItemRev(ItemModel localItem,
			String folderRelation) {
		// TODO Auto-generated method stub
		String sql = "select A.puid,A.pobject_name,C.pitem_id,C.pitem_revision_id from ( "+
				"select B.puid,B.pobject_name  from ( "+
				"select A.RSECONDARY_OBJECTU from pimanrelation A,PIMANTYPE B  "+
				" where A.rprimary_objectu = ? and A.rrelation_typeu = B.puid and B.ptype_name = ? "+
				") A join pworkspaceobject B on A.RSECONDARY_OBJECTU = B.puid )  A join ( "+  
				"select B.puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B "+  
				"where upper(A.pitem_id) like '%' and upper(B.pitem_revision_id) like '%' and A.puid = B.ritems_tagu "+   
				")  C on A.puid = C.puid";
		PreparedStatement statement=null;
		ResultSet rs = null;

		List<tempModelItemRevision> itemRevisions =new ArrayList<tempModelItemRevision>();
		try{


			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, localItem.getPuid());
			statement.setString(2, folderRelation);
			System.out.println("findFolderItemRev-->"+sql);			
			rs = statement.executeQuery();	
			int count = 0;

			while(rs.next()){
				count++;
				String puid = rs.getString("puid");
				String pobject_name = rs.getString("pobject_name");
				String pitem_id = rs.getString("pitem_id");
				String pitem_revision_id = rs.getString("pitem_revision_id");
				tempModelItemRevision itemRevision =new tempModelItemRevision();
				itemRevision.setPuid(puid);
				itemRevision.setItemid(pitem_id);
				itemRevision.setRevid(pitem_revision_id);
				itemRevision.setName(pobject_name);
				itemRevisions.add(itemRevision);

			}
			System.out.println("findFolderItemRev itemrev size-->"+count);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		return itemRevisions;
	}


	private ItemModel findLocalItem(String loacl_item_id) {
		// TODO Auto-generated method stub
		String sql = "select A.puid,C.pitem_id,A.pobject_name,A.pdate_released from ("+  
				"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1 "+   
				")  A join (  select A.puid,A.pitem_id from PITEM A "+
				"where upper(A.pitem_id) like ? "+  
				")  C on A.puid = C.puid  ";
		PreparedStatement statement=null;
		ResultSet rs = null;
		ItemModel itemModel =new ItemModel();

		try{


			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, loacl_item_id);

			System.out.println("findLocalItem-->"+sql);			
			rs = statement.executeQuery();	
			int count = 0;

			while(rs.next()){
				count++;
				String pitem_id = rs.getString("pitem_id");
				String puid = rs.getString("puid");
				String pobject_name = rs.getString("pobject_name");
				itemModel.setPuid(puid);
				itemModel.setItem_id(pitem_id);
				itemModel.setItem_name(pobject_name);


			}
			System.out.println("res item size-->"+count+"-->local_item_id-->"+itemModel.getItem_id());

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		return itemModel;
	}


	private void getljt(List<ItemModel> ims,String item_id_new, String item_id,String item_id_old, String item_name,
			String item_revision, SearchType searchType, FWUser fwUser) {
		// TODO Auto-generated method stub
		System.out.println(searchType.getType_name()+"--->"+searchType.getType_code1()+"-->item_id-->"+item_id+"-->item_id_new-->"+item_id_new+"-->item_id_old-->"+item_id_old);
		//List<tempModelItemRevision>  itemRevs =findItemRevByType(item_id, item_revision, item_name, "SU4_ProductRevision");


		List<tempModelItemRevision>  itemRevs = null;


		//String str = "'Fw9_LBJ_AZRevision','Fw9_LBJ_BXRevision','Fw9_LBJ_CQTRevision','Fw9_LBJ_CZRevision','Fw9_LBJ_HBRevision','Fw9_LBJ_HJRevision','Fw9_LBJ_KXRevision','Fw9_LBJ_QYXRevision','Fw9_LBJ_SSRevision','Fw9_LBJ_ZPRevision','Fw9_LBJ_ZSRevision','Fw9_LBJ_ZTRevision'";	    
		if(!item_id_new.equals(""))
			itemRevs =findItemRevByTypeNewLJT(item_id_new, item_revision, item_name, searchType.getType_code1());
		else
			itemRevs =findItemRevByTypeOldLJT(item_id_old, item_revision, item_name, searchType.getType_code1());


		//查找总图下的数据集
		System.out.println("not into find ds -->"+itemRevs.size());
		for(tempModelItemRevision ir:itemRevs){
			System.out.println(" into find ds -->"+itemRevs.size());


			findDatasetByItemRev(ir,"'"+con.getRelation(searchType.getRev_ds_rel())+"'");


		}		
		Department depart = departManager.getPersonDepartByUserId(fwUser.getUuid());
		Authority authority = null;
		if("dba".equals(depart.getDepartment_name())) {
			authority = new Authority(true, true, true, true, true, true);
		} else {

			authority = authorityManager.getPersonACL(fwUser.getUuid(),searchType.getType_id());
			if(authority == null) {
				authority = authorityManager.getUsersDepartACLById(searchType.getType_id(), fwUser.getUuid());
			}
		}




		for(tempModelItemRevision ir:itemRevs){

			String itemid = ir.getItemid();
			String itemid_new = ir.getItemid_new();
			String itemid_old = ir.getItemid_old();
			String revid = ir.getRevid();
			String name = ir.getName();
			String object_code = ir.getObject_code();


			System.out.println("dataSetsize--->"+ir.getDss().size());
			for(tempModelDS ds:ir.getDss()){
				String dsname = ds.getDsname();
				String dstype = ds.getDstype();
				String dsfp = ds.getFilepath();

				ItemModel itemModel = new ItemModel();
				//if(!item_id_new.equals("null"))
				itemModel.setItem_id_new(itemid_new);
				itemModel.setItem_id(itemid);
				//if(!item_id_new.equals("null")||!item_id_old.equals("null"))
				itemModel.setItem_id_old(itemid_old);
				itemModel.setItem_name(name);
				itemModel.setItem_revision(revid);
				itemModel.setDataset_name(dsname);
				itemModel.setDataset_type(dstype);
				itemModel.setFilePath(dsfp);
				itemModel.setNodePath(con.getPnode_name());
				itemModel.setObject_code(object_code);

				//ACLModel model = acl.findACLByTypeAndUser(type, username);




				if(authority!=null){

					if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
						itemModel.setCanDownload(authority.isCan_download_excel());
						itemModel.setCanPrint(authority.isCan_print_excel());
						itemModel.setCanRead(true);
					}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
						itemModel.setCanDownload(authority.isCan_download_word());
						itemModel.setCanPrint(authority.isCan_print_word());
						itemModel.setCanRead(true);
					}else if(dstype.equals("PDF")){
						itemModel.setCanDownload(authority.isCan_download_pdf());
						itemModel.setCanPrint(authority.isCan_print_pdf());
						itemModel.setCanRead(true);
					}else{
						itemModel.setCanDownload(false);
						itemModel.setCanPrint(false);
						itemModel.setCanRead(false);
					}

				}else{
					itemModel.setCanDownload(false);
					itemModel.setCanPrint(false);
					itemModel.setCanRead(true);
				}
				System.out.println("downloadFilePath---->"+itemModel.getFilePath());
				ims.add(itemModel);
			}


		}
		String folderRelation = searchType.getType_code2();
		//item关联文件下的item,itemrev,dataset
		if(folderRelation.equals("Fw9_AssociatedDrawings")) {
			for(tempModelItemRevision ir:itemRevs) {
				String parent_item_id = ir.getItemid();
				String parent_item_id_new = ir.getItemid_new();
				String parent_item_id_old = ir.getItemid_old();
				String parent_item_id_rev = ir.getRevid();
				String parent_item_name = ir.getName();
				String parent_object_code = ir.getObject_code();
				String loacl_item_id = ir.getItemid();
				ItemModel localItem = findLocalItem(loacl_item_id);
				/////////////////////////////////itemRev
				List<tempModelItemRevision>  folderItemRevs =findFolderItemRev(localItem,folderRelation);

				String itemid = parent_item_id;
				String itemid_new = parent_item_id_new;
				String itemid_old = parent_item_id_old;
				String revid = parent_item_id_rev;
				String name = parent_item_name;
				String object_code =parent_object_code;

				for(tempModelItemRevision ir2:folderItemRevs) {
					findDatasetByItemRevRelation(ir2);





					String child_item_id = ir2.getItemid();
					String child_item_rev = ir2.getRevid();
					System.out.println("dataSetsize--->"+ir.getDss().size());
					for(tempModelDS ds:ir2.getDss()){
						String dsname = ds.getDsname();
						String dstype = ds.getDstype();
						String dsfp = ds.getFilepath();

						ItemModel itemModel = new ItemModel();

						itemModel.setItem_id_new(itemid_new);
						itemModel.setItem_id(itemid);

						itemModel.setItem_id_old(itemid_old);
						itemModel.setItem_name(name);
						itemModel.setItem_revision(revid);
						itemModel.setDataset_name(dsname);
						itemModel.setDataset_type(dstype);
						itemModel.setFilePath(dsfp);
						itemModel.setNodePath(con.getPnode_name());
						itemModel.setChild_id(child_item_id);
						itemModel.setChild_rev_id(child_item_rev);
						itemModel.setObject_code(object_code);




						if(authority!=null){

							if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
								itemModel.setCanDownload(authority.isCan_download_excel());
								itemModel.setCanPrint(authority.isCan_print_excel());
								itemModel.setCanRead(true);
							}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
								itemModel.setCanDownload(authority.isCan_download_word());
								itemModel.setCanPrint(authority.isCan_print_word());
								itemModel.setCanRead(true);
							}else if(dstype.equals("PDF")){
								itemModel.setCanDownload(authority.isCan_download_pdf());
								itemModel.setCanPrint(authority.isCan_print_pdf());
								itemModel.setCanRead(true);
							}else{
								itemModel.setCanDownload(false);
								itemModel.setCanPrint(false);
								itemModel.setCanRead(false);
							}

						}else{
							itemModel.setCanDownload(false);
							itemModel.setCanPrint(false);
							itemModel.setCanRead(true);
						}
						System.out.println("folder itemRev---->"+itemModel.getFilePath());
						ims.add(itemModel);
					}






				}
				/////////////////////////////////dataset
				List<tempModelDS> folderDataSets = findFolderDataSet(localItem,folderRelation);
				for(tempModelDS ds:folderDataSets){
					String dsname = ds.getDsname();
					String dstype = ds.getDstype();
					String dsfp = ds.getFilepath();

					ItemModel itemModel = new ItemModel();

					itemModel.setItem_id_new(itemid_new);
					itemModel.setItem_id(itemid);

					itemModel.setItem_id_old(itemid_old);
					itemModel.setItem_name(name);
					itemModel.setItem_revision(revid);
					itemModel.setDataset_name(dsname);
					itemModel.setDataset_type(dstype);
					itemModel.setFilePath(dsfp);
					itemModel.setNodePath(con.getPnode_name());
					itemModel.setObject_code(object_code);





					if(authority!=null){

						if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
							itemModel.setCanDownload(authority.isCan_download_excel());
							itemModel.setCanPrint(authority.isCan_print_excel());
							itemModel.setCanRead(true);
						}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
							itemModel.setCanDownload(authority.isCan_download_word());
							itemModel.setCanPrint(authority.isCan_print_word());
							itemModel.setCanRead(true);
						}else if(dstype.equals("PDF")){
							itemModel.setCanDownload(authority.isCan_download_pdf());
							itemModel.setCanPrint(authority.isCan_print_pdf());
							itemModel.setCanRead(true);
						}else{
							itemModel.setCanDownload(false);
							itemModel.setCanPrint(false);
							itemModel.setCanRead(false);
						}

					}else{
						itemModel.setCanDownload(false);
						itemModel.setCanPrint(false);
						itemModel.setCanRead(true);
					}
					System.out.println("folder dataSet---->"+itemModel.getFilePath());
					ims.add(itemModel);
				}

				/////////////////////////////////item itemRev
				List<tempModelItemRevision>  folderItemItemRevs = findFolderItemItemRev(localItem,folderRelation);
				for(tempModelItemRevision ir2:folderItemItemRevs) {
					findDatasetByItemRevRelation(ir2);

					String child_item_id = ir2.getItemid();
					String child_item_rev = ir2.getRevid();
					System.out.println("dataSetsize--->"+ir.getDss().size());
					for(tempModelDS ds:ir2.getDss()){
						String dsname = ds.getDsname();
						String dstype = ds.getDstype();
						String dsfp = ds.getFilepath();

						ItemModel itemModel = new ItemModel();

						itemModel.setItem_id_new(itemid_new);
						itemModel.setItem_id(itemid);

						itemModel.setItem_id_old(itemid_old);
						itemModel.setItem_name(name);
						itemModel.setItem_revision(revid);
						itemModel.setDataset_name(dsname);
						itemModel.setDataset_type(dstype);
						itemModel.setFilePath(dsfp);
						itemModel.setNodePath(con.getPnode_name());
						itemModel.setChild_id(child_item_id);
						itemModel.setChild_rev_id(child_item_rev);
						itemModel.setObject_code(object_code);




						if(authority!=null){

							if(dstype.equals("MSExcel")||dstype.equals("MSExcelX")){
								itemModel.setCanDownload(authority.isCan_download_excel());
								itemModel.setCanPrint(authority.isCan_print_excel());
								itemModel.setCanRead(true);
							}else if(dstype.equals("MSWord")||dstype.equals("MSWordX")){
								itemModel.setCanDownload(authority.isCan_download_word());
								itemModel.setCanPrint(authority.isCan_print_word());
								itemModel.setCanRead(true);
							}else if(dstype.equals("PDF")){
								itemModel.setCanDownload(authority.isCan_download_pdf());
								itemModel.setCanPrint(authority.isCan_print_pdf());
								itemModel.setCanRead(true);
							}else{
								itemModel.setCanDownload(false);
								itemModel.setCanPrint(false);
								itemModel.setCanRead(false);
							}

						}else{
							itemModel.setCanDownload(false);
							itemModel.setCanPrint(false);
							itemModel.setCanRead(true);
						}
						System.out.println("folder itemitemRev---->"+itemModel.getFilePath());
						ims.add(itemModel);
					}






				}

			}

		}
		solveNoResult(itemRevs, ims);

	}



	@Override
	public String FileToSWFPath(File sourceFile,String dataset_type,String realpath) {
		// TODO Auto-generated method stub
		String resultPath ="";
		try {
			String sourcePath = sourceFile.getCanonicalPath();
			System.out.println(sourcePath+" type:"+dataset_type);

			if(dataset_type.equals("MSExcel")||dataset_type.equals("MSExcelX")){

				String toPDFFile = realpath + doActionPath +"/" +  getSimpleFileName(sourceFile.getName()) + ".pdf";

				File pdfFile = new File(toPDFFile);				

				if(!pdfFile.exists()){
					if (!pdfFile.getParentFile().exists()) 
						pdfFile.getParentFile().mkdirs();

					//pdfFile = Excel2PDF(sourcePath,toPDFFile);
					File fitFile = new File(realpath + doActionPath +"/fit" +sourceFile.getName());
					pdfFile = Excel2PDF(fitExcel(sourceFile, fitFile ).getCanonicalPath(),toPDFFile);
				}

				if(pdfFile.exists()){
					String tmp = Cn2Spell.converterToSpell(toPDFFile);
					if(!tmp.equals(toPDFFile)){
						Copy(toPDFFile, tmp);
						toPDFFile = tmp;
					}
				}
				System.out.println("preview pdf path:"+toPDFFile);
				resultPath = new File(toPDFFile).getName();
				/*String swfFilePath = toPDFFile+".swf";
				File swfFile = new File(swfFilePath);
				if(!swfFile.exists()){
					try {
						PDF2SWF1(toPDFFile,swfFilePath,realpath);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
					if(!swfFile.exists()){
						try {
							PDF2SWF1(toPDFFile,swfFilePath,realpath);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();

						}
					}
				}

				File swfFile1 = new File(swfFilePath);
				if(swfFile1.exists()){
					resultPath = swfFile1.getName();
				}*/

			}else if(dataset_type.equals("MSWord")||dataset_type.equals("MSWordX")){

				String toPDFFile = realpath + doActionPath +"/" +  getSimpleFileName(sourceFile.getName()) + ".pdf";

				File pdfFile = new File(toPDFFile);				

				if(!pdfFile.exists()){
					if (!pdfFile.getParentFile().exists()) 
						pdfFile.getParentFile().mkdirs();

					pdfFile = Word2PDF(sourcePath,toPDFFile);
				}	

				if(pdfFile.exists()){
					String tmp = Cn2Spell.converterToSpell(toPDFFile);
					if(!tmp.equals(toPDFFile)){
						Copy(toPDFFile, tmp);
						toPDFFile = tmp;
					}
				}
				System.out.println("preview pdf path:"+toPDFFile);
				resultPath = new File(toPDFFile).getName();
				/*String swfFilePath = toPDFFile+".swf";
				File swfFile = new File(swfFilePath);
				if(!swfFile.exists()){
					try {
						PDF2SWF1(toPDFFile,swfFilePath,realpath);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
					if(!swfFile.exists()){
						try {
							PDF2SWF1(toPDFFile,swfFilePath,realpath);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();

						}
					}
				}

				File swfFile1 = new File(swfFilePath);
				if(swfFile1.exists()){
					resultPath = swfFile1.getName();
				}*/

			}if(dataset_type.equals("PDF")||dataset_type.equals("PDF")){
				//String swfFilePath = realpath + doActionPath +"/" + sourceFile.getName() + ".swf";
				String tfp = sourceFile.getCanonicalPath();
				String toPDFFile = realpath + doActionPath +"/" + getSimpleFileName(sourceFile.getName())  + ".pdf";
				if(sourceFile.exists()){
					String tmp = Cn2Spell.converterToSpell(toPDFFile);
					//if(!tmp.equals(toPDFFile)){
					Copy(sourceFile.getCanonicalPath(), tmp);
					toPDFFile = tmp;
					//}
				}
				System.out.println("preview pdf path:"+toPDFFile);
				resultPath = new File(toPDFFile).getName();
				/*File tfpf = new File(tfp);
				String swfFilePath = realpath + doActionPath +"/" + tfpf.getName() + ".swf";

				File swfFile = new File(swfFilePath);
				if(!swfFile.exists()){
					try {
						PDF2SWF1(tfp,swfFilePath,realpath);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
					if(!swfFile.exists()){
						try {
							PDF2SWF1(tfp,swfFilePath,realpath);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();

						}
					}
				}

				File swfFile1 = new File(swfFilePath);
				if(swfFile1.exists()){
					resultPath = swfFile1.getName();
				}*/
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		return resultPath;
	}

	File Word2PDF(String sourcePath,String toPDFFile){
		System.out.println("开始转换成pdf文件:" + sourcePath); 
		long lasting2 = System.currentTimeMillis();
		ActiveXComponent app = null;  
		try{
			ComThread.InitSTA();
			app = new ActiveXComponent("Word.Application");  
			app.setProperty("Visible", false);
			Dispatch docs = app.getProperty("Documents").toDispatch();  
			Dispatch doc = Dispatch.call(docs,//  
					"Open", //  
					sourcePath,// FileName  
					false,// ConfirmConversions  
					true // ReadOnly  
					).toDispatch(); 
			Dispatch.call(doc,//  
					"SaveAs", //  
					toPDFFile, // FileName  
					17);
			Dispatch.call(doc, "Close", false);  

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (app != null)  
				app.invoke("Quit", 0);  
			ComThread.Release();
		}
		System.out.println(toPDFFile+"转换完成,耗时:"+(System.currentTimeMillis() - lasting2) + " ms"); 

		File file = new File(toPDFFile);
		if(file.exists()){
			return file;
		}		
		return null;
	}
	String SWFTools_HOME;

	public String getSWFTools_HOME() {
		return SWFTools_HOME;
	}


	public void setSWFTools_HOME(String sWFTools_HOME) {
		SWFTools_HOME = sWFTools_HOME;
	}

	void  PDF2SWF1(String sourceFile, String destFile, String realpath) throws Exception  {  

		long lasting2 = System.currentTimeMillis();
		// 目标路径不存在则建立目标路径  
		File dest = new File(destFile);  
		if (!dest.getParentFile().exists())  
			dest.getParentFile().mkdirs();   

		// 源文件不存在则返回  
		File source = new File(sourceFile);  

		if (!source.exists()) {  
			return ;  
		}  

		//因为下面进行系统调用，这样就会把系统执行的操作新开启一个线程（在此linux也叫进程），所以它和主扫描程序是独立运行，所以下次还会扫描这个转换中的文件，所以这里要将它设置为不可读，  
		source.setReadable(false);  

		//FileConvertUtil fileConvertUtil = new FileConvertUtil();  
		//fileName = fileConvertUtil.getFileName(fileName);//获取文件名  

		//String outputFile = destPath + fileName + ".swf";  

		//log.debug("开始调用swftools转换pdf文件:" + outputFile);  
		System.out.println("开始调用swftools转换pdf文件:" + destFile);  

		List<String>  command = new   ArrayList<String>();  
		command.add( realpath + "SWFTools/pdf2swf.exe");//从配置文件里读取  
		command.add("-z");  
		//      command.add("-B");  
		//      command.add("rfxview.swf");  

		command.add("-s");  
		command.add("flashversion=9"); 


		command.add("-s");  
		command.add("poly2bitmap");//加入poly2bitmap的目的是为了防止出现大文件或图形过多的文件转换时的出错，没有生成swf文件的异常  

		//windows平台下  
		//      command.add("languagedir=C:/xpdf/chinese-simplified/");  
		command.add(sourceFile);  
		command.add("-o");  
		command.add(destFile);  

		ProcessBuilder processBuilder = new ProcessBuilder();  
		processBuilder.command(command);  
		Process process = processBuilder.start();  

		//      dealWith(process);//改用下面的方式来处理：  
		InputStreamWathThread inputWathThread = new InputStreamWathThread(process);  
		inputWathThread.start();  
		ErrorInputStreamWathThread errorInputWathThread = new ErrorInputStreamWathThread(process);  
		errorInputWathThread.start();  

		try {  
			process.waitFor();//等待子进程的结束，子进程就是系统调用文件转换这个新进程  
			inputWathThread.setOver(true);//转换完，停止流的处理  
			errorInputWathThread.setOver(true);  
		} catch (InterruptedException e) {  
			e.printStackTrace();  
		}  
		System.out.println(destFile+"转换完成,耗时:"+(System.currentTimeMillis() - lasting2) + " ms"); 

		//log.debug("转换完成");  

		// return outputFile;  
	}

	void  PDF2SWF(String sourceFile, String destFile, String realpath) throws Exception  {  

		long lasting2 = System.currentTimeMillis();
		// 目标路径不存在则建立目标路径  
		File dest = new File(destFile);  
		if (!dest.getParentFile().exists())  
			dest.getParentFile().mkdirs();   

		// 源文件不存在则返回  
		File source = new File(sourceFile);  

		if (!source.exists()) {  
			return ;  
		}  

		//因为下面进行系统调用，这样就会把系统执行的操作新开启一个线程（在此linux也叫进程），所以它和主扫描程序是独立运行，所以下次还会扫描这个转换中的文件，所以这里要将它设置为不可读，  
		source.setReadable(false);  

		//FileConvertUtil fileConvertUtil = new FileConvertUtil();  
		//fileName = fileConvertUtil.getFileName(fileName);//获取文件名  

		//String outputFile = destPath + fileName + ".swf";  

		//log.debug("开始调用swftools转换pdf文件:" + outputFile);  
		System.out.println("开始调用swftools转换pdf文件:" + destFile);  

		List<String>  command = new   ArrayList<String>();  
		command.add( realpath + "SWFTools/pdf2swf.exe");//从配置文件里读取  
		command.add("-z");  
		//      command.add("-B");  
		//      command.add("rfxview.swf");  

		command.add("-s");  
		command.add("flashversion=9"); 


		//command.add("-s");  
		//command.add("poly2bitmap");//加入poly2bitmap的目的是为了防止出现大文件或图形过多的文件转换时的出错，没有生成swf文件的异常  

		//windows平台下  
		//      command.add("languagedir=C:/xpdf/chinese-simplified/");  
		command.add(sourceFile);  
		command.add("-o");  
		command.add(destFile);  

		ProcessBuilder processBuilder = new ProcessBuilder();  
		processBuilder.command(command);  
		Process process = processBuilder.start();  

		//      dealWith(process);//改用下面的方式来处理：  
		InputStreamWathThread inputWathThread = new InputStreamWathThread(process);  
		inputWathThread.start();  
		ErrorInputStreamWathThread errorInputWathThread = new ErrorInputStreamWathThread(process);  
		errorInputWathThread.start();  

		try {  
			process.waitFor();//等待子进程的结束，子进程就是系统调用文件转换这个新进程  
			inputWathThread.setOver(true);//转换完，停止流的处理  
			errorInputWathThread.setOver(true);  
		} catch (InterruptedException e) {  
			e.printStackTrace();  
		}  
		System.out.println(destFile+"转换完成,耗时:"+(System.currentTimeMillis() - lasting2) + " ms"); 

		//log.debug("转换完成");  

		// return outputFile;  
	}

	@Override
	public List<PrinterModel> getAllPrinter() {
		// TODO Auto-generated method stub
		List<PrinterModel> pms =new ArrayList<PrinterModel> ();

		HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();//构建打印请求属性集 

		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE; //设置打印格式，因为未确定文件类型，所以选着AUTOSENSE

		PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, pras);//查找所有的可用打印服务
		if(pservices!=null&&pservices.length>0){

			for(PrintService pservice:pservices){
				PrinterModel m = new PrinterModel();
				m.setName(pservice.getName());
				pms.add(m);
			}
		}


		return pms;
	}


	@Override
	public void PrintFile(File sourceFile, String dataset_type,
			String realpath, String printer_name,String waterm_name,String username,String pss,String po,String wp, String bgroup ) {

		// TODO Auto-generated method stub

		String globalPDFPath ="";
		PrintService ps = findPrintServiceByName(printer_name);	
		try {
			System.out.println("PrintFile-->"+username+"-->"+wp+"-->"+bgroup+"sourcePath"+sourceFile.getCanonicalPath()+" type:"+dataset_type);
			String sourceFilePath = sourceFile.getCanonicalPath();

			//Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfDetail = new SimpleDateFormat("yyyyMMddHHmmss");
			String toPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(sourceFile.getName()) + ".pdf";
			File toPDFFile = new File(toPDFFilePath);

			if (!toPDFFile.getParentFile().exists()) 
				toPDFFile.getParentFile().mkdirs(); 



			String wPosition = "";
			if (wp.equals("左上"))
				wPosition = "leftup";
			else if (wp.equals("右下"))
				wPosition = "rightdown";
			else if(wp.equals("左下"))
				wPosition = "leftdown";
			else 
				wPosition = "rightup";

			if(dataset_type.equals("PDF")||dataset_type.equals("PDF")){


				if(!toPDFFile.exists()){
					Copy(sourceFile.getCanonicalPath(),toPDFFilePath);
				}

			}else if(dataset_type.equals("MSExcel")||dataset_type.equals("MSExcelX")){			

				toPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(sourceFile.getName())+"excel2pdf" + ".pdf";
				toPDFFile = new File(toPDFFilePath);
				if(!toPDFFile.exists()) {
					File localFile = new File(realpath + doActionPath +"/" +sourceFile.getName());
					if(!localFile.exists()) {
						FileUtil.copyFile(sourceFile, localFile); //解决(请求的操作无法在使用用户映射区域打开的文件上执行。)
					}
					toPDFFile = Excel2PDF(localFile.getCanonicalPath(),toPDFFilePath);
				}



			}else if(dataset_type.equals("MSWord")||dataset_type.equals("MSWordX")){


				toPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(sourceFile.getName())+"word2pdf" + ".pdf";
				toPDFFile = new File(toPDFFilePath);
				if(!toPDFFile.exists()) {
					File localFile = new File(realpath + doActionPath +"/" +sourceFile.getName());
					if(!localFile.exists()) {
						FileUtil.copyFile(sourceFile, localFile); //解决(请求的操作无法在使用用户映射区域打开的文件上执行。)
					}
					toPDFFile = Word2PDF(localFile.getCanonicalPath(),toPDFFilePath);
				}




			}

			System.out.println("ImgName-->"+waterm_name+"-->path-->"+realpath + "WaterMark1/"+waterm_name+".png");
			System.out.println(toPDFFilePath);
			System.out.println(sourceFilePath);
			if(waterm_name==null||waterm_name.equals("nowatermark")){
				globalPDFPath=toPDFFile.getCanonicalPath();
				printFile(new File(globalPDFPath),ps,pss,po);
			}else{
				String destPDFFilePath = "";
				//File destPDFFile = new File(destPDFFilePath);
				String waterImgFilePath = realpath + "WaterMark/"+waterm_name+".png";

				/*String tmpWaterImgFilePath = realpath + "WaterMark1/"+waterm_name+"tmp.png";
				File tmpWaterImgFile = new File(tmpWaterImgFilePath);*/


				//waterImgFilePath = ImageFactory.imgZoom(new File(waterImgFilePath),tmpWaterImgFile , 0.6f, 0.6f).getCanonicalPath(); 


				/*if (wp.equals("左上"))
						addPdfMarkLeftUp(toPDFFilePath, destPDFFilePath,waterImgFilePath, 10, 10);
					else if (wp.equals("右下"))
						addPdfMarkRightDown(toPDFFilePath, destPDFFilePath, waterImgFilePath, 10, 10);
					else if (wp.equals("左下"))
						addPdfMarkLeftDown(toPDFFilePath, destPDFFilePath, waterImgFilePath, 10, 10);
					else
						addPdfMarkRightUp(toPDFFilePath, destPDFFilePath, waterImgFilePath, 10, 10);




				if ((waterm_name.equals("shizhi")||waterm_name.equals("shoukong")||waterm_name.equals("yiciyongtu"))) {

					String tmpPdfFilePath = (new StringBuilder(String.valueOf(realpath))).append(doActionPath).append("/").append(getSimpleFileName(sourceFile.getName())).append(sdfDetail.format(new Date())).append(".pdf").toString();
					File tmpPdfFile = new File(tmpPdfFilePath);



						if (wp.equals("左上"))
							addImageDatePDFLeftUp(destPDFFilePath, tmpPdfFilePath,waterImgFilePath, sdf.format(new Date()));
						else if(wp.equals("右下"))
							addImageDatePDFRightDown(destPDFFilePath, tmpPdfFilePath,waterImgFilePath, sdf.format(new Date()));
						else if (wp.equals("左下"))
							addImageDatePDFLeftDown(destPDFFilePath, tmpPdfFilePath,waterImgFilePath, sdf.format(new Date()));
						else
							addImageDatePDFRightUp(destPDFFilePath, tmpPdfFilePath,waterImgFilePath, sdf.format(new Date()));

					//return new File(pdfFileTmp).getName(); 
					printFile(tmpPdfFile,ps,pss,po);
				}


				printFile(destPDFFile,ps,pss,po);*/
				//if(!destPDFFile.exists())
				if ((waterm_name.equals("shizhi")||waterm_name.equals("shoukong")||waterm_name.equals("yiciyongtu"))) {
					//String pdfFileTmp = (new StringBuilder(String.valueOf(realpath))).append(doActionPath).append("/").append(sourceFile.getName()).append(sdf.format(new Date())).append(".pdf").toString();
					destPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(toPDFFile.getName()) + waterm_name+wPosition+sdf.format(new Date()) +".pdf";
					File destPDFFile = new File(destPDFFilePath);
					if(destPDFFile.exists() ) {
						printFile(destPDFFile,ps,pss,po);
					}
					if (wp.equals("左上"))
						addImageDatePDFLeftUp(toPDFFilePath, destPDFFilePath,waterImgFilePath,sdf.format(new Date()));
					else if(wp.equals("右下"))
						addImageDatePDFRightDown(toPDFFilePath, destPDFFilePath,waterImgFilePath,sdf.format(new Date()));
					else if(wp.equals("左下"))
						addImageDatePDFLeftDown(toPDFFilePath, destPDFFilePath, waterImgFilePath, sdf.format(new Date()));
					else 
						addImageDatePDFRightUp(toPDFFilePath, destPDFFilePath, waterImgFilePath, sdf.format(new Date()));



					//	return tmpPdfFile.getName(); 
				} else {
					destPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(toPDFFile.getName()) + waterm_name+wPosition +".pdf";
					File destPDFFile = new File(destPDFFilePath);
					if(destPDFFile.exists() ) {
						printFile(destPDFFile,ps,pss,po);
					}
					if (wp.equals("左上"))
						addImageDatePDFLeftUp(toPDFFilePath, destPDFFilePath,waterImgFilePath,"");
					else if(wp.equals("右下"))
						addImageDatePDFRightDown(toPDFFilePath, destPDFFilePath,waterImgFilePath,"");
					else if(wp.equals("左下"))
						addImageDatePDFLeftDown(toPDFFilePath, destPDFFilePath, waterImgFilePath, "");
					else 
						addImageDatePDFRightUp(toPDFFilePath, destPDFFilePath, waterImgFilePath, "");





				}
				File destPDFFile = new File(destPDFFilePath);
				System.out.println("destPDFFilePath-->"+new File(destPDFFilePath).getName());


				//return tmpPdfFile.getName(); 
				printFile(destPDFFile,ps,pss,po);


			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	



		System.out.println("dstype->"+dataset_type+"-->path-->"+globalPDFPath);



	}

	void addPdfMark2(String InPdfFile, String outPdfFile, String markImagePath)
			throws Exception
	{
		PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));
		Image img = Image.getInstance(markImagePath);
		for (int i = 1; i <= reader.getNumberOfPages(); i++)
		{
			float pageHeight = reader.getPageSize(i).getHeight();
			float imgHeight = img.getHeight();
			float pageWidth = reader.getPageSize(i).getWidth();
			float imgWidth = img.getWidth();
			img.setAbsolutePosition(10, (pageHeight - imgHeight) / 2.0F);
			PdfContentByte under = stamp.getOverContent(i);
			under.addImage(img);
		}

		stamp.close();
		reader.close();
	}

	void printFile(File f, PrintService ps,String pss,String po){

		try {
			PDDocument document = PDDocument.load(f);
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintService(ps);
			job.setPageable(new PDFPageable(document));

			Paper paper = new Paper();
			if(pss.equals("A4")){
				paper.setSize(594,840);
				paper.setImageableArea(5, 0, paper.getWidth()-15, paper.getHeight()-10);
			}else if(pss.equals("A3")){
				paper.setSize(1000,1400);	        	 
				paper.setImageableArea(5, 0, paper.getWidth()-15, paper.getHeight()-10);
			}


			PageFormat pageFormat = new PageFormat();

			if(po.equals("横向")){
				pageFormat.setOrientation(PageFormat.LANDSCAPE);
			}else if(po.equals("纵向")){
				pageFormat.setOrientation(PageFormat.PORTRAIT);
			}

			pageFormat.setPaper(paper);

			Book book = new Book();
			book.append(new PDFPrintable(document), pageFormat,document.getNumberOfPages());
			job.setPageable(book);

			job.print();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}



	
	private void addImageDatePDFLeftUpNew(String soucefile,String destfile,String parentWaterPath,List<Stamp> stamps,String dateStr) {
		// TODO Auto-generated method stub
		try{
			String pdfFilePath = soucefile; 
			String pdfOutFilePath = destfile;
			File file = new File(pdfFilePath);
			System.out.println("file path:"+file.exists()+" path:"+file.getCanonicalPath());
			PdfReader pdfReader = new PdfReader(pdfFilePath);
			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader
					, new FileOutputStream(
							pdfOutFilePath));
			// addWatermark(pdfStamper, username,60,55);
			addWatermarkLeftUpNew(pdfStamper,parentWaterPath,dateStr, stamps);

			pdfStamper.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	private void addImageDatePDFRightUpNew(String soucefile,String destfile,String parentWaterPath,List<Stamp> stamps,String dateStr) {
		// TODO Auto-generated method stub
		
		try{
			String pdfFilePath = soucefile; 
			String pdfOutFilePath = destfile; 
			PdfReader pdfReader = new PdfReader(pdfFilePath);
			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader
					, new FileOutputStream(
							pdfOutFilePath));
			// addWatermark(pdfStamper, username,60,55);
			addWatermarkRightUpNew(pdfStamper, parentWaterPath, dateStr, stamps);

			pdfStamper.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	private void addImageDatePDFLeftDownNew(String soucefile,String destfile,String parentWaterPath,List<Stamp> stamps,String dateStr) {
		// TODO Auto-generated method stub
		try{
			String pdfFilePath = soucefile; 
			String pdfOutFilePath = destfile; 
			PdfReader pdfReader = new PdfReader(pdfFilePath);
			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader
					, new FileOutputStream(
							pdfOutFilePath));
			//addWatermarkLeftDown(pdfStamper, waterImgPath,dateStr,55,20);
			addWatermarkLeftDownNew(pdfStamper, parentWaterPath, dateStr, stamps);
			//addWatermarkRightUpNew(pdfStamper, parentWaterPath, dateStr, stamps);
			pdfStamper.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	private void addImageDatePDFRightDownNew(String soucefile,String destfile,String parentWaterPath,List<Stamp> stamps,String dateStr) {
		// TODO Auto-generated method stub
		try{
			String pdfFilePath = soucefile; 
			String pdfOutFilePath = destfile; 
			PdfReader pdfReader = new PdfReader(pdfFilePath);
			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader
					, new FileOutputStream(
							pdfOutFilePath));

			addWatermarkRightDownNew(pdfStamper, parentWaterPath, dateStr, stamps);

			pdfStamper.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	private void addImageDatePDFLeftUp(String soucefile,String destfile,String waterImgPath,String dateStr) {
		// TODO Auto-generated method stub
		try{
			String pdfFilePath = soucefile; 
			String pdfOutFilePath = destfile;
			File file = new File(pdfFilePath);
			System.out.println("file path:"+file.exists()+" path:"+file.getCanonicalPath());
			PdfReader pdfReader = new PdfReader(pdfFilePath);
			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader
					, new FileOutputStream(
							pdfOutFilePath));
			// addWatermark(pdfStamper, username,60,55);
			addWatermarkLeftUp(pdfStamper, waterImgPath,dateStr,55,46);

			pdfStamper.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private void addImageDatePDFRightUp(String soucefile,String destfile,String waterImgPath,String dateStr) {
		// TODO Auto-generated method stub
		System.out.println("addDateImagePDFRightUp-->soucefile->"+soucefile+"-->destfile->"+destfile+"-->dateStr->"+dateStr+"-->waterImgPath->"+waterImgPath);
		try{
			String pdfFilePath = soucefile; 
			String pdfOutFilePath = destfile; 
			PdfReader pdfReader = new PdfReader(pdfFilePath);
			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader
					, new FileOutputStream(
							pdfOutFilePath));
			// addWatermark(pdfStamper, username,60,55);
			addWatermarkRightUp(pdfStamper, waterImgPath,dateStr,65,46);

			pdfStamper.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	

	


	private void addImageDatePDFLeftDown(String soucefile,String destfile,String waterImgPath,String dateStr) {
		// TODO Auto-generated method stub
		try{
			String pdfFilePath = soucefile; 
			String pdfOutFilePath = destfile; 
			PdfReader pdfReader = new PdfReader(pdfFilePath);
			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader
					, new FileOutputStream(
							pdfOutFilePath));
			addWatermarkLeftDown(pdfStamper, waterImgPath,dateStr,55,20);

			pdfStamper.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	private void addImageDatePDFRightDown(String soucefile,String destfile,String waterImgPath,String dateStr) {
		// TODO Auto-generated method stub
		try{
			String pdfFilePath = soucefile; 
			String pdfOutFilePath = destfile; 
			PdfReader pdfReader = new PdfReader(pdfFilePath);
			// Get the PdfStamper object
			PdfStamper pdfStamper = new PdfStamper(pdfReader
					, new FileOutputStream(
							pdfOutFilePath));

			addWatermarkRightDown(pdfStamper, waterImgPath,dateStr,65,20);

			pdfStamper.close();
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	
	void addWatermarkLeftDown(PdfStamper pdfStamper
			, String waterImgPath ,String waterMarkName,float x,float y) {
		PdfContentByte content = null;
		BaseFont base = null;
		//Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
			// 设置字体
			base = BaseFont.createFont("STSongStd-Light", 
					"UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (base == null || pdfStamper == null) {
				return;
			}
			// 设置透明度为0.4
			gs.setFillOpacity(1.0f);
			gs.setStrokeOpacity(1.0f);
			int toPage = pdfStamper.getReader().getNumberOfPages();
			for (int i = 1; i <= toPage; i++) {
				//pageRect = pdfStamper.getReader().
				//   getPageSizeWithRotation(i);
				// 计算水印X,Y坐标
				//float x = pageRect.getWidth() / 2;
				//float y1 = pageRect.getHeight() ;
				//获得PDF最顶层
				content = pdfStamper.getOverContent(i);
				content.saveState();
				// set Transparency
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.RED);

				content.setFontAndSize(base, 12);
				// 水印文字成45度角倾斜
				Image instance = Image.getInstance(waterImgPath);
				instance.setAbsolutePosition(x-46,  y-10);
				content.addImage(instance);
				content.showTextAligned(Element.ALIGN_CENTER
						, waterMarkName, x,
						y, 0);
				content.endText(); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			content = null;
			base = null;
			//pageRect = null;
		}
	}


	void addWatermarkRightDown(PdfStamper pdfStamper
			, String waterImgPath ,String waterMarkName,float x,float y) {
		PdfContentByte content = null;
		BaseFont base = null;
		Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
			// 设置字体
			base = BaseFont.createFont("STSongStd-Light", 
					"UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (base == null || pdfStamper == null) {
				return;
			}
			// 设置透明度为0.4
			gs.setFillOpacity(1.0f);
			gs.setStrokeOpacity(1.0f);
			int toPage = pdfStamper.getReader().getNumberOfPages();
			for (int i = 1; i <= toPage; i++) {
				pageRect = pdfStamper.getReader().
						getPageSizeWithRotation(i);
				// 计算水印X,Y坐标
				float x1 = pageRect.getWidth() ;
				//float y1 = pageRect.getHeight() ;
				//获得PDF最顶层
				content = pdfStamper.getOverContent(i);
				content.saveState();
				// set Transparency
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.RED);
				content.setFontAndSize(base, 12);
				// 水印文字成45度角倾斜
				Image instance = Image.getInstance(waterImgPath);
				instance.setAbsolutePosition(x1-x-46,  y-10);
				content.addImage(instance);
				content.showTextAligned(Element.ALIGN_CENTER
						, waterMarkName, x1-x,
						y, 0);
				content.endText(); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			content = null;
			base = null;
			//pageRect = null;
		}
	}


	void addWatermarkRightUp(PdfStamper pdfStamper
			, String waterImgPath,String waterMarkName,float x,float y) {
		PdfContentByte content = null;
		BaseFont base = null;
		Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
			// 设置字体
			base = BaseFont.createFont("STSongStd-Light", 
					"UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (base == null || pdfStamper == null) {
				return;
			}
			// 设置透明度为0.4
			gs.setFillOpacity(1.0f);
			gs.setStrokeOpacity(1.0f);
			int toPage = pdfStamper.getReader().getNumberOfPages();
			for (int i = 1; i <= toPage; i++) {
				pageRect = pdfStamper.getReader().
						getPageSizeWithRotation(i);
				// 计算水印X,Y坐标
				float x1 = pageRect.getWidth() ;
				float y1 = pageRect.getHeight() ;
				//float y1 = pageRect.getHeight() ;
				//获得PDF最顶层
				content = pdfStamper.getOverContent(i);
				content.saveState();
				// set Transparency
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.RED);
				content.setFontAndSize(base, 12);
				// 水印文字成45度角倾斜
				Image instance = Image.getInstance(waterImgPath);
				instance.setAbsolutePosition(x1-x-47,  y1-y-10);
				content.addImage(instance);

				content.showTextAligned(Element.ALIGN_CENTER
						, waterMarkName, x1-x,
						y1-y, 0);
				content.endText(); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			content = null;
			base = null;
			//pageRect = null;
		}
	}
	
	
	void addWatermarkRightDownNew(PdfStamper pdfStamper
			, String waterImgPath , String dateStr,List<Stamp> stamps) {
		PdfContentByte content = null;
		BaseFont base = null;
		Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
			// 设置字体
			base = BaseFont.createFont("STSongStd-Light", 
					"UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (base == null || pdfStamper == null) {
				return;
			}
			// 设置透明度为0.4
			gs.setFillOpacity(1.0f);
			gs.setStrokeOpacity(1.0f);
			int toPage = pdfStamper.getReader().getNumberOfPages();
			for (int i = 1; i <= toPage; i++) {
				pageRect = pdfStamper.getReader().
						getPageSizeWithRotation(i);
				// 计算水印X,Y坐标
				float x1 = pageRect.getWidth() ;
				//float y1 = pageRect.getHeight() ;
				//获得PDF最顶层
				content = pdfStamper.getOverContent(i);
				content.saveState();
				// set Transparency
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.RED);
				content.setFontAndSize(base, 12);
				// 水印文字成45度角倾斜
			/*	Image instance = Image.getInstance(waterImgPath);
				instance.setAbsolutePosition(x1-x-46,  y-10);
				content.addImage(instance);
				content.showTextAligned(Element.ALIGN_CENTER
						, waterMarkName, x1-x,
						y, 0);*/
				
				Image instance;
				float lastImgHeight = 10;
				float x = 65;
				for (int j = 0; j < stamps.size(); j++) {
					Stamp stamp = stamps.get(j);
					String stamp_true_name = stamp.getStamp_true_name();
					String wtp = waterImgPath + stamp_true_name + ".png";
					instance = Image.getInstance(wtp);
					
					//instance.setAbsolutePosition(x-46,  y1-y-10-lastImgHeight);
					instance.setAbsolutePosition(x1-instance.getWidth()-10,lastImgHeight);
					
					content.addImage(instance);
					// 水印文字成45度角倾斜
					if ((stamp_true_name.equals("shizhi")||stamp_true_name.equals("shoukong")||stamp_true_name.equals("yiciyongtu"))) {
						/*content.showTextAligned(Element.ALIGN_CENTER
								, dateStr, x,
								y1-y-lastImgHeight, 0);	*/  
						
						content.showTextAligned(Element.ALIGN_CENTER
								, dateStr, x1-instance.getWidth()/2-10,
								1*instance.getHeight()/4+lastImgHeight, 0);
					}
					
					lastImgHeight += instance.getHeight();
					 
				}
				
				content.endText(); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			content = null;
			base = null;
			//pageRect = null;
		}
	}
	
	void addWatermarkLeftDownNew(PdfStamper pdfStamper
			, String waterImgPath , String dateStr,List<Stamp> stamps) {
		PdfContentByte content = null;
		BaseFont base = null;
		//Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
			// 设置字体
			base = BaseFont.createFont("STSongStd-Light", 
					"UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (base == null || pdfStamper == null) {
				return;
			}
			// 设置透明度为0.4
			gs.setFillOpacity(1.0f);
			gs.setStrokeOpacity(1.0f);
			int toPage = pdfStamper.getReader().getNumberOfPages();
			for (int i = 1; i <= toPage; i++) {
				//pageRect = pdfStamper.getReader().
				//   getPageSizeWithRotation(i);
				// 计算水印X,Y坐标
				//float x = pageRect.getWidth() / 2;
				//float y1 = pageRect.getHeight() ;
				//获得PDF最顶层
				content = pdfStamper.getOverContent(i);
				content.saveState();
				// set Transparency
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.RED);

				content.setFontAndSize(base, 12);
				// 水印文字成45度角倾斜 x 55 y 20
				/*Image instance = Image.getInstance(waterImgPath);
				instance.setAbsolutePosition(x-46,  y-10);
				content.addImage(instance);
				content.showTextAligned(Element.ALIGN_CENTER
						, waterMarkName, x,
						y, 0);*/
				
				Image instance;
				float lastImgHeight = 10;
				float x = 65;
				for (int j = 0; j < stamps.size(); j++) {
					Stamp stamp = stamps.get(j);
					String stamp_true_name = stamp.getStamp_true_name();
					String wtp = waterImgPath + stamp_true_name + ".png";
					instance = Image.getInstance(wtp);
					
					//instance.setAbsolutePosition(x-46,  y1-y-10-lastImgHeight);
					instance.setAbsolutePosition(10,lastImgHeight);
					
					content.addImage(instance);
					// 水印文字成45度角倾斜
					if ((stamp_true_name.equals("shizhi")||stamp_true_name.equals("shoukong")||stamp_true_name.equals("yiciyongtu"))) {
						/*content.showTextAligned(Element.ALIGN_CENTER
								, dateStr, x,
								y1-y-lastImgHeight, 0);	*/  
						
						content.showTextAligned(Element.ALIGN_CENTER
								, dateStr, instance.getWidth()/2+10,
								1*instance.getHeight()/4+lastImgHeight, 0);
					}
					
					lastImgHeight += instance.getHeight();
					 
				}
				
				content.endText(); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			content = null;
			base = null;
			//pageRect = null;
		}
	}
	void addWatermarkRightUpNew(PdfStamper pdfStamper
			, String waterImgPath , String dateStr,List<Stamp> stamps) {
		PdfContentByte content = null;
		BaseFont base = null;
		Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
			// 设置字体
			base = BaseFont.createFont("STSongStd-Light", 
					"UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (base == null || pdfStamper == null) {
				return;
			}
			// 设置透明度为0.4
			gs.setFillOpacity(1.0f);
			gs.setStrokeOpacity(1.0f);
			int toPage = pdfStamper.getReader().getNumberOfPages();
			for (int i = 1; i <= toPage; i++) {
				pageRect = pdfStamper.getReader().
						getPageSizeWithRotation(i);
				// 计算水印X,Y坐标
				float x1 = pageRect.getWidth() ;
				float y1 = pageRect.getHeight() ;
				//float y1 = pageRect.getHeight() ;
				//获得PDF最顶层
				content = pdfStamper.getOverContent(i);
				content.saveState();
				// set Transparency
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.RED);
				content.setFontAndSize(base, 12);
				// 水印文字成45度角倾斜 x 65 y 46
				/*Image instance = Image.getInstance(waterImgPath);
				instance.setAbsolutePosition(x1-x-47,  y1-y-10);
				content.addImage(instance);

				content.showTextAligned(Element.ALIGN_CENTER
						, waterMarkName, x1-x,
						y1-y, 0);*/
				Image instance;
				float lastImgHeight = 10;
				float x = 65;
				for (int j = 0; j < stamps.size(); j++) {
					Stamp stamp = stamps.get(j);
					String stamp_true_name = stamp.getStamp_true_name();
					String wtp = waterImgPath + stamp_true_name + ".png";
					instance = Image.getInstance(wtp);
					
					//instance.setAbsolutePosition(x-46,  y1-y-10-lastImgHeight);
					instance.setAbsolutePosition(x1-instance.getWidth()-10,  y1-instance.getHeight()-lastImgHeight);
					
					content.addImage(instance);
					// 水印文字成45度角倾斜
					if ((stamp_true_name.equals("shizhi")||stamp_true_name.equals("shoukong")||stamp_true_name.equals("yiciyongtu"))) {
						/*content.showTextAligned(Element.ALIGN_CENTER
								, dateStr, x,
								y1-y-lastImgHeight, 0);	*/  
						
						content.showTextAligned(Element.ALIGN_CENTER
								, dateStr, x1-instance.getWidth()/2-10,
								y1-3*instance.getHeight()/4-lastImgHeight, 0);
					}
					
					lastImgHeight += instance.getHeight();
					 
				}
				
				
				content.endText(); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			content = null;
			base = null;
			//pageRect = null;
		}
	}
	void addWatermarkLeftUpNew(PdfStamper pdfStamper
			, String waterImgPath , String dateStr,List<Stamp> stamps) {
		PdfContentByte content = null;
		BaseFont base = null;
		Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
			// 设置字体
			base = BaseFont.createFont("STSongStd-Light", 
					"UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (base == null || pdfStamper == null) {
				return;
			}
			// 设置透明度为0.4
			gs.setFillOpacity(1.0f);
			gs.setStrokeOpacity(1.0f);
			int toPage = pdfStamper.getReader().getNumberOfPages();
			for (int i = 1; i <= toPage; i++) {
				pageRect = pdfStamper.getReader().
						getPageSizeWithRotation(i);
				// 计算水印X,Y坐标
				//float x = pageRect.getWidth() / 2;
				float y1 = pageRect.getHeight() ;
				//获得PDF最顶层
				content = pdfStamper.getOverContent(i);
				content.saveState();
				// set Transparency
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.RED);
				content.setFontAndSize(base, 12);
				
				Image instance;
				float lastImgHeight = 10;
				float x = 55;
				for (int j = 0; j < stamps.size(); j++) {
					Stamp stamp = stamps.get(j);
					String stamp_true_name = stamp.getStamp_true_name();
					String wtp = waterImgPath + stamp_true_name + ".png";
					//File des = new File(waterImgPath+stamp_true_name+"tmp.png");
					instance = Image.getInstance(wtp);
					//instance = Image.getInstance(ImageFactory.imgZoom(new File(wtp), des, 0.5f, 0.5f).getCanonicalPath());
					//instance.setAbsolutePosition(x-46,  y1-y-10-lastImgHeight);
					instance.setAbsolutePosition(x-46,  y1-instance.getHeight()-lastImgHeight);
					
					content.addImage(instance);
					
					// 水印文字成45度角倾斜
					if ((stamp_true_name.equals("shizhi")||stamp_true_name.equals("shoukong")||stamp_true_name.equals("yiciyongtu"))) {
						/*content.showTextAligned(Element.ALIGN_CENTER
								, dateStr, x,
								y1-y-lastImgHeight, 0);	*/  
						
						content.showTextAligned(Element.ALIGN_CENTER
								, dateStr, x,
								y1-3*instance.getHeight()/4-lastImgHeight, 0);
					}
					
					lastImgHeight += instance.getHeight();
					 
				}
				                 
				content.endText(); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			content = null;
			base = null;
			pageRect = null;
		}
	}	
	//
	void addWatermarkLeftUp(PdfStamper pdfStamper
			, String waterImgPath ,String waterMarkName,float x,float y) {
		PdfContentByte content = null;
		BaseFont base = null;
		Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
			// 设置字体
			base = BaseFont.createFont("STSongStd-Light", 
					"UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (base == null || pdfStamper == null) {
				return;
			}
			// 设置透明度为0.4
			gs.setFillOpacity(1.0f);
			gs.setStrokeOpacity(1.0f);
			int toPage = pdfStamper.getReader().getNumberOfPages();
			for (int i = 1; i <= toPage; i++) {
				pageRect = pdfStamper.getReader().
						getPageSizeWithRotation(i);
				// 计算水印X,Y坐标
				//float x = pageRect.getWidth() / 2;
				float y1 = pageRect.getHeight() ;
				//获得PDF最顶层
				content = pdfStamper.getOverContent(i);
				content.saveState();
				// set Transparency
				content.setGState(gs);
				content.beginText();
				content.setColorFill(BaseColor.RED);
				content.setFontAndSize(base, 12);
				Image instance = Image.getInstance(waterImgPath);
				instance.setAbsolutePosition(x-46,  y1-y-10);
				content.addImage(instance);
				// 水印文字成45度角倾斜
				content.showTextAligned(Element.ALIGN_CENTER
						, waterMarkName, x,
						y1-y, 0);	                    
				content.endText(); 
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			content = null;
			base = null;
			pageRect = null;
		}
	}	
	


	//左上
	void addPdfMarkLeftUp(String InPdfFile, String outPdfFile, String markImagePath,int x,int y) throws Exception {  

		PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());  

		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));  

		Image img = Image.getInstance(markImagePath);// 插入水印     


		for(int i = 1; i <= reader.getNumberOfPages(); i++) {  

			float pageHeight = reader.getPageSize(i).getHeight();
			float imgHeight = img.getHeight();


			img.setAbsolutePosition(x, pageHeight-imgHeight-y);


			PdfContentByte under = stamp.getOverContent(i);  

			under.addImage(img);  


		}  

		stamp.close();// 关闭   



	} 
	//左中
	void addPdfMarkLeftCenter(String InPdfFile, String outPdfFile, String markImagePath,int x,int y) throws Exception {  

		PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());  

		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));  

		Image img = Image.getInstance(markImagePath);// 插入水印     


		for(int i = 1; i <= reader.getNumberOfPages(); i++) {  
			float pageHeight = reader.getPageSize(i).getHeight();
			float imgHeight = img.getHeight();

			img.setAbsolutePosition(x, (pageHeight-imgHeight)/2);


			PdfContentByte under = stamp.getOverContent(i);  

			under.addImage(img);  


		}  

		stamp.close();// 关闭   



	} 

	void addPdfMarkRightDown(String InPdfFile, String outPdfFile, String markImagePath,int x,int y) throws Exception {  

		PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());  

		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));  

		Image img = Image.getInstance(markImagePath);// 插入水印     


		for(int i = 1; i <= reader.getNumberOfPages(); i++) {  
			float pageHeight = reader.getPageSize(i).getHeight();
			float pageWidth = reader.getPageSize(i).getWidth();
			float imgHeight = img.getHeight();
			float imgWidth = img.getWidth();

			img.setAbsolutePosition(pageWidth-imgWidth-x, y);


			PdfContentByte under = stamp.getOverContent(i);  

			under.addImage(img);  


		}  

		stamp.close();// 关闭   



	} 
	void addPdfMarkRightUp(String InPdfFile, String outPdfFile, String markImagePath,int x,int y) throws Exception {  

		PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());  

		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));  

		Image img = Image.getInstance(markImagePath);// 插入水印     


		for(int i = 1; i <= reader.getNumberOfPages(); i++) {  
			float pageHeight = reader.getPageSize(i).getHeight();
			float pageWidth = reader.getPageSize(i).getWidth();
			float imgHeight = img.getHeight();
			float imgWidth = img.getWidth();
			String info = "page:"+i+" pageHeight:"+pageHeight+" pageWidth:"+pageWidth+" imgHeight:"+imgHeight+" imgWidth:"+imgWidth;
			System.out.println(info);
			System.out.println("why not print------------>");
			System.out.println("img-->x:"+img.getAbsoluteX()+" y:"+img.getAbsoluteY());
			img.setAbsolutePosition(pageWidth-imgWidth-x, pageHeight-imgHeight-y);


			PdfContentByte under = stamp.getOverContent(i);  

			under.addImage(img);  


		}  

		stamp.close();// 关闭   



	} 
	void addPdfMarkLeftDown(String InPdfFile, String outPdfFile, String markImagePath,int x,int y) throws Exception {  

		PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());  

		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));  

		Image img = Image.getInstance(markImagePath);// 插入水印     


		for(int i = 1; i <= reader.getNumberOfPages(); i++) {  


			img.setAbsolutePosition(x, y);


			PdfContentByte under = stamp.getOverContent(i);  

			under.addImage(img);  


		}  

		stamp.close();// 关闭   



	}


	PrintService findPrintServiceByName(String name){
		PrintService ps=null;

		HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet(); 

		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE; 

		PrintService[] pservices = PrintServiceLookup.lookupPrintServices(flavor, pras);

		if(pservices!=null&&pservices.length>0){
			for(PrintService pservice:pservices){
				if(name.equals(pservice.getName())){
					return pservice;
				}
			}
		}

		return ps;
	}
	public String DownloadFileto(File sourceFile, String dataset_type,
			String realpath,String username,String bgroup) {
		// TODO Auto-generated method stub
		System.out.println("DownloadFileto---------------------------------->");
		String path ="";
		try {
			String sourcePath = sourceFile.getCanonicalPath();
			System.out.println(sourcePath+" type:"+dataset_type);
			Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			if(dataset_type.equals("MSExcel")||dataset_type.equals("MSExcelX")){
				String toPDFFile = realpath + doActionPath +"/" + sourceFile.getName() + ".pdf";

				File pdfFile = new File(toPDFFile);				

				if(!pdfFile.exists()){
					if (!pdfFile.getParentFile().exists()) 
						pdfFile.getParentFile().mkdirs();

					pdfFile = Excel2PDF(sourcePath,toPDFFile);

				}
				path=pdfFile.getName();
				//rotatedPdf(toPDFFile,90);




			}else if(dataset_type.equals("MSWord")||dataset_type.equals("MSWordX")){

				String toPDFFile = realpath + doActionPath +"/" + sourceFile.getName() + ".pdf";

				File pdfFile = new File(toPDFFile);				

				if(!pdfFile.exists()){
					if (!pdfFile.getParentFile().exists()) 
						pdfFile.getParentFile().mkdirs();

					pdfFile = Word2PDF(sourcePath,toPDFFile);

				}					

				path=pdfFile.getName();




			}if(dataset_type.equals("PDF")||dataset_type.equals("PDF")){

				String PDFFile = realpath + doActionPath +"/" + sourceFile.getName();
				File pdfFile = new File(PDFFile);

				if(!pdfFile.exists()){
					Copy(sourceFile.getCanonicalPath(),PDFFile);

				}


				path = sourceFile.getName();


			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

		return path;
	}
	public  String getSimpleFileName(String fileName) {
		return fileName.substring(0, fileName.indexOf("."));
	}

	@Override
	public String DownloadFile(File sourceFile, String dataset_type,
			String realpath,List<Stamp> stamp_list,String username,String wp,String bgroup,boolean previewExcel) {
		// TODO Auto-generated method stub

		String globalPDFPath ="";
		try {
			System.out.println("DownloadFile-->"+username+"-->"+wp+"-->"+bgroup+"-->sourcePath"+sourceFile.getCanonicalPath()+" type:"+dataset_type);
			String sourceFilePath = sourceFile.getCanonicalPath();

			//Date date = new Date();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfDetail = new SimpleDateFormat("yyyyMMddHHmmss");
			String toPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(sourceFile.getName()) + ".pdf";
			File toPDFFile = new File(toPDFFilePath);

			if (!toPDFFile.getParentFile().exists()) 
				toPDFFile.getParentFile().mkdirs(); 



			String wPosition = "";
			if (wp.equals("左上"))
				wPosition = "leftup";
			else if (wp.equals("右下"))
				wPosition = "rightdown";
			else if(wp.equals("左下"))
				wPosition = "leftdown";
			else 
				wPosition = "rightup";



			if(dataset_type.equals("PDF")||dataset_type.equals("PDF")){


				if(!toPDFFile.exists()){
					Copy(sourceFile.getCanonicalPath(),toPDFFilePath);
				}

			}else if(dataset_type.equals("MSExcel")||dataset_type.equals("MSExcelX")){			

				/*if(previewExcel) { //预览Excel直接打开Excel预览

					File localFile = new File(realpath + doActionPath +"/" +sourceFile.getName());
					if(!localFile.exists()) {
						FileUtil.copyFile(sourceFile, localFile); //解决(请求的操作无法在使用用户映射区域打开的文件上执行。)
					}
					return localFile.getName();

				} else {

					toPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(sourceFile.getName())+"excel2pdf" + ".pdf";
					toPDFFile = new File(toPDFFilePath);
					if(!toPDFFile.exists()) {
						File localFile = new File(realpath + doActionPath +"/" +sourceFile.getName());
						if(!localFile.exists()) {
							FileUtil.copyFile(sourceFile, localFile); //解决(请求的操作无法在使用用户映射区域打开的文件上执行。)
						}

						toPDFFile = Excel2PDF(localFile.getCanonicalPath(),toPDFFilePath);
					}

				}*/



			
				toPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(sourceFile.getName())+"excel2pdf" + ".pdf";
				toPDFFile = new File(toPDFFilePath);
				if(!toPDFFile.exists()) {
					//File localFile = new File(realpath + doActionPath +"/" +sourceFile.getName());
					File fitFile = new File(realpath + doActionPath +"/" +sourceFile.getName());
					if(!fitFile.exists()) {//解决缓存
						fitExcel(sourceFile, fitFile);
						//FileUtil.copyFile(arg0, arg1);
						//FileUtil.copyFile(sourceFile, localFile); //解决(请求的操作无法在使用用户映射区域打开的文件上执行。)
					}

					toPDFFile = Excel2PDF(fitFile.getCanonicalPath(),toPDFFilePath);
				}
				
			}else if(dataset_type.equals("MSWord")||dataset_type.equals("MSWordX")){


				toPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(sourceFile.getName())+"word2pdf" + ".pdf";
				toPDFFile = new File(toPDFFilePath);
				if(!toPDFFile.exists()) {
					File localFile = new File(realpath + doActionPath +"/" +sourceFile.getName());
					if(!localFile.exists()) {
						FileUtil.copyFile(sourceFile, localFile); //解决(请求的操作无法在使用用户映射区域打开的文件上执行。)
					}
					toPDFFile = Word2PDF(localFile.getCanonicalPath(),toPDFFilePath);
				}




			}

			System.out.println("ImgName-->"+stamp_list+"-->path-->"+realpath + "WaterMark1/"+stamp_list+".png");
			System.out.println("toPDFFilePath-->"+toPDFFilePath);
			System.out.println("sourceFilePath-->"+sourceFilePath);
			if(stamp_list==null
			 ||(
					 stamp_list.size() == 1 &&
					 stamp_list.get(0).getStamp_true_name().equals("nowatermark")
				)){
				globalPDFPath=toPDFFile.getName();
			}else{
				String destPDFFilePath ;


				//String waterImgFilePath = realpath + "WaterMark/"+stamp_list+".png";



/*
				if ((stamp_list.equals("shizhi")||stamp_list.equals("shoukong")||stamp_list.equals("yiciyongtu"))) {
					//String pdfFileTmp = (new StringBuilder(String.valueOf(realpath))).append(doActionPath).append("/").append(sourceFile.getName()).append(sdf.format(new Date())).append(".pdf").toString();
					destPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(toPDFFile.getName()) +sdfDetail.format(new Date()) +".pdf";
					File destPDFFile = new File(destPDFFilePath);
					if(destPDFFile.exists() ) {
						return destPDFFile.getName(); 
					}
					if (wp.equals("左上"))
						addImageDatePDFLeftUpNew(toPDFFilePath, destPDFFilePath,stamp_list,sdf.format(new Date()));
					else if(wp.equals("右下"))
						addImageDatePDFRightDown(toPDFFilePath, destPDFFilePath,waterImgFilePath,sdf.format(new Date()));
					else if(wp.equals("左下"))
						addImageDatePDFLeftDown(toPDFFilePath, destPDFFilePath, waterImgFilePath, sdf.format(new Date()));
					else 
						addImageDatePDFRightUp(toPDFFilePath, destPDFFilePath, waterImgFilePath, sdf.format(new Date()));



					//	return tmpPdfFile.getName(); 
				} else {
					destPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(toPDFFile.getName()) + stamp_list+wPosition +".pdf";
					File destPDFFile = new File(destPDFFilePath);
					if(destPDFFile.exists() ) {
						return destPDFFile.getName(); 
					}
					if (wp.equals("左上"))
						addImageDatePDFLeftUp(toPDFFilePath, destPDFFilePath,waterImgFilePath,"");
					else if(wp.equals("右下"))
						addImageDatePDFRightDown(toPDFFilePath, destPDFFilePath,waterImgFilePath,"");
					else if(wp.equals("左下"))
						addImageDatePDFLeftDown(toPDFFilePath, destPDFFilePath, waterImgFilePath, "");
					else 
						addImageDatePDFRightUp(toPDFFilePath, destPDFFilePath, waterImgFilePath, "");





				}*/
				String waterImgFilePath = realpath + "WaterMark/";
				destPDFFilePath = realpath + doActionPath +"/" + getSimpleFileName(toPDFFile.getName()) +sdfDetail.format(new Date()) +".pdf";
				if (wp.equals("左上"))
					addImageDatePDFLeftUpNew(toPDFFilePath, destPDFFilePath,waterImgFilePath,stamp_list,sdf.format(new Date()));
				else if(wp.equals("右下"))
					addImageDatePDFRightDownNew(toPDFFilePath, destPDFFilePath, waterImgFilePath,stamp_list, sdf.format(new Date()));
				else if(wp.equals("左下"))
					addImageDatePDFLeftDownNew(toPDFFilePath, destPDFFilePath, waterImgFilePath,stamp_list, sdf.format(new Date()));
				else 
					addImageDatePDFRightUpNew(toPDFFilePath, destPDFFilePath, waterImgFilePath,stamp_list, sdf.format(new Date()));
				
				File destPDFFile = new File(destPDFFilePath);
				System.out.println("destPDFFilePath-->"+new File(destPDFFilePath).getName());
				return destPDFFile.getName(); 



			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	



		System.out.println("dstype->"+dataset_type+"-->path-->"+globalPDFPath);
		return globalPDFPath;
	}

	public File fitExcel(File src,File des) {
		
		try {
			Workbook workbook = WorkbookFactory.create(src);
			Sheet sheet = workbook.getSheetAt(0);
			
			Map<Integer,Integer> map = new HashMap<>();
			
			for (int r = 0; r < sheet.getPhysicalNumberOfRows(); r++) {
				Row row = sheet.getRow(r);
			    if(row != null) {
			    	for (int c = 0; c < row.getPhysicalNumberOfCells(); c++) {
						Cell cell = row.getCell(c);
						if(cell != null) {
							String cellValue = getCellValue(cell);
							 org.apache.poi.ss.usermodel.Font font = workbook.getFontAt(cell.getCellStyle().getFontIndex());
							 
							// System.out.println(font.getFontName());
							 
							 FontMetrics metrics = new FontMetrics(new Font(font.getFontName(),Font.PLAIN, font.getFontHeightInPoints())) {  
							 };  
							 java.awt.geom.Rectangle2D bounds = metrics.getStringBounds(cellValue, null);  
							 int widthInPixels = (int) bounds.getWidth(); 
							 int heightInPixels = (int) bounds.getHeight(); 
							// System.out.println(widthInPixels);
							// System.out.println(heightInPixels);
							 
							if(map.containsKey(c)) {
								map.put(c, Math.max(widthInPixels, map.get(c)));
								//map.put(c, Math.max(cellValue.getBytes("GBK").length, map.get(c)));
							} else {
								//map.put(c, cellValue.getBytes("GBK").length);
								map.put(c, widthInPixels);
							}
						}
					}
			    }
			}
			
			
			for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
				sheet.setColumnWidth(entry.getKey(),entry.getValue()*70);
			}
			
			
			
			 workbook.write(new FileOutputStream(des));
			 return des;
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	
	}
	
	public  String getCellValue(Cell cell) {
		String value;
		switch (cell.getCellType()) {	
		case Cell.CELL_TYPE_FORMULA :
			System.out.print(cell.getCellFormula()+" ");
			
			try {
				value = String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				value = String.valueOf(cell.getRichStringCellValue());
			}			
			break;
		case Cell.CELL_TYPE_NUMERIC :
			value = String.format("%.0f", cell.getNumericCellValue()) ;
			
			break;
		case Cell.CELL_TYPE_STRING :
			value = cell.getStringCellValue();
			
			break;
		case Cell.CELL_TYPE_BOOLEAN :
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK :
			value = "";
			break;
		default :
			value = "unknown";
			break;
		}
		return value;
	}
	
	@Override
	public void  Copy(String oldPath, String newPath) {
		try {
			FileUtil.copyFile(new File(oldPath), new File(newPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	File Excel2PDF(String sourcePath,String toPDFFile){
		System.out.println("开始转换成pdf文件:" + sourcePath); 
		long lasting2 = System.currentTimeMillis();


		ActiveXComponent app = null;  
		try{
			ComThread.InitSTA();
			app = new ActiveXComponent("Excel.Application");  
			app.setProperty("Visible", false);
			Dispatch excels = app.getProperty("Workbooks").toDispatch(); 


			Dispatch excel = Dispatch.call(excels,
					"Open",
					sourcePath,
					false,
					true
					).toDispatch(); 


			Dispatch.call(excel, "ExportAsFixedFormat", new Variant(0), new Variant(toPDFFile), new Variant(0), 
					new Variant(false), new Variant(true)); 


			Dispatch.call(excel, "Close",false); 

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (app != null)  
				app.invoke("Quit");  
			ComThread.Release();
		}
		System.out.println(toPDFFile+"转换完成,耗时:"+(System.currentTimeMillis() - lasting2) + " ms"); 

		File file = new File(toPDFFile);
		if(file.exists()){
			return file;
		}		
		return null;
	}

	File PPT2PDF(String sourcePath,String toPDFFile){
		System.out.println("开始转换成pdf文件:" + sourcePath);

		long lasting2 = System.currentTimeMillis();
		ActiveXComponent app = null;  
		try{
			ComThread.InitSTA();
			app = new ActiveXComponent("PowerPoint.Application");  
			//app.setProperty("Visible", false);
			Dispatch ppts = app.getProperty("Presentations").toDispatch(); 
			Dispatch ppt = Dispatch.call(ppts,
					"Open",
					sourcePath,
					true,//ReadOnly
					true,//Untitled指定文件是否有标题
					false//WithWindow指定文件是否可见
					).toDispatch();
			Dispatch.call(ppt,
					"SaveAs",
					toPDFFile,
					32 
					);
			Dispatch.call(ppt, "Close"); 

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (app != null)  
				app.invoke("Quit");  
			ComThread.Release();
		}
		System.out.println(toPDFFile+"转换完成,耗时:"+(System.currentTimeMillis() - lasting2) + " ms"); 

		File file = new File(toPDFFile);
		if(file.exists()){
			return file;
		}		
		return null;
	}


	@Override
	public List<ImageModel> getWaterMark(String site,String group) {
		// TODO Auto-generated method stub
		List<ImageModel> images = new ArrayList<ImageModel> ();

		//String path = ServletActionContext.getServletContext().getRealPath("/");
		String toPngFileHead ="WaterMark1/";

		System.out.println("getWaterMark departname:--------->"+group);

		if(group.equals("dba")||group.equals("资料室")){
			List all = stampManager.getStampsByOrder();
			for(Iterator<Stamp> iterator = all.iterator();iterator.hasNext();) {
				Stamp next = iterator.next();
				ImageModel model = new ImageModel();
				model.setSrc(toPngFileHead+next.getStamp_true_name()+".png");
				model.setCaption(next.getStamp_disy_name());
				images.add(model);
			}



		} else {

			/*			Stamp nowaterStamp = stampManager.getStampById("1");

			ImageModel nowaterStampModel = new ImageModel();
			nowaterStampModel.setSrc(toPngFileHead+nowaterStamp.getStamp_true_name()+".png");
			nowaterStampModel.setCaption(nowaterStamp.getStamp_disy_name());
			images.add(nowaterStampModel);*/
			
			
			if("财务部".equals(group)
					||"仓库".equals(group)) {
						List departStamp1 = stampManager.getStampAfterCK();
						for(Iterator<Stamp> iterator = departStamp1.iterator();iterator.hasNext();) {
							Stamp next = iterator.next();
							ImageModel model = new ImageModel();
							model.setSrc(toPngFileHead+next.getStamp_true_name()+".png");
							model.setCaption(next.getStamp_disy_name());
							images.add(model);
						}
						return images;
					}

			List departStamp = stampManager.getStampByDepartName(group);
			System.out.println(group+":departStamp size:"+departStamp.size());
			for(Iterator<Stamp> iterator = departStamp.iterator();iterator.hasNext();) {
				Stamp next = iterator.next();
				ImageModel model = new ImageModel();
				System.out.println(group+":getStamp_true_name:"+next.getStamp_true_name());
				model.setSrc(toPngFileHead+next.getStamp_true_name()+".png");
				model.setCaption(next.getStamp_disy_name());
				images.add(model);
			}
			if("驱动桥研发部".equals(group)) {
				List departStamp1 = stampManager.getStampByDepartName("产品开发部");
				for(Iterator<Stamp> iterator = departStamp1.iterator();iterator.hasNext();) {
					Stamp next = iterator.next();
					ImageModel model = new ImageModel();
					model.setSrc(toPngFileHead+next.getStamp_true_name()+".png");
					model.setCaption(next.getStamp_disy_name());
					images.add(model);
				}
			}
			
			if("经营部".equals(group)
			  ||"采购部".equals(group)) {
            		ImageModel model = new ImageModel();
					model.setSrc(toPngFileHead+"shoukong"+".png");
					model.setCaption("受控");
					images.add(model);
				
			}


		}



		return images;
	}






	void findDatasetByItemRev(tempModelItemRevision ir,String relation){

		String sql = 
				"select pfile_name,psd_path_name,dsname,dstype from PIMANFILE  L,(  "+
						"select pvalu_0,dspuid,dsname,dstype from PREF_LIST_0  J,(  "+
						"select H.puid  dspuid,H.pobject_name  dsname,H.pobject_type  dstype from PWORKSPACEOBJECT  H,(	  "+	
						"select F.rsecondary_objectu  dspuid from PIMANRELATION F  "+
						"where F.rprimary_objectu = ? and  rrelation_typeu in ("+relation+") "+
						")  I where H.puid = I.dspuid and H.pobject_type in ('MSExcelX','MSWordX','MSExcel','MSWord','PDF') and pactive_seq='1' "+  //prevision_limit
						")  K where J.puid = K.dspuid "+
						")  M where L.puid = M.pvalu_0 "
						;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, ir.getPuid());			

			rs = statement.executeQuery();		
			List<tempModelDS> dss = new ArrayList<tempModelDS>();
			System.out.println("prepare in search ds----------->itemrev-->"+ir.getPuid()+"-->relation--->"+relation);
			while(rs.next()){
				System.out.println("having in search ds----------->");
				String s1 = rs.getString("pfile_name");
				String s2 = rs.getString("psd_path_name");
				String s3 = rs.getString("dsname");
				String s4 = rs.getString("dstype");

				tempModelDS model = new tempModelDS();
				model.setDsname(s3);
				model.setDstype(s4);
				System.out.println("dsname-->"+s3+"-->dstype-->"+s4);

				for(String fp:con.getPwnt_path_name()){
					File tf = new File(fp+"\\"+s2+"\\"+s1);
					System.out.println("Path-->"+fp+"\\"+s2+"\\"+s1);
					if(tf.exists()){
						model.setFilepath(fp+"\\"+s2+"\\"+s1);
						System.out.println("filePath-->"+model.getFilepath());
						break;
					}
				}

				dss.add(model);

			}

			ir.setDss(dss);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
	}
	
	void findDatasetByItemRev(tempModelItemRevision ir,String relation,List<tempModelDS> dss){

		String sql = 
				"select pfile_name,psd_path_name,dsname,dstype from PIMANFILE  L,(  "+
						"select pvalu_0,dspuid,dsname,dstype from PREF_LIST_0  J,(  "+
						"select H.puid  dspuid,H.pobject_name  dsname,H.pobject_type  dstype from PWORKSPACEOBJECT  H,(	  "+	
						"select F.rsecondary_objectu  dspuid from PIMANRELATION F  "+
						"where F.rprimary_objectu = ? and  rrelation_typeu in ("+relation+") "+
						")  I where H.puid = I.dspuid and H.pobject_type in ('MSExcelX','MSWordX','MSExcel','MSWord','PDF') and pactive_seq='1' "+  //prevision_limit
						")  K where J.puid = K.dspuid "+
						")  M where L.puid = M.pvalu_0 "
						;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, ir.getPuid());			

			rs = statement.executeQuery();		
			//List<tempModelDS> dss = new ArrayList<tempModelDS>();
			System.out.println("prepare in search ds----------->itemrev-->"+ir.getPuid()+"-->relation--->"+relation);
			while(rs.next()){
				System.out.println("having in search ds----------->");
				String s1 = rs.getString("pfile_name");
				String s2 = rs.getString("psd_path_name");
				String s3 = rs.getString("dsname");
				String s4 = rs.getString("dstype");

				tempModelDS model = new tempModelDS();
				model.setDsname(s3);
				model.setDstype(s4);
				System.out.println("dsname-->"+s3+"-->dstype-->"+s4);

				for(String fp:con.getPwnt_path_name()){
					File tf = new File(fp+"\\"+s2+"\\"+s1);
					System.out.println("Path-->"+fp+"\\"+s2+"\\"+s1);
					if(tf.exists()){
						model.setFilepath(fp+"\\"+s2+"\\"+s1);
						System.out.println("filePath-->"+model.getFilepath());
						break;
					}
				}

				dss.add(model);

			}

			ir.setDss(dss);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
	}

	void findDatasetByItemRevRelation(tempModelItemRevision ir){

		String sql = 
				"select pfile_name,psd_path_name,dsname,dstype from PIMANFILE  L,( "+ 
						"select pvalu_0,dspuid,dsname,dstype from PREF_LIST_0  J,(  "+ 
						"select B.puid dspuid,B.pobject_name dsname,B.pobject_type  dstype,A.rrelation_typeu from (select RSECONDARY_OBJECTU,rrelation_typeu "+ 
						"from pimanrelation where rprimary_objectu = ?) A "+ 
						"join pworkspaceobject B on A.RSECONDARY_OBJECTU = B.puid and B.pobject_type in ('MSExcelX','MSWordX','MSExcel','MSWord','PDF') and pactive_seq='1'"+  
						")  K where J.puid = K.dspuid "+ 
						")  M where L.puid = M.pvalu_0 ";
		;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, ir.getPuid());			

			rs = statement.executeQuery();		
			List<tempModelDS> dss = new ArrayList<tempModelDS>();
			System.out.println("prepare in search ds----------->itemrev-->"+ir.getPuid());
			while(rs.next()){
				System.out.println("having in search ds----------->");
				String s1 = rs.getString("pfile_name");
				String s2 = rs.getString("psd_path_name");
				String s3 = rs.getString("dsname");
				String s4 = rs.getString("dstype");

				tempModelDS model = new tempModelDS();
				model.setDsname(s3);
				model.setDstype(s4);
				System.out.println("dsname-->"+s3+"-->dstype-->"+s4);

				for(String fp:con.getPwnt_path_name()){
					File tf = new File(fp+"\\"+s2+"\\"+s1);
					System.out.println("Path-->"+fp+"\\"+s2+"\\"+s1);
					if(tf.exists()){
						model.setFilepath(fp+"\\"+s2+"\\"+s1);
						System.out.println("filePath-->"+model.getFilepath());
						break;
					}
				}

				dss.add(model);

			}

			ir.setDss(dss);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
	}



	List<tempModelItemRevision> findItemRevByTypeOfQDQ(String itemid,String revid,String name,String type){
		List<tempModelItemRevision> itemRevs = new ArrayList<tempModelItemRevision>();
		System.out.println("findItemRevByType---->");

		String sql = 
				"select Z.pitem_id,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released,Z.pobject_desc,Z.pfw9MaterialCode from (  "+
						"select A.puid,A.pobject_desc,C.pitem_id,A.pobject_name,C.pitem_revision_id,A.pdate_released,C.pfw9MaterialCode from (  "+
						"select puid,pobject_name,pdate_released,pobject_desc from PWORKSPACEOBJECT where upper(pobject_name) like ? and pobject_type = ? and pactive_seq=1  "+ 
						")  A join (  "+
						"select B.puid,A.pitem_id,B.pitem_revision_id,C.pfw9MaterialCode from PITEM A,PITEMREVISION B,PFW9ITEM C  "+
						"where upper(A.pitem_id) like ? and upper(B.pitem_revision_id) like ? and A.puid = B.ritems_tagu and A.puid =C.puid   "+

						")  C on A.puid = C.puid  "+
						") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc  "
						;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			if(itemid.equals("")){
				itemid="*";
			}

			if(revid.equals("")){
				revid="*";
			}

			if(name.equals("")){
				name="*";
			}

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, name.replace("*", "%").toUpperCase());
			statement.setString(2, type);
			statement.setString(3, itemid.replace("*", "%").toUpperCase());
			statement.setString(4, revid.replace("*", "%").toUpperCase());
			System.out.println("findItemRevByType-->"+sql);			
			rs = statement.executeQuery();	
			int count = 0;

			while(rs.next()){
				count++;
				String pitem_id = rs.getString("pitem_id");
				String puid = rs.getString("puid");
				String pitem_revision_id = rs.getString("pitem_revision_id");
				String pobject_name = rs.getString("pobject_name");
				String pobject_desc = rs.getString("pobject_desc");
				String object_code = rs.getString("pfw9MaterialCode");
				tempModelItemRevision model = new tempModelItemRevision();
				model.setItemid(pitem_id);
				model.setItemid_new(pitem_id);
				model.setPuid(puid);
				model.setRevid(pitem_revision_id);
				model.setName(pobject_name);
				model.setRemark(pobject_desc);
				model.setObject_code(object_code);


				if(!isContainsItemRevByItemid(itemRevs,model)){
					itemRevs.add(model);
				}

			}
			System.out.println("res itemRev size-->"+count);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return itemRevs;
	}
	List<tempModelItemRevision> findItemRevByType(String itemid,String revid,String name,String type){
		List<tempModelItemRevision> itemRevs = new ArrayList<tempModelItemRevision>();
		System.out.println("findItemRevByType---->");

		String sql = 
				"select Z.pitem_id,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (  "+
						"select A.puid,C.pitem_id,A.pobject_name,C.pitem_revision_id,A.pdate_released from (  "+
						"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where upper(pobject_name) like ? and pobject_type = ? and pactive_seq=1  "+ 
						")  A join (  "+
						"select B.puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B  "+
						"where upper(A.pitem_id) like ? and upper(B.pitem_revision_id) like ? and A.puid = B.ritems_tagu   "+

						")  C on A.puid = C.puid  "+
						") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc  "
						;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			if(itemid.equals("")){
				itemid="*";
			}

			if(revid.equals("")){
				revid="*";
			}

			if(name.equals("")){
				name="*";
			}

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, name.replace("*", "%").toUpperCase());
			statement.setString(2, type);
			statement.setString(3, itemid.replace("*", "%").toUpperCase());
			statement.setString(4, revid.replace("*", "%").toUpperCase());
			System.out.println("findItemRevByType-->"+sql);			
			rs = statement.executeQuery();	
			int count = 0;

			while(rs.next()){
				count++;
				String s1 = rs.getString("pitem_id");
				String s2 = rs.getString("puid");
				String s3 = rs.getString("pitem_revision_id");
				String s4 = rs.getString("pobject_name");
				tempModelItemRevision model = new tempModelItemRevision();
				model.setItemid(s1);
				model.setItemid_new(s1);
				model.setPuid(s2);
				model.setRevid(s3);
				model.setName(s4);

				if(!isContainsItemRevByItemid(itemRevs,model)){
					itemRevs.add(model);
				}

			}
			System.out.println("res itemRev size-->"+count);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return itemRevs;
	}
	List<tempModelItemRevision> findItemRevByTypeOld(String itemid,String revid,String name,String type){
		List<tempModelItemRevision> itemRevs = new ArrayList<tempModelItemRevision>();
		System.out.println("findItemRevByTypeOld---->");
		String sql = 
				"select Z.pitem_id,Z.pitem_id_old,Z.object_code,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (  "+
						"select A.puid,C.pitem_id,C.pitem_id_old,C.object_code,A.pobject_name,C.pitem_revision_id,A.pdate_released from (  "+
						"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where upper(pobject_name) like ? and pobject_type = ? and pactive_seq=1  "+ 
						")  A join (  "+
						"select B.puid,A.pitem_id,Y.pfw9_cp_lbj_03 pitem_id_old,Y.pfw9_cp_lbj_01 object_code,B.pitem_revision_id from PITEM A,PITEMREVISION B,PFW9_CP_LBJ Y  "+
						"where A.puid = Y.puid and upper(Y.pfw9_cp_lbj_03) like ? and upper(B.pitem_revision_id) like ? and A.puid = B.ritems_tagu   "+

						")  C on A.puid = C.puid  "+
						") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc  "
						;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			if(itemid.equals("")){
				itemid="*";
			}

			if(revid.equals("")){
				revid="*";
			}

			if(name.equals("")){
				name="*";
			}

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, name.replace("*", "%").toUpperCase());
			statement.setString(2, type);
			statement.setString(3, itemid.replace("*", "%").toUpperCase());
			statement.setString(4, revid.replace("*", "%").toUpperCase());
			System.out.println("sql item_id_old-->"+sql);			
			rs = statement.executeQuery();		

			while(rs.next()){
				String s1 = rs.getString("pitem_id");
				String strold = rs.getString("pitem_id_old");
				String s2 = rs.getString("puid");
				String s3 = rs.getString("pitem_revision_id");
				String s4 = rs.getString("pobject_name");
				String object_code = rs.getString("object_code");
				tempModelItemRevision model = new tempModelItemRevision();
				model.setItemid(s1);
				model.setItemid_new(s1);
				model.setItemid_old(strold);
				model.setPuid(s2);
				model.setRevid(s3);
				model.setName(s4);
				model.setObject_code(object_code);

				if(!isContainsItemRevByItemid(itemRevs,model)){
					itemRevs.add(model);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return itemRevs;
	}

	List<tempModelItemRevision> findItemRevByTypeOldLJT(String itemid,String revid,String name,String type){
		List<tempModelItemRevision> itemRevs = new ArrayList<tempModelItemRevision>();
		System.out.println("findItemRevByTypeOld---->");
		String sql = 
				"select Z.pitem_id,Z.pitem_id_old,Z.object_code,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (  "+
						"select A.puid,C.pitem_id,C.pitem_id_old,C.object_code,A.pobject_name,C.pitem_revision_id,A.pdate_released from (  "+
						"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where upper(pobject_name) like ? and pobject_type in ("+type+") and pactive_seq=1  "+ 
						")  A join (  "+
						"select B.puid,A.pitem_id,Y.pfw9_cp_lbj_03 pitem_id_old,Y.pfw9_cp_lbj_01 object_code,B.pitem_revision_id from PITEM A,PITEMREVISION B,PFW9_CP_LBJ Y  "+
						"where A.puid = Y.puid and upper(Y.pfw9_cp_lbj_03) like ? and upper(B.pitem_revision_id) like ? and A.puid = B.ritems_tagu   "+

						")  C on A.puid = C.puid  "+
						") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc  "
						;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			if(itemid.equals("")){
				itemid="*";
			}

			if(revid.equals("")){
				revid="*";
			}

			if(name.equals("")){
				name="*";
			}

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, name.replace("*", "%").toUpperCase());
			//statement.setString(2, type);
			statement.setString(2, itemid.replace("*", "%").toUpperCase());
			statement.setString(3, revid.replace("*", "%").toUpperCase());
			System.out.println("sql-->"+sql);			
			rs = statement.executeQuery();		

			while(rs.next()){
				String s1 = rs.getString("pitem_id");
				String strold = rs.getString("pitem_id_old");
				String s2 = rs.getString("puid");
				String s3 = rs.getString("pitem_revision_id");
				String s4 = rs.getString("pobject_name");
				String object_code = rs.getString("object_code");
				tempModelItemRevision model = new tempModelItemRevision();
				model.setItemid(s1);
				model.setItemid_new(s1);
				model.setItemid_old(strold);
				model.setPuid(s2);
				model.setRevid(s3);
				model.setName(s4);
				model.setObject_code(object_code);
				if(!isContainsItemRevByItemid(itemRevs,model)){
					itemRevs.add(model);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return itemRevs;
	}

	List<tempModelItemRevision> findItemRevByTypeNew(String itemid_new,String revid,String name,String type){
		List<tempModelItemRevision> itemRevs = new ArrayList<tempModelItemRevision>();
		System.out.println("findItemRevByTypeNew---->");
		String sql = 
				"select Z.pitem_id,Z.pitem_id_old,Z.object_code,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (  "+
						"select A.puid,C.pitem_id,C.pitem_id_old,C.object_code,A.pobject_name,C.pitem_revision_id,A.pdate_released from (  "+
						"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where upper(pobject_name) like ? and pobject_type = ? and pactive_seq=1  "+ 
						")  A join (  "+
						"select B.puid,A.pitem_id,Y.pfw9_cp_lbj_03 pitem_id_old,Y.pfw9_cp_lbj_01 object_code,B.pitem_revision_id from PITEM A,PITEMREVISION B,PFW9_CP_LBJ Y  "+
						"where A.puid = Y.puid and upper(A.pitem_id) like ? and A.puid = Y.puid and upper(B.pitem_revision_id) like ? and A.puid = B.ritems_tagu   "+

						")  C on A.puid = C.puid  "+
						") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc  "
						;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			if(itemid_new.equals("")){
				itemid_new="*";
			}

			if(revid.equals("")){
				revid="*";
			}

			if(name.equals("")){
				name="*";
			}

			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, name.replace("*", "%").toUpperCase());
			statement.setString(2, type);
			statement.setString(3, itemid_new.replace("*", "%").toUpperCase());
			statement.setString(4, revid.replace("*", "%").toUpperCase());
			System.out.println("sql-->"+sql);			
			rs = statement.executeQuery();		

			while(rs.next()){
				String s1 = rs.getString("pitem_id");
				String strold = rs.getString("pitem_id_old");
				String s2 = rs.getString("puid");
				String s3 = rs.getString("pitem_revision_id");
				String s4 = rs.getString("pobject_name");
				String object_code = rs.getString("object_code");
				tempModelItemRevision model = new tempModelItemRevision();
				model.setItemid(s1);
				model.setItemid_new(s1);
				model.setItemid_old(strold);
				model.setPuid(s2);
				model.setRevid(s3);
				model.setName(s4);
				model.setObject_code(object_code);

				if(!isContainsItemRevByItemid(itemRevs,model)){
					itemRevs.add(model);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return itemRevs;
	}
	List<tempModelItemRevision> findItemRevByTypeNewLJT(String itemid_new,String revid,String name,String type){
		List<tempModelItemRevision> itemRevs = new ArrayList<tempModelItemRevision>();
		System.out.println("findItemRevByTypeNew---->");
		String sql = 
				"select Z.pitem_id,Z.pitem_id_old,Z.object_code,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (  "+
						"select A.puid,C.pitem_id,C.pitem_id_old,C.object_code,A.pobject_name,C.pitem_revision_id,A.pdate_released from (  "+
						"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where upper(pobject_name) like ? and pobject_type in ("+type+") and pactive_seq=1  "+ 
						")  A join (  "+
						"select B.puid,A.pitem_id,Y.pfw9_cp_lbj_03 pitem_id_old,Y.pfw9_cp_lbj_01 object_code,B.pitem_revision_id from PITEM A,PITEMREVISION B,PFW9_CP_LBJ Y  "+
						"where A.puid = Y.puid and upper(A.pitem_id) like ? and A.puid = Y.puid and upper(B.pitem_revision_id) like ? and A.puid = B.ritems_tagu   "+

						")  C on A.puid = C.puid  "+
						") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc  "
						;
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{

			if(itemid_new.equals("")){
				itemid_new="*";
			}

			if(revid.equals("")){
				revid="*";
			}

			if(name.equals("")){
				name="*";
			}
			System.out.println("sql-->"+sql);
			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, name.replace("*", "%").toUpperCase());
			statement.setString(2, itemid_new.replace("*", "%").toUpperCase());
			statement.setString(3, revid.replace("*", "%").toUpperCase());

			rs = statement.executeQuery();		

			while(rs.next()){
				String s1 = rs.getString("pitem_id");
				String strold = rs.getString("pitem_id_old");
				String s2 = rs.getString("puid");
				String s3 = rs.getString("pitem_revision_id");
				String s4 = rs.getString("pobject_name");
				String object_code =rs.getString("object_code");
				tempModelItemRevision model = new tempModelItemRevision();
				model.setItemid(s1);
				model.setItemid_new(s1);
				model.setItemid_old(strold);
				model.setPuid(s2);
				model.setRevid(s3);
				model.setName(s4);
				model.setObject_code(object_code);

				if(!isContainsItemRevByItemid(itemRevs,model)){
					itemRevs.add(model);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return itemRevs;
	}



	List<tempModelItemRevision> findItemRevByItemRevFolder(tempModelItemRevision tempItemRev,String folderRelation){
		List<tempModelItemRevision> itemRevs = new ArrayList<tempModelItemRevision>();

		String sql = "select A.puid,A.pobject_name,C.pitem_id,C.pitem_revision_id from (select B.puid,B.pobject_name  from (select A.RSECONDARY_OBJECTU from pimanrelation A,PIMANTYPE B "+
				"where A.rprimary_objectu = ? and A.rrelation_typeu = B.puid and B.ptype_name in ("+folderRelation+")) A "+
				" join pworkspaceobject B on A.RSECONDARY_OBJECTU = B.puid )  A join (  "+
				"select B.puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B  "+
				"where  A.puid = B.ritems_tagu   "+
				")  C on A.puid = C.puid";
		System.out.println("lost myself-->"+tempItemRev.getPuid()+"-->"+folderRelation+"-->"+sql);
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{


			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, tempItemRev.getPuid());
			//statement.setString(2, folderRelation);


			rs = statement.executeQuery();		

			while(rs.next()){
				String s1 = rs.getString("pitem_id");
				String s2 = rs.getString("puid");
				String s3 = rs.getString("pitem_revision_id");
				String s4 = rs.getString("pobject_name");
				tempModelItemRevision model = new tempModelItemRevision();
				model.setItemid(s1);
				model.setPuid(s2);
				model.setRevid(s3);
				model.setName(s4);

				if(!isContainsItemRevByItemid(itemRevs,model)){
					itemRevs.add(model);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return itemRevs;
	}

	boolean isContainsItemRevByItemid(List<tempModelItemRevision> itemRevs ,tempModelItemRevision ir){

		for(tempModelItemRevision itemRev:itemRevs){
			if(itemRev.getItemid().equals(ir.getItemid())){
				return true;
			}
		}

		return false;
	}


	@Override
	public String getDAPath() {

		// TODO Auto-generated method stub
		return this.doActionPath;
	}


	@Override
	public List<MaterialAttrs> getQDQNormalAttrs(String item_id,
			String item_rev_id) {
		String[] titles = {
			"图号",
			"零部件名称",
			"材料",
			"净重",
			"单位",
			"桥型",
			"客户编号",
			"客户名称",
			"分类",
			"规格",
			"阶段",
			"是否正式号",
			"备注",
			"发货状态"
		};
		String[] contents = {
				"pfw9drawnNo",
				"pfw9partName",
				"pfw9material",
				"pfw9weight",
				"pfw9unit",
				"pfw9axleType",
				"pfw9no",
				"pfw9customerName",
				"pfw9class",
				"pfw9specif",
				"pfw9phase",
				"pfw9officialID",
				"pfw9note",
				"pfw9fahuo"	
		};
		
		List<MaterialAttrs> attrList = new ArrayList<MaterialAttrs>();
		String sql = "select Y.pitem_id,Y.puid,Y.pobject_name,Y.pitem_revision_id,J.* from PFW9ITEMREVISION J  join (" +
				 "select Z.pitem_id,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (  "+
				 "	select A.puid,C.pitem_id,A.pobject_name,C.pitem_revision_id,A.pdate_released from (    "+
				 "		select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1    "+
				 "	)  A join (    "+
				 "		select B.puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B    "+
				 "			where upper(A.pitem_id) = ? and upper(B.pitem_revision_id) = ? and A.puid = B.ritems_tagu    "+
                 "	)  C on A.puid = C.puid    "+
                 "	) Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc) Y  on J.puid = Y.puid";
		
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{


			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, item_id);
			statement.setString(2, item_rev_id);


			rs = statement.executeQuery();		
			String stage = null;
			while(rs.next()){
	
				for (int i = 0; i < contents.length; i++) {
					MaterialAttrs attr = null;
					if("pfw9phase".equals(contents[i])) {
						
						stage = rs.getString(contents[i]);	
						//MaterialAttrs attr;
						if(stage != null) {
							String lovDes = getLovDes(stage);
							attr = new MaterialAttrs("阶段", lovDes);	
						} else {
							attr = new MaterialAttrs("阶段", "");	
						}
						//attrList.add(attr);
					} else
					 attr = new MaterialAttrs(titles[i], rs.getString(contents[i])==null?"":rs.getString(contents[i]));
					
					attrList.add(attr);
				}
				

				

			}
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return attrList;
	}


	@Override
	public List<MaterialAttrs> getGCQCPNormalAttrs(String item_id,
			String item_rev_id) {

		String[] titles = {
			"物料描述",
			"英文名称",
			"适用销售区域",
			"单重（Kg）",
			"对应总图图号",
			"是否出总图",
			"备注"
		};
		String[] contents = {
				"pfw9_CP_LBJRev_01",
				"pfw9_CP_LBJRev_03",
				"pfw9_CPRev_01",
				"pfw9_CP_LBJRev_05",
				"pfw9_CP_LBJRev_06",
				"pfw9_CPRev_03",
				"pfw9_CP_LBJRev_07"
		};
		
		List<MaterialAttrs> attrList = new ArrayList<MaterialAttrs>();
		String sql = "select A.pitem_id,A.puid,A.pobject_name,A.pitem_revision_id, "+
				"B.pfw9_CP_LBJRev_01,B.pfw9_CP_LBJRev_03,B.pfw9_CP_LBJRev_05,B.pfw9_CP_LBJRev_06,C.pfw9_CPRev_03,B.pfw9_CP_LBJRev_07  "+
				   "from(select Z.pitem_id,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (   "+
						"select A.puid,C.pitem_id,A.pobject_name,C.pitem_revision_id,A.pdate_released from (  "+ 
							"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1   "+
						")  A join (   "+
							"select B.puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B   "+
								"where upper(A.pitem_id) = ? and upper(B.pitem_revision_id) = ? and A.puid = B.ritems_tagu  "+ 

						")  C on A.puid = C.puid   "+
					") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc) A,PFW9_CP_LBJREVISION B,PFW9_CPREVISION C "+
					"where A.puid = B.puid "+
					"and B.puid = C.puid";
		
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{


			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, item_id);
			statement.setString(2, item_rev_id);


			rs = statement.executeQuery();		

			String puid = null;
			while(rs.next()){
				puid = rs.getString("puid");
				for (int i = 0; i < contents.length; i++) {
					MaterialAttrs attr = null;
					if("pfw9_CPRev_01".equals(contents[i])) {	 
						
					} else {
						attr = new MaterialAttrs(titles[i], rs.getString(contents[i])==null?"":rs.getString(contents[i]));
						
						attrList.add(attr);
					}
					 
				}
				
			}
			sql = "select PVAL_0 from PFW9_CPREV_01 where  puid = ?";
			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, puid);
			rs = statement.executeQuery();	
			List<String> tmpList= new ArrayList<String>(); 
			while(rs.next()){
				
				tmpList.add(rs.getString("PVAL_0"));
			}
			if(!tmpList.isEmpty()) {
				String lovDes = getLovDes(tmpList.toArray(new String[0]));
				attrList.add(new MaterialAttrs("适用销售区域", lovDes));
			} else {
				attrList.add(new MaterialAttrs("适用销售区域", ""));
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return attrList;
	
	}


	@Override
	public List<MaterialAttrs> getGCQPartNormalAttrs(String item_id,
			String item_rev_id) {

		String[] titles = {
			"物料描述",
			"英文名称",
			"材料",
			"零件类型",
			"首次生成所属系列",
			"单重（Kg）",
			"对应总图图号",
			"对应毛坯图号",
			"供应商",
			"备注"
			
		};
		String[] contents = {
				"pfw9_CP_LBJRev_01",
				"pfw9_CP_LBJRev_03",
				"pfw9_LBJRev_01",
				"pfw9_LBJRev_03",
				"pfw9_LBJRev_05",
				"pfw9_CP_LBJRev_05",
				"pfw9_CP_LBJRev_06",
				"pfw9_LBJRev_09",
				"producer",
				"pfw9_CP_LBJRev_07"
		};
		
		List<MaterialAttrs> attrList = new ArrayList<MaterialAttrs>();
		String sql = "select D.pitem_id,D.puid,D.pobject_name,D.pitem_revision_id, "+
			  "D.pfw9_CP_LBJRev_01,D.pfw9_CP_LBJRev_03,D.pfw9_LBJRev_01,D.pfw9_LBJRev_03,D.pfw9_LBJRev_05,D.pfw9_CP_LBJRev_05,D.pfw9_CP_LBJRev_06,D.pfw9_LBJRev_09,D.pfw9_CP_LBJRev_07,E.PVAL_0 producer "+
				"from (select A.pitem_id,A.puid,A.pobject_name,A.pitem_revision_id, "+
				"B.pfw9_CP_LBJRev_01,B.pfw9_CP_LBJRev_03,C.pfw9_LBJRev_01,C.pfw9_LBJRev_03,C.pfw9_LBJRev_05,B.pfw9_CP_LBJRev_05,B.pfw9_CP_LBJRev_06,C.pfw9_LBJRev_09,B.pfw9_CP_LBJRev_07  "+
				   "from(select Z.pitem_id,Z.puid,Z.pobject_name,Z.pitem_revision_id,Z.pdate_released from (  "+
						"select A.puid,C.pitem_id,A.pobject_name,C.pitem_revision_id,A.pdate_released from (   "+
							"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1   "+
						")  A join (  "+
							"select B.puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B  "+
								"where upper(A.pitem_id) = ? and upper(B.pitem_revision_id) = ? and A.puid = B.ritems_tagu   "+
						")  C on A.puid = C.puid   "+
					") Z where pdate_released is not null order by pitem_id desc,pitem_revision_id desc,pdate_released desc) A,PFW9_CP_LBJREVISION B,PFW9_LBJREVISION C "+
					"where A.puid = B.puid "+
					"and B.puid = C.puid) D left join ( select puid ,listagg( PVAL_0, ',' ) within group ( order by puid ) as PVAL_0 "+
						" from pfw9_LBJRev_11 Y "+			 
						" GROUP BY puid) E on D.puid = E.puid"; 
		
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{


			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, item_id);
			statement.setString(2, item_rev_id);


			rs = statement.executeQuery();		

			while(rs.next()){
	
				for (int i = 0; i < contents.length; i++) {	
					 MaterialAttrs attr = null;
					 if("pfw9_LBJRev_03".equals(contents[i])) {
						 String string = rs.getString(contents[i]);
						 if(string != null && !string.isEmpty()) {
							 string = getLovDes(string);
						 }
						 attr = new MaterialAttrs(titles[i], string ==null?"":string);					 
					 } else {
						 attr = new MaterialAttrs(titles[i], rs.getString(contents[i])==null?"":rs.getString(contents[i]));					 
					 }
					 
					attrList.add(attr);
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return attrList;
	
	}


	@Override
	public List<MaterialAttrs> getGCQPartClassAttrs(String item_id,
			String item_rev_id) {



		List<MaterialAttrs> attrList = new ArrayList<MaterialAttrs>();
		String sql = "	select M.pname,L.* from( "+
	   "select K.punct,K.pdbindex,J.* from ( "+
	   " select I.puid ppuid,H.* from PSMLB0 I join ( "+
	     "  select distinct(F.RSECONDARY_OBJECTU), F.pitem_id,F.pitem_revision_id,G.* from ( "+
                    "select C.puid,C.pitem_id,C.pitem_revision_id,D.RSECONDARY_OBJECTU from	( "+
					       "select B.puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B "+
								"where upper(A.pitem_id) = ? and upper(B.pitem_revision_id) = ? and A.puid = B.ritems_tagu  )	C,PIMANRELATION D,PIMANTYPE E "+
								  "where C.puid = D.RPRIMARY_OBJECTU and D.rrelation_typeu = E.puid and E.ptype_name = 'IMAN_classification') F join PICM0 G  "+
								     "on F.RSECONDARY_OBJECTU = G.puid ) H on  I.PCID = H.PCID) J "+
	               " join PSMLB1 K on J.ppuid = K.RTAG_TO_HEADERU) L join punct_dict M on L.punct = M.punct";
		
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{


			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, item_id);
			statement.setString(2, item_rev_id);


			rs = statement.executeQuery();		

			while(rs.next()){
				String title = rs.getString("pname");
				int index = rs.getInt("pdbindex");
				String format = String.format("psm%02d", index);
				String content = rs.getString(format);
				MaterialAttrs attr = new MaterialAttrs(title, content);
				attrList.add(attr);
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}


		return attrList;
	
	
	}
	String getLovDes(String... trueValues) {
		StringBuffer buffer = new StringBuffer();
		StringBuffer buffer1 = new StringBuffer();
		String prefix = "en_US:M:A:0000:                                :";
		for (int i = 0; i < trueValues.length; i++) {
			buffer.append("'");
			buffer.append(prefix);
			buffer.append(trueValues[i]);
			buffer.append("'");
			if(i != trueValues.length-1) 
				buffer.append(",");
		}
		
		String sql = "SELECT DISTINCT(PVAL_0) from PL10N_FND0ENTRY WHERE puid in ( "
				+ "SELECT distinct(puid) from PL10N_FND0ENTRY where   PVAL_0 in ("+buffer.toString()+")) and PVAL_0 like 'zh_CN%'";
		
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{
			statement = con.getConn().prepareStatement(sql);
			//statement.setString(1, buffer.toString());
			rs = statement.executeQuery();		
			
			while(rs.next()){
				String s1 = rs.getString("PVAL_0");
				String[] split = s1.split(":");
				buffer1.append(split[split.length-1]);
				buffer1.append(",");
			}
			if(buffer1.length()>0)
			return buffer1.substring(0, buffer1.length()-1);

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		return "";
	}


	@Override
	public List<MaterialTreeModel> getParentMaterial(String item_id,
			String item_rev_id) {
		// TODO Auto-generated method stub
		String sql = "select E.PITEM_ID,F.pobject_name,G.pitem_revision_id from PITEM E,PWORKSPACEOBJECT F,PITEMREVISION G "+
       "where E.puid in "+
		"(select D.puid from pbom_view_tags D where D.pvalu_0 in ( select C.rbom_viewu from ppsbomviewrevision C where C.puid in ( "+
				  "select B.rparent_bvru from  PPSOCCURRENCE B  "+
					"where rchild_itemu	= (select puid from PITEM A where upper(A.PITEM_ID) = ?) ) "+		 
		" )) and G.puid = F.puid and E.puid = G.ritems_tagu and G.pitem_revision_id = ( "+
		"select max(pitem_revision_id) from pitemrevision A1,PWORKSPACEOBJECT A2  "+
		"where ritems_tagu = E.puid and  A1.puid = A2.puid and A2.pactive_seq=1 and A2.pdate_released is not null)";
		
		List<MaterialTreeModel> list = new ArrayList<MaterialTreeModel>();
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{
			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, item_id);
			rs = statement.executeQuery();		
			
			while(rs.next()){
				String pitem_id = rs.getString("PITEM_ID");
				String pitem_name = rs.getString("pobject_name");
				String pitem_rev_id = rs.getString("pitem_revision_id");
				MaterialTreeModel model = new MaterialTreeModel();
				model.setId(pitem_id);
				model.setItem_id(pitem_id);
				model.setItem_rev_id(pitem_rev_id);
				model.setText(pitem_id+"/"+pitem_rev_id+";"+pitem_name);
				model.setIconCls("item_rev_icon");
				
				PreparedStatement statement2 = con.getConn().prepareStatement(sql);
				statement2.setString(1, pitem_id);
				ResultSet rs2 = statement2.executeQuery();	
				
				if(rs2.next())
				model.setLeaf(false);
				else
				model.setLeaf(true);
				
				if(rs2 != null) {
					rs2.close();
				}
				if(statement2 != null) {
					statement2.close();
				}
				list.add(model);
				
			}
			
			
			if(rs != null) {
				rs.close();
			}
			if(statement != null) {
				statement.close();
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		
		return list;
	}


	@Override
	public GCQBOMModel getGCQBOMTitle(String item_id, String item_rev_id) {

		// TODO Auto-generated method stub
		/*String sql = "select A.pitem_id,B.pitem_revision_id,C.pdate_released from PITEM A,PITEMREVISION B,PWORKSPACEOBJECT C  "+
										"where upper(A.pitem_id) = ?   "+
										"and A.puid = B.ritems_tagu    "+
										"and B.puid = C.puid   "+
										"and C.pdate_released is not null   "+
										"and B.pitem_revision_id = (SELECT max(pitem_revision_id) from PITEMREVISION WHERE ritems_tagu = A.puid)  "+
										"order by B.pitem_revision_id desc";*/
		String sql ="select   L.pitem_id,L.pobject_name,L.pitem_revision_id,L.parent_unit, "+
			"M.pfw9_CP_LBJ_03,M.pfw9_CP_LBJ_05 from "+
			"(select A.puid item_puid,B.puid item_rev_puid,C.PSYMBOL parent_unit,A.pitem_id,B.pitem_revision_id,D.POBJECT_NAME from PITEM A,PITEMREVISION B,PUNITOFMEASURE C,PWORKSPACEOBJECT D   "+ 
								"where upper(A.pitem_id) = ? and upper(B.pitem_revision_id) = (SELECT max(B.pitem_revision_id) from PITEMREVISION B,PWORKSPACEOBJECT C WHERE B.ritems_tagu = A.puid and B.puid = C.puid and C.pdate_released is not null) "+
								"and A.puid = B.ritems_tagu  and A.RUOM_TAGU = C.puid and B.puid = D.puid ) L,PFW9_CP_LBJ M "+
							"where L.item_puid = M.puid ";	
		
		List<MaterialTreeModel> list = new ArrayList<MaterialTreeModel>();
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{
			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, item_id);
			rs = statement.executeQuery();					
			while(rs.next()){
				
				String pitem_rev_id = rs.getString("pitem_revision_id");
				if(item_rev_id.equalsIgnoreCase(pitem_rev_id)) {
					List<GCQBOMModel> gcqbom = getGCQBOM(item_id, pitem_rev_id);
					if(gcqbom == null || gcqbom.isEmpty()) {
						return new GCQBOMModel();
					} else {
						//return getGCQBOMTitle(item_id, pitem_rev_id);
						String pitem_id = rs.getString("pitem_id");
						String pitem_name = rs.getString("pobject_name");
						String unit = rs.getString("parent_unit")==null?"":rs.getString("parent_unit");
						String item_id_old = rs.getString("pfw9_CP_LBJ_03")==null?"":rs.getString("pfw9_CP_LBJ_03");
						String client_object_code = rs.getString("pfw9_CP_LBJ_05")==null?"":rs.getString("pfw9_CP_LBJ_05");
						String bomline = item_id+"/"+item_rev_id+";-"+pitem_name+" (视图)";
					/*	GCQBOMModel model = new GCQBOMModel();
						model.setItem_id(pitem_id);
						model.setItem_rev_id(pitem_rev_id);
						model.setItem_name(pitem_name);
						model.setUnit(unit);
						model.setItem_id_old(item_id_old);*/
						GCQBOMModel model = new GCQBOMModel(item_id, item_rev_id, item_id_old, client_object_code, pitem_name, "", unit, "", "", "", "", "",true,bomline);
						return model;
					}
				} else {
					return null;
				}
				
				
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		
		return null;
	
	}


	@Override
	public QDQBOMModel getQDQBOMTitle(String item_id, String item_rev_id) {


		// TODO Auto-generated method stub
		String sql = "  select L.item_puid,L.pitem_id,L.pobject_name,L.pitem_revision_id, "+
      "M.pfw9drawnNo, M.pfw9partName, M.pfw9axleType, M.pfw9material, M.pfw9specif, M.pfw9weight, M.pfw9unit, M.pfw9class, M.pfw9phase, M.pfw9customerName, M.pfw9no, M.pfw9note from(  "+ 
							"select A.puid item_puid,B.puid,A.pitem_id,B.pitem_revision_id,B.puid item_rev_puid,C.POBJECT_NAME  from PITEM A,PITEMREVISION B,PWORKSPACEOBJECT C   "+
								"where upper(A.pitem_id) = ?  "+
								"and upper(B.pitem_revision_id) = (SELECT max(B.pitem_revision_id) from PITEMREVISION B,PWORKSPACEOBJECT C WHERE B.ritems_tagu = A.puid and B.puid = C.puid and C.pdate_released is not null) and A.puid = B.ritems_tagu and B.puid = C.puid  and C.pactive_seq=1  "+ 
						") L  join ( select puid,pfw9drawnNo,pfw9partName,pfw9axleType,pfw9material,pfw9specif,pfw9weight,pfw9unit,pfw9class,pfw9phase,pfw9customerName,pfw9no,pfw9note from pFw9ItemRevision ) M "+
									 "on L.item_rev_puid = M.puid";
		
		List<MaterialTreeModel> list = new ArrayList<MaterialTreeModel>();
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{
			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, item_id);
			rs = statement.executeQuery();		
			
			while(rs.next()){
				
				String pitem_rev_id = rs.getString("pitem_revision_id");
				if(item_rev_id.equalsIgnoreCase(pitem_rev_id)) {
					List<QDQBOMModel> gcqbom = getQDQBOM(item_id, pitem_rev_id);
					if(gcqbom == null || gcqbom.isEmpty()) {
						return new QDQBOMModel();
					} else {
						String pitem_id = rs.getString("pfw9drawnNo");
						//String pitem_name = rs.getString("pobject_name");
						//String pitem_i = rs.getString("pitem_revision_id");
						//String pitem_name = rs.getString("pfw9drawnNo");
						String pitem_name = rs.getString("pfw9partName")==null?"":rs.getString("pfw9partName");
						String axle_type = rs.getString("pfw9axleType")==null?"":rs.getString("pfw9axleType");
						String material = rs.getString("pfw9material")==null?"":rs.getString("pfw9material");
						String specif = rs.getString("pfw9specif")==null?"":rs.getString("pfw9specif");
						String weight = rs.getString("pfw9weight")==null?"":rs.getString("pfw9weight");
						String unit = rs.getString("pfw9unit")==null?"":rs.getString("pfw9unit");
						String class_type = rs.getString("pfw9class")==null?"":rs.getString("pfw9class");
						String phase = rs.getString("pfw9phase")==null?"":rs.getString("pfw9phase");
						if(phase != null && !phase.isEmpty()) {
							phase = getLovDes(phase);
						}
						String client_name = rs.getString("pfw9customerName")==null?"":rs.getString("pfw9customerName");
						String client_no = rs.getString("pfw9no")==null?"":rs.getString("pfw9no");
						String remark = rs.getString("pfw9note")==null?"":rs.getString("pfw9note");
						QDQBOMModel model = new QDQBOMModel(pitem_id, pitem_rev_id, axle_type, specif, pitem_name, material, unit, "", weight, class_type, phase, client_name, client_no, remark,true);
						return model;
					}
				} else {
					return null;
				}
				
				
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		
		return null;
	
	
	}


	@Override
	public List<GCQBOMModel> getGCQBOM(String item_id, String item_rev_id) {
		// TODO Auto-generated method stub
		String sql = "select   L.rchild_itemu,L.pitem_id,L.pobject_name,L.pitem_revision_id,L.parent_unit,L.child_item_id,L.child_item_name,L.child_item_rev_type,L.child_item_revision_id, "+
			"M.pfw9_CP_LBJ_03,M.pfw9_CP_LBJ_05,N.pfw9_LBJRev_01,L.child_unit,O.pfw9_CP_LBJRev_05,L.PQTY_VALUE,L.pseq_no,N.pfw9_LBJRev_03,O.pfw9_CP_LBJRev_07,O.pfw9_CP_LBJRev_05*PQTY_VALUE total_weight,to_number((case when L.pseq_no is null then '0' else L.pseq_no end)) seq from "+
			"(select  distinct J.rchild_itemu,J.parent_unit,J.pitem_id,J.pobject_name,J.pitem_revision_id,K.child_item_id,K.child_item_name,K.child_item_rev_type,K.child_item_revision_id,J.PQTY_VALUE,J.pseq_no,K.item_rev_puid,K.child_unit from (  "+
			 "select distinct I.rchild_itemu,H.item_puid,H.parent_unit,H.pitem_id,H.pobject_name,H.pitem_revision_id,H.puid,I.PQTY_VALUE,I.pseq_no from( "+
				"select distinct F.item_puid,F.item_rev_puid,F.parent_unit,F.pvalu_0,F.pitem_id,F.pobject_name,F.pitem_revision_id,G.puid,rownum from (	 "+			  
				 " select DISTINCT D.item_puid,D.item_rev_puid,D.parent_unit,E.pvalu_0,D.pitem_id,D.pobject_name,D.pitem_revision_id from (   "+
						"select C.item_puid,C.item_rev_puid,C.parent_unit,A.puid,C.pitem_id,A.pobject_name,C.pitem_revision_id from (   "+
							"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1   "+
						")  A join (   "+
							"select A.puid item_puid,C.PSYMBOL parent_unit,B.puid item_rev_puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B,PUNITOFMEASURE C    "+
								"where upper(A.pitem_id) = ? and upper(B.pitem_revision_id) = ? and A.puid = B.ritems_tagu  and A.RUOM_TAGU = C.puid "+
						")  C on A.puid = C.item_rev_puid   "+
					" ) D  join ( "+
					 "select puid,pvalu_0 from  pbom_view_tags ) E  "+
					" on D.item_puid = E.puid ) F  "+
					" join  (	 "+				 
					 "select A.puid,A.rbom_viewu,A.PSTRUCT_LAST_MOD_DATE,D.pobject_name,F.puid rpuid  from ppsbomviewrevision A,ppsbomview B,PPSVIEWTYPE C,PWORKSPACEOBJECT D,PSTRUCTURE_REVISIONS F  where  A.puid = D.puid and D.pactive_seq =1 and B.puid = A.rbom_viewu and C.PUID = B.RVIEW_TYPEU  and C.PNAME ='view' and F.PVALU_0 = D.puid "+		 
					 ") G on G.rpuid = F.item_rev_puid ) H "+
					" join ( select rparent_bvru,rchild_itemu,PQTY_VALUE,pseq_no from PPSOCCURRENCE) I  "+
					" on H.puid = I.rparent_bvru) J  join (  "+
					 "select C.item_puid,C.child_unit,A.puid,C.pitem_id child_item_id,A.pobject_name child_item_name,A.pobject_type child_item_rev_type,C.pitem_revision_id child_item_revision_id,C.item_rev_puid from (   "+
					"	 select puid,pobject_name,pobject_type,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1   "+
						"	)  A join (   "+
							"	select AA.puid item_puid,C.PSYMBOL child_unit,B.puid item_rev_puid,AA.pitem_id,B.pitem_revision_id from PITEM AA,PITEMREVISION B,PUNITOFMEASURE C  "+    
									"where  AA.puid = B.ritems_tagu  and AA.RUOM_TAGU = C.puid "+
							")  C on A.puid = C.item_rev_puid    ) K on K.item_puid = J.rchild_itemu  and K.child_item_revision_id = ( "+
							                                                          "select max(pitem_revision_id) from pitemrevision A1,PWORKSPACEOBJECT A2  "+
																					   " where A1.ritems_tagu = K.item_puid and  A1.puid = A2.puid and A2.pactive_seq=1   ) "+
							") L,PFW9_CP_LBJ M,PFW9_LBJREVISION N,PFW9_CP_LBJREVISION O "+
							"where L.rchild_itemu = M.puid and L.item_rev_puid = N.puid and N.puid = O.puid order by seq asc,L.child_item_id asc,L.child_item_revision_id";
					//to_number(regexp_substr(L.pseq_no,'[0-9]*[0-9]',1))
					List<GCQBOMModel> list = new ArrayList<GCQBOMModel>();
					PreparedStatement statement=null;
					ResultSet rs = null;

					try{
						statement = con.getConn().prepareStatement(sql);
						statement.setString(1, item_id);
						statement.setString(2, item_rev_id);
						//statement.setString(3, "%/"+item_rev_id+"%");
						rs = statement.executeQuery();		
						
						while(rs.next()){
							String pitem_id = rs.getString("child_item_id");
							String pitem_name = rs.getString("child_item_name");
							String child_item_rev_type = rs.getString("child_item_rev_type");
							String pitem_rev_id = rs.getString("child_item_revision_id");
							String quantity = rs.getString("PQTY_VALUE");
							String item_id_old = rs.getString("pfw9_CP_LBJ_03");
							String client_object_code = rs.getString("pfw9_CP_LBJ_05");
							String material = rs.getString("pfw9_LBJRev_01");
							String unit = rs.getString("child_unit");
							String single_weight = rs.getString("pfw9_CP_LBJRev_05");
							String part_type = rs.getString("pfw9_LBJRev_03");
							String seq = rs.getString("seq");
							//System.out.println(pitem_id+"-->"+seq);
							if(part_type!=null && !part_type.isEmpty()) {
								part_type = getLovDes(part_type);
							}
							String remark = rs.getString("pfw9_CP_LBJRev_07");
							String total_weight =  rs.getString("total_weight");	
							
							//String pactive_seq = rs.getString("pactive_seq");
							//System.out.println(pitem_id+":pactive_seq:"+pactive_seq);
							
							GCQBOMModel model = new GCQBOMModel();
							
							model.setItem_id(pitem_id);
							model.setItem_rev_id(pitem_rev_id);
							model.setItem_name(pitem_name);
							model.setQuantity(quantity);
							model.setItem_id_old(item_id_old);
							model.setClient_object_code(client_object_code);
							model.setMaterial(material);
							model.setUnit(unit);
							model.setSingle_weight(single_weight);
							model.setPart_type(part_type);
							model.setRemark(remark);
							model.setTotal_weight(total_weight);
							
							String bomline ;
							
							/*if(pitem_id.startsWith("BM"))
							model.setIconCls("product_icon");
							else
							model.setIconCls("item_rev_icon");*/
							
							PreparedStatement statement2 = con.getConn().prepareStatement(sql);
							statement2.setString(1, pitem_id);
							statement2.setString(2, pitem_rev_id);
							//statement2.setString(3, "%/"+pitem_rev_id+"%");
							ResultSet rs2 = statement2.executeQuery();	
							if(rs2.next()) {
								model.setLeaf(false);
								bomline = pitem_id+"/"+pitem_rev_id+";"+pitem_name+" (视图) x "+quantity;
							} else {
								model.setLeaf(true);
								bomline = pitem_id+"/"+pitem_rev_id+";"+pitem_name+" x "+quantity;
							}
							
							model.setBomline(bomline);
							
							if(rs2 != null) {
								rs2.close();
							}
							if(statement2 != null) {
								statement2.close();
							}
							
							
							list.add(model);
							
						}
						if(rs != null) {
							rs.close();
						}
						if(statement != null) {
							statement.close();
						}
						

					}catch(Exception e){
						e.printStackTrace();
					}finally{
						con.closeConn(rs, statement, null);
					}
					
					return list;
	}


	@Override
	public List<QDQBOMModel> getQDQBOM(String item_id, String item_rev_id) {

		// TODO Auto-generated method stub
		
		
		String sql = "  select L.rchild_itemu,L.pitem_id,L.pobject_name,L.pitem_revision_id,L.child_item_id,L.child_item_name,L.child_item_revision_id,L.num,to_number((case when L.pseq_no is null then '0' else L.pseq_no end)) seqme, "+
			     " M.pfw9drawnNo, M.pfw9partName, M.pfw9axleType, M.pfw9material, M.pfw9specif, M.pfw9weight, M.pfw9unit, M.pfw9class, M.pfw9phase, M.pfw9customerName, M.pfw9no, M.pfw9note from "+
			    "(select distinct J.rchild_itemu,J.pitem_id,J.pobject_name,J.pitem_revision_id,K.child_item_id,K.child_item_name,K.child_item_revision_id,J.num,J.pseq_no,K.item_rev_puid from (  "+
						" select distinct I.rchild_itemu,H.item_puid,H.pitem_id,H.pobject_name,H.pitem_revision_id,H.puid,I.num,I.pseq_no from( "+
							"select distinct F.item_puid,F.item_rev_puid,F.pvalu_0,F.pitem_id,F.pobject_name,F.pitem_revision_id,G.puid from ( "+				  
							 " select DISTINCT D.item_puid,D.item_rev_puid,E.pvalu_0,D.pitem_id,D.pobject_name,D.pitem_revision_id from (  "+ 
									"select C.item_puid,C.item_rev_puid,A.puid,C.pitem_id,A.pobject_name,C.pitem_revision_id from (  "+ 
										"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1  "+ 
									")  A join (   "+
										"select A.puid item_puid,B.puid item_rev_puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B "+  
										"	where upper(A.pitem_id) = ? and upper(B.pitem_revision_id) = ? and A.puid = B.ritems_tagu   "+
									")  C on A.puid = C.item_rev_puid "+  
								" ) D  join ( "+
								" select puid,pvalu_0 from  pbom_view_tags ) E "+ 
								" on D.item_puid = E.puid ) F  "+
								" join  ( "+					 
								" select A.puid,A.rbom_viewu,D.pobject_name,F.puid rpuid from ppsbomviewrevision A,ppsbomview B,PPSVIEWTYPE C,PWORKSPACEOBJECT D,PSTRUCTURE_REVISIONS F  where  A.puid = D.puid and D.pactive_seq =1 and B.puid = A.rbom_viewu and C.PUID = B.RVIEW_TYPEU  and C.PNAME ='view' and F.PVALU_0 = D.puid  "+					 
								 ") G on  G.rpuid = F.item_rev_puid ) H "+
								 "join ( select rchild_itemu,rparent_bvru,count(rchild_itemu) num,pseq_no from PPSOCCURRENCE GROUP BY rchild_itemu,rparent_bvru,pseq_no) I  "+
								" on H.puid = I.rparent_bvru) J  join (  "+
								" select C.item_puid,A.puid,C.pitem_id child_item_id,A.pobject_name child_item_name,C.pitem_revision_id child_item_revision_id,C.item_rev_puid from (   "+
									" select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1   "+
										")  A join (   "+
										"	select AA.puid item_puid,B.puid item_rev_puid,AA.pitem_id,B.pitem_revision_id from PITEM AA,PITEMREVISION B   "+
												"where  AA.puid = B.ritems_tagu   "+
										")  C on A.puid = C.item_rev_puid    ) K on K.item_puid = J.rchild_itemu  and K.child_item_revision_id = ( "+
										                                                          "select max(pitem_revision_id) from pitemrevision A1,PWORKSPACEOBJECT A2  "+
																								   " where ritems_tagu = K.item_puid and  A1.puid = A2.puid and A2.pactive_seq=1  ) "+
										"order by K.child_item_id asc,K.child_item_revision_id asc) L  join (  "+
										                                     "select puid,pfw9drawnNo,pfw9partName,pfw9axleType,pfw9material,pfw9specif,pfw9weight,pfw9unit,pfw9class,pfw9phase,pfw9customerName,pfw9no,pfw9note from pFw9ItemRevision ) M "+
																			 " on L.item_rev_puid = M.puid order by seqme  asc,L.child_item_id asc,L.child_item_revision_id";
					
					List<QDQBOMModel> list = new ArrayList<QDQBOMModel>();
					PreparedStatement statement=null;
					ResultSet rs = null;

					try{
						statement = con.getConn().prepareStatement(sql);
						statement.setString(1, item_id);
						statement.setString(2, item_rev_id);
						//statement.setString(3, "%/"+item_rev_id+"%");
						rs = statement.executeQuery();		
						
		
						while(rs.next()){
							String pitem_id = rs.getString("pfw9drawnNo");
							String pitem_name = rs.getString("pfw9partName");
							String pitem_rev_id = rs.getString("child_item_revision_id");
							String quantity = rs.getString("num");
							String axle_type = rs.getString("pfw9axleType");
							String material = rs.getString("pfw9material");
							String specif = rs.getString("pfw9specif");
							String weight = rs.getString("pfw9weight");
							String unit = rs.getString("pfw9unit");
							String class_type = rs.getString("pfw9class");
							String phase = rs.getString("pfw9phase");
							String client_name = rs.getString("pfw9customerName");
							String client_no = rs.getString("pfw9no");
							String remark = rs.getString("pfw9note");
							if(phase!=null && !phase.isEmpty()) {
								phase = getLovDes(phase);
							}
							
							QDQBOMModel model = new QDQBOMModel();
							model.setItem_id(pitem_id);
							model.setItem_rev_id(pitem_rev_id);
							model.setItem_name(pitem_name);
							model.setQuantity(quantity);
							model.setAxle_type(axle_type);
							model.setMaterial(material);
							model.setSpecif(specif);
							model.setWeight(weight);
							model.setUnit(unit);
							model.setClass_type(class_type);
							model.setPhase(phase);
							model.setClass_type(class_type);
							model.setClient_name(client_name);
							model.setClient_no(client_no);
							model.setRemark(remark);
							
							/*if(isFindChildren) {
								List<QDQBOMModel> list2 = getQDQBOM(pitem_id,pitem_rev_id,false);
								
								if(list2.size() > 0) {
									model.setLeaf(true);
								} else {
									model.setLeaf(false);
								}
							}*/
							PreparedStatement statement2 = con.getConn().prepareStatement(sql);
							statement2.setString(1, pitem_id);
							statement2.setString(2, pitem_rev_id);
							//statement2.setString(3, "%/"+pitem_rev_id+"%");
							ResultSet rs2 = statement2.executeQuery();	
							if(rs2.next()) {
								model.setLeaf(false);
							} else {
								model.setLeaf(true);
							}
							
							if(rs2 != null) {
								rs2.close();
							}
							if(statement2 != null) {
								statement2.close();
							}
							
							list.add(model);
							
						}
						if(rs != null) {
							rs.close();
						}
						if(statement != null) {
							statement.close();
						}
						

					}catch(Exception e){
						e.printStackTrace();
					}finally{
						con.closeConn(rs, statement, null);
					}
					
					return list;
	
	}


	@Override
	public String getLastItemRev(String item_id) {



		// TODO Auto-generated method stub
		String sql = "  select A.puid,C.item_puid,C.ITEM_REV_PUID,C.pitem_id,C.pitem_revision_id,A.pobject_name from (  "+
							"select puid,pobject_name,pdate_released from PWORKSPACEOBJECT where  pactive_seq=1   "+
						")  A join ( "+ 
							"select A.puid item_puid,B.puid item_rev_puid,A.pitem_id,B.pitem_revision_id from PITEM A,PITEMREVISION B,PUNITOFMEASURE C  "+  
								"where upper(A.pitem_id) = ? and  A.puid = B.ritems_tagu  order by pitem_revision_id desc "+
						")  C on A.puid = C.item_rev_puid  and A.pdate_released is not null and ROWNUM < =1";
		
	
		PreparedStatement statement=null;
		ResultSet rs = null;

		try{
			statement = con.getConn().prepareStatement(sql);
			statement.setString(1, item_id);
			rs = statement.executeQuery();		
			
			while(rs.next()){
				String pitem_rev_id = rs.getString("pitem_revision_id");
				return pitem_rev_id;
			}
			

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			con.closeConn(rs, statement, null);
		}
		
		return null;
	
	
	
	}

}

class tempModelItem{
	String puid;
	String itemid;
	String name;

	List<tempModelItem> items;
	List<tempModelItemRevision> itemRevs;
	List<tempModelDS> dss;
	public String getPuid() {
		return puid;
	}
	public void setPuid(String puid) {
		this.puid = puid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<tempModelItem> getItems() {
		return items;
	}
	public void setItems(List<tempModelItem> items) {
		this.items = items;
	}
	public List<tempModelItemRevision> getItemRevs() {
		return itemRevs;
	}
	public void setItemRevs(List<tempModelItemRevision> itemRevs) {
		this.itemRevs = itemRevs;
	}
	public List<tempModelDS> getDss() {
		return dss;
	}
	public void setDss(List<tempModelDS> dss) {
		this.dss = dss;
	}
}

class tempModelDS{

	String dsname;
	String dstype;
	String filepath;

	public String getDsname() {
		return dsname;
	}
	public void setDsname(String dsname) {
		this.dsname = dsname;
	}
	public String getDstype() {
		return dstype;
	}
	public void setDstype(String dstype) {
		this.dstype = dstype;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}


}


class tempModelItemRevision{
	String puid;
	String itemid;
	String itemid_new;
	String itemid_old;
	String revid;
	String name;
	String remark;
	String object_code;

	tempModelItem referenceItem;
	String childItemId;
	String childItemRev;



	public String getObject_code() {
		return object_code;
	}
	public void setObject_code(String object_code) {
		this.object_code = object_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getChildItemId() {
		return childItemId;
	}
	public void setChildItemId(String childItemId) {
		this.childItemId = childItemId;
	}
	public String getChildItemRev() {
		return childItemRev;
	}
	public void setChildItemRev(String childItemRev) {
		this.childItemRev = childItemRev;
	}
	public String getItemid_new() {
		return itemid_new;
	}
	public void setItemid_new(String itemid_new) {
		this.itemid_new = itemid_new;
	}
	public String getItemid_old() {
		return itemid_old;
	}
	public void setItemid_old(String itemid_old) {
		this.itemid_old = itemid_old;
	}
	public tempModelItem getReferenceItem() {
		return referenceItem;
	}
	public void setReferenceItem(tempModelItem referenceItem) {
		this.referenceItem = referenceItem;
	}
	public String getPuid() {
		return puid;
	}
	public void setPuid(String puid) {
		this.puid = puid;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getRevid() {
		return revid;
	}
	public void setRevid(String revid) {
		this.revid = revid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<tempModelItem> getItems() {
		return items;
	}
	public void setItems(List<tempModelItem> items) {
		this.items = items;
	}
	public List<tempModelItemRevision> getItemRevs() {
		return itemRevs;
	}
	public void setItemRevs(List<tempModelItemRevision> itemRevs) {
		this.itemRevs = itemRevs;
	}
	public List<tempModelDS> getDss() {
		return dss;
	}
	public void setDss(List<tempModelDS> dss) {
		this.dss = dss;
	}
	List<tempModelItem> items;
	List<tempModelItemRevision> itemRevs;
	List<tempModelDS> dss;
}


