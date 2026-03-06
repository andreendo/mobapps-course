const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const { delay } = require('./utils');

const app = express();
const port = 3001;

app.use(cors());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

// ------------------------------- //
// available users
const users = [
    {
        username: "user1",
        password: "123"
    },
    {
        username: "user2",
        password: "1234"
    }
];

// ------------------------------- //
// ------------------------------- //
// routes

app.get('/status', async (req, res) => {
    console.log('GET status');

    const shownUsers = users.map(u => ({
        ...u,
        password: "*******"
    }));

    res.json({ availableUsers: shownUsers });
});

app.post('/login', async (req, res) => {
    const { username, password } = req.body;
    console.log("POST login: " + JSON.stringify(req.body));

    await delay(2000);

    if (!username || !password) {
        return res.json({ message: 'Username and password are required.' });
    }

    const user = users.find(u => u.username === username);
    if (user === undefined) {
        return res.json({ message: 'unexisting username' });
    }

    if (user.password !== password) {
        return res.json({ message: 'wrong password' });
    }

    res.json({ message: 'success' });
});

// ------------------------------- //
app.listen(port, () => {
    console.log('started server...');
});