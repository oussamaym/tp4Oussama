/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.emsi.tpbanqueoussama.jsf;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.emsi.tpbanqueoussama.entities.CompteBancaire;
import org.emsi.tpbanqueoussama.service.GestionnaireCompte;

/**
 *
 * @author DELL
 */
/*
Backing bean pour la page de transfert
 */
@Named(value = "transfert")
@RequestScoped
public class transfert {

    Long idSource;
    Long idDestination;
    int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of transfert
     */
    public transfert() {
    }

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String transferer() {
        CompteBancaire source = gestionnaireCompte.findById(this.idSource);
        CompteBancaire destination = gestionnaireCompte.findById(this.idDestination);
        gestionnaireCompte.transferer(source, destination, this.montant);
        return "listeComptes?faces-redirect=true";
    }

}
