class Solution {
    public String simplifyPath(String path) {
        String[] splitPath = path.split("/");
        List<String> result = new ArrayList<>();

        for (int i = 0; i < splitPath.length; i++) {
            if (splitPath[i].equals("..")) {
                if (result.size()  > 0) {
                result.remove(result.size() - 1);
                }
            } else {
                if (!splitPath[i].equals("") && !splitPath[i].equals(".")) {
                result.add(splitPath[i]);
                }
            }
        }
        
        return "/" + String.join("/", result);
    }
}