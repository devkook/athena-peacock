/* 
 * Copyright 2013 The Athena-Peacock Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * Sang-cheon Park	2013. 10. 31.		First Draft.
 */
package com.athena.peacock.controller.common.component;

import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * <pre>
 * Spring RestTemplate을 이용하여 RHEV Manager의 RESTFul 서비스를 호출하기 위한 Component 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component("restTemplate")
public class RHEVMRestTemplate implements InitializingBean {
	
    private static final Logger logger = LoggerFactory.getLogger(RHEVMRestTemplate.class);
	
	private static final String HOST_HEADER_KEY = "Host";
	private static final String AUTH_HEADER_KEY = "Authorization";
	
	@Value("#{contextProperties['rhev.manager.protocol'] ?: 'https'}")
	private String protocol;
	@Value("#{contextProperties['rhev.manager.host']}")
	private String host;
	@Value("#{contextProperties['rhev.manager.domain']}")
	private String domain;
	@Value("#{contextProperties['rhev.manager.port'] ?: '443'}")
	private String port;
	@Value("#{contextProperties['rhev.manager.username']}")
	private String username;
	@Value("#{contextProperties['rhev.manager.password']}")
	private String password;
	
	private String credential;
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		/**
		 * 유효하지 않은 인증서를 가진 호스트로 HTTPS 호출 시 HandShake Exception 및 인증서 관련 Exception이 발생하기 때문에
		 * RHEV Manager(host) 및 SSL 인증서 관련 검증 시 예외를 발생하지 않도록 추가됨.
		 */
		// Create a hostname verifier that does not validate hostname
	    HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				if (hostname.equals(host)) {
                    return true;
				}
				
				return false;
			}
	    });
	    
	    // Create a trust manager that does not validate certificate chains
	    // Refer to https://code.google.com/p/misc-utils/wiki/JavaHttpsUrl
	    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
	        @Override
	        public void checkClientTrusted(X509Certificate[] chain, String authType ) throws CertificateException {
	        	// nothing to do.
	        }
	        
	        @Override
	        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	        	// nothing to do.
	        }
	        
	        @Override
	        public X509Certificate[] getAcceptedIssuers() {
	            return null;
	        }
	    }};
	    
	    try {
			// Install the all-trusting trust manager
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
		} catch (KeyManagementException e) {
			logger.error("KeyManagementException has occurred.", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("NoSuchAlgorithmException has occurred.", e);
		}
	}//end of afterPropertiesSet()
	
	/**
	 * <pre>
	 * HTTP Header에 인증 정보를 포함시킨다.
	 * </pre>
	 * @return
	 */
	private HttpEntity<Object> setHTTPHeader() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set(HOST_HEADER_KEY, host);
		requestHeaders.set(AUTH_HEADER_KEY, getCredential());
		
		return new HttpEntity<Object>(requestHeaders);
	}//end of addAuth()
	
	/**
	 * <pre>
	 * RHEV Manager에 전달하기 위한 인증 정보를 생성한다. 
	 * </pre>
	 * @return
	 */
	public String getCredential() {
		if (credential == null) {
			try {
				String plain = username + "@" + domain + ":" + password;
				credential = "Basic " + Base64.encodeBase64String(plain.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error("UnsupportedEncodingException has occurred.", e);
			}
		}
		
		return credential;
	}//end of getCredential()
	
	/**
	 * <pre>
	 * RHEV Manager를 호출하기 위한 URL를 조합한다.
	 * </pre>
	 * @param api
	 * @return
	 */
	public String getUrl(String api) {
		StringBuilder sb = new StringBuilder();
		
		if (protocol.toLowerCase().equals("http")) {
			sb.append("http://");
		} else {
			sb.append("https://");
		}
		
		sb.append(host).append(":").append(port);
		
		if (!api.startsWith("/")) {
			sb.append("/");
		}
		
		sb.append(api);
		
		return sb.toString();
	}//end of getUrl()
	
	/**
	 * <pre>
	 * RHEV Manager로 해당 API를 호출하고 결과를 반환한다.
	 * </pre>
	 * @param api RHEV Manager API (/api, /api/vms 등)
	 * @param clazz 변환될 Target Object Class
	 * @return
	 * @throws RestClientException
	 * @throws Exception
	 */
	public <T> T submit(String api, Class<T> clazz) throws RestClientException, Exception {
		Assert.isTrue(StringUtils.isNotEmpty(api), "api must not be null");
		Assert.notNull(clazz, "clazz must not be null.");
		
		try {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<?> response = rt.exchange(getUrl(api), HttpMethod.GET, setHTTPHeader(), clazz);
			
			logger.debug("[Request URL] : {}", getUrl(api));
			logger.debug("[Response] : {}", response);
			
			return clazz.cast(response.getBody());
		} catch (RestClientException e) {
			logger.error("RestClientException has occurred.", e);
			throw e;
		} catch (Exception e) {
			logger.error("Unhandled Exception has occurred.", e);
			throw e;
		}
	}//end of submit()
}
//end of RHEVMRestTemplate.java