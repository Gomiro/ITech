package testJSF;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpRetryException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@ManagedBean(name = "tHandler")
@SessionScoped
public class TestHandler implements Serializable {
	private String gruss = "Hallo Nutzer!";
	private String menge;

	public String getGruss() {
		return gruss;
	}

	public void setGruss(String gruss) {
		this.gruss = gruss;
	}
	
	public String getEtwas() {
		return "Das ist etwas!";
		
	}
	
	public ArrayList<Zug> getZuege(String s){
		ArrayList<Zug> zugList = new ArrayList<>();
		zugList.add(new Zug("Flugzeug", 42));
		zugList.add(new Zug("Helikopter", 5));
		
		return zugList;
	}
	
	public void setWarenkorb(String value) throws UnsupportedEncodingException {
		FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("einCookie", URLEncoder.encode(value, "UTF-8"), null);
	}
 
	public String getWarenkorb() throws UnsupportedEncodingException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie cookie = null;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie co : cookies) {
				if(co.getName().equals("einCookie")) {
					return URLDecoder.decode(co.getValue(), "UTF-8");
				}
			}
		}
		return "Fehler";
		
	}
	
	public void setACookie(String value) throws UnsupportedEncodingException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie cookie = null;
		String wert = "";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie co : cookies) {
				if(co.getName().equals("BCookie")) {
					cookie = co;
					break;
				}
			}
			
		}
		if(cookie == null) {
			cookie = new Cookie("BCookie", URLEncoder.encode(value, "UTF-8"));
			cookie.setMaxAge(60*60);
		} else {
			
			wert = URLDecoder.decode(cookie.getValue(), "UTF-8");
			if(wert.equals("")) {
				wert = value;
			} else {
			wert = wert + " " + value;
			}
			cookie.setValue(URLEncoder.encode(wert, "UTF-8"));
			cookie.setMaxAge(60*60);
		}
		
		
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.addCookie(cookie);
	}
	
	public String getACookie() throws UnsupportedEncodingException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie cookie = null;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie co : cookies) {
				if(co.getName().equals("BCookie")) {
					return URLDecoder.decode(co.getValue(), "UTF-8");
				}
			}
		}
		return "Fehler";
		
	}
	
	public String resetCookie() throws UnsupportedEncodingException {
		FacesContext.getCurrentInstance().getExternalContext().addResponseCookie("BCookie", URLEncoder.encode("", "UTF-8"), null);
		return "test.xhtml";
	}
	
	public void setMapCookie(String value) throws UnsupportedEncodingException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie cookie = null;
		String wert = "";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie co : cookies) {
				if(co.getName().equals("BCookie")) {
					cookie = co;
					break;
				}
			}
			
		}
		if(cookie == null) {
			cookie = new Cookie("BCookie", URLEncoder.encode(value+" "+getMenge(), "UTF-8"));
		} else {
			wert = URLDecoder.decode(cookie.getValue(), "UTF-8");
			cookie.setValue(URLEncoder.encode(" "+wert+" "+value+" "+getMenge(), "UTF-8"));
		}
		//cookie.setValue(URLEncoder.encode(wert, "UTF-8"));
		cookie.setMaxAge(60*60);
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.addCookie(cookie);
		
	}
	
	public String getMapCookie() throws UnsupportedEncodingException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		Cookie cookie = null;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie co : cookies) {
				if(co.getName().equals("BCookie")) {
					System.out.println(URLDecoder.decode(co.getValue(), "UTF-8"));
					return URLDecoder.decode(co.getValue(), "UTF-8");
				}
			}
		}
		return "Fehler";
	}
	
	public void setMenge(String menge) {
		this.menge = menge;
	}
	
	public String getMenge() {
		return menge;
	}
	
}
