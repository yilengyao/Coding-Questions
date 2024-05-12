class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<Integer> octets = new ArrayList<>();
        List<String> ipAddresses = new ArrayList<>();
        ipHelper(s, 0, octets, ipAddresses);
        return ipAddresses;        
    }

    void ipHelper(String s, int left, List<Integer> octets, List<String> ipAddresses) {
        if (octets.size() == 3) {
            if (validOctet(s, left, s.length())) {
                StringBuilder sb = new StringBuilder(s);
                for (int i = 2; i >= 0; i--) {
                    sb.insert(octets.get(i), ".");
                }
                ipAddresses.add(sb.toString());
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (validOctet(s, left, left + i)) {

                octets.add(left + i);
                ipHelper(s, left + i, octets, ipAddresses);
                octets.remove(octets.size()-1);
            }
        }
        return;
    }

    boolean validOctet(String s, int left, int right) {

        if (right > s.length()) {
            return false;
        }

        String substring = s.substring(left, right);

        if (substring.length() > 1 && substring.charAt(0) == '0') {
            return false;
        }

        try {
            Integer octet = Integer.parseInt(substring);

            if (0 <= octet && octet <= 255) {
                return true;
            }
            return false;
        } catch (NumberFormatException e){
            return false;
        }
    }
}