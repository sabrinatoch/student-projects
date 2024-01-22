let readyPlayerCount = 0;
exports.listen = (io) => {
  const pongNamespace = io.of("/pong");
  pongNamespace.on("connection", (socket) => {
    let room;
    console.log(`A user connected with id ${socket.id}`);
    socket.on("ready", () => {
      room = "room" + Math.floor(readyPlayerCount / 2);
      socket.join(room);
      console.log(`Player with id ${socket.id} ready ${room}`);
      readyPlayerCount++;
      if (readyPlayerCount % 2 === 0) {
        pongNamespace.in(room).emit("startGame", socket.id);
      } // if 2 players are ready
    }); // on ready
    socket.on("paddleMove", (paddleData) => {
      socket.to(room).emit("paddleMove", paddleData);
    }); // on paddleMove
    socket.on("ballMove", (ballData) => {
      socket.to(room).emit("ballMove", ballData);
    }); // on ballMove
    socket.on("disconnext", (reason) => {
      console.log(`Client ${socket.id} disconnected: ${reason}`);
      socket.leave(room);
    }); // on disconnect
  }); // on connection
}; // listen
