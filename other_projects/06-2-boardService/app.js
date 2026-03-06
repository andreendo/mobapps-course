const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 3000;

// initial data
let boards = initBoards();

app.use(cors());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get('/reset', async (req, res) => {
    console.log('reset');
    boards = initBoards();
    res.json({ message: 'boards reset' });
});

app.get('/board', async (req, res) => {
    console.log('get boards');
    res.json(boards);
});

app.get('/board/:iid', async (req, res) => {
    const iid = parseInt(req.params.iid);
    console.log('get board ' + iid);
    for (b of boards) {
        if (b.id === iid) {
            res.json(b);
            return;
        }
    }

    res.status(404).send('Board not found');
});

app.get('/board/:iid/messages', async (req, res) => {
    const iid = parseInt(req.params.iid);
    console.log(`get board ${iid} messages`);
    bb = findBoard(boards, iid);
    if (bb != null) {
        res.json(bb.messages);
        return;
    }

    res.status(404).send('Board not found');
});

app.post('/board/:iid/messages', async (req, res) => {
    const iid = parseInt(req.params.iid);
    console.log(`post message to board ${iid}`);
    bb = findBoard(boards, iid);
    if (bb != null) {
        const msg = req.body;
        msg.timestamp = (new Date()).toString();
        bb.messages.push(msg);
        res.json({ message: 'Message added to board ' + bb.name });
        return;
    }

    res.status(404).send('Board not found');
});

app.delete('/board/:iid', async (req, res) => {
    const iid = parseInt(req.params.iid);
    console.log(`delete board ${iid}`);
    bb = findBoard(boards, iid);
    if (bb != null) {
        boards = boards.filter(b => b.id != bb.id);
        res.json({ message: 'Board removed' });
        return;
    }

    res.status(404).send('Board not found');
});

app.post('/board', async (req, res) => {
    const board = req.body;
    console.log("post board: " + board);
    board.id = boards.length + 1;
    board.messages = []
    boards.push(board);
    res.json(board);
});

app.listen(port, () => {
    console.log('started server...');
});

function findBoard(boards, iid) {
    for (b of boards) {
        if (b.id === iid) {
            return b;
        }
    }
    return null;
}

function initBoards () {
    return [
        {
            id: 1,
            name: 'board_test1',
            messages: [
                {
                    from: "user 1",
                    to: "user 2",
                    text: "Hello you!",
                    timestamp: (new Date()).toString()
                },
                {
                    from: "user 2",
                    to: "user 1",
                    text: "Hi :-)",
                    timestamp: (new Date()).toString()
                }
            ]
        },
        {
            id: 2,
            name: 'board_test2',
            messages: [
                {
                    from: "user 1",
                    to: "anyone",
                    text: "YESSSSS",
                    timestamp: (new Date()).toString()
                }
            ]
        }
    ];
}