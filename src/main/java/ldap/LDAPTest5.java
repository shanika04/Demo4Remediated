package ldap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;

public class LDAPTest5 {

  public boolean test(HttpServletRequest request, String domainName, DirContext ctx)
      throws NamingException {
    String pass = request.getParameter("pass");
    String user = request.getParameter("user");
    String tmp = "{0}" + "_USER";
    user = tmp + ".old";
    String saltedPassword = "{1}" + "%!";
    String filter = "(&(uid=" + user + ")(userPassword=" + saltedPassword + "))";

    NamingEnumeration<SearchResult> results =
        ctx.search("ou=system", filter, new String[] {user, pass}, new SearchControls());
    return results.hasMore();
  }
}
