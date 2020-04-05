package org.thespheres.payara.clientcert.test;

import fish.payara.security.annotations.CertificateAuthenticationMechanismDefinition;
import fish.payara.security.annotations.CertificateIdentityStoreDefinition;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.SessionScoped;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Configures JAX-RS for the application.
 *
 * @author Juneau
 */
@DeclareRoles("unitadmin")
@ServletSecurity(
        value = @HttpConstraint(
                transportGuarantee = TransportGuarantee.CONFIDENTIAL,
                rolesAllowed = {"unitadmin"}
        )
)
@CertificateAuthenticationMechanismDefinition
@CertificateIdentityStoreDefinition(value = "admin-cert", assignGroups = "unitadmin")
@SessionScoped
@WebServlet(name = "AdminServlet", urlPatterns = {"/*"})
public class AdminServlet extends HttpServlet {

}
