package cn.heckman.api.realm;

import java.net.URL;

import javax.servlet.Filter;

public class Test {

	public static void main(String[] args) {

		URL url = Filter.class.getProtectionDomain().getCodeSource()
				.getLocation();
		System.out.println("path:" + url.getPath() + "\n name:" + url.getFile());

	}

}
