import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import 'package:all_project/Project9_Expense_Tracker/expense.dart';

class DatabaseHelper {
  static final DatabaseHelper instance = DatabaseHelper._init();
  static Database? _database;

  DatabaseHelper._init();

  Future<Database> get database async {
    if (_database != null) return _database!;

    _database = await _initDB('expenses.db');
    return _database!;
  }

  Future<Database> _initDB(String filePath) async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, filePath);

    return await openDatabase(path, version: 1, onCreate: _createDB);
  }

  Future _createDB(Database db, int version) async {
    const idType = 'INTEGER PRIMARY KEY AUTOINCREMENT';
    const textType = 'TEXT NOT NULL';
    const doubleType = 'REAL NOT NULL';

    await db.execute('''
    CREATE TABLE expenses (
      id $idType,
      title $textType,
      amount $doubleType,
      date $textType
    )
    ''');
  }

  Future<Expense> create(Expense expense) async {
    final db = await instance.database;

    final id = await db.insert('expenses', expense.toMap());
    return expense.copy(id: id);
  }

  Future<List<Expense>> readAllExpenses() async {
    final db = await instance.database;

    final orderBy = 'date ASC';
    final result = await db.query('expenses', orderBy: orderBy);

    return result.map((json) => Expense.fromMap(json)).toList();
  }

  Future close() async {
    final db = await instance.database;

    db.close();
  }
}
