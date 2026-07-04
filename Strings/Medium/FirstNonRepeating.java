import java.util.HashMap;

class FirstNonRepeating {

    public static void main(String[] args){
        String s = "swiss";
        System.out.println(firstNonRepeating(s));
    }

    private static char firstNonRepeating(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for(int i=0; i<s.length(); i++){
            if(map.get(s.charAt(i)) == 1){
                return s.charAt(i);
            }
        }

        return '\0';
    }
}