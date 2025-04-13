package intermediate;

public class palindromeCheck {
    char[] rev;
    char[] chars;
    
    public palindromeCheck(String msg) {
        chars = msg.toCharArray();
        rev = new char[chars.length];
        check(chars, 0);
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
        System.out.print( " ");
        for (int i = 0; i < rev.length; i++) {
            System.out.print(rev[i]);
        }
        System.out.println();
        System.out.println(isPalindrome());
    }
    
    public boolean isPalindrome() {
        for (int i = 0, k = rev.length - 1; i < k; i++, k--) {
            if (rev[i] != rev[k]) {
                return false;
            }
        }
        return true;
    }
    
    public char check(char[] a, int index) {
        if (index == rev.length) {
            return 'x';
        }
        
        rev[index] = chars[index];
        
        return check(a, index + 1);
    }
    
    
    public static void main(String[] args) {
        palindromeCheck p = new palindromeCheck("nola");
    }
}