// import 'package:all_project/Project10_Contact_App/Project10_Contact_App.dart';
// import 'package:path/path.dart';
// import 'package:sqflite/sqflite.dart';
//
// class DatabaseHelper {
//   static final DatabaseHelper instance = DatabaseHelper._init();
//   static Database? _database;
//
//   DatabaseHelper._init();
//
//   Future<Database> get database async {
//     if (_database != null) return _database!;
//
//     _database = await _initDB('contacts.db');
//     return _database!;
//   }
//
//   Future<Database> _initDB(String filePath) async {
//     final dbPath = await getDatabasesPath();
//     final path = join(dbPath, filePath);
//
//     return await openDatabase(path, version: 1, onCreate: _createDB);
//   }
//
//   Future _createDB(Database db, int version) async {
//     const idType = 'INTEGER PRIMARY KEY AUTOINCREMENT';
//     const textType = 'TEXT NOT NULL';
//
//     await db.execute('''
//     CREATE TABLE contacts (
//       id $idType,
//       name $textType,
//       phone $textType,
//       email $textType
//     )
//     ''');
//   }
//
//   Future<Contact> create(Contact contact) async {
//     final db = await instance.database;
//
//     final id = await db.insert('contacts', contact.toMap());
//     return contact.copy(id: id);
//   }
//
//   Future<List<Contact>> readAllContacts() async {
//     final db = await instance.database;
//
//     final orderBy = 'name ASC';
//     final result = await db.query('contacts', orderBy: orderBy);
//
//     return result.map((json) => Contact.fromMap(json)).toList();
//   }
//
//   Future close() async {
//     final db = await instance.database;
//
//     db.close();
//   }
// }
