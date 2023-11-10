/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package org.emsi.tpbanqueoussama.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import org.emsi.tpbanqueoussama.entities.CompteBancaire;
import org.emsi.tpbanqueoussama.service.GestionnaireCompte;

/**
 *
 * @author DELL
 */
/*
Managed bean pour affichage de liste des comptes
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    public ListeComptes() {
    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public List<CompteBancaire> getAllComptes() {
        return gestionnaireCompte.getAllComptes();
    }

}
