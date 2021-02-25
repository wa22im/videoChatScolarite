// A screen that allows users to take a picture using a given camera.
import 'dart:async';
import 'package:FaceNetAuthentication/video/src/video_chat.dart';
import 'package:intl/intl.dart';

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

class SignIn extends StatefulWidget {
  final CameraDescription cameraDescription;

  const SignIn({
    Key key,
    @required this.cameraDescription,
  }) : super(key: key);

  @override
  SignInState createState() => SignInState();
}

class SignInState extends State<SignIn> {
  /// Service injection
  CameraService _cameraService = CameraService();
  MLVisionService _mlVisionService = MLVisionService();
  FaceNetService _faceNetService = FaceNetService();
  static final String ipAdress = "192.168.1.21:8000";

  Future _initializeControllerFuture;

  bool cameraInitializated = false;
  bool _detectingFaces = false;
  bool pictureTaked = false;

  // switchs when the user press the camera
  bool _saving = false;

  String imagePath;
  Size imageSize;
  Face faceDetected;

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

          if (faces != null) {
            if (faces.length > 0) {
              // preprocessing the image
              setState(() {
                faceDetected = faces[0];
              });

              if (_saving) {
                _saving = false;
                _faceNetService.setCurrentPrediction(image, faceDetected);
              }
            } else {
              setState(() {
                faceDetected = null;
              });
            }
          }

          _detectingFaces = false;
        } catch (e) {
          print(e);
          _detectingFaces = false;
        }
      }
    });
  }

  /// handles the button pressed event
  Future<void> onShot() async {
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

      await Future.delayed(Duration(milliseconds: 500));
      await _cameraService.cameraController.stopImageStream();
      await Future.delayed(Duration(milliseconds: 200));
      await _cameraService.takePicture(imagePath);

      setState(() {
        pictureTaked = true;
      });

      return true;
    }
  }

  ScolariteService _scolariteService = ScolariteService();

  @override
  Widget build(BuildContext context) {
    final width = MediaQuery.of(context).size.width;
    return SafeArea(
      child: Scaffold(
        body: Center(
          child: FutureBuilder<void>(
            future: _initializeControllerFuture,
            builder: (context, snapshot) {
              if (snapshot.connectionState == ConnectionState.done) {
                if (pictureTaked) {
                  return FutureBuilder(
                      future: _scolariteService
                          .predict(_faceNetService.predictedData),
                      builder: (ctx, snap) {
                        if (snap.connectionState ==
                            ConnectionState.done) if (snap.hasData) {
                          return Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Image.network(
                                "http://" +
                                    ipAdress +
                                    "/image/" +
                                    snap.data["picsName"],
                                errorBuilder: (ctx, obj, c) {
                                  return Image.asset(
                                      "FaceNetAuthentication-Logo.png");
                                },
                              ),
                              Container(
                                child: Text(
                                  'Nom = ' + snap.data["nom"],
                                  style: TextStyle(fontSize: 20),
                                ),
                              ),
                              Container(
                                child: Text(
                                  'prenom = ' + snap.data["prenom"],
                                  style: TextStyle(fontSize: 20),
                                ),
                              ),
                              RaisedButton(
                                  child: Text('retour'),
                                  onPressed: () {
                                    Navigator.pushAndRemoveUntil(
                                        context,
                                        MaterialPageRoute(
                                            builder: (BuildContext context) =>
                                                MyHomePage()),
                                        (Route<dynamic> route) => false);
                                  }),
                              RaisedButton(
                                  child: Text('Call List'),
                                  onPressed: () {
                                    Navigator.push(
                                        context,
                                        MaterialPageRoute(
                                            builder: (BuildContext context) =>
                                                VideoChat(
                                                    displayName: snap
                                                            .data["nom"] +
                                                        snap.data["prenom"])));
                                  }),
                            ],
                          );
                        } else {
                          return Column(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Container(
                                  child: Text(
                                'User not found 😞',
                                style: TextStyle(fontSize: 20),
                              )),
                              RaisedButton(
                                  child: Text('retour'),
                                  onPressed: () {
                                    Navigator.pushAndRemoveUntil(
                                        context,
                                        MaterialPageRoute(
                                            builder: (BuildContext context) =>
                                                MyHomePage()),
                                        (Route<dynamic> route) => false);
                                  }),
                            ],
                          );
                        }
                        else
                          return CircularProgressIndicator();
                      });
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
                                )
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
        ),
        floatingActionButtonLocation: FloatingActionButtonLocation.centerFloat,
        floatingActionButton: pictureTaked
            ? Container()
            : FloatingActionButton.extended(
                label: Text('Sign in'),
                icon: Icon(Icons.camera_alt),
                // Provide an onPressed callback.
                onPressed: onShot,
              ),
      ),
    );
  }
}
