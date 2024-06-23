import 'package:flutter/material.dart';
import 'package:flutter_form_builder/flutter_form_builder.dart';
import 'package:provider/provider.dart';
import 'auth_provider.dart';
import 'signup_page.dart';

class SignInPage extends StatelessWidget {
  final _formKey = GlobalKey<FormBuilderState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Sign In'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: FormBuilder(
          key: _formKey,
          child: Column(
            children: <Widget>[
              FormBuilderTextField(
                name: 'email',
                decoration: InputDecoration(labelText: 'Email'),
                validator: FormBuilderValidators.compose([
                  FormBuilderValidators.required(context),
                  FormBuilderValidators.email(context),
                ]),
              ),
              FormBuilderTextField(
                name: 'password',
                decoration: InputDecoration(labelText: 'Password'),
                obscureText: true,
                validator: FormBuilderValidators.compose([
                  FormBuilderValidators.required(context),
                  FormBuilderValidators.minLength(context, 6),
                ]),
              ),
              SizedBox(height: 20),
              ElevatedButton(
                onPressed: () {
                  if (_formKey.currentState?.saveAndValidate() ?? false) {
                    final email = _formKey.currentState?.fields['email']?.value;
                    final password = _formKey.currentState?.fields['password']?.value;
                    Provider.of<AuthProvider>(context, listen: false).signIn(email, password);
                  }
                },
                child: Text('Sign In'),
              ),
              TextButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => SignUpPage()),
                  );
                },
                child: Text('Don\'t have an account? Sign Up'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
