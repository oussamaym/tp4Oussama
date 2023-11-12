/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.emsi.tpbanqueoussama.service;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import org.emsi.tpbanqueoussama.entities.CompteBancaire;

/**
 *
 * @author DELL
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root", // nom et
        password = "mnbvcxz", // mot de passe que vous avez donnés lors de la création de la base de données
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)
/*
Bean CDI pour gérer les comptes bancaires dans la base de données
 */
@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    public List<CompteBancaire> getAllComptes() {
        String jpqlQuery = "SELECT c FROM CompteBancaire c";
        TypedQuery<CompteBancaire> query = em.createQuery(jpqlQuery, CompteBancaire.class);
        return query.getResultList();
    }

    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public long nbComptes() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM CompteBancaire c", Long.class);
        return query.getSingleResult();
    }

    public CompteBancaire findById(Long idCompteB) {
        return em.find(CompteBancaire.class, idCompteB);
    }

    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination,
            int montant) {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }
}
