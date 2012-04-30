package net.yeticraft.xxtraineexx.hofcraft;

public class HofPlayer {

	String pName;
	String pClass;
	int pInt;
	int pRes;
	
	public HofPlayer (String incPlayer, String incClass, int incInt, int incRes){
		pName = incPlayer;
		pClass = incClass;
		pInt = incInt;
		pRes = incRes;
		
	}
	
	public String getpName(){
		return pName;
	}
	
	public String getpClass(){
		return pClass;
	}
	
	public int getInt(){
		return pInt;
	}
	
	public int getRes(){
		return pRes;
	}
	
	public boolean setpName(String incName){
		pName = incName.toLowerCase();
		return true;
	}
	
	public boolean setpClass(String incClass){
		pClass = incClass.toLowerCase();
		return true;
	}
	
	public boolean setInt(short incInt){
		pInt = incInt;
		return true;
	}
	
	public boolean setRes(short incRes){
		pRes = incRes;
		return true;
	}

	
	
	
}
