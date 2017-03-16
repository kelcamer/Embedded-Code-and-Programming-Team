import java.util.ArrayList;

public class weight_transformation {
// the toggle is based on the original string
	public static void main(String[] args) {
		String orig = "ABB";
		ArrayList<String> trans = getTransformations(orig.length(),"", new ArrayList<String>());
		
		// for each transformation get toggle and get weight
		// weight - toggle
		// getMax
		
		System.out.println(getMax(trans, orig));
		
		
		
	}
	private static int getMax(ArrayList<String> trans, String orig) {
		int max = 0;
		for(int x = 0; x < trans.size(); x++){
			int tog = getToggle(trans.get(x), orig);
			int weight = getWeight(trans.get(x));
			if(max < weight - tog){
				max = weight - tog;
			}
			
		}
		return max;
		
	}
	private static int getWeight(String string) {
		if(string.contains("A") && string.contains("B")){
			return 4 + string.length()-2;
		}
		return string.length();
	}
	// only toggle if it is not a pair
	// only toggle if letters are the same
	private static int getToggle(String look, String orig) {
	
		// ABABAAA
		// BABAAAA
		if(look.contains("A") && look.contains("B")){
			if(!orig.contains("A") || !orig.contains("B")){
				return 1;
			}
			
		}
		return 0;
	}
	public static ArrayList<String> getTransformations(int n, String str, ArrayList<String> list){
		if(str.length() == n){
			list.add(str);
			return null;
		}
		getTransformations(n,str+'A',list);
		getTransformations(n, str+'B',list);
		return list;
	}

	
	
	
}
