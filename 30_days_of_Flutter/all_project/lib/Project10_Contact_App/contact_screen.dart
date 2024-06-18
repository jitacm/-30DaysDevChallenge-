// import 'package:all_project/Project10_Contact_App/contact.dart';
// import 'package:all_project/Project10_Contact_App/contact_provider.dart';
// import 'package:flutter/material.dart';
// import 'package:provider/provider.dart';
//
//
// class ContactScreen extends StatefulWidget {
//   @override
//   _ContactScreenState createState() => _ContactScreenState();
// }
//
// class _ContactScreenState extends State<ContactScreen> {
//   final _nameController = TextEditingController();
//   final _phoneController = TextEditingController();
//   final _emailController = TextEditingController();
//
//   @override
//   void initState() {
//     super.initState();
//     Provider.of<ContactProvider>(context, listen: false).fetchContacts();
//   }
//
//   void _addContact() {
//     final name = _nameController.text;
//     final phone = _phoneController.text;
//     final email = _emailController.text;
//
//     if (name.isEmpty || phone.isEmpty || email.isEmpty) {
//       return;
//     }
//
//     final newContact = Contact(
//       id: 0,  // ID will be set by the database upon insertion
//       name: name,
//       phone: phone,
//       email: email,
//     );
//
//     Provider.of<ContactProvider>(context, listen: false).addContact(newContact);
//
//     Navigator.of(context).pop();
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     final contacts = Provider.of<ContactProvider>(context).contacts;
//
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('Contact List'),
//       ),
//       body: ListView.builder(
//         itemCount: contacts.length,
//         itemBuilder: (ctx, index) {
//           return ListTile(
//             title: Text(contacts[index].name),
//             subtitle: Text('Phone: ${contacts[index].phone}\nEmail: ${contacts[index].email}'),
//           );
//         },
//       ),
//       floatingActionButton: FloatingActionButton(
//         child: Icon(Icons.add),
//         onPressed: () {
//           showModalBottomSheet(
//             context: context,
//             builder: (_) {
//               return Padding(
//                 padding: const EdgeInsets.all(16.0),
//                 child: Column(
//                   crossAxisAlignment: CrossAxisAlignment.end,
//                   children: <Widget>[
//                     TextField(
//                       controller: _nameController,
//                       decoration: InputDecoration(labelText: 'Name'),
//                     ),
//                     TextField(
//                       controller: _phoneController,
//                       decoration: InputDecoration(labelText: 'Phone'),
//                       keyboardType: TextInputType.phone,
//                     ),
//                     TextField(
//                       controller: _emailController,
//                       decoration: InputDecoration(labelText: 'Email'),
//                       keyboardType: TextInputType.emailAddress,
//                     ),
//                     ElevatedButton(
//                       child: Text('Add Contact'),
//                       onPressed: _addContact,
//                     ),
//                   ],
//                 ),
//               );
//             },
//           );
//         },
//       ),
//     );
//   }
// }
