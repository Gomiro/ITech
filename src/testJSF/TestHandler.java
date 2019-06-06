package testJSF;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "tHandler")
@SessionScoped
public class TestHandler implements Serializable {
	private String gruss = "Hallo Nutzer!";

	public String getGruss() {
		return gruss;
	}

	public void setGruss(String gruss) {
		this.gruss = gruss;
	}
	
	
}
