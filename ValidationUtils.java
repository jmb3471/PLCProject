public class ValidationUtils {

    public static boolean validateDouble(double num)
    {
        String fullNum = Double.toString(num);
        char[] chars = fullNum.toCharArray();

        if (chars.length == 0) {
            return true;
        }

        if (chars[0] == '-' || chars[0] == '+') {
            return validateDouble(Double.valueOf(fullNum.substring(1, fullNum.length() - 1)));
        }

        if (chars[0] == '.') {
            if (!Character.isDigit(chars[1])) {
                return false;
            } else {
                return validateDouble(Double.valueOf(fullNum.substring(1, fullNum.length() - 1)));
            }

        } else if (Character.isDigit(chars[0])) {
            return validateDouble(Double.valueOf(fullNum.substring(1, fullNum.length() - 1)));
        } else {
            return false;
        }
    }
    
    public static boolean validateInt(int num)
    {
        String fullNum = Integer.toString(num);
        char[] chars = fullNum.toCharArray();

        if (chars.length == 0) {
            return true;
        }

        if (chars[0] == '-' || chars[0] == '+') {
            return validateInt(Integer.valueOf(fullNum.substring(1, fullNum.length() - 1)));
        }

        if (Character.isDigit(chars[0])) {
            return validateInt(Integer.valueOf(fullNum.substring(1, fullNum.length() - 1)));
        }

        return false;
    }

    public static boolean validateRelOp(String relOp)
    {
        if (relOp.equals(">")) {
            return true;
        }
        if (relOp.equals(">=")) {
            return true;
        }
        if (relOp.equals("<")) {
            return true;
        }
        if (relOp.equals("<=")) {
            return true;
        }
        if (relOp.equals("==")) {
            return true;
        }
        if (relOp.equals("!=")) {
            return true;
        }

        return false;
    }
    
    public static boolean validateChar(char c)
    {
        if(!Character.isLetterOrDigit(c))
            return false;

        return true;
    }
}
