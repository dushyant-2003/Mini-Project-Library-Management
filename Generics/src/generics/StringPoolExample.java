package generics;

public class StringPoolExample {
    public static void main(String[] args) {
        // Creating string literals
        String str1 = "Hello";
        String str2 = "Hello";
        
        // Comparing string literals
        System.out.println("str1 == str2: " + (str1 == str2)); // true

        // Creating new String objects using new keyword
        String str3 = new String("Hello");
        String str4 = new String("Hellovxv");
        
        // Comparing new String objects
        System.out.println("str3 == str4: " + (str3 == str4)); // false

        // Interning new String objects
        String str5 = str3.intern();
        String str6 = str4.intern();
        
        // Comparing interned strings
        System.out.println("str5 == str6: " + (str5 == str6)); // true
    }
}

