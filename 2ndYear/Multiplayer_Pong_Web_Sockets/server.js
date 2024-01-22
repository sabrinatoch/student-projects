const apiServer = require('./api.js')
const http = require("http");
const httpServer = http.createServer(apiServer);
const io = require("socket.io");
const socketServer = io (httpServer);
const port = 3000;
const sockets = require('./socket.js')
sockets.listen(socketServer);

httpServer.listen(port, () => {
  console.log(`Listening on port ${port}`);
}); // listen
