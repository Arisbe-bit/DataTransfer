package com.neoris.tcl;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataTransferApplicationTests {

	@Test
	void contextLoads() {
	}
	void testEncryptor() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("N30r1s&tcl");
		config.setAlgorithm("PBEWithMD5AndDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		
		String user     = "HFM_DEVELOPER";
		String password = "Hfm_123dev";
		String url      = "jdbc:oracle:thin:@//ebstest03.tclgroup.com:1521/EBSTEST";
		
		System.out.println("user     => " + encryptor.encrypt(user));
		System.out.println("password => " + encryptor.encrypt(password));
		System.out.println("url      => " + encryptor.encrypt(url));
		
		//System.out.println("user     => " + encryptor.decrypt("lW80MMwtYXFSZwbOqJAk93cn9WL38Mw0"));
		//System.out.println("password => " + encryptor.decrypt("tRSc+pIZNUkJoVVrt0ACUAWW+Ek4QU4k"));
		//System.out.println("url      => " + encryptor.decrypt("k+aNqUCUnCxKrB7cjD2HmlqyzqYR9HJFjLSvz8AGtiXdVGAIhg1x8BOEvHn2rpEQZ/axqZ2vWnr2OEph/hZdUQ=="));
	}
}
