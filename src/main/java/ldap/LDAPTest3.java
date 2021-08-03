package ldap;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.http.HttpServletRequest;

public class LDAPTest3 {

  public boolean test(HttpServletRequest request, DirContext ctx) throws NamingException {
    String pass = request.getParameter("pass");
    String user = "{0}";
    user = user + "_USER";

    String filter = "(&(uid=" + user + ")(userPassword=" + "{1}" + "))";

    NamingEnumeration<SearchResult> results =
        ctx.search(
            "ou=system",
            filter,
            new String[] {request.getParameter("user"), pass},
            new SearchControls());
    return results.hasMore();
  }
}
