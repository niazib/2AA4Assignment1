package ca.mcmaster.se2aa4.mazerunner;

public class StringHandler {
    
    public String factorize_path(String path) {
        String result = "";
        int count = 1;

        for (int i = 0; i < path.length(); i++) {
            if (i+1<path.length() && path.charAt(i) == path.charAt(i+1)) {
                count = count+1;
            }
            else {
                if (count > 1) { // Repeating character
                    result = result + count + "" + path.charAt(i) + " ";
                }
                else { // Single character
                    result = result + path.charAt(i) + " ";
                }
                count = 1;
            }
        }
        return result.trim();
    }

    public String expand_path(String path) {
        String result = "";
        int i = 0;
        int int_length;
        String the_int;

        while (i < path.length()) {
            char c = path.charAt(i);

            if (Character.isDigit(c)) {
                i = i+1;
                the_int = "" + c;
                while (i < path.length()) {
                    if (Character.isDigit(path.charAt(i))) {
                        the_int = the_int + path.charAt(i);
                    }
                    else {
                        break;
                    }
                    i = i + 1;
                }
                int count = Integer.parseInt(the_int);
                if (i < path.length() && (path.charAt(i) == 'F' || path.charAt(i) == 'L' || path.charAt(i) == 'R')) {
                    for (int j = 0; j < count; j++) {
                        result = result + path.charAt(i);
                    }
                }
            }
            else if (c == 'F' || c == 'L' || c == 'R') {
                result = result + c;
            }
            i=i+1;
        }
        return result;
    }
}
