import java.util.HashSet;

class FindAllSumPairs {

    public static void main(String [] args){
        int [] arr = {1, 5, 7, -1, 5};
        findAllPairs(arr, 6);
    }

    private static void findAllPairs(int [] arr, int sum) {
        
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i<arr.length; i++){
            int comp = sum - arr[i];
            if(set.contains(comp)){
                System.out.print("(Pair found: " + arr[i] + "," + comp + ")");
            } else{
                set.add(arr[i]);
            }
        }
}
}