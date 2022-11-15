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

        if (chars.length == 1) {
            return Character.isDigit(chars[0]);
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

    public static boolean validateMathOp(String mathOp)
    {
        if (mathOp.equals("+")) {
            return true;
        }
        if (mathOp.equals("-")) {
            return true;
        }
        if (mathOp.equals("*")) {
            return true;
        }
        if (mathOp.equals("/")) {
            return true;
        }

        return false;
    }
    
    public static boolean validateChar(char c)
    {
        if (!Character.isLetterOrDigit(c))
            return false;

        return true;
    }
    

    public static boolean validateString(String s)
    {
        if ((s.indexOf("\"") == 0 && s.lastIndexOf("\"") == s.length() - 1))
        {
            for(int i = 0; i < s.length()-1; i++)
            {
                boolean isChar = ValidationUtils.validateChar(s.substring(i, i + 1).charAt(0));
                boolean isSpace = Character.isWhitespace(s.substring(i, i + 1).charAt(0));
                if (!isChar && !isSpace)
                {
                    return false;
                }
            }
        } else {
            return false;
        }
        
        return true;
    }


}
