/*import 'dart:convert';

//import 'package:FaceNetAuthentication/pages/db/database.dart';
import 'package:FaceNetAuthentication/pages/profile.dart';
import 'package:FaceNetAuthentication/services/facenet.service.dart';
import 'package:flutter/material.dart';
import '../home.dart';
import 'package:http/http.dart' as http;

class PredictDataModel {
  String user;
  List predictedData;

  PredictDataModel({@required this.user, @required this.predictedData});
}

class User {
  String user;

  User({
    @required this.user,
  });
}

class AuthActionButton extends StatefulWidget {
  AuthActionButton(this._initializeControllerFuture,
      {@required this.onPressed, @required this.isLogin});
  final Future _initializeControllerFuture;
  final Function onPressed;
  final bool isLogin;
  @override
  _AuthActionButtonState createState() => _AuthActionButtonState();
}

class _AuthActionButtonState extends State<AuthActionButton> {
  /// service injection
  final FaceNetService _faceNetService = FaceNetService();
  // final DataBaseService _dataBaseService = DataBaseService();

  final TextEditingController _userTextEditingController =
      TextEditingController(text: '');
  final TextEditingController _passwordTextEditingController =
      TextEditingController(text: '');

  User predictedUser;

  Future _signUp(context) async {
    /// gets predicted data from facenet service (user face detected)
    List predictedData = _faceNetService.predictedData;
    String user = _userTextEditingController.text;
    PredictDataModel userModel =
        new PredictDataModel(user: user, predictedData: predictedData);
    Map<String, dynamic> ll = {'user': user, 'predictedData': predictedData};
    var uri = Uri.http('62.73.5.23:8082', 'predict');

    var response =
        await http.post(uri, body: jsonEncode(ll), headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    });
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');

    /// creates a new user in the 'database'
    //await _dataBaseService.saveData(user, password, predictedData);
    // http connection with backend here !!

    /// resets the face stored in the face net sevice
    this._faceNetService.setPredictedData(null);
    Navigator.push(context,
        MaterialPageRoute(builder: (BuildContext context) => MyHomePage()));
  }

  Future _signIn(context) async {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (BuildContext context) => Profile(
                  username: this.predictedUser.user,
                )));
  }

  Future<String> _predictUser() async {
    String userAndPass = await _faceNetService.predict();
    print(userAndPass);
    return userAndPass ?? null;
  }

  @override
  Widget build(BuildContext context) {
    return FloatingActionButton.extended(
      label: widget.isLogin ? Text('Sign in') : Text('Sign up'),
      icon: Icon(Icons.camera_alt),
      // Provide an onPressed callback.
      onPressed: () async {
        try {
          // Ensure that the camera is initialized.
          await widget._initializeControllerFuture;
          // onShot event (takes the image and predict output)
          bool faceDetected = await widget.onPressed();

          if (faceDetected) {
            Scaffold.of(context)
                .showBottomSheet((context) => signSheet(context));
          }
        } catch (e) {
          // If an error occurs, log the error to the console.
          print(e);
        }
      },
    );
  }

  signSheet(context) {
    return Container(
      padding: EdgeInsets.all(20),
      height: 300,
      child: Column(
        children: [
          widget.isLogin
              ? FutureBuilder(
                  future: _predictUser(),
                  builder: (ctx, snap) {
                    if (snap.connectionState == ConnectionState.done) {
                      if (snap.hasData)
                        predictedUser = User(user: snap.data);
                      else
                        predictedUser = null;
                      return widget.isLogin && predictedUser != null
                          ? Column(
                              children: [
                                Container(
                                  child: Text(
                                    'Welcome back, ' + snap.data + '! 😄',
                                    style: TextStyle(fontSize: 20),
                                  ),
                                ),
                                RaisedButton(
                                  child: Text('Login'),
                                  onPressed: () async {
                                    _signIn(context);
                                  },
                                )
                              ],
                            )
                          : widget.isLogin
                              ? Column(
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
                                                  builder:
                                                      (BuildContext context) =>
                                                          MyHomePage()),
                                              (Route<dynamic> route) => false);
                                        }),
                                  ],
                                )
                              : Container();
                    } else
                      return CircularProgressIndicator();
                  })
              : Container(),
          !widget.isLogin
              ? Column(
                  children: [
                    TextField(
                      controller: _userTextEditingController,
                      decoration: InputDecoration(labelText: "Your Name"),
                    ),
                    RaisedButton(
                      child: Text('Sign Up!'),
                      onPressed: () async {
                        await _signUp(context);
                      },
                    ),
                  ],
                )
              : Container(),
        ],
      ),
    );
  }

  @override
  void dispose() {
    super.dispose();
  }
}
*/
