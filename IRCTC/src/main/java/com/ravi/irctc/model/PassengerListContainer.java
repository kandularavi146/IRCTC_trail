package com.ravi.irctc.model;

import java.util.LinkedList;

public class PassengerListContainer {

	private LinkedList<Passenger> linkedList=new LinkedList<Passenger>();

	public PassengerListContainer(LinkedList<Passenger> linkedList) {
		super();
		this.linkedList = linkedList;
	}

	public PassengerListContainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LinkedList<Passenger> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(LinkedList<Passenger> linkedList) {
		this.linkedList = linkedList;
	}
	
}
