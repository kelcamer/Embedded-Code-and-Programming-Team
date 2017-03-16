/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  HashExperiments
 * Date: February 3, 2016
 */

import java.util.ArrayList;

public class SeparateChainingHashTable {
	// defines variables 
	int m_nTableSize = 10000;
	DataObjectList[] m_ObjectArray;
	int elements = 0;

	public SeparateChainingHashTable() {
		// creates an array of object lists (ArrayLists)
		m_ObjectArray = new DataObjectList[m_nTableSize];
		// adds new blank object into array
		for(int x = 0; x < m_ObjectArray.length; x++){
			m_ObjectArray[x] = new DataObjectList();
		}
		
	}

	public SeparateChainingHashTable(int nTableSize) {
		// stores sizes, creates new hash array
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObjectList[m_nTableSize];
		// adds new blank object into array
		for(int x = 0; x < m_ObjectArray.length; x++){
			m_ObjectArray[x] = new DataObjectList();
		}
		
	}

	public void put(String strKey, DataObject objData) {
		// gets long from string
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		// while spot is taken go to next spot
		while (!m_ObjectArray[(int) (lHash % m_nTableSize)].list.isEmpty()) {
			lHash++;
		}
		// if you can add it, increment elements
		if (m_ObjectArray[(int) (lHash % m_nTableSize)] == null) {
			elements++;
		}
		// data storage
		m_ObjectArray[(int) lHash].list.add(objData);
	}

	public DataObjectList get(String strKey) {
		// gets long from string
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		// while you have data and haven't exceeded table size
		while( m_ObjectArray[(int)lHash%m_nTableSize] != null && lHash < m_nTableSize){
			// loop through each list in array
		for(int x = 0; x < m_ObjectArray[(int)lHash%m_nTableSize].list.size(); x++){
			// if you found key, hooray, return it
		if(m_ObjectArray[(int)lHash%m_nTableSize].list.get(x).m_strKey == strKey){
			return m_ObjectArray[(int)lHash%m_nTableSize];
		}
		
		}
		// keep searching if not
		lHash++;
		}
		
		return null;
	}
	public DataObject[] expand(DataObject[] hash){
		DataObject[] next = new DataObject[hash.length*2];
		for(int x = 0; x < hash.length; x++){
			next[x] = hash[x];
		}
		
		return next;
		
	}
}
