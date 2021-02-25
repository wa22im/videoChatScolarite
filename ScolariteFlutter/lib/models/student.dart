class Student {
  String _nom;
  String _prenom;

  Student({nom, prenom, parentalContact, classe, cantine})
      : _nom = nom,
        _prenom = prenom;

  get nom => _nom;
  get prenom => _prenom;

  set nom(nom) => _nom = nom;
  set prenom(prenom) => _prenom = prenom;

  Map<String, dynamic> toJson() {
    return {
      'nom': nom,
      'prenom': prenom,
    };
  }

  /* fromJson(Map<String, dynamic> mp) {
    
    nom(mp["nom"]);
    prenom(mp["prenom"]);
   
  }*/
}
