package ldap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;

public class LDAPTest1 {

  public boolean test(HttpServletRequest request, DirContext ctx) throws NamingException {

    String pass = request.getParameter("pass");
    String user = request.getParameter("user");
    String filter = "(&(uid=" + "{0}" + ")(userPassword=" + "{1}" + "))";

    NamingEnumeration<SearchResult> results =
        ctx.search("ou=system", filter, new String[] {user, pass}, new SearchControls());
    return results.hasMore();
  }
}
