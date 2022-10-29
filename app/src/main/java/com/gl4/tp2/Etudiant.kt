package com.gl4.tp2

class Etudiant(nom:String,prenom:String, gender:String, presences : List<Presence>) {
    private val nom = nom;
    private val prenom = prenom;
    private val gender = gender;
    public val presences = presences;

    public fun getNom() : String {
        return this.nom;
    }

    public fun getPrenom() : String {
        return this.prenom;
    }

    public fun getGender() : String {
        return this.gender;
    }
}

class Presence(matiere:String,presence:Boolean){
    public val matiere = matiere;
    public val presence = presence;
}