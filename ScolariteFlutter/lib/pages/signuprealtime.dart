import 'dart:async';
import 'dart:io';
import 'package:FaceNetAuthentication/models/student.dart';
import 'package:FaceNetAuthentication/pages/home.dart';
import 'package:FaceNetAuthentication/pages/widgets/FacePainter.dart';
import 'package:FaceNetAuthentication/services/camera.service.dart';
import 'package:FaceNetAuthentication/services/facenet.service.dart';
import 'package:FaceNetAuthentication/services/ml_vision_service.dart';
import 'package:FaceNetAuthentication/services/scolarite.service.dart';
import 'package:camera/camera.dart';
import 'package:firebase_ml_vision/firebase_ml_vision.dart';
import 'package:flutter/material.dart';
import 'package:path/path.dart' show join;
import 'package:path_provider/path_provider.dart';
import 'package:path/path.dart' as path;

class PredictDataModel {
  String user;
  List predictedData;

  PredictDataModel({@required this.user, @required this.predictedData});
}

class SignUp extends StatefulWidget {
  final CameraDescription cameraDescription;

  const SignUp({Key key, @required this.cameraDescription}) : super(key: key);

  @override
  SignUpState createState() => SignUpState();
}

class SignUpState extends State<SignUp> {
  String imagePath;
  Face faceDetected;
  Size imageSize;

  bool _detectingFaces = false;
  bool pictureTaked = false;

  TextEditingController nom = TextEditingController();
  TextEditingController prenom = TextEditingController();

  Future _initializeControllerFuture;
  bool cameraInitializated = false;

  String faceid;

  // switchs when the user press the camera
  bool _saving = false;

  // service injection
  MLVisionService _mlVisionService = MLVisionService();
  CameraService _cameraService = CameraService();
  FaceNetService _faceNetService = FaceNetService();
  ScolariteService _scolariteService = ScolariteService();

  List data = List();

  @override
  void initState() {
    super.initState();

    /// starts the camera & start framing faces
    _start();
  }

  @override
  void dispose() {
    // Dispose of the controller when the widget is disposed.
    _cameraService.dispose();
    super.dispose();
  }

  /// starts the camera & start framing faces
  _start() async {
    _initializeControllerFuture =
        _cameraService.startService(widget.cameraDescription);
    await _initializeControllerFuture;

    setState(() {
      cameraInitializated = true;
    });

    _frameFaces();
  }

  /// handles the button pressed event
  Future<void> onShot() async {
    print('onShot performed');

    if (faceDetected == null) {
      showDialog(
          context: context,
          child: AlertDialog(
            content: Text('No face detected!'),
          ));

      return false;
    } else {
      imagePath =
          join((await getTemporaryDirectory()).path, '${DateTime.now()}.png');

      _saving = true;

      await _cameraService.cameraController.stopImageStream();
      await Future.delayed(Duration(milliseconds: 200));
      await _cameraService.takePicture(imagePath);

      setState(() {
        pictureTaked = true;
      });

      return true;
    }
  }

  /// draws rectangles when detects faces
  _frameFaces() {
    imageSize = _cameraService.getImageSize();

    _cameraService.cameraController.startImageStream((image) async {
      if (_cameraService.cameraController != null) {
        // if its currently busy, avoids overprocessing
        if (_detectingFaces) return;

        _detectingFaces = true;

        try {
          List<Face> faces = await _mlVisionService.getFacesFromImage(image);

          if (faces.length > 0) {
            setState(() {
              faceDetected = faces[0];
            });

            if (_saving) {
              _faceNetService.setCurrentPrediction(image, faceDetected);

              setState(() {
                _saving = false;
              });
            }
          } else {
            setState(() {
              faceDetected = null;
            });
          }

          _detectingFaces = false;
        } catch (e) {
          print(e);
          _detectingFaces = false;
        }
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    return SafeArea(
      child: Scaffold(
          body: FutureBuilder<void>(
            future: _initializeControllerFuture,
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.done) {
                if (pictureTaked) {
                  return buildForm(context);
                } else {
                  return Transform.scale(
                    scale: 1.0,
                    child: AspectRatio(
                      aspectRatio: MediaQuery.of(context).size.aspectRatio,
                      child: OverflowBox(
                        alignment: Alignment.center,
                        child: FittedBox(
                          fit: BoxFit.fitHeight,
                          child: Container(
                            width: width,
                            height: width /
                                _cameraService
                                    .cameraController.value.aspectRatio,
                            child: Stack(
                              fit: StackFit.expand,
                              children: <Widget>[
                                CameraPreview(_cameraService.cameraController),
                                CustomPaint(
                                  painter: FacePainter(
                                      face: faceDetected, imageSize: imageSize),
                                ),
                              ],
                            ),
                          ),
                        ),
                      ),
                    ),
                  );
                }
              } else {
                return Center(child: CircularProgressIndicator());
              }
            },
          ),
          floatingActionButtonLocation:
              FloatingActionButtonLocation.centerFloat,
          floatingActionButton: !pictureTaked
              ? FloatingActionButton.extended(
                  label: Text('Sign up'),
                  icon: Icon(Icons.camera_alt),
                  // Provide an onPressed callback.
                  onPressed: onShot,
                )
              : Container()),
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
          RaisedButton(
            child: Text('confirmer'),
            onPressed: () {
              _scolariteService
                  .postStudent(
                      _faceNetService.predictedData,
                      Student(
                        nom: nom.text,
                        prenom: prenom.text,
                      ))
                  .then((value) async {
                if (value["idstudent"] > 0) {
                  File picture = new File(imagePath);
                  print('Original path: ${picture.path}');
                  String dir = path.dirname(picture.path);
                  String newPath = path.join(dir, '${value["idstudent"]}.jpg');
                  print('NewPath: $newPath');
                  File f = await File(picture.path).copy(newPath);

                  _scolariteService.upload(f).then((value) {
                    if (value == 200) {
                      this._faceNetService.setPredictedData(null);

                      Navigator.push(context,
                          MaterialPageRoute(builder: (ctx) => MyHomePage()));
                    }
                  });
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
