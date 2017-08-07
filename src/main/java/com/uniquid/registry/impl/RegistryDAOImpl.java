package com.uniquid.registry.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uniquid.blockchain.AddressInfo;
import com.uniquid.blockchain.exception.BlockChainException;
import com.uniquid.registry.RegistryDAO;
import com.uniquid.registry.exception.RegistryException;

public class RegistryDAOImpl implements RegistryDAO {
	
	private static final String PUT_URL = "%1&s/registry";
	private static final String GET_URL = "%1&s/registry";
	
	private String registryAddress;
	
	public RegistryDAOImpl(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	@Override
	public void insertMapping(String providerName, String providerAddress) throws RegistryException {
		
		try {

			JSONObject jsonMessage = new JSONObject();

			jsonMessage.put("provider_name", providerName);
			jsonMessage.put("provider_address", providerAddress);

			byte[] postDataBytes = jsonMessage.toString().getBytes("UTF-8");

			URL url = new URL(PUT_URL.replace("%1&s", registryAddress));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// optional default is GET
			connection.setRequestMethod("POST");

			// add request header
			connection.setRequestProperty("User-Agent", "UNIQUID-UTILS-0.1");

			connection.setRequestProperty("Content-Type", "application/json");

			connection.setRequestProperty("Charset", "utf-8");

			connection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));

			connection.setDoOutput(true);

			connection.getOutputStream().write(postDataBytes);

			if (201 == connection.getResponseCode()) {

				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}

				in.close();

				return; /*(response.toString());*/

			} else {

				throw new RegistryException("Error while submitting post: " + connection.getResponseCode() + " "
						+ connection.getResponseMessage());

			}

		} catch (MalformedURLException ex) {

			throw new RegistryException("Exception Accessing URL", ex);

		} catch (IOException ex) {

			throw new RegistryException("Exception while I/O", ex);

		} catch (Throwable t) {

			throw new RegistryException("Unexpected Exception", t);

		}

	}
	
	private static String addressFromJsonString(String string, String providerAddress) throws JSONException {

		JSONArray jsonArray = new JSONArray(string);
		
		Iterator i = jsonArray.iterator();
		
		while (i.hasNext()) {
			
			JSONObject jsonMessage = (JSONObject) i.next();

			String a = jsonMessage.getString("provider_address");
			String n = jsonMessage.getString("provider_name");
	
			if (providerAddress.equals(a)) {
				
				return n;
				
			}
			
		}
		
		return null;

	}

	@Override
	public String retrieveProviderName(String providerAddress) throws RegistryException {
		
		try {
			URL url = new URL(GET_URL.replace("%1&s", registryAddress));

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// optional default is GET
			connection.setRequestMethod("GET");

			// add request header
			connection.setRequestProperty("User-Agent", "UNIQUID-UTILS-0.1");

			if (200 == connection.getResponseCode()) {

				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}

				in.close();

				return addressFromJsonString(response.toString(), providerAddress);

			} else {

				return null;

			}

		} catch (MalformedURLException ex) {

			throw new RegistryException("Exception Accessing URL", ex);

		} catch (IOException ex) {

			throw new RegistryException("Exception while I/O", ex);

		} catch (Throwable t) {
			
			throw new RegistryException("Unexpected Exception", t);
			
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		RegistryDAOImpl i = new RegistryDAOImpl("http://appliance4.uniquid.co:8080");
		
		String a = i.retrieveProviderName("n2PFqZKd3XfbgKQwkV5jJ1Je1pj114U4LJ");
		
		System.out.println(a);
		
	}

}
