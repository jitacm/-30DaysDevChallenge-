const express = require('express');
const { MongoClient } = require('mongodb');
const dotenv = require('dotenv');
const bodyParser = require('body-parser');
const { v4: uuidv4 } = require('uuid');
const cors = require('cors');
const bcrypt = require('bcrypt');

dotenv.config();

// Connection URL
const url = process.env.MONGO_URL || 'mongodb://localhost:27017';
const client = new MongoClient(url, { useNewUrlParser: true, useUnifiedTopology: true });

// Database Name
const dbName = 'ExamPrep';
const app = express();

const port = process.env.PORT || '3000';
app.use(bodyParser.json());
app.use(cors());

client.connect(err => {
    if (err) {
        console.error('Failed to connect to the database', err);
        process.exit(1);
    } else {
        console.log('Connected successfully to database');
    }
});

const db = client.db(dbName);
const userCollection = db.collection('User Details');

app.get('/users/:username', async (req, res) => {
    const { username } = req.params;

    try {
        const user = await userCollection.findOne({ username: username });
        if (user) {
            res.json(user);
        } else {
            res.status(404).json({ message: 'User not found' });
        }
    } catch (error) {
        res.status(500).json({ message: 'Error fetching user data', error });
    }
});

app.get('/users', async (req, res) => {
    try {
        const users = await userCollection.find({}).toArray();
        res.json(users);
    } catch (error) {
        res.status(500).json({ message: 'Error fetching users data', error });
    }
});

app.post('/signup', async (req, res) => {
    const { username, email, password } = req.body;

    try {
        const existingUser = await userCollection.findOne({ email: email });
        if (existingUser) {
            return res.status(400).json({ success: false, message: 'Email is already registered' });
        }

        const hashedPassword = await bcrypt.hash(password, 10);
        const newUser = {
            id: uuidv4(),
            username,
            email,
            password: hashedPassword,
            maths_easy_score: '-',
            maths_medium_score: '-',
            maths_hard_score: '-',
            science_easy_score: '-',
            science_medium_score: '-',
            science_hard_score: '-'
        };

        await userCollection.insertOne(newUser);
        res.status(201).json({ success: true });
    } catch (error) {
        res.status(500).json({ success: false, message: 'Error signing up', error });
    }
});

app.put('/update-password', async (req, res) => {
    const { username, password } = req.body;

    if (!username || !password) {
        return res.status(400).json({ success: false, message: 'Username and password are required' });
    }

    try {
        const hashedPassword = await bcrypt.hash(password, 10);
        const updateResult = await userCollection.updateOne(
            { username },
            { $set: { password: hashedPassword } }
        );

        if (updateResult.matchedCount === 0) {
            return res.status(404).json({ success: false, message: 'User not found' });
        }

        res.json({ success: true });
    } catch (error) {
        res.status(500).json({ success: false, message: 'Error updating password', error });
    }
});

app.put('/update-score', async (req, res) => {
    const { username, subject, level, score } = req.body;

    if (!username || !subject || !level || typeof score !== 'number') {
        return res.status(400).json({ success: false, message: 'Invalid input' });
    }

    const scoreKey = `${subject.split('-')[0]}_${level}_score`;

    try {
        const user = await userCollection.findOne({ username });
        if (!user) {
            return res.status(404).json({ success: false, message: 'User not found' });
        }

        const currentBestScore = user[scoreKey] === '-' ? -Infinity : parseInt(user[scoreKey], 10);

        if (score > currentBestScore) {
            await userCollection.updateOne(
                { username },
                { $set: { [scoreKey]: score } }
            );
            return res.json({ success: true, message: 'Score updated' });
        } else {
            return res.json({ success: true, message: 'New score is not higher than the current best score' });
        }
    } catch (error) {
        res.status(500).json({ success: false, message: 'Error updating score', error });
    }
});


// Verify user credentials during login
// Backend route for login
app.post("/login", async (req, res) => {
    const { email, password } = req.body;
    const db = client.db(dbName);
    const collection = db.collection("User Details");
  
    try {
      const user = await collection.findOne({ email: email });
      if (!user) {
        return res.status(404).json({ message: "Email not found" });
      }
      // Compare hashed password
      const passwordMatch = await bcrypt.compare(password, user.password);
      if (passwordMatch) {
        res.status(200).json({ username: user.username });
      } else {
        res.status(401).json({ message: "Incorrect password" });
      }
    } catch (error) {
      res.status(500).json({ message: "Error logging in", error });
    }
  });
  


app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});
