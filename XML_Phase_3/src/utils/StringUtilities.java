package utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtilities {
	static {
        NEW_LINE = "\n";
        try{
            //NEW_LINE = System.getProperty("line.separator");
            NEW_LINE = System.lineSeparator();
        }
        catch(Exception ex)
        {
            Logger.log(ex.getMessage());
        }

        FILE_SEPARATOR = "/";
        try{
            
            FILE_SEPARATOR = System.getProperty("file.separator");
        }
        catch(Exception ex)
        {
            Logger.log(ex.getMessage());        
        }
        PATH_SEPARATOR = "/";
        try{
            PATH_SEPARATOR = System.getProperty("path.separator");
        }
        catch(Exception ex)
        {
            Logger.log(ex.getMessage());
        }
        USER_DIR = "";
        try{
            USER_DIR = System.getProperty("user.dir");
        }
        catch(Exception ex)
        {
            Logger.log(ex.getMessage());
        }
        
    }

    public static String NEW_LINE;
    public static String FILE_SEPARATOR;
    public static String PATH_SEPARATOR;
    public static String USER_DIR;
    public static final String PROXY_PROPERTY = "java.net.useSystemProxies";

    public static boolean isBlank(String str){
        if (str == null) return true;
        return str.isEmpty();
    }

    public static String[] parameterSplit(String args){
        Pattern p = Pattern.compile("((?<=(\\\"))[\\S ]*(?=(\\\")))|((?<!\\\")\\w+(?!\\\"))");
                                    
        Matcher m = p.matcher(args);
        ArrayList<String> tmp = new ArrayList<String>();
        while (m.find()){
            tmp.add(m.group());
        }
        
        String [] words = new String[tmp.size()];
        words = (String[]) tmp.toArray(words);
        String count = Integer.toString(words.length);
        
        String[] word;
        word = new String [words.length + 1];
        word[0] = count;
        System.arraycopy(words, 0, word, 1, words.length);
        
        return word;
    }
    
    public static String removeQuote(String str)
    {
        int length = str.length();
        if (length < 2) return str;
        if (str.charAt(0) == '"' && str.charAt(length - 1) == '"') 
        {
            if (length == 2) 
            {
                return "";
            }
            else
            {
                return str.substring(1, length - 2);
            }
        }
        return str;
    }
    
    public static String fileNameFromURL(String url) 
    {
        try
        {
            String encoded = encodeURL(url);
            if (encoded.length() > 220)
            {
                encoded = encoded.substring(0, 199);
            }
            return encoded;
        }
        catch (UnsupportedEncodingException uee)
        {
            Logger.log("Cannot generate file name from URL");
            return "UnknownFilename";
            
        }
    }
    
    public static String encodeURL(String url) throws UnsupportedEncodingException
    {
        return URLEncoder.encode(url, "UTF-8");        
    }
    
    public static String from(BigInteger bi){
    	return (new String(bi.toByteArray()));
    }
    
    public static boolean contains(String target, String kw){
    	return (target.indexOf(kw) >= 0);
    }
}
