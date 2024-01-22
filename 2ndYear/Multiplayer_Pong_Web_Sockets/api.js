const express = require('express');
const path = require('path');
const api = express();

api.use(express.static('public'));

api.get('/', (req, res) => {
    res.setHeader("Content-Type", "text/html");
    res.sendFile(__dirname + '/index.html');
})

module.exports = api;

// api.listen(port, () => {
//     console.log(`Listening on port ${port}`);
// })