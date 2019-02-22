package cn.jsonXxxx.jyTest.config;

import java.io.Serializable;

import org.apache.shiro.util.SimpleByteSource;
import org.springframework.stereotype.Component;

@Component
public class MySimpleByteSource extends SimpleByteSource implements Serializable{

	 private static final long serialVersionUID = 1L;

	    public MySimpleByteSource(byte[] bytes) {
	        super(bytes);
	    }
}
