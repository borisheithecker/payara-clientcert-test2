package org.thespheres.payara.clientcert.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;

@Stateless
@DeclareRoles({"unitadmin"})
//@RolesAllowed({"unitadmin"})
@WebService(serviceName = "AdminService")
public class AdminService {

    @Resource
    protected SessionContext context;

    @WebMethod(operationName = "hello")
    public String hello() {
        final boolean isAdmin = context.isCallerInRole("unitadmin");
        final String name = context.getCallerPrincipal().getName();
        return "Hello from " + name + " as admin: " + isAdmin;
    }
}
