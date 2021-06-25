package it.polito.tdp.PremierLeague.model;

import javax.naming.spi.DirStateFactory.Result;

import org.mariadb.jdbc.MariaDbDatabaseMetaData;

public class TestModel {

	public static void main(String[] args) {
		
		Model md = new Model ();
		md.creaGrafo(1);
		System.out.println(md.getBestPlayer());
		md.sim(10, 1);
	}

}
