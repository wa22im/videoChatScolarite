class FaceId {
  String _id;
  List<double> _faceId;

  FaceId(this._faceId);

  get faceId => _faceId.toList();
  get id => _id;

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'faceId': faceId,
    };
  }
}
