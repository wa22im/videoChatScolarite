import 'dart:io';
import 'dart:ui';
import 'package:FaceNetAuthentication/models/student.dart';
import 'package:FaceNetAuthentication/pages/home.dart';
import 'package:FaceNetAuthentication/services/facenet.service.dart';
import 'package:FaceNetAuthentication/services/scolarite.service.dart';
import 'package:firebase_ml_vision/firebase_ml_vision.dart';
import 'package:flutter/services.dart';

import 'package:image/image.dart' as imglib;
import 'package:flutter/material.dart';

class SignUpFromGallery extends StatefulWidget {
  const SignUpFromGallery({File image}) : _image = image;

  @override
  _SignUpFromGalleryState createState() => _SignUpFromGalleryState();
  final File _image;
}

class _SignUpFromGalleryState extends State<SignUpFromGallery> {
  TextEditingController nom = TextEditingController();
  TextEditingController prenom = TextEditingController();
  TextEditingController contact = TextEditingController();

  String faceId;
  bool ready = false;
  bool sent = false;
  FaceNetService _faceNetService = FaceNetService();
  ScolariteService _scolariteService = ScolariteService();
  bool pictureConfirmed = true;

  bool cantine = false;
  int classe;
  List data = List();
  fetchAllClasses() async {
    if (data.length < 1) data = await _scolariteService.getAllClasses();
  }

  /*Future<String> sendPicture() async {
    faceId =
        await _scolariteService.postFacePrint(_faceNetService.predictedData);
    setState(() {
      sent = true;
    });
    return faceId;
  }*/
  init() async {
    fetchAllClasses();
    FirebaseVisionImage image = FirebaseVisionImage.fromFile(widget._image);
    final faceDetector = FirebaseVision.instance.faceDetector(
        FaceDetectorOptions(
            mode: FaceDetectorMode.fast, enableLandmarks: true));
    List<Face> faces = await faceDetector.processImage(image);
    if (faces.length > 0) {
      imglib.Image imag = imglib.decodeImage(await widget._image.readAsBytes());
      _faceNetService.setCurrentPrediction(imag, faces[0]);
    }
  }

  @override
  initState() {
    super.initState();
    init();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Sign Up'),
      ),
      body: Center(
          child: widget._image == null
              ? Text('No image selected.')
              : Column(
                  children: [buildForm(context)],
                )),
    );
  }

  Widget buildForm(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(10),
      child: Column(
        children: [
          _buildCustomTextField(
            hintText: 'nom',
            color: Colors.blue,
            widgetCustomController: nom,
          ),
          SizedBox(
            height: 10,
          ),
          _buildCustomTextField(
            hintText: 'prenom',
            color: Colors.blue,
            widgetCustomController: prenom,
          ),
          SizedBox(
            height: 10,
          ),
          _buildCustomTextField(
            hintText: 'Contact parental',
            color: Colors.blue,
            widgetCustomController: contact,
          ),
          Row(
            children: [
              Text("Cantine"),
              SizedBox(
                width: 10,
              ),
              Checkbox(
                  value: cantine,
                  onChanged: (v) {
                    setState(() {
                      cantine = v;
                    });
                  }),
            ],
          ),
          SizedBox(
            height: 10,
          ),
          DropdownButton(
            hint: Text("Selectionner classe"),
            items: data.map((item) {
              return new DropdownMenuItem(
                child: new Text(item['codeClasse'].toString()),
                value: item['id'],
              );
            }).toList(),
            onChanged: (newVal) {
              setState(() {
                classe = newVal;
              });
            },
            value: classe,
          ),
          SizedBox(
            height: 10,
          ),
          RaisedButton(
            child: Text('confirmer'),
            onPressed: () {
              _scolariteService
                  .postStudent(
                      _faceNetService.predictedData,
                      Student(
                          nom: nom.text,
                          prenom: prenom.text,
                          parentalContact: contact.text,
                          cantine: cantine,
                          classe: classe))
                  .then((value) {
                if (value == 200) {
                  _faceNetService.setPredictedData(null);
                  Navigator.push(
                    context,
                    MaterialPageRoute(
                      builder: (ctx) => MyHomePage(),
                    ),
                  );
                }
              });
            },
          )
        ],
      ),
    );
  }

  Widget _buildCustomTextField(
      {hintText,
      inputType,
      isPassword = false,
      widgetCustomController,
      labeled = true,
      color}) {
    return Container(
      height: 50.0,
      child: TextField(
        controller: widgetCustomController,
        keyboardType: inputType != null ? inputType : TextInputType.name,
        cursorColor: color,
        decoration: InputDecoration(
          contentPadding: const EdgeInsets.only(
            left: 20.0,
          ),
          enabledBorder: OutlineInputBorder(
            borderSide: BorderSide(
              color: color,
              width: 2.0,
            ),
          ),
          focusedBorder: OutlineInputBorder(
            gapPadding: 1.0,
            borderSide: BorderSide(
              color: color,
              width: 2.0,
            ),
          ),
          hintText: hintText,
          hintStyle: TextStyle(
            fontFamily: 'Montserrat',
            color: Colors.grey,
          ),
          labelText: labeled ? hintText : null,
          labelStyle: const TextStyle(
            fontFamily: 'Montserrat',
            color: Colors.grey,
          ),
          suffixIcon: isPassword
              ? Icon(
                  Icons.remove_red_eye,
                  color: color,
                )
              : null,
        ),
      ),
    );
  }
}
