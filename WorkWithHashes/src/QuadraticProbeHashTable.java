/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  HashExperiments
 * Date: February 3, 2016
 */
import java.math.BigInteger;

public class QuadraticProbeHashTable {
	// defines variables
	int m_nTableSize = 100000;
	DataObject[] m_ObjectArray;
	int elements = 0;

	// constructors that allow you to create default hash or predefined size
	public QuadraticProbeHashTable() {
		m_ObjectArray = new DataObject[m_nTableSize];
	}

	public QuadraticProbeHashTable(int nTableSize) {
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}

	public void put(String strKey, DataObject objData) {
		// gets long from string
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		// q = quadratic counter
		int q = 0;
		
		// while the next spot is taken and you haven't had a duplicate
		while (m_ObjectArray[(int) (lHash % m_nTableSize)+q*q] != null
				&& !m_ObjectArray[(int) lHash % m_nTableSize+q*q].equals((int) lHash)) {
			// increment indexes and check next q
			q++;
			lHash++;
		}
		if (m_ObjectArray[(int) (lHash % m_nTableSize)+q*q] == null) {
			// you get to add new element
			elements++;
		}
		//  data storage
		m_ObjectArray[(int) (lHash % m_nTableSize)+q*q] = objData;
	}

	public DataObject get(String strKey) {
		// gets long from string
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		// counter
		int q =0;
		
		// while spot is taken
		while (m_ObjectArray[(int) (lHash % m_nTableSize) + q*q] != null) {
			// if you didn't find the key, go to next spot.
			if(m_ObjectArray[(int) (lHash % m_nTableSize) + q*q].GetKey() != strKey){
			lHash++;
			}
			// found key, return value
			else{
				return (m_ObjectArray[(int) (lHash % m_nTableSize) + q*q]);
				
			}
			// check next spot
			q++;
		}
		return null;
	}
	public DataObject[] expand(DataObject[] hash){
		// gets next prime number of double hash size 
		BigInteger oldsize = new BigInteger(hash.length*2 + "");
		int num = oldsize.nextProbablePrime().intValue();
		DataObject[] next = new DataObject[num];
		// transfers data
		for(int x = 0; x < hash.length; x++){
			next[x] = hash[x];
		}
		
		return next;
		
	}
}
