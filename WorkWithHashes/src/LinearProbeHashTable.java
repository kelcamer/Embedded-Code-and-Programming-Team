/*
 * Name: Kelsey Cameron
 * Course Number: COP3503C-16Spring 0001
 * Assignment title:  HashExperiments
 * Date: February 3, 2016
 */
public class LinearProbeHashTable {
	// defines sizes and creates variables
	int m_nTableSize = 10000;
	DataObject[] m_ObjectArray;
	int elements = 0;

	public LinearProbeHashTable() {
		// creates a new array of dataobjects
		m_ObjectArray = new DataObject[m_nTableSize];
	}

	// overloaded constructor, allows you to define the table size
	public LinearProbeHashTable(int nTableSize) {
		m_nTableSize = nTableSize;
		m_ObjectArray = new DataObject[m_nTableSize];
	}

	public void put(String strKey, DataObject objData) {
		// crazy algorithm to get long from string
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		
		// while the next index mod the table size != null, look for next null spot and 
		// you have no duplicates
		while (m_ObjectArray[(int) (lHash % m_nTableSize)] != null
				&& !m_ObjectArray[(int) lHash % m_nTableSize].equals((int) lHash)) {
			// go to next index
			lHash++;
		}
		// if you found a null spot, congratulations you're adding an element
		if (m_ObjectArray[(int) (lHash % m_nTableSize)] == null) {
			elements++;
		}
		// adds actual element
		m_ObjectArray[(int) lHash] = objData;
	}

	public DataObject get(String strKey) {
		// gets long from string
		long lHash = Utility.HashFromString(strKey) % m_nTableSize;
		// while your spot is taken
		while ( m_ObjectArray[(int) (lHash % m_nTableSize)] != null) {
			// if you haven't found the key, increment index
			if(m_ObjectArray[(int) (lHash % m_nTableSize)].GetKey() != strKey){
				lHash++;
			}
			else{
				// return stored data
				return (m_ObjectArray[(int) (lHash % m_nTableSize)]);
				
			}
			
		}
		// if it isn't in there, return null
		return null;
	}
	public DataObject[] expand(DataObject[] hash){
		// creates new object double size
		DataObject[] next = new DataObject[hash.length*2];
		// stores data again
		for(int x = 0; x < hash.length; x++){
			next[x] = hash[x];
		}
		// returns new array
		return next;
		
	}
}
