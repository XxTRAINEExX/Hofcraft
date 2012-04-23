package net.yeticraft.xxtraineexx.hofcraft;

public class HofPlayer {

	String pClass = "undecided";
	short pInt = 100;
	short pRes = 100;
	
	public String getpClass(){
		return pClass;
	}
	
	public short getInt(){
		return pInt;
	}
	
	public short getRes(){
		return pRes;
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
