package com.shop.statesManagement;

public class State{
	private int stateCode;
	private String stateName;
	
	public State(int stateCode, String stateName) {
		super();
		this.stateCode = stateCode;
		this.stateName = stateName;
	}

	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return stateName;
	}
}
