package app.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 业务相关配置
 * @author Administrator
 *
 */
public class BizConfig {
	
	private static BizConfig instance = null;
	private Properties prop =  new  Properties(); 
	private BizConfig() {
		InputStream in = this.getClass() .getResourceAsStream( "/biz.properties" );    
        try  {    
           prop.load(in);    
       }  catch  (IOException e) {    
           e.printStackTrace();    
       }   
	}
	
	public static synchronized BizConfig getInstance() {
		if(instance == null) {
			instance = new BizConfig();
		}
		return instance;
	}
	
	public String getValue(String key) {
		return prop.getProperty(key);
	}
	
	
	public static void main(String[] args) {
		BizConfig bizConfig = BizConfig.getInstance();
		System.out.println(bizConfig.getValue("target.site.domain"));
	}
	
}
