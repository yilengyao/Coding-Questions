class Solution {
    public int compareVersion(String version1, String version2) {
        String[] splitVersion1 = version1.split("\\.");
        String[] splitVersion2 = version2.split("\\.");

        int index1 = 0;
        int index2 = 0;      

        while (index1 < splitVersion1.length && index2 < splitVersion2.length) {
            if (Integer.parseInt(splitVersion1[index1]) > Integer.parseInt(splitVersion2[index2])) {
                return 1;
            } else if (Integer.parseInt(splitVersion1[index1]) < Integer.parseInt(splitVersion2[index2])) {
                return -1;
            }
            index1++;
            index2++;
        }

        while (index1 < splitVersion1.length) {
            if (Integer.parseInt(splitVersion1[index1]) > 0) {
                return 1;
            }
            index1++;
        }

        while (index2 < splitVersion2.length) {
            if (Integer.parseInt(splitVersion2[index2]) > 0) {
                return -1;
            }
            index2++;
        }

        return 0;
    }
}