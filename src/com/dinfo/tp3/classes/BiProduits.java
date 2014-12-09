package com.dinfo.tp3.classes;

// Generated Dec 6, 2014 1:39:51 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * BiProduits generated by hbm2java
 */
public class BiProduits implements java.io.Serializable {

	private int codeProduit;
	private String nom;
	private String description;
	private BigDecimal prixUnitaire;
	private char indicateurTaxable;
	private int qteEnInventaire;
	private int niveauRuptureStock;
	private int qteAcommander;
	private Set biVentesproduitses = new HashSet(0);

	public BiProduits() {
	}

	public BiProduits(int codeProduit, String nom, String description,
			BigDecimal prixUnitaire, char indicateurTaxable,
			int qteEnInventaire, int niveauRuptureStock, int qteAcommander) {
		this.codeProduit = codeProduit;
		this.nom = nom;
		this.description = description;
		this.prixUnitaire = prixUnitaire;
		this.indicateurTaxable = indicateurTaxable;
		this.qteEnInventaire = qteEnInventaire;
		this.niveauRuptureStock = niveauRuptureStock;
		this.qteAcommander = qteAcommander;
	}

	public BiProduits(int codeProduit, String nom, String description,
			BigDecimal prixUnitaire, char indicateurTaxable,
			int qteEnInventaire, int niveauRuptureStock, int qteAcommander,
			Set biVentesproduitses) {
		this.codeProduit = codeProduit;
		this.nom = nom;
		this.description = description;
		this.prixUnitaire = prixUnitaire;
		this.indicateurTaxable = indicateurTaxable;
		this.qteEnInventaire = qteEnInventaire;
		this.niveauRuptureStock = niveauRuptureStock;
		this.qteAcommander = qteAcommander;
		this.biVentesproduitses = biVentesproduitses;
	}

	public int getCodeProduit() {
		return this.codeProduit;
	}

	public void setCodeProduit(int codeProduit) {
		this.codeProduit = codeProduit;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public char getIndicateurTaxable() {
		return this.indicateurTaxable;
	}

	public void setIndicateurTaxable(char indicateurTaxable) {
		this.indicateurTaxable = indicateurTaxable;
	}

	public int getQteEnInventaire() {
		return this.qteEnInventaire;
	}

	public void setQteEnInventaire(int qteEnInventaire) {
		this.qteEnInventaire = qteEnInventaire;
	}

	public int getNiveauRuptureStock() {
		return this.niveauRuptureStock;
	}

	public void setNiveauRuptureStock(int niveauRuptureStock) {
		this.niveauRuptureStock = niveauRuptureStock;
	}

	public int getQteAcommander() {
		return this.qteAcommander;
	}

	public void setQteAcommander(int qteAcommander) {
		this.qteAcommander = qteAcommander;
	}

	public Set getBiVentesproduitses() {
		return this.biVentesproduitses;
	}

	public void setBiVentesproduitses(Set biVentesproduitses) {
		this.biVentesproduitses = biVentesproduitses;
	}

}