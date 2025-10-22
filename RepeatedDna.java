// This uses robin karp algorithm to calculate hashes based on given string and add into a set initially
// We will mainatain a 10 length window and keep updating this hash value by removing first char and adding last
// If at every point, hash is already stored, we consider that as a sub string that is repeated
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Map<Character, Integer> map = new HashMap();
        map.put('A', 1);
        map.put('C', 2);
        map.put('G', 3);
        map.put('T', 4);

        Set<Long> hashes = new HashSet();
        Set<String> set = new HashSet();

        long val = 0, posFac = (long) Math.pow(4, 10);
        for(int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            val=val*4+(map.get(c));
            if(i>=10) {
                val=val-(map.get(s.charAt(i-10))*posFac);
            }
            if(i>=9) {
                if(hashes.contains(val)) {
                    set.add(s.substring(i-9,i+1));
                } else {
                    hashes.add(val);
                }
            }
        }
        return new ArrayList(set);
    }
}
