package Dominio;

import java.util.List;


/**
 * @author dcs-rjao
 *
 * 19/Mar/2003
 */

public interface IBranch {

	public String getCode();
	public String getName();
	public List getScopes();

	public void setCode(String code);
	public void setName(String name);
	public void setScopes(List scopes);
}