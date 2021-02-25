import 'dart:convert';
import 'dart:io';
import 'package:FaceNetAuthentication/config/conf.dart';
import 'package:FaceNetAuthentication/models/student.dart';
import 'package:http/http.dart' as http;

class ScolariteService {
  static final ScolariteService _scolariteService =
      ScolariteService._internal();

  static final String ipAdress = serverIP;
  factory ScolariteService() {
    return _scolariteService;
  }

  ScolariteService._internal();

  Future<Map<String, dynamic>> postStudent(
      List<dynamic> facePrint, Student student) async {
    Map<String, dynamic> map;
    int i = -1;
    map = Map.fromIterable(facePrint,
        key: (e) {
          i++;
          return "e" + i.toString();
        },
        value: (e) => e);

    map.addEntries(student.toJson().entries);

    map.forEach((key, value) {
      print(key + "value" + value.toString());
    });

    // Map<String, dynamic> ll = {'predictedData': map};
    var uri = Uri.http(ipAdress, 'student');

    var response =
        await http.post(uri, body: jsonEncode(map), headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    });
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');

    return jsonDecode(response.body);
  }

  Future<List> getAllClasses() async {
    var uri = Uri.http(ipAdress, "classe");

    var response = await http.get(uri, headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    });
    if (response.statusCode != 200) return null;
    var rep = jsonDecode(response.body);
    print("reeeeep" + rep.toString());
    return response.body.isEmpty ? null : rep['classeEntities'];
  }

/*  Future<int> postStudent(Student student) async {
    var uri = Uri.http('62.73.5.23:8080', 'student');

    var response = await http.post(uri,
        body: jsonEncode(student.toJson()),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        });
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');

    return response.statusCode;
  }
*/
  Future<Map<String, dynamic>> predict(List predictedData) async {
    /* for (int i = 0; i < predictedData.length; i++)
      print(predictedData[i].toString());*/
    Map<String, dynamic> mp = {'predictTable': predictedData};
    var uri = Uri.http(ipAdress, 'predict/getid');
    Map<String, String> headers = {
      'Content-Type': 'application/json;charset=UTF-8',
      'Charset': 'utf-8'
    };
    var response = await http.post(uri, body: jsonEncode(mp), headers: headers);
    var rslt;
    response.body.isNotEmpty ? rslt = jsonDecode(response.body) : rslt = null;
    print(rslt);

    return rslt;
  }

  Future<int> postClasse(Map<String, String> classe) async {
    /* for (int i = 0; i < predictedData.length; i++)
      print(predictedData[i].toString());*/
    var uri = Uri.http(ipAdress, 'classe');
    Map<String, String> headers = {
      'Content-Type': 'application/json;charset=UTF-8',
      'Charset': 'utf-8'
    };
    var response =
        await http.post(uri, body: jsonEncode(classe), headers: headers);
    var rslt;
    response.body.isNotEmpty ? rslt = jsonDecode(response.body) : rslt = null;
    print(rslt);

    return response.statusCode;
  }

  Future<int> postEmploi(Map<String, dynamic> classe) async {
    /* for (int i = 0; i < predictedData.length; i++)
      print(predictedData[i].toString());*/
    var uri = Uri.http(ipAdress, 'emploi');
    Map<String, String> headers = {
      'Content-Type': 'application/json;charset=UTF-8',
      'Charset': 'utf-8'
    };
    var response =
        await http.post(uri, body: jsonEncode(classe), headers: headers);
    var rslt;
    response.body.isNotEmpty ? rslt = jsonDecode(response.body) : rslt = null;
    print(rslt);

    return response.statusCode;
  }

  Future<int> upload(File file) async {
    print('uploading ...');

    var request = http.MultipartRequest(
      'POST',
      Uri.http(ipAdress, 'image'),
    );
    request.files.add(await http.MultipartFile.fromPath('file', file.path));
    var res = await request.send();
    var response = await http.Response.fromStream(res);
    print(response.body);
    return response.statusCode;
  }
}
